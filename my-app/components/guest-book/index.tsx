'use client'

import './style.scss';
import React, { Fragment, useRef, useState } from 'react';
import {
    Button,
    TextField,
    IconButton,
    Popper,
    Paper
} from '@mui/material';
import SendIcon from '@mui/icons-material/Send';
import data from '@emoji-mart/data';
import Picker from '@emoji-mart/react';
import EmojiEmotionsIcon from '@mui/icons-material/EmojiEmotions';

export default function GuestBook() {
    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const pickerRef = useRef<HTMLDivElement>(null);

    const handleEmojiButtonClick = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorEl(anchorEl ? null : event.currentTarget);
    };

    const onEmojiSelect = (emoji: any) => {
        setAnchorEl(null);
    };

    const open = Boolean(anchorEl);
    const id = open ? 'emoji-popper' : undefined;

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
                            <div className='row'>
                                <div className='col-lg-6 col-md-6'>
                                    <TextField id="outlined-basic"
                                        label="Fullname"
                                        variant="outlined"
                                        required
                                        error />
                                </div>
                                <div className='col-lg-6 col-md-6'>
                                    <TextField id="outlined-basic"
                                        label="Email"
                                        variant="outlined"
                                        required
                                        error />
                                </div>
                            </div>
                            <div className='row'>
                                <div className='col-lg-12 col-md-12'>
                                    <TextField id="outlined-basic"
                                        label="Blessing"
                                        variant="outlined"
                                        multiline
                                        rows={4}
                                        maxRows={4}
                                        required
                                        error />
                                    {/* <Picker data={data}
                                        previewPosition={'none'}
                                        skinTonePosition={'none'}
                                        theme={'light'}
                                        onEmojiSelect={console.log} /> */}
                                    <IconButton
                                        aria-label="emoji-picker"
                                        onClick={handleEmojiButtonClick}
                                        aria-describedby={id} >
                                        <EmojiEmotionsIcon/>
                                    </IconButton>
                                    <Popper id={id} open={open} anchorEl={anchorEl} placement="bottom-start">
                                        <Paper ref={pickerRef}>
                                            <Picker data={data}
                                                previewPosition={'none'}
                                                skinTonePosition={'none'}
                                                theme={'light'}
                                                onSelect={onEmojiSelect} />
                                        </Paper>
                                    </Popper>
                                </div>
                            </div>
                            <div className='row'>
                                <div className='col-lg-12 col-md-12'>
                                    <Button variant="contained"
                                        endIcon={<SendIcon />}
                                        disableElevation>
                                        Send
                                    </Button>
                                </div>
                            </div>
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
