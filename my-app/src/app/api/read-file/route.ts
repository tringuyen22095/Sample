import { NextResponse } from 'next/server';
import { GuestBookType } from 'models';
import fs from 'fs';
import path from 'path';
import readline from 'readline';

export async function GET(): Promise<NextResponse<GuestBookType[] | { error: string }>> {
    const filePath = path.join(process.cwd(), 'data', 'data.txt');

    try {
        // const lines: GuestBookType[] = [];
        // const fileStream = fs.createReadStream(filePath);
        // const rl = readline.createInterface({
        //     input: fileStream,
        //     crlfDelay: Infinity
        // });
        // for await (const line of rl) {
        //     if (line) {
        //         const record: GuestBookType = JSON.parse(line);
        //         if (!record.isDeleted) lines.push(record);
        //     }
        // }
        const data = fs.readFileSync(filePath, 'utf8');
        return NextResponse.json(JSON.parse(data), { status: 200 });
    } catch (err) {
        return NextResponse.json({ error: 'Failed to read file', msg: err }, { status: 500 });
    }
}
