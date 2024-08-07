import { NextResponse } from 'next/server';
import { guestBookSchema, GuestBookType } from 'models';
import type { NextRequest } from 'next/server';
import fs from 'fs';
import path from 'path';

const temporaryData: GuestBookType[] = [];

// export async function POST(request: Request): Promise<NextResponse<{ error?: string, message?: string }>> {
//     const folderPath = path.join(process.cwd(), 'data');
//     if (!fs.existsSync(folderPath)) fs.mkdirSync(folderPath, { recursive: true });

//     try {
//         const payload: GuestBookType = await request.json();
//         guestBookSchema.parse(payload);
//         const filePath = path.join(folderPath, 'data.txt');
//         const finalizeData: GuestBookType[] = [];

//         if (!fs.existsSync(filePath)) {
//             finalizeData.push(payload);
//         } else {
//             const data: GuestBookType[] = JSON.parse(fs.readFileSync(filePath, 'utf8') || '[]');
//             data.push(payload);
//             finalizeData.push(...data);
//         }
//         fs.writeFileSync(filePath, JSON.stringify(finalizeData, null, 2), 'utf8');
//         return NextResponse.json({ error: 'File written successfully' }, { status: 200 });
//     } catch (err) {
//         return NextResponse.json({ error: 'File written fail' }, { status: 500 });
//     }
// }

export async function POST(request: NextRequest): Promise<NextResponse<{ error?: string, message?: string }>> {
    try {
        if (request.method === 'OPTIONS') {
            return buildResponse(null, 200);
        }
        const payload: GuestBookType = await request.json();
        guestBookSchema.parse(payload);
        temporaryData.push(payload);

        return buildResponse(null, 200);
    } catch (err) {
        return buildResponse({ error: 'File written fail' }, 500);
    }
}

export async function GET(): Promise<NextResponse<GuestBookType[] | { error: string }>> {
    try {
        return buildResponse(temporaryData, 200);
    } catch (err) {
        return buildResponse({ error: 'Failed to read file' }, 500);
    }
}

function buildResponse(payload: any | null, httpStatusInt: 200 | 500): NextResponse<any> {
    const response = NextResponse.json(payload, { status: httpStatusInt });

    response.headers.set('Access-Control-Allow-Credentials', 'true');
    response.headers.set('Access-Control-Allow-Origin', '*');
    response.headers.set('Access-Control-Allow-Methods', 'GET,OPTIONS,POST,PUT');
    response.headers.set('Access-Control-Allow-Headers', 'Content-Type, Authorization');

    return response;
}