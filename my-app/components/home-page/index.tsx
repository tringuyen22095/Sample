'use client'

import React, { Fragment, useEffect, useState } from 'react';
import { DATETIME_FORMAT } from 'constant';
import { formatTimeLeft } from 'utils';
import { Slider } from 'widgets';
import moment from 'moment';
import { Envs } from 'env';
import './style.scss';

const weddingDate = moment(Envs.WEDDING_DATE, 'DD-MM-yyyy HH:mm:ss');

export default function HomePage() {
    const [display, setDisplay] = useState('');

    function calculateTimeLeft() {
        const difference = weddingDate.diff(moment(), 's', true);
        return Math.max(0, Math.floor(difference));
    }

    useEffect(() => {
        const timeLeft = calculateTimeLeft();
        setDisplay(formatTimeLeft(timeLeft));

        const timer = setInterval(() => {
            const timeLeft = calculateTimeLeft();
            setDisplay(formatTimeLeft(timeLeft));
        }, 1000);
        return () => clearInterval(timer);
    }, []);

    return (<Fragment>
        <span id='homePage'/>
        <Slider>
            <div className='homePageContainer'>
                <div className='main-notice d-flex flex-column align-items-center'>
                    <div className='time-left text-wrap w-75'>
                        {display}
                    </div>
                    <div className='name text-wrap'>
                        We&apos;re getting married!
                    </div>
                    <div className='detail'>
                        {weddingDate.format(DATETIME_FORMAT)}
                    </div>
                </div>
            </div>
        </Slider>
    </Fragment>);
}