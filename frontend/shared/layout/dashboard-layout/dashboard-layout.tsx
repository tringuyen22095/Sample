import React, { PropsWithChildren, Suspense, useCallback, useEffect, useState } from 'react';
import HeaderBar from '@/components/header-bar/header-bar';
import { Collapse, styled } from '@mui/material';
import LeftNav from '@/components/left-nav/left-nav';
import * as RoomService from '@/shared/service/room.service';
import { RoomItem } from '@/shared/models/model';

const DivContainer = styled('div')(({ theme }) => ({
  width: '100%',
  display: 'flex',
  flex: 'row'
}));

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
      <DivContainer>
        <Collapse orientation='horizontal' in={collapse}>
          <LeftNav headerHeight={dimensions.height} lstRoom={lstRoom} />
        </Collapse>
        <Suspense fallback={<p>Loading</p>}>
          {children}
        </Suspense>
      </DivContainer>
    </div>
  )
}

export default DashboardLayout;