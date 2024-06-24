'use client'

import './style.scss';
import React, { Fragment, useEffect, useState } from 'react';

export default function AboutUs() {

    return (<Fragment>
        <div className="aboutUsContainer" id='aboutUs'>
            <div className='summary'>
                <div className='summary-title'>
                    About Us
                </div>
                <div className='summary-content'>
                    From this day forward, you'll never walk alone.
                </div>
            </div>
            <div className='couple'>
                <div className='personal-information'>
                    <div className='image image-boy'></div>
                    <div className='description'>
                        <span>Nguyễn Thành Trí</span>
                        <span>Quận 5, TP. Hồ Chí Minh</span>
                    </div>
                </div>
                <div className='personal-information reverse'>
                    <div className='image image-girl'></div>
                    <div className='description'>
                        <span>Chu Thị Hải Anh</span>
                        <span>Vũng Tàu, TP. Bà Rịa Vũng Tàu</span>
                    </div>
                </div>
            </div>
        </div>
    </Fragment>);
}