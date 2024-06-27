'use client'

import classNames from 'classnames';
import './style.scss';
import React, { Fragment, useEffect, useState } from 'react';


type ItemTemplate = {

};

export default function OurStory() {

    const render = (lst: ItemTemplate[]) => {
        return (<></>);
    }

    return (<Fragment>
        <div className='slide-container'>
            <div className='timeline'>
                <div className='swiper-container'>
                    <div className='swiper-wrapper'>
                        <div className='swiper-slide' data-year='2011'>
                            <div className='swiper-slide-content'><span className='timeline-year'>2011</span>
                                <h4 className='timeline-title'>Our nice super title</h4>
                                <p className='timeline-text'>Lorem ipsum dolor site amet, consectetur adipscing elit, sed do eisumod tempor incididut ut labore et dolore magna aliqua. Ut enim ad mimim venjam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            </div>
                        </div>
                        <div className='swiper-slide' data-year='2012'>
                            <div className='swiper-slide-content'><span className='timeline-year'>2012</span>
                                <h4 className='timeline-title'>Our nice super title</h4>
                                <p className='timeline-text'>Lorem ipsum dolor site amet, consectetur adipscing elit, sed do eisumod tempor incididut ut labore et dolore magna aliqua. Ut enim ad mimim venjam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            </div>
                        </div>
                    </div>
                    <div className='swiper-button-prev'></div>
                    <div className='swiper-button-next'></div>
                    <div className='swiper-pagination'></div>
                </div>
            </div>
        </div>
    </Fragment >);
}