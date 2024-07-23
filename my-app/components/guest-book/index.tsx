'use client'

import './style.scss';
import React, { Fragment, useRef, useState } from 'react';
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
import { initGuestBookFormValues, guestBookSchema, FormSchema } from 'models';
import { zodResolver } from '@hookform/resolvers/zod';
import moment from 'moment';
import { VN_DATETIME_FORMAT, wishesSuggest } from 'constant';
import LightbulbOutlinedIcon from '@mui/icons-material/LightbulbOutlined';
import Autocomplete from '@mui/material/Autocomplete';

export default function GuestBook() {
    const { register, handleSubmit, reset, setError, watch, setFocus, getValues, trigger, setValue, formState: { errors, isDirty, isSubmitted, isValid } } = useForm<FormSchema>({
        defaultValues: initGuestBookFormValues,
        resolver: zodResolver(guestBookSchema)
    });
    const contentValue = watch('content', '');
    const textareaRef = useRef(null);
    const [autocompleteValue, setAutocompleteValue] = useState(null);

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

    // Form
    const onSubmit = (data: FormSchema) => {
        data.createdOn = moment().format(VN_DATETIME_FORMAT);
        console.log('Form Data:', data);
    }

    return (<Fragment>
        <span id='guestBook' />
        <div className='guestBookContainer'>
            <div className='container'>
                <div className='summary text-center'>
                    <div className='summary-title'>
                        Guest Book
                    </div>
                </div>
                <div className='contentSection row'>
                    <div className='col col-lg-6 col-md-6'>
                        <div className='wish-form'>
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
                                                type='email'
                                                id='txtEmail'
                                                label='Email' />
                                        </FormControl>
                                    </div>
                                </div>
                                <div className='row mt-4'>
                                    <div className='col-lg-12 col-md-12' style={{textAlign: 'right'}}>
                                        <FormControl error={!!errors.content}
                                            size='small'
                                            fullWidth>
                                            <InputLabel htmlFor='txtYourWishes'>Your Wishes*</InputLabel>
                                            <OutlinedInput id='txtYourWishes'
                                                label='Your Wishes'
                                                value={contentValue}
                                                {...register('content')}
                                                multiline
                                                rows={5}
                                                onChange={(e) => {
                                                    setValue('content', e.target.value);
                                                }}
                                                ref={textareaRef} />
                                        </FormControl>
                                        <IconButton onClick={handleIdeaShow}>
                                            <LightbulbOutlinedIcon />
                                        </IconButton>
                                        <IconButton onClick={handleEmojiShow}
                                            ref={btnEmojiRef}>
                                            <EmojiEmotionsIcon />
                                        </IconButton>
                                        {
                                            show && (<Autocomplete options={wishesSuggest}
                                                getOptionLabel={(option) => option.label}
                                                renderInput={(params) => <TextField {...params} label="Select Option" size='small' variant="outlined" />}
                                                value={autocompleteValue}
                                                onChange={onIdeaSelect}
                                                clearOnEscape={true}
                                                clearIcon={null}
                                                size='small'
                                                open />)
                                        }
                                        {/* <Popper anchorEl={anchorIdeaEl}
                                            style={{
                                                width: textareaRef.current?.clientWidth
                                            }}
                                            open={Boolean(anchorIdeaEl)}>
                                                <Paper style={{ width: '100%' }}>
                                                    <Autocomplete open={Boolean(anchorIdeaEl)}
                                                        options={wishesSuggest}
                                                        getOptionLabel={(option) => option.label}
                                                        onChange={onIdeaSelect}
                                                        renderInput={(params) => (
                                                            <TextField {...params} label="Select Option" variant="outlined" />
                                                        )} />
                                                </Paper>
                                        </Popper> */}
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
                                <div className='row'>
                                    <div className='col-lg-12 col-md-12 text-center'>
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
                    </div>
                    <div className='col col-lg-6 col-md-6'>
                        <div className='wish-box'></div>
                    </div>
                </div>
            </div>
        </div>
    </Fragment >);
}
