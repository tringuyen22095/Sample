import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { GuestBookType } from 'models';

const initialState: GuestBookType[] = [];

const dataSlice = createSlice({
  name: 'data',
  initialState,
  reducers: {
    setData: (state, action: PayloadAction<GuestBookType[]>) => {
      return action.payload;
    }
  },
});

export const { setData } = dataSlice.actions;
export default dataSlice.reducer;
