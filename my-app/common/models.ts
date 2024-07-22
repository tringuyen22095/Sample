import { z } from 'zod';

export type GuestBookType = {
    createbBy: string,
    createdOn: string,
    content: string,
    email?: string
};

export const guestBookSchema = z.object({
    createbBy: z.string({ message: 'fullName is required!' }),
    createdOn: z.string({ message: 'Create date is required!' }),
    content: z.string({ message: 'Content is required!' }),
    email: z.string().email('Email isn\'t correct!').optional()
});