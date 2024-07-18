import { z } from 'zod';

export type GuestBookType = {
    createbBy: string,
    createdOn: string,
    content: string,
    email?: string
};

export const guestBookSchema = z.object({
    createbBy: z.string(),
    createdOn: z.string(),
    content: z.string()
});