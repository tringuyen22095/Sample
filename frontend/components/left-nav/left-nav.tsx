import * as React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';

interface Props {
  headerHeight: string
}

export default function LeftNav(props: Props) {
  return (
    <List sx={{
      width: '100%',
      maxWidth: { xs: '10px', sm: '100px', md: '500px' },
      bgcolor: 'background.paper',
      minHeight: `calc(100vh - ${props.headerHeight}px)`,
      boxShadow: '#BEBFC0 1px 0 3px 0'
    }}>
      <ListItem alignItems="flex-start">
        <ListItemAvatar>
          <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
        </ListItemAvatar>
        <ListItemText primary="Brunch this weekend?"
          secondary={
            <React.Fragment>
              <Typography sx={{ display: 'inline' }}
                component="span"
                variant="body2"
                color="text.primary">
                Ali Connors
              </Typography>
              {" — I'll be in your neighborhood doing errands this…"}
            </React.Fragment>
          }
        />
      </ListItem>
      {/* <Divider variant="inset" component="li" /> */}
      
    </List>
  );
}