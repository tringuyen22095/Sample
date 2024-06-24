'use client'

import './style.scss';
import React from 'react';

export default function Header() {

    return (<>
        <div className='headerContainer'>
            <span>
                <a href="#homePage">Home</a>
            </span>
            <span>
                <a href="#aboutUs">About Us</a>
            </span>
            <span>
                <a href="#ourStory">Our Story</a>
            </span>
            <span>
                <a href="#gallery">Gallery</a>
            </span>
            <span>
                <a href="#guestbook">Guestbook</a>
            </span>
        </div>
    </>);
}