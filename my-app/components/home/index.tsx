'use client'

import moment from 'moment';
import './style.scss';
import { Envs } from '../../common/env';
import React, { useEffect, useState } from 'react';
import { formatTimeLeft } from 'utils';
import { DATETIME_FORMAT } from 'constant';
import { Slider } from 'widgets';

const weddingDate = moment(Envs.WEDDING_DATE, 'dd-MM-yyyy HH:mm:ss');

export default function PaegOne() {
    const [timeLeft, setTimeLeft] = useState(calculateTimeLeft());
    const [display, setDisplay] = useState(formatTimeLeft(timeLeft));

    useEffect(() => {
        const timer = setInterval(() => {
            setTimeLeft(calculateTimeLeft());
        }, 1000);
        return () => clearInterval(timer);
    }, []);

    useEffect(() => {
        setDisplay(formatTimeLeft(timeLeft));
    }, [timeLeft]);

    function calculateTimeLeft() {
        const difference = weddingDate.diff(moment.now(), 's', true);
        return Math.max(0, Math.floor(difference));
    }

    return (<>
        <Slider>
            <div className='page-one' id='page-one'>
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
    </>);
}