'use server'

import React from 'react';
import './style.scss';

export default async function ThankYou() {

    return <div className='thankYouContainer'>
        <div className='summary d-flex flex-row justify-content-center'>
            <span className='summary-title fs-1'>
                Thank You
            </span>
        </div>
    </div>;
}