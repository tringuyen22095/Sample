import React, { PropsWithChildren, Suspense, useCallback, useEffect, useState } from 'react';
import HeaderBar from '@/components/header-bar/header-bar';
import { Box, Collapse, styled } from '@mui/material';
import LeftNav from '@/components/left-nav/left-nav';
import * as RoomService from '@/shared/service/room.service';
import { RoomItem } from '@/shared/models/model';

const DashboardLayout: React.FC<PropsWithChildren> = ({ children }) => {
  const [collapse, setCollapse] = useState<boolean>(true);
  const [lstRoom, setLstRoom] = useState<RoomItem[]>([]);
  const [dimensions, setDimensions] = useState({ width: '0', height: '' });

  function menuHit() {
    setCollapse(!collapse);
  }

  const fetchLstRoom = useCallback(async () => {
    const res = await RoomService.getRoom();
    setLstRoom(res);
  }, [])

  useEffect(() => {
    fetchLstRoom();
  }, [fetchLstRoom]);

  return (
    <div className='dashboard'>
      <HeaderBar setCollapse={menuHit} setDimensions={setDimensions}></HeaderBar>
      <Box component='div'
        sx={{
          display: 'flex',
          flexDirection: 'row'
        }}>
        <Box component='div'>
          <Collapse orientation='horizontal' in={collapse} sx={{minWidth: 'unset'}}>
            <LeftNav headerHeight={dimensions.height} lstRoom={lstRoom} />
          </Collapse>
        </Box>
        {children}
      </Box>
    </div>
  )
}

export default DashboardLayout;