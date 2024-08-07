'use client'

import { Box, Drawer, Fab, Link, List, ListItem, ListItemButton, ListItemIcon, ListItemText } from '@mui/material';
import { ErrorModel, HEADER_NAVIGATION_TEMPLATE, HTTP_HEADERS } from 'constant';
import React, { Fragment, useEffect, useState } from 'react';
import MenuIcon from '@mui/icons-material/Menu';
import { useAppDispatch } from 'hooks';
import { setData } from 'dataReducer';
import './style.scss';
import HomeIcon from '@mui/icons-material/Home';
import InfoIcon from '@mui/icons-material/Info';
import AutoStoriesIcon from '@mui/icons-material/AutoStories';
import CollectionsIcon from '@mui/icons-material/Collections';
import ModeEditIcon from '@mui/icons-material/ModeEdit';

export default function Header() {
    const [open, setOpen] = useState(false);
    const dispatch = useAppDispatch();

    const toggleDrawer = (newOpen: boolean) => () => {
        setOpen(newOpen);
    };

    useEffect(() => {
        const fetchData = async () => {
            const res = await fetch('https://stored-blush.vercel.app/api/data', {
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
        };
        fetchData();
    }, [dispatch]);

    const DrawerList = (
        <Box sx={{ width: 250 }}
            role='presentation'
            onClick={toggleDrawer(false)}>
            <List>
                {HEADER_NAVIGATION_TEMPLATE.map(({ redirect, text }, index) => (
                    <Link href={redirect} key={index} className='href'>
                        <ListItem disablePadding>
                            <ListItemIcon className='d-flex justify-content-center'>
                                {generateIcon(text)}
                            </ListItemIcon>
                            <ListItemButton>
                                <ListItemText primary={text} />
                            </ListItemButton>
                        </ListItem>
                    </Link>
                ))}
            </List>
        </Box>
    );

    function generateIcon(text: string) {
        if (text.includes('About Us')) return <InfoIcon />
        if (text.includes('Our Story')) return <AutoStoriesIcon />
        if (text.includes('Gallery')) return <CollectionsIcon />
        if (text.includes('Guest Book')) return <ModeEditIcon />
        return <HomeIcon />
    }

    function headerRender() {
        return HEADER_NAVIGATION_TEMPLATE.map((v, i) => {
            return <Fragment key={`nav-${i}`}>
                <span>
                    <a href={v.redirect}>{v.text}</a>
                </span>
            </Fragment>;
        });
    }

    return (<Fragment>
        <div className='headerContainer'>
            {headerRender()}
        </div>
        <Fab onClick={toggleDrawer(true)}
            className='nav-btn'
            color='primary'>
            <MenuIcon />
        </Fab>
        <Drawer onClose={toggleDrawer(false)}
            className='nav-menu'
            anchor='left'
            open={open}>
            {DrawerList}
        </Drawer>
    </Fragment>);
}