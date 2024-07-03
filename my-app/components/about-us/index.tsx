'use server'

import './style.scss';
import React, { Fragment } from 'react';
import { ABOUT_US_TEMPLATE } from 'constant';

export default async function AboutUs() {

    function render() {
        const {
            description,
            menSrc,
            menSummary,
            womanSrc,
            womanSummary
        } = ABOUT_US_TEMPLATE;
        return (
            <div className='aboutUsContainer'>
                <div className='summary'>
                    <div className='summary-title'>
                        About Us
                    </div>
                    <div className='summary-content'>
                        { description }
                    </div>
                </div>
                <div className='couple'>
                    <div className='personal-information'>
                        <div className='image' style={{backgroundImage: `url('${menSrc}')`}}></div>
                        <div className='description'>
                            <span>{ menSummary.fullName }</span>
                            <span>{ `${menSummary.dob} (${menSummary.zodiac})` }</span>
                            <span>{ menSummary.slogan }</span>
                        </div>
                    </div>
                    <div className='personal-information reverse'>
                    <div className='image' style={{backgroundImage: `url('${womanSrc}')`}}></div>
                        <div className='description'>
                            <span>{ womanSummary.fullName }</span>
                            <span>{ `${womanSummary.dob} (${womanSummary.zodiac})` }</span>
                            <span>{ womanSummary.slogan }</span>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    return (<Fragment>
        <span id='aboutUs'/>
        {render()}
    </Fragment>);
}