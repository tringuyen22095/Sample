import { NextResponse } from 'next/server';
import fs from 'fs';
import path from 'path';

export async function POST(request: Request) {
    const { content } = await request.json();
    const filePath = path.join(process.cwd(), 'data', 'file.txt');
    try {
        fs.writeFileSync(filePath, content, 'utf8');
        return NextResponse.json({ message: 'File written successfully' });
    } catch (err) {
        return NextResponse.json({ error: 'Failed to write to file' }, { status: 500 });
    }
}
