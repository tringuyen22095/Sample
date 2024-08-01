'use client'

import React, { Fragment, useEffect, useRef, useState } from 'react';
import './style.scss';
import classNames from 'classnames';
import { HOMEPAGE_IMAGE_SRC_TEMPLATE } from 'constant';
import Image from 'next/image';

type Props = {
    children: React.ReactNode
}

const SliderGenerate = ({ children }: Props) => {
    const template = HOMEPAGE_IMAGE_SRC_TEMPLATE;
    const maxSlide = template.length;

    const [html, setHtml] = useState<any>();

    const [activeSlide, setActiveSlide] = useState(0);
    const activeSlideRef = useRef(activeSlide);

    const [slidingBlocked, setSlidingBlockedRef] = useState(false);
    const slidingBlockedRef = useRef(slidingBlocked);

    const refs = useRef<(React.RefObject<HTMLInputElement> | null)[]>([]);
    const leftRef = useRef<HTMLDivElement | null>(null);
    const rightRef = useRef<HTMLDivElement | null>(null);

    const rolateIconTimeout: number = 1300;
    const autoSlideTimeout: number = 15000;

    // execute as constructor
    useEffect(() => {
        refs.current = Array.from({ length: maxSlide }, () => React.createRef());
        setHtml(slideRender());

        if (leftRef.current) leftRef.current.addEventListener('click', handleClick);
        if (rightRef.current) rightRef.current.addEventListener('click', handleClick);
        
        return () => {
            if (leftRef.current) leftRef.current.removeEventListener('click', handleClick);
            if (rightRef.current) rightRef.current.removeEventListener('click', handleClick);
        };
    }, []);
    
    useEffect(() => {
        activeSlideRef.current = activeSlide;
        const interval = setInterval(() => rightRef.current.click(), autoSlideTimeout);
        return () => clearInterval(interval);
    }, [activeSlide]);

    useEffect(() => {
        slidingBlockedRef.current = slidingBlocked;
    }, [slidingBlocked]);

    function handleClick() {
        if (slidingBlockedRef.current) return;
        setSlidingBlockedRef(true);

        const _isRight = this == rightRef.current;
        const _control = _isRight ? rightRef.current : leftRef.current;
        const _currentIndex = activeSlideRef;
        const _prevIndex = _currentIndex.current === 0 ? maxSlide - 1 : _currentIndex.current - 1;
        const _incommingIndex = Math.abs(_currentIndex.current + (_isRight ? 1 : (-maxSlide + 1))) % maxSlide;
        const _incommingPrevIndex = _incommingIndex === 0 ? maxSlide - 1 : _incommingIndex - 1;

        _control.classList.add('a--rotation');
        refs.current[_currentIndex.current].current.classList.remove('s--active', 's--active-prev');
        refs.current[_prevIndex].current.classList.remove('s--prev');
        refs.current[_incommingIndex].current.classList.add('s--active');
        if (!_isRight) refs.current[_incommingIndex].current.classList.add('s--active-prev');

        refs.current[_incommingPrevIndex].current.classList.add('s--prev');
        setActiveSlide(_incommingIndex);
        setTimeout(function () {
            _control.classList.remove('a--rotation');
            setSlidingBlockedRef(false);
        }, rolateIconTimeout * 0.75);
    };

    const slideRender = () => {
        return template.map(({src}, i) => {
            return (<Fragment key={'slide' + i}>
                <div className={classNames('slide', {
                    's--prev': i === maxSlide - 1,
                    's--active': i === 0
                })} ref={refs.current[i]}>
                    <Image src={src}
                        height={0}
                        width={0}
                        sizes='100vw'
                        priority
                        alt={`Image ${i}`}
                        className={classNames('slide__inner', `img_slide${i+1}`)} />
                </div>
            </Fragment>);
        });
    }

    return (
        <Fragment>
            <div className='slider'>
                <div className='slider__slides'>
                    {html}
                </div>
                <div className='slider__control' ref={leftRef}>
                    <div className='slider__control-line'></div>
                    <div className='slider__control-line'></div>
                </div>
                <div className='slider__control slider__control--right m--right' ref={rightRef}>
                    <div className='slider__control-line'></div>
                    <div className='slider__control-line'></div>
                </div>
            </div>
            <div className='content'>
                {children}
            </div>
        </Fragment>
    )
};

export default SliderGenerate;