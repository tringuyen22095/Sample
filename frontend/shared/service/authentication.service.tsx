'use client';

import baseAxios from './base-url';

async function signIn(payload) {
    return await baseAxios().post<{ sessionGuid: string, authorization: string }>('oru-auth/login', { ...payload, source: 'CFF-BRK-FRONTEND' });
}

export { signIn };