'use client'

import './style.scss';
import React from 'react';

export default function Header() {

    return (<>
        <div className='header'>
            <span>
                <a href="#page-one">Home</a>
            </span>
            <span>
                <a href="#page-two">Our Story</a>
            </span>
            <span>
                <a href="#page-two">Gallery</a>
            </span>
            <span>
                <a href="#">Wedding Event</a>
            </span>
        </div>
    </>);
}