'use client'

import { Box, Drawer, Fab, Link, List, ListItem, ListItemButton, ListItemText } from '@mui/material';
import { ErrorModel, HEADER_NAVIGATION_TEMPLATE, HTTP_HEADERS } from 'constant';
import React, { Fragment, useEffect, useState } from 'react';
import MenuIcon from '@mui/icons-material/Menu';
import { useAppDispatch } from 'hooks';
import { setData } from 'dataReducer';
import './style.scss';

export default function Header() {
    const [open, setOpen] = useState(false);
    const dispatch = useAppDispatch();

    const toggleDrawer = (newOpen: boolean) => () => {
        setOpen(newOpen);
    };

    useEffect(() => {
        const fetchData = async () => {
            const res = await fetch('/api/read-file', {
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
                    <Link href={redirect} key={index}>
                        <ListItem disablePadding>
                            <ListItemButton>
                                <ListItemText primary={text} />
                            </ListItemButton>
                        </ListItem>
                    </Link>
                ))}
            </List>
        </Box>
    );

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
            className='nav-menu'
            color='primary'>
            <MenuIcon />
        </Fab>
        <Drawer anchor='left' open={open} onClose={toggleDrawer(false)}>
            {DrawerList}
        </Drawer>
    </Fragment>);
}