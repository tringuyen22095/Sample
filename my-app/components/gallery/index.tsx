'use client';

import './style.scss';
import React, { Fragment, useEffect, useRef, useState } from 'react';
import { GALLERY_TEMPLATE } from 'constant';
import classNames from 'classnames';

export default function Gallery() {
    const template = GALLERY_TEMPLATE;
    const length = template.length;

    const [html, setHtml] = useState<any>(null);
    
    const parentRefs = useRef<(React.RefObject<HTMLImageElement> | null)[]>(Array(length).fill(null));
    const childRefs = useRef<(React.RefObject<HTMLImageElement> | null)[]>(Array(length).fill(null));
    const previewRef = useRef<HTMLImageElement>(null);

    useEffect(() => {
        setHtml(renderGrid());

        parentRefs.current.forEach((parentRef, index) => {
            const child = childRefs.current[index];
            if (child) {
                child.current.addEventListener('load', function () {
                    if (parentRef) {
                        parentRef.current.style.opacity = '1';
                    }
                });
            }
        });
        return () => {
            childRefs.current.forEach((child) => {
                if (child) {
                    child.current.removeEventListener('load', () => {});
                }
            });
        };
    }, []);

    function renderGrid() {
        return GALLERY_TEMPLATE.map((v, i) => {
            return (<Fragment key={`imgItem-${i}`}>
                <figure  ref={parentRefs.current[i]}
                    style={{
                        gridColumn: v.gridColumn,
                        gridRow: v.gridRow
                    }}>
                    <div ref={childRefs.current[i]}
                        style={{
                            backgroundImage: `url('${v.src}')`
                        }}
                        className={classNames({
                            vertical: v.imgType === 'VERTICAL',
                            horizontal: v.imgType === 'HORIZONTAL'
                        })}></div>
                </figure>
            </Fragment>);
        });
    }

    function renderHref() {
        return (<Fragment>
                <a href='#_preview_out' className="lightbox trans" id='img_preview'>
                    <img ref={previewRef} />
                </a>
            </Fragment>);
    }

    return (<Fragment>
        <span id='gallery' />
        <div className='galleryContainer'>
            <div className='summary'>
                <div className='summary-title'>
                    Gallery
                </div>
            </div>
            <section className="grid">
                {/* {html} */}
                <figure ref={parentRefs.current[0]}
                    style={{
                        gridColumn: null,
                        gridRow: null
                    }}>
                    <div ref={childRefs.current[0]}
                        style={{
                            backgroundImage: `url('/gallery/GIN00021.jpg')`
                        }}
                        className={classNames({
                            vertical: true
                        })}></div>
                </figure>
            </section>
            {/* {renderHref()} */}
            
            <a href='#_preview_out' className="lightbox trans" id='img_preview'>
                <img ref={previewRef} />
            </a>
        </div>
    </Fragment>);
}