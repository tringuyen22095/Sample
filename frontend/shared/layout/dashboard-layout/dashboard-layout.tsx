import React, { PropsWithChildren, Suspense, useState } from 'react';
import HeaderBar from '@/components/header-bar/header-bar';
import { Collapse, styled } from '@mui/material';
import LeftNav from '@/components/left-nav/left-nav';

const DivContainer = styled('div')(({ theme }) => ({
  width: '100%',
  display: 'flex',
  flex: 'row'
}));

const DashboardLayout: React.FC<PropsWithChildren> = ({ children }) => {
  const [collapse, setCollapse] = useState(true);
  const [dimensions, setDimensions] = useState({width: '0', height: ''});

  function menuHit() {
    setCollapse(!collapse);
  }

  return (
    <div className='dashboard'>
      <HeaderBar setCollapse={menuHit} setDimensions={setDimensions}></HeaderBar>
      <DivContainer>
        <Collapse orientation='horizontal' in={collapse}>
          <LeftNav headerHeight={dimensions.height} />
        </Collapse>
        <Suspense fallback={<p>Loading</p>}>
          {children}
        </Suspense>
      </DivContainer>
    </div>
  )
}

export default DashboardLayout;