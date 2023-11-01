import { createSlice } from '@reduxjs/toolkit';

interface LoaddingState {
    isLoading: boolean;
    count: number;
}

const initialState: LoaddingState = {
    isLoading: false,
    count: 0
  };

const counterSlice = createSlice({
  name: 'isLoading',
  initialState,
  reducers: {
    show: (state) => {
      state.count += 1;
      if (state.count)
        state.isLoading = true
    },
    hide: (state) => {
      state.count -= 1;
      if (!state.count)
        state.isLoading = false
    },
  },
});

export const { show, hide } = counterSlice.actions;

export default counterSlice.reducer;
