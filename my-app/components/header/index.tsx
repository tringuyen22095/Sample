'use client'

import './style.scss';
import React, { Fragment, useState } from 'react';
import { HEADER_NAVIGATION_TEMPLATE } from 'constant';
import { Box, Button, Drawer, Fab, Link, List, ListItem, ListItemButton, ListItemText } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';

export default function Header() {
    const [open, setOpen] = useState(false);
    const toggleDrawer = (newOpen: boolean) => () => {
      setOpen(newOpen);
    };

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