'use client'

import './style.scss';
import React, { Fragment, useEffect, useRef, useState } from 'react';
import {
    Button,
    IconButton,
    Popper,
    Paper,
    FormControl,
    InputLabel,
    OutlinedInput,
    ClickAwayListener,
    TextField
} from '@mui/material';
import SendIcon from '@mui/icons-material/Send';
import data from '@emoji-mart/data';
import Picker from '@emoji-mart/react';
import EmojiEmotionsIcon from '@mui/icons-material/EmojiEmotions';
import { useForm } from 'react-hook-form';
import { initGuestBookFormValues, guestBookSchema, FormSchema, GuestBookType } from 'models';
import { zodResolver } from '@hookform/resolvers/zod';
import moment from 'moment';
import { VN_DATETIME_FORMAT, wishesSuggest } from 'constant';
import LightbulbOutlinedIcon from '@mui/icons-material/LightbulbOutlined';
import Autocomplete from '@mui/material/Autocomplete';
import QrCode2Icon from '@mui/icons-material/QrCode2';
import Image from 'next/image';
import Link from 'next/link';
import classNames from 'classnames';

export default function GuestBook() {
    const { register, handleSubmit, reset, setError, watch, setFocus, getValues, trigger, setValue, formState: { errors, isDirty, isSubmitted, isValid } } = useForm<FormSchema>({
        defaultValues: initGuestBookFormValues,
        resolver: zodResolver(guestBookSchema)
    });
    const contentValue = watch('content', '');
    const [autocompleteValue, setAutocompleteValue] = useState(null);

    const [lstData, setLstData] = useState<GuestBookType[]>([]);
    
    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const res = await fetch('/api/read-file', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        });
        const body = await res.json();
        setLstData(body);
    }

    // Emoji
    const [anchorEmojiEl, setAnchorEmojiEl] = useState<null | HTMLElement>(null);
    const btnEmojiRef = useRef(null);
    const handleEmojiShow = (event?: React.MouseEvent<HTMLElement>) => {
        setAnchorEmojiEl(anchorEmojiEl ? null : btnEmojiRef.current);
    };
    const onEmojiSelect = (emoji: any) => {
        const currentContent = contentValue || '';
        setValue('content', `${currentContent}${emoji.native}`);
    };

    // Idea
    const [show, setShow] = useState<boolean>(false);
    const handleIdeaShow = (event?: React.MouseEvent<HTMLElement>) => {
        setShow(!show);
    };
    const onIdeaSelect = (event: any, idea: { label: string }) => {
        setValue('content', idea.label);
        setAutocompleteValue(null);
        setShow(false);
    };

    // QR
    const qrRef = useRef(null);
    const [anchorQrEl, setAnchorQrEl] = useState<null | HTMLElement>(null);

    // Form
    const onSubmit = async (form: FormSchema) => {
        form.createdOn = moment().format(VN_DATETIME_FORMAT);
        if (!isValid) return;
        form.content = form.content.replaceAll(/\\[n|r]/gm, '<br/>');
        try {
            await fetch('/api/write-file', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8',
                },
                body: JSON.stringify(form)
            });
        } catch (error) {
            console.error(error);
        } finally {
            reset();
            await fetchData();
        }
    }

    // Other
    function renderListData() {
        return lstData.map((item, index) => {
            return <Fragment key={`data${index}`}>
                <div className={classNames(index % 2 ? 'even' : 'odd', 'd-flex', 'flex-column')}>
                    <div className='wishes-description'>
                        <span>{item.createdBy}</span><span>({item.createdOn})</span>
                    </div>
                    <div className='wishes-content' dangerouslySetInnerHTML={{__html: item.content}}/>
                </div>
            </Fragment>;
        });
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
                    <div className='col col-xl-6 col-sm-12 col-12 mb-4'>
                        <div className='wish-form d-flex align-items-center justify-content-center'>
                            <form onSubmit={handleSubmit(onSubmit)}>
                                <div className='row'>
                                    <div className='col-lg-6 col-md-6 d-flex flex-row align-items-center justify-content-center'>
                                        <FormControl size='small'
                                            fullWidth
                                            error={!!errors.createdBy}>
                                            <InputLabel htmlFor='txtFullname'>Fullname*</InputLabel>
                                            <OutlinedInput {...register('createdBy')}
                                                id='txtFullname'
                                                label='Fullname' />
                                        </FormControl>
                                    </div>
                                    <div className='col-lg-6 col-md-6 d-flex flex-row align-items-center justify-content-center'>
                                        <FormControl size='small'
                                            fullWidth
                                            error={!!errors.email}>
                                            <InputLabel htmlFor='txtEmail'>Email</InputLabel>
                                            <OutlinedInput {...register('email')}
                                                id='txtEmail'
                                                label='Email' />
                                        </FormControl>
                                    </div>
                                </div>
                                <div className='row mt-4'>
                                    <div className='col-12' style={{textAlign: 'right'}}>
                                        <Paper elevation={1}>
                                            <FormControl error={!!errors.content}
                                                size='small'
                                                fullWidth>
                                                <InputLabel htmlFor='txtYourWishes'>Your Wishes*</InputLabel>
                                                <OutlinedInput onChange={(e) => setValue('content', e.target.value)}
                                                    {...register('content')}
                                                    value={contentValue}
                                                    label='Your Wishes'
                                                    id='txtYourWishes'
                                                    multiline
                                                    rows={5} />
                                            </FormControl>
                                            <IconButton onClick={handleIdeaShow}>
                                                <LightbulbOutlinedIcon />
                                            </IconButton>
                                            <IconButton onClick={handleEmojiShow}
                                                ref={btnEmojiRef}>
                                                <EmojiEmotionsIcon />
                                            </IconButton>
                                        </Paper>
                                        <Popper placement='right-end'
                                            anchorEl={anchorEmojiEl}
                                            open={Boolean(anchorEmojiEl)}>
                                            <ClickAwayListener onClickAway={() => handleEmojiShow()}>
                                                <Paper>
                                                    <Picker data={data}
                                                        previewPosition={'none'}
                                                        skinTonePosition={'none'}
                                                        maxFrequentRows={0}
                                                        theme={'light'}
                                                        onEmojiSelect={onEmojiSelect} />
                                                </Paper>
                                            </ClickAwayListener>
                                        </Popper>
                                    </div>
                                </div>
                                <div className='row mt-2'>
                                    <div className='col-12 text-center'>
                                        {
                                            show ?
                                            (<Autocomplete options={wishesSuggest}
                                                renderInput={(params) => <TextField {...params} label="Select Option" size='small' variant="outlined" />}
                                                getOptionLabel={(option) => option.label}
                                                value={autocompleteValue}
                                                onChange={onIdeaSelect}
                                                clearOnEscape={true}
                                                clearIcon={null}
                                                size='small'
                                                open />)
                                            :
                                            (<Fragment>
                                                <Button variant='contained'
                                                    className='btnSubmit'
                                                    endIcon={<SendIcon />}
                                                    type='submit'
                                                    disableElevation>
                                                    Send
                                                </Button>
                                                <Link ref={qrRef}
                                                    onClick={(e) => e.preventDefault()}
                                                    onMouseEnter={() => setAnchorQrEl(qrRef.current)}
                                                    onMouseLeave={() => setAnchorQrEl(null)}
                                                    href='#'>
                                                    <QrCode2Icon fontSize='large'/>
                                                </Link>
                                            </Fragment>)
                                        }
                                        <Popper placement='top-start'
                                            anchorEl={anchorQrEl}
                                            open={Boolean(anchorQrEl)}
                                            style={{
                                                zIndex: 1
                                            }}>
                                            <Image src='/qr.jpeg'
                                                alt='QR banking'
                                                loading='eager'
                                                sizes='100vw'
                                                height={0}
                                                width={0}
                                                style={{
                                                    width: 'auto',
                                                    height: '300px'
                                                }} />
                                        </Popper>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div className='col col-xl-6 col-sm-12 col-12'>
                        <div className='wish-box'>
                            {renderListData()}
                        </div>
                    </div>
                </div>
                <div className='contentSection row'>
                    <div className='col col-xl-6 col-sm-12 col-12 mb-4'>
                        <div className='maps-container'>
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.770089646576!2d106.65942877577592!3d10.752194459638716!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f0c4d558edb%3A0x2c60d7b2f3e598a0!2zTmjDoCBIw6BuZyDDgWkgSHXDqiAyIC0g5oSb6I-vIDIg5aSn5rSS5qiT!5e0!3m2!1svi!2s!4v1721982196698!5m2!1svi!2s"
                            allowFullScreen={false}
                            loading="lazy"
                            referrerPolicy="no-referrer-when-downgrade"></iframe>
                        </div>
                    </div>
                    <div className='col col-xl-6 col-sm-12 col-12 mb-4'>
                        TIMELINE
                    </div>
                </div>
            </div>
        </div>
    </Fragment >);
}
