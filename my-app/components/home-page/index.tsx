'use client'

import moment from 'moment';
import './style.scss';
import { Envs } from '../../common/env';
import React, { Fragment, useEffect, useState } from 'react';
import { formatTimeLeft } from 'utils';
import { DATETIME_FORMAT } from 'constant';
import { Slider } from 'widgets';

const weddingDate = moment(Envs.WEDDING_DATE, 'dd-MM-yyyy HH:mm:ss');

export default function HomePage() {
    const [display, setDisplay] = useState('');

    function calculateTimeLeft() {
        const difference = weddingDate.diff(moment.now(), 's', true);
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
                <div className='main-notice'>
                    <div className='time-left'>
                        {display}
                    </div>
                    <div className='name'>
                        We're getting married!
                    </div>
                    <div className='detail'>
                        {weddingDate.format(DATETIME_FORMAT)}
                    </div>
                </div>
            </div>
        </Slider>
    </Fragment>);
}