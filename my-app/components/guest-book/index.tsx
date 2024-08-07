'use client'

import { ErrorModel, HTTP_HEADERS, TIMELINE_SIZE, VN_DATETIME_FORMAT, wishesSuggest } from 'constant';
import { initGuestBookFormValues, guestBookSchema, FormSchema } from 'models';
import LightbulbOutlinedIcon from '@mui/icons-material/LightbulbOutlined';
import React, { Fragment, useEffect, useRef, useState } from 'react';
import EmojiEmotionsIcon from '@mui/icons-material/EmojiEmotions';
import { useAppDispatch, useAppSelector } from 'hooks';
import { zodResolver } from '@hookform/resolvers/zod';
import Autocomplete from '@mui/material/Autocomplete';
import SendIcon from '@mui/icons-material/Send';
import { useForm } from 'react-hook-form';
import { setData } from 'dataReducer';
import Picker from '@emoji-mart/react';
import data from '@emoji-mart/data';
import classNames from 'classnames';
import Image from 'next/image';
import moment from 'moment';
import './style.scss';
import {
    Button,
    IconButton,
    Popper,
    Paper,
    FormControl,
    InputLabel,
    OutlinedInput,
    TextField
} from '@mui/material';

export default function GuestBook() {
    const { register, handleSubmit, reset, setError, watch, setFocus, getValues, trigger, setValue, formState: { errors, isDirty, isSubmitted, isValid } } = useForm<FormSchema>({
        defaultValues: initGuestBookFormValues,
        resolver: zodResolver(guestBookSchema)
    });
    const [formSquareHeight, setFormSquareHeight] = useState<string | null>(null);
    const [mapSquareWidth, setMapSquareWidth] = useState<string | null>(null);
    const [mapContainerHeight, setMapContainerHeight] = useState<string | null>(null);
    const [autoCompleteWidth, setAutoCompleteWidth] = useState<string | null>(null);
    const [autoCompleteValue, setAutoCompleteValue] = useState(null);

    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const lstData = useAppSelector(state => state.data);
    const dispatch = useAppDispatch();

    const textareaRef = useRef<HTMLTextAreaElement>(null);
    const formSquare = useRef(null);
    const mapSquare = useRef(null);
    const mapContainer = useRef(null);

    useEffect(() => {
        if (formSquare.current) {
            setFormSquareHeight(`${formSquare.current.offsetHeight}px`);
        }
        if (textareaRef.current) {
            setAutoCompleteWidth(`${textareaRef.current.offsetWidth}px`);
        }
        if (mapSquare.current) {
            setMapSquareWidth(`${mapSquare.current.offsetWidth}px`);
        }
        if (mapContainer.current) {
            setMapContainerHeight(`${mapContainer.current.offsetHeight}px`);
        }
    }, [lstData]);

    // Emoji
    const [emojiShow, setEmojiShow] = useState<boolean>(false);
    const handleEmojiShow = (event?: React.MouseEvent<HTMLButtonElement>) => {
        setEmojiShow(!emojiShow);
        setIdeaShow(false);
    };
    const onEmojiSelect = (emoji: any) => {
        const textarea = textareaRef.current;
        if (textarea) {
            const contentValue = getValues('content');
            const cursorPos = textarea.selectionStart;
            const textBefore = contentValue.slice(0, cursorPos);
            const textAfter = contentValue.slice(cursorPos);
            setValue('content', `${textBefore}${emoji.native}${textAfter}`);
            // Set cursor position after emoji
            setTimeout(() => {
                textarea.selectionStart = textarea.selectionEnd = cursorPos + emoji.native.length;
            }, 0);
        }
    };

    // Idea
    const [ideaShow, setIdeaShow] = useState<boolean>(false);
    const handleIdeaShow = (event?: React.MouseEvent<HTMLElement>) => {
        setIdeaShow(!ideaShow);
        setEmojiShow(false);
    };
    const onIdeaSelect = (event: any, idea: { label: string }) => {
        setValue('content', idea.label);
        setAutoCompleteValue(null);
        setIdeaShow(false);
    };

    // Form
    const onSubmit = async (form: FormSchema) => {
        form.createdOn = moment().format(VN_DATETIME_FORMAT);
        if (!isValid) return;
        form.content = form.content.replaceAll(/\r\n|\n/gm, '<br/>');
        saveData(form);
    }

    // Api
    const fetchData = async () => {
        const res = await fetch('/api/data', {
            method: 'GET',
            headers: HTTP_HEADERS
        });
        if (res.ok) {
            const body = await res.json();
            dispatch(setData(body));
        } else {
            const errBody: ErrorModel = await res.json();
            console.log('There is error', errBody);
        }
    }
    const saveData = async (form: FormSchema) => {
        const res = await fetch('/api/data', {
            method: 'POST',
            headers: HTTP_HEADERS,
            body: JSON.stringify(form)
        });
        if (res.ok) {
            reset();
            await fetchData();
        } else {
            const errBody: ErrorModel = await res.json();
            console.log('There is error', errBody);
        }
    }

    // Render
    function renderListData() {
        return lstData.map((item, index) => (
            <Fragment key={`data${index}`}>
                <div className={classNames(index % 2 ? 'even' : 'odd', 'd-flex', 'flex-column')}>
                    <div className='wishes-description'>
                        <span>{item.createdBy}</span><span>({item.createdOn})</span>
                    </div>
                    <div className='wishes-content' dangerouslySetInnerHTML={{ __html: item.content }} />
                </div>
            </Fragment>
        ));
    }

    return (<Fragment>
        <span id='guestBook' />
        <div className='guestBookContainer'>
            <div className='container'>
                <div className='summary mb-4 text-center'>
                    <div className='summary-title'>
                        Guest Book
                    </div>
                </div>
                <div className='contentSection row'>
                    <div className='col col-xl-6 col-sm-12 col-12 mb-4 px-4'>
                        <div className='p-5 left-side' ref={formSquare} >
                            <div className='form'>
                                <form onSubmit={handleSubmit(onSubmit)}>
                                    <div className='row'>
                                        <div className='col-lg-6 col-md-6 mb-2 d-flex flex-row justify-content-center'>
                                            <FormControl error={!!errors.createdBy}
                                                size='small'
                                                fullWidth>
                                                <InputLabel htmlFor='txtFullname'>Fullname*</InputLabel>
                                                <OutlinedInput {...register('createdBy')}
                                                    value={watch('createdBy', '')}
                                                    id='txtFullname'
                                                    label='Fullname' />
                                            </FormControl>
                                        </div>
                                        <div className='col-lg-6 col-md-6 mb-2 d-flex flex-row justify-content-center'>
                                            <FormControl error={!!errors.email}
                                                size='small'
                                                fullWidth>
                                                <InputLabel htmlFor='txtEmail'>Email</InputLabel>
                                                <OutlinedInput {...register('email')}
                                                    value={watch('email', '')}
                                                    id='txtEmail'
                                                    label='Email' />
                                            </FormControl>
                                        </div>
                                    </div>
                                    <div className='row mb-3'>
                                        <div className='col-12'>
                                            <Paper elevation={1} style={{ textAlign: 'right' }}>
                                                <FormControl error={!!errors.content}
                                                    size='small'
                                                    fullWidth>
                                                    <InputLabel htmlFor='txtYourWishes'>Your Wishes*</InputLabel>
                                                    <OutlinedInput onChange={(e) => setValue('content', e.target.value)}
                                                        value={watch('content', '')}
                                                        {...register('content')}
                                                        inputRef={textareaRef}
                                                        label='Your Wishes'
                                                        id='txtYourWishes'
                                                        multiline
                                                        rows={5} />
                                                </FormControl>
                                                <IconButton onClick={handleIdeaShow}>
                                                    <LightbulbOutlinedIcon />
                                                </IconButton>
                                                <IconButton onClick={handleEmojiShow}>
                                                    <EmojiEmotionsIcon />
                                                </IconButton>
                                                <div ref={(e) => { setAnchorEl(e) }} />
                                            </Paper>
                                            <Popper placement='bottom'
                                                anchorEl={anchorEl}
                                                open={ideaShow}
                                                className='d-flex justify-content-center'
                                                style={{
                                                    zIndex: 1,
                                                    width: autoCompleteWidth
                                                }}>
                                                <Autocomplete options={wishesSuggest}
                                                    renderInput={(params) => <TextField {...params} label='Select Option' size='small' variant='outlined' />}
                                                    getOptionLabel={(option) => option.label}
                                                    value={autoCompleteValue}
                                                    onChange={onIdeaSelect}
                                                    clearOnEscape={true}
                                                    clearIcon={null}
                                                    size='small'
                                                    style={{
                                                        backgroundColor: '#fff'
                                                    }}
                                                    fullWidth
                                                    open />
                                            </Popper>
                                            <Popper placement='bottom-end'
                                                anchorEl={anchorEl}
                                                open={emojiShow}
                                                className='d-flex justify-content-end'
                                                style={{
                                                    zIndex: 1,
                                                    width: autoCompleteWidth
                                                }}>
                                                <Picker categories={['people', 'foods', 'activity', 'places', 'objects']}
                                                    onClickOutside={() => setEmojiShow(false)}
                                                    onEmojiSelect={onEmojiSelect}
                                                    skinTonePosition={'none'}
                                                    previewPosition={'none'}
                                                    maxFrequentRows={0}
                                                    theme={'light'}
                                                    perLine={6}
                                                    data={data} />
                                            </Popper>
                                        </div>
                                    </div>
                                    <div className='row'>
                                        <div className='col-12 text-center'>
                                            <Button variant='contained'
                                                className='btnSubmit'
                                                endIcon={<SendIcon />}
                                                type='submit'
                                                disableElevation>
                                                Send
                                            </Button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div className='seperate-line my-3 w-75 mx-auto' />
                            <div className='qr text-center'>
                                <div className='text mb-2'>
                                    Rejoice with us by another way 💌 💰
                                </div>
                                <div className='qr-code'>
                                    <Image src='/qr.jpeg'
                                        alt='QR banking'
                                        priority
                                        sizes='100vw'
                                        height={0}
                                        width={0}
                                        style={{
                                            width: 'auto',
                                            height: '200px'
                                        }} />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className='col col-xl-6 col-sm-12 col-12 mb-4 px-4'>
                        <div className='right-side' style={{ height: formSquareHeight }}>
                            {renderListData()}
                        </div>
                    </div>
                </div>
                <div className='contentSection row'>
                    <div className='col col-xl-6 col-sm-12 col-12 mb-5 px-4 d-flex flex-column' ref={mapSquare}>
                        <span className='w-100 text-center mb-2 fs-4'>Wedding Place</span>
                        <div className='maps-container' ref={mapContainer}>
                            <div className='address mb-1 text-center'>Ai Hue 2 Restaurant-<span>338 Đ. Trần Hưng Đạo, Phường 11, Quận 5, Hồ Chí Minh</span></div>
                            <iframe src='https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.770089646576!2d106.65942877577592!3d10.752194459638716!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f0c4d558edb%3A0x2c60d7b2f3e598a0!2zTmjDoCBIw6BuZyDDgWkgSHXDqiAyIC0g5oSb6I-vIDIg5aSn5rSS5qiT!5e0!3m2!1svi!2s!4v1721982196698!5m2!1svi!2s'
                                allowFullScreen={false}
                                loading='lazy'
                                referrerPolicy='no-referrer-when-downgrade'></iframe>
                        </div>
                    </div>
                    <div className='col col-xl-6 col-sm-12 col-12 mb-5 px-4 d-flex flex-column'>
                        <span className='w-100 text-center mb-2 fs-4'>Our Timeline</span>
                        <div className='d-flex flex-column justify-content-between timeline' style={{ height: mapContainerHeight }}>
                            <div className='separate-line' style={{
                                height: mapContainerHeight,
                                left: `calc(50% + ${mapSquareWidth} / 2)`
                            }} />
                            <div className='d-flex flex-row justify-content-around align-items-center pb-4'>
                                <div className='w-50 d-flex justify-content-center'>
                                    <Image src='/welcome.svg'
                                        alt='welcome'
                                        priority
                                        height={TIMELINE_SIZE}
                                        width={TIMELINE_SIZE} />
                                </div>
                                <div className='d-flex flex-column align-items-center w-50 text-wrap'>
                                    <span className='detail fw-bold fs-4'>Pick Up</span>
                                    <span className='time fst-italic fw-light'>18:00</span>
                                </div>
                            </div>
                            <div className='d-flex flex-row-reverse justify-content-around align-items-center pb-4'>
                                <div className='w-50 d-flex justify-content-center'>
                                    <Image src='/wedding.svg'
                                        alt='wedding'
                                        priority
                                        height={TIMELINE_SIZE}
                                        width={TIMELINE_SIZE} />
                                </div>
                                <div className='d-flex flex-column align-items-center w-50 text-wrap'>
                                    <span className='detail fw-bold fs-4'>Celebrate</span>
                                    <span className='time fst-italic fw-light'>19:30</span>
                                </div>
                            </div>
                            <div className='d-flex flex-row justify-content-around align-items-center'>
                                <div className='w-50 d-flex justify-content-center'>
                                    <Image src='/meat.svg'
                                        alt='meat'
                                        priority
                                        height={TIMELINE_SIZE}
                                        width={TIMELINE_SIZE} />
                                </div>
                                <div className='d-flex flex-column align-items-center w-50 text-wrap'>
                                    <span className='detail fw-bold fs-4 text-center'>Join The Party</span>
                                    <span className='time fst-italic fw-light'>19:50</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </Fragment>);
}
