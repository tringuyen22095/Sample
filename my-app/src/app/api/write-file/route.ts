import { NextResponse } from 'next/server';
import { guestBookSchema, GuestBookType } from 'models';
import fs from 'fs';
import path from 'path';
import { log } from 'console';

export async function POST(request: Request): Promise<NextResponse<{ error?: string, message?: string }>> {
    const folderPath = path.join(process.cwd(), 'data');
    if (!fs.existsSync(folderPath)) fs.mkdirSync(folderPath, { recursive: true });

    try {
        const payload: GuestBookType = await request.json();
        guestBookSchema.parse(payload);
        const filePath = path.join(folderPath, 'data.txt');
        const finalizeData: GuestBookType[] = [];

        if (!fs.existsSync(filePath)) {
            finalizeData.push(payload);
        } else {
            const data: GuestBookType[] = JSON.parse(fs.readFileSync(filePath, 'utf8') || '[]');
            data.push(payload);
            finalizeData.push(...data);
        }
        fs.writeFileSync(filePath, JSON.stringify(finalizeData, null, 2), 'utf8');
        return NextResponse.json({ error: 'File written successfully' }, { status: 200 });
    } catch (err) {
        return NextResponse.json({ error: 'File written fail' }, { status: 500 });
    }
}
