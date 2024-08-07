'use server'

import React from 'react';
import './style.scss';
import Image from 'next/image';

export default async function ThankYou() {

    return <div className='thankYouContainer'>
        <div className='summary d-flex flex-row justify-content-center'>
            <Image src={'/corner.png'}
                alt=''
                width={0}
                height={0}
                sizes='100vw'
                loading='eager'
                className='me-3'
                style={{
                    width: '150px',
                    height: 'auto'
                }} />
            <span className='summary-title fs-1'>
                Thank You
            </span>
            <Image src={'/corner.png'}
                alt=''
                width={0}
                height={0}
                sizes='100vw'
                loading='eager'
                className='ms-3'
                style={{
                    width: '150px',
                    height: 'auto',
                    transform: 'rotateY(-180deg)'
                }} />
        </div>
    </div>;
}