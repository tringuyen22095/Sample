'use client';

import React, { useEffect, useState } from 'react';
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';
import { Box, ButtonGroup, Divider, IconButton, TextareaAutosize } from '@mui/material';
import { useAppDispatch } from '@/shared/redux/store';
import { show, hide } from "@/shared/common/loading/loading-slice";
import { useForm } from 'react-hook-form';
import './chat-container.scss';
import { Collections, Send } from '@mui/icons-material';

const yupSchema = Yup.object().shape({
    content: Yup.string().when(['files'], (value, schema) => {
        if (!value?.[0]?.length)
            return schema.required().trim();
        return schema;
    }),
    files: Yup.array().when(['content'], (value, schema) => {
        if (!value?.[0]?.trim())
            return schema.min(1, '');
        return schema;
    })
}, [['files', 'content']]);

const initFormValues = {
    content: '',
    files: []
};

interface Props { }

const ChatContainer = (props: Props) => {
    const dispatch = useAppDispatch();

    const { register, handleSubmit, reset, setError, watch, setFocus, getValues, trigger, setValue, formState: { errors, isDirty, isSubmitted, isValid } } = useForm({
        mode: 'all',
        defaultValues: initFormValues,
        criteriaMode: 'all',
        resolver: yupResolver(yupSchema)
    });

    const onSend = async (data) => {
        try {
            if (isValid) {
                dispatch(show());
            }
        } finally {
            dispatch(hide());
            reset();
        }
    };

    const onSelect = () => {

    };

    const onKeyDown = (event: React.KeyboardEvent<HTMLElement>) => {
        if ((event.metaKey || event.ctrlKey) && event.key === 'Enter') {
            handleSubmit(onSend);
        }
    };

    return (<>
        <Box className='chat-container'
            component='div'>
            {/* header */}
            <Box component='div'></Box>

            {/* body */}
            <Box component='div'></Box>

            {/* footer */}
            <Box className='input-container'
                component='div'>
                <TextareaAutosize className='content-field'
                    id='content'
                    name='content'
                    onKeyDown={onKeyDown}
                    {...register('content')} />
                <ButtonGroup variant="text">
                    <IconButton onClick={handleSubmit(onSend)} disabled={!isValid}>
                        <Send />
                    </IconButton>
                    <IconButton onClick={onSelect}>
                        <Collections />
                    </IconButton>
                </ButtonGroup>
            </Box>
        </Box>
    </>);
}

export default ChatContainer;