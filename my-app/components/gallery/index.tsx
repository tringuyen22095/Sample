'use client';

import './style.scss';
import React, { Fragment, useEffect, useRef } from 'react';
import { GALLERY_TEMPLATE, GALLERY_TYPE } from 'constant';
import classNames from 'classnames';
import Image from 'next/image';

function renderGrid(childRefs, previewRef, template: GALLERY_TYPE[]) {
    return template.map((v, i) => {
        return <Fragment key={`imgItem-${i}`}>
            <figure style={{
                    gridColumn: v.gridColumn,
                    gridRow: v.gridRow,
                    justifyContent: v.justifyContent,
                    alignItems: v.alignItems
                }}>
                <a href='#img_preview' onClick={() => previewRef.current.src = v.src}>
                    <Image ref={childRefs[i]}
                        src={v.src}
                        style={{
                            objectFit: v.objectFit,
                            objectPosition: v.objectPosition,
                            height: v.height,
                            width: v.width
                        }}
                        height={0}
                        width={0}
                        sizes='100vw'
                        alt={`Image ${i}`}
                        className={classNames({
                            vertical: v.imgType === 'VERTICAL',
                            horizontal: v.imgType === 'HORIZONTAL'
                        })}/>
                </a>
            </figure>
        </Fragment>;
    });
}

const Gallery = () => {
    const template = GALLERY_TEMPLATE.filter(v => v.display === true);

    const refs = useRef<(React.RefObject<HTMLImageElement> | null)[]>(template.map(() => React.createRef()));
    const previewRef = useRef<HTMLImageElement>(null);

    useEffect(() => {
        const handleLoad = (index) => {
            if (refs.current[index].current) {
                refs.current[index].current.style.opacity = '1';
            }
        };

        refs.current.forEach((ref, index) => {
            if (ref.current) {
                ref.current.addEventListener('load', () => handleLoad(index));
                if (ref.current.complete) {
                    handleLoad(index);
                }
            }
        });
        return () => {
            refs.current.forEach((ref) => {
                if (ref.current) {
                    ref.current.removeEventListener('load', () => handleLoad(ref));
                }
            });
        };
    }, []);

    return (<Fragment>
        <span id='gallery' />
        <div className='galleryContainer'>
            <div className='summary'>
                <div className='summary-title'>
                    Gallery
                </div>
            </div>
            <section className="grid">
                { renderGrid(refs.current, previewRef, template) }
            </section>
            <a href='#_preview_out' className="lightbox trans" id='img_preview'>
                <img ref={previewRef} />
            </a>
            <div className='test'></div>
        </div>
    </Fragment>);
}

export default Gallery;