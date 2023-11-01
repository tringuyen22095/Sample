import React from "react";
import { Backdrop, CircularProgress } from '@mui/material';
import { useAppSelector } from "@/shared/redux/store";

export default function Loading() {
    const isOpen = useAppSelector(state => state.isLoading.isLoading);

    return (
        <Backdrop open={isOpen}
            sx={{
                color: '#fff',
                zIndex: (theme) => theme.zIndex.drawer + 1
            }}>
            <CircularProgress color="inherit" />
        </Backdrop>
    );

}