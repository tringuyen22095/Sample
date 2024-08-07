'use client';

import { GALLERY_TEMPLATE, GALLERY_TYPE, ANIMATION_KEYS } from 'constant';
import React, { Fragment, useEffect, useRef } from 'react';
import Image from 'next/image';
import './style.scss';

function getRandomAnimationKeys(): string {
    const index = (Math.random() * Number.MAX_SAFE_INTEGER) % (ANIMATION_KEYS.length - 1);
    return ANIMATION_KEYS[Math.floor(index)];
}

function renderGrid(figureRefs: React.RefObject<any>[], childRefs: React.RefObject<HTMLImageElement>[], template: GALLERY_TYPE[]) {
    return template.map(({
        src,
        imgType,
        objectFit,
        objectPosition
    }, i) => {
        return <Fragment key={`imgItem-${i}`}>
            <figure ref={figureRefs[i]}
                className={`img-${i+1}`}>
                    <Image ref={childRefs[i]}
                        src={src}
                        style={{
                            objectFit,
                            objectPosition
                        }}
                        height={0}
                        width={0}
                        priority
                        sizes='100vw'
                        alt={`Image ${i+1}`}
                        className={imgType} />
            </figure>
        </Fragment>;
    });
}

function renderPreview(template: GALLERY_TYPE[]) {
    return template.map(({
        src
    }, i) => {
        return <Fragment key={`imgPreview-${i}`}>
            <a href='#_preview_out'
                className='lightbox trans'
                id={`preview_${i}`}>
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
        </Fragment>;
    });
}

const Gallery = () => {
    const template = GALLERY_TEMPLATE.filter(v => v.display === true);

    const figureRefs = useRef<(React.RefObject<any> | null)[]>(template.map(() => React.createRef()));
    const refs = useRef<(React.RefObject<HTMLImageElement> | null)[]>(template.map(() => React.createRef()));

    useEffect(() => {
        const currents = refs.current;
        const handleLoad = (index) => {
            if (refs.current[index].current) {
                refs.current[index].current.style.opacity = '1';
                const animation = `${getRandomAnimationKeys()} 4s ease`;
                figureRefs.current[index].current.style.animation = animation;
                figureRefs.current[index].current.style.webkitAnimation = animation; // Safari and Chrome
                figureRefs.current[index].current.style.mozAnimation = animation; // Firefox
                figureRefs.current[index].current.style.oAnimation = animation; // Opera
            }
        };

        currents.forEach((ref, index) => {
            if (ref.current) {
                ref.current.addEventListener('load', () => handleLoad(index));
                if (ref.current.complete) {
                    handleLoad(index);
                }
            }
        });
 
        return () => {
            currents.forEach((ref) => {
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
            <section className='grid'>
                { renderGrid(figureRefs.current, refs.current, template) }
            </section>
            <div className='test'></div>
        </div>
    </Fragment>);
}

export default Gallery;