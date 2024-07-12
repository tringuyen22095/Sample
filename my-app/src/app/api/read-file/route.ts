import { NextResponse } from 'next/server';
import fs from 'fs';
import path from 'path';

export async function GET() {
    const filePath = path.join(process.cwd(), 'data', 'file.txt');
    try {
        const data = fs.readFileSync(filePath, 'utf8');
        return NextResponse.json({ content: data });
    } catch (err) {
        return NextResponse.json({ error: 'Failed to read file' }, { status: 500 });
    }
}
