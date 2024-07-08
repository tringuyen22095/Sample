'use client';

import './style.scss';
import React, { Fragment, useEffect, useRef } from 'react';
import { GALLERY_TEMPLATE, GALLERY_TYPE } from 'constant';
import classNames from 'classnames';

function renderGrid(childRefs, previewRef, template: GALLERY_TYPE[]) {
    return template.map((v, i) => {
        return <Fragment key={`imgItem-${i}`}>
            <figure style={{
                    gridColumn: v.gridColumn,
                    gridRow: v.gridRow
                }}>
                <a href='#img_preview' onClick={() => previewRef.current.src = v.src}>
                    <img ref={childRefs[i]}
                        src={v.src}
                        style={{
                            objectFit: v.objectFit,
                            objectPosition: v.objectPosition
                        }}
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

    const childRefs = useRef<(React.RefObject<HTMLImageElement> | null)[]>(template.map(() => React.createRef()));
    const previewRef = useRef<HTMLImageElement>(null);

    useEffect(() => {
        const handleLoad = (index) => {
            childRefs.current[index].current.style.opacity = '1';
        };

        childRefs.current.forEach((child, index) => {
            const img = new Image();
            img.src = template[index].src;
            img.onload = () => handleLoad(index);
        });
        return () => {
            childRefs.current.forEach((child) => {
                if (child.current)
                    child.current.style.opacity = '0';
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
                {renderGrid(childRefs.current, previewRef, template)}
            </section>
            <a href='#_preview_out' className="lightbox trans" id='img_preview'>
                <img ref={previewRef} />
            </a>
        </div>
    </Fragment>);
}

export default Gallery;