import { NextResponse } from 'next/server';
import { GuestBookType } from 'models';
import fs from 'fs';
import path from 'path';

export async function GET(): Promise<NextResponse<GuestBookType[] | { error: string }>> {
    const filePath = path.join(process.cwd(), 'data', 'data.txt');

    try {
        const data = fs.readFileSync(filePath, 'utf8');
        return NextResponse.json(JSON.parse(data), { status: 200 });
    } catch (err) {
        return NextResponse.json({ error: 'Failed to read file' }, { status: 500 });
    }
}
