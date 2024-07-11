'use client';

import './style.scss';
import React, { Fragment, useEffect, useRef, useState } from 'react';
import { GALLERY_TEMPLATE, GALLERY_TYPE, ANIMATION_KEYS } from 'constant';
import classNames from 'classnames';
import Image from 'next/image';

function getRandomAnimationKeys(): string {
    const index = (Math.random() * Number.MAX_SAFE_INTEGER) % ANIMATION_KEYS.length;
    return ANIMATION_KEYS[Math.floor(index)];
}

function renderGrid(figureRefs: React.RefObject<any>[], childRefs: React.RefObject<HTMLImageElement>[], setSrc: (string) => void, template: GALLERY_TYPE[]) {
    return template.map(({
        src,
        imgType,
        gridColumn,
        gridRow,
        objectFit,
        objectPosition,
        width,
        height,
        justifyContent,
        alignItems,
        clipPath
    }, i) => {
        return <Fragment key={`imgItem-${i}`}>
            <figure ref={figureRefs[i]}
                style={{
                    gridColumn,
                    gridRow,
                    justifyContent,
                    alignItems,
                    clipPath
                }}>
                <a href='#img_preview' onClick={() => setSrc(src)}>
                    <Image ref={childRefs[i]}
                        src={src}
                        style={{
                            objectFit,
                            objectPosition,
                            height,
                            width,
                            clipPath
                        }}
                        height={0}
                        width={0}
                        sizes='100vw'
                        alt={`Image ${i}`}
                        className={classNames({
                            [imgType]: true
                        })} />
                </a>
            </figure>
        </Fragment>;
    });
}

const Gallery = () => {
    const template = GALLERY_TEMPLATE.filter(v => v.display === true);

    const figureRefs = useRef<(React.RefObject<any> | null)[]>(template.map(() => React.createRef()));
    const refs = useRef<(React.RefObject<HTMLImageElement> | null)[]>(template.map(() => React.createRef()));
    const href = useRef(null);
    const [src, setSrc] = useState<string>('/blank.png');

    useEffect(() => {
        const handleLoad = (index) => {
            if (refs.current[index].current) {
                refs.current[index].current.style.opacity = '1';
                const animation = `${getRandomAnimationKeys()} 2s ease`;
                figureRefs.current[index].current.style.animation = animation;
                figureRefs.current[index].current.style.webkitAnimation = animation; // Safari and Chrome
                figureRefs.current[index].current.style.mozAnimation = animation; // Firefox
                figureRefs.current[index].current.style.oAnimation = animation; // Opera
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
                { renderGrid(figureRefs.current, refs.current, setSrc, template) }
            </section>
            <a href='#_preview_out'
                ref={href}
                className="lightbox trans"
                id='img_preview'>
                <Image src={src}
                        height={0}
                        width={0}
                        sizes='100vw'
                        style={{
                            width: 'auto',
                            height: '100%'
                        }}
                        alt={`Image preview`} />
            </a>
            <div className='test'></div>
        </div>
    </Fragment>);
}

export default Gallery;