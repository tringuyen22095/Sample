'use server'

import React from 'react';
import './style.scss';

export default async function ThankYou() {

    return <div className='thankYouContainer'>
        <div className='summary'>
            <div className='summary-title fs-1 text-center'>
                Thank You
            </div>
        </div>
    </div>;
}