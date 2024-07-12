'use server'

import classNames from 'classnames';
import './style.scss';
import React, { Fragment } from 'react';
import { OUR_STORY_TEMPLATE } from 'constant';
import moment from 'moment';
import Image from 'next/image';

export default async function OurStory() {

    const render = () => {
        return OUR_STORY_TEMPLATE.map((v, i) => {
            const isEven = (i % 2) === 0;
            return (<Fragment key={`ourStory-${i}`}>
                <div className={classNames('story', {
                    isEven: isEven,
                    isOdd: !isEven
                })}>
                    <div className='bg-image'>
                        <Image src={v.imgSrc}
                            height={0}
                            width={0}
                            loading='eager'
                            sizes='100vw'
                            alt={`Image ${i}`}
                            style={{
                                objectFit: 'cover'
                            }}
                            className={classNames('img', v.type)} />
                    </div>
                    <div className='separate'>
                        <div className='line'></div>
                        <div className='pot'></div>
                    </div>
                    <div className='description'>
                        <span>{moment(v.date).format('DD MMM yyyy')}</span>
                        <span dangerouslySetInnerHTML={{ __html: v.description }} />
                    </div>
                </div>
            </Fragment>);
        });
    }

    return (<Fragment>
        <span id='ourStory'/>
        <div className='ourStoryContainer'>
            <div className='summary'>
                <div className='summary-title'>
                    Our Story...
                </div>
            </div>
            <div className='contentSection'>
                {render()}
            </div>
        </div>
    </Fragment>);
}