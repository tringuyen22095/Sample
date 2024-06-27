'use client'

import './style.scss';
import React, { Fragment } from 'react';
import { HEADER_NAVIGATION } from 'constant';

export default function Header() {

    function headerRender() {
        return HEADER_NAVIGATION.map((v, i) => {
            return <Fragment key={`nav-${i}`}>
                <span>
                    <a href={v.redirect}>{v.text}</a>
                </span>
            </Fragment>;
        });
    }

    return (<div className='headerContainer'>
            {headerRender()}
        </div>);
}