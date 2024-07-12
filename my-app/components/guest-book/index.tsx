'use client'

import classNames from 'classnames';
import './style.scss';
import React, { Fragment, useEffect, useState } from 'react';

export default function GuestBook() {

    return (<Fragment>
        <span id='guestGook'/>
        <div className='guestBookContainer'>
            <div className='summary'>
                <div className='summary-title'>
                    Guest Book
                </div>
            </div>
        </div>
    </Fragment >);
}