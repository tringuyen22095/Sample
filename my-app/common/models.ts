import { z } from 'zod';

export type GuestBookType = {
    createdBy: string,
    createdOn: string,
    content: string,
    email?: string
};

export const guestBookSchema = z.object({
    createdBy: z.string().min(1, { message: 'FullName is required!' }),
    createdOn: z.string().optional(),
    content: z.string().min(1, { message: 'Content is required!' }),
    email: z.string()
        .optional()
        .refine((value) => value === '' || /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value), {
            message: 'Invalid email address'
        })
});

export type FormSchema = z.infer<typeof guestBookSchema>;

export const initGuestBookFormValues = {
    createdBy: '',
    createdOn: '',
    content: '',
    email: ''
};