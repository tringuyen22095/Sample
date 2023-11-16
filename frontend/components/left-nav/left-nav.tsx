import * as React from 'react';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import { Box, ListItemButton } from '@mui/material';
import { RoomItem } from '@/shared/models/model';
import Grid2 from '@mui/material/Unstable_Grid2/Grid2';

interface Props {
  headerHeight: string;
  lstRoom: RoomItem[];
}

export default function LeftNav(props: Props) {
  const roomCount = props.lstRoom.length;

  const genListRoomItem = () => {
    return props.lstRoom.map((item, index) => {
      return (
        <React.Fragment key={item.id}>
          <ListItemButton alignItems='flex-start'>
            <ListItemAvatar>
              <Avatar alt={item.name.toUpperCase()} src='/static/images/avatar/1.jpg' />
            </ListItemAvatar>
            <Grid2 container>
              <Grid2 xs={12} lg={12} md={12} sm={12} xl={12}>
                <Typography component='div'
                  variant='body2'
                  color='text.primary'
                  sx={{
                    textOverflow: 'ellipsis',
                    whiteSpace: 'nowrap',
                    overflow: 'hidden',
                    fontWeight: 'bold',
                    fontSize: '18px'
                  }}>
                  {item.name}
                </Typography>
              </Grid2>
              <Grid2 xs={12} sm={12} md={12} lg={12} xl={12}>
                <Box component='div'
                  sx={{
                    textOverflow: 'ellipsis',
                    whiteSpace: 'nowrap',
                    overflow: 'hidden'
                  }}>
                  <Typography sx={{ display: 'inline' }}
                    component='span'
                    variant='body2'
                    color='text.primary'>
                    {item.lastMessage.id}
                  </Typography>
                  {item.lastMessage.content}
                </Box>
              </Grid2>
            </Grid2>
          </ListItemButton>
          {(roomCount - 1) !== index ? <Divider variant='middle' component='div' /> : <></>}
        </React.Fragment>
      );
    });
  };

  return (
    <Box sx={{
      width: { xs: '10px', sm: '100px', md: '500px' },
      bgcolor: 'background.paper',
      minHeight: `calc(100vh - ${props.headerHeight}px)`,
      boxShadow: '#BEBFC0 1px 0 3px 0'
    }}>
      <List sx={{p: '0'}}>
        {genListRoomItem()}
      </List>
    </Box>
  );
}