import { NextResponse } from 'next/server';
import { guestBookSchema } from 'models';
import fs from 'fs';
import path from 'path';

export async function POST(request: Request): Promise<NextResponse<{ error?: string, message?: string }>> {
    const folderPath = path.join(process.cwd(), 'data');
    if (!fs.existsSync(folderPath)) fs.mkdirSync(folderPath, { recursive: true });

    try {
        const payload: string = await request.json();
        guestBookSchema.parse(payload);
        const filePath = path.join(folderPath, 'data.txt');
        if (!fs.existsSync(filePath))
            fs.writeFileSync(filePath, JSON.stringify(payload), 'utf8');
        else
            fs.appendFileSync(filePath, `\r\n${JSON.stringify(payload)}`, 'utf8');
        return NextResponse.json({ message: 'File written successfully' }, { status: 200 });
    } catch(err) {
        return NextResponse.json(err, { status: 500 });
    }
}
