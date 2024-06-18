'use client'

import React, { Fragment, useEffect, useRef, useState } from 'react';
import './style.scss';
import classNames from 'classnames';

type Props = {
    children: React.ReactNode
}

const SliderGenerate = (props: Props) => {
    const [slide, setSlide] = useState(0);
    const [resetCountdown, setResetCountdown] = useState<boolean | null>(null);
    const refs = useRef<(React.RefObject<HTMLInputElement> | null)[]>([]);

    // execute as constructor
    useEffect(() => {
        Array.from({ length: 3 })
            .forEach((_, i) => {
                refs.current[i] = refs.current[i] || React.createRef();
            });
        setResetCountdown(true);
    }, []);

    useEffect(() => {
            const interval = setInterval(() => {
                const nextIndex = (slide + 1) % 3;
                radioClicked(nextIndex);
            }, 5000);
            return () => clearInterval(interval);
        
    }, [ slide]);

    const radioClicked = (index: number) => {
        if (refs.current[index]?.current) {
            setSlide(index);
            refs.current[index].current.checked = true;
            refs.current[index].current.focus();
        }
    }

    const slideRender = () => {
        return Array.from({ length: 3 }).map((v, i) => {
            return (<Fragment key={'trigger' + i}>
                <input type='radio' id={'trigger' + i}
                    name='slider' ref={refs.current[i]}
                    defaultChecked={i === 0} autoFocus={i === 0}
                    onClick={() => radioClicked(i)} />
                <label htmlFor={'trigger' + i}>
                    <span className='sr-only'>Slide {i} of 5.</span>
                </label>
                <div className={classNames('slide', `bg${i}`)}></div>
            </Fragment>);
        });
    }

    return (
        <Fragment>
            <div className={classNames('background', 'slider')}>
                {slideRender()}
            </div>
            <div className={classNames('content', 'slider')}>
                {props.children}
            </div>
        </Fragment>
    )
};

export default SliderGenerate;