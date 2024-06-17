'use client'

import moment from 'moment';
import './style.scss';
import { Envs } from '../../common/env';
import React, { useEffect, useState } from 'react';
import { formatTimeLeft } from 'utils';
import { DATETIME_FORMAT } from 'constant';

const weddingDate = moment(Envs.WEDDING_DATE, 'dd-MM-yyyy HH:mm:ss');

export default function PaegTwo() {

    return (<>
        <main>
            <div className='main-notice'>
                page 2
            </div>
        </main>
    </>);
}