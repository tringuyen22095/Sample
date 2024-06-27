'use client'

import classNames from 'classnames';
import './style.scss';
import React, { Fragment, useEffect, useState } from 'react';
import { OUR_STORY_TEMPLATE } from 'constant';
import moment from 'moment';

export default function OurStory() {

    const render = () => {
        return OUR_STORY_TEMPLATE.map((v, i) => {
            const isEven = (i % 2) === 0;
            return (<Fragment key={`ourStory-${i}`}>
                <div className={classNames('story', {
                    isEven: isEven,
                    isOdd: !isEven
                })}>
                    <div className='bg-image'>
                        <div className={classNames('img', {
                            landscape: v.type === 'landscape'
                        })}
                            style={{
                                backgroundImage: `url(${v.imgSrc})`,
                                backgroundPosition: v.backgroundPositionOverride
                            }} />
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

    return (<div className='ourStoryContainer'>
        <div className='summary'>
            <div className='summary-title'>
                Our Story...
            </div>
        </div>
        <div className='contentSection'>
            {render()}
        </div>
    </div>);
}