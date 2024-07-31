// hooks.ts or similar file
import { useDispatch, useSelector } from 'react-redux';
import type { RootState, AppDispatch } from './store'; // Make sure this path is correct

export const useAppDispatch = () => useDispatch<AppDispatch>();
export const useAppSelector: <TSelected>(selector: (state: RootState) => TSelected) => TSelected = useSelector;
