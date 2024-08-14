import { NextResponse } from 'next/server';
import { guestBookSchema, GuestBookType } from 'models';
import type { NextRequest } from 'next/server';
import { sql } from "@vercel/postgres";

export async function POST(request: NextRequest): Promise<NextResponse<{ error?: string, message?: string }>> {
    try {
        if (request.method === 'OPTIONS') {
            return buildResponse(null, 200);
        }
        const payload: GuestBookType = await request.json();
        guestBookSchema.parse(payload);
        await sql`INSERT INTO blessing ("createdBy", content, email)
            VALUES (${payload.createdBy}, ${payload.content}, ${payload.email});`;

        return buildResponse(null, 200);
    } catch (err) {
        return buildResponse({ error: 'File written fail' }, 500);
    }
}

export async function GET(): Promise<NextResponse<GuestBookType[] | { error: string }>> {
    try {
        const { rows } = await sql`SELECT "createdBy", timezone('utc+7', "createdOn") AS "createdOn", content, email 
        FROM blessing WHERE "isDeleted" = false 
        ORDER BY "createdOn";`;
        return buildResponse(rows, 200);
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