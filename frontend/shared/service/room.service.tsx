'use client';

import { of } from 'rxjs';
import baseAxios from './base-url';
import { RoomItem } from '../models/model';
import { MessageType } from '../models/constant';

async function getRoom() {
    return await of(a()).toPromise();
}

const a = (): RoomItem[] => {
    return [
        {
            createdBy: 'abc',
            createdOn: '2023-11-11 00:00:00',
            updatedBy: 'abc',
            updatedOn: '2023-11-11 01:00:00',
            id: '166e0662-82b9-11ee-a466-325096b39f47',
            name: 'test1',
            lastMessage: {
                createdBy: 'abc',
                createdOn: '2023-11-11 00:00:00',
                updatedBy: 'abc',
                updatedOn: '2023-11-11 01:00:00',
                type: 'TEXT',
                id: '166e082e-82b9-11ee-9940-325096b39f47',
                content: 'you are my sunshine, yes you are my sunside'
            }
        },
        {
            createdBy: 'abc',
            createdOn: '2023-11-11 00:00:00',
            updatedBy: 'abc',
            updatedOn: '2023-11-11 01:00:00',
            id: '166e082e-82b9-11ee-9940-325096b39f47',
            name: 'test2',
            lastMessage: {
                createdBy: 'abc',
                createdOn: '2023-11-11 00:00:00',
                updatedBy: 'abc',
                updatedOn: '2023-11-11 01:00:00',
                type: 'TEXT',
                id: '166e082e-82b9-11ee-9940-325096b39f47',
                content: 'you are my sunshine, yes you are my sunside'
            }
        },
        {
            createdBy: 'abc',
            createdOn: '2023-11-11 00:00:00',
            updatedBy: 'abc',
            updatedOn: '2023-11-11 01:00:00',
            id: '166e089c-82b9-11ee-be37-325096b39f47',
            name: 'test3',
            lastMessage: {
                createdBy: 'abc',
                createdOn: '2023-11-11 00:00:00',
                updatedBy: 'abc',
                updatedOn: '2023-11-11 01:00:00',
                type: 'TEXT',
                id: '166e082e-82b9-11ee-9940-325096b39f47',
                content: `Baby, life was good to me
                But you just made it better
                I love the way you stand by me
                Through any kind of weather
                I don't wanna run away, just wanna make your day
                When you feel the world is on your shoulders
                I don't wanna make it worse, just wanna make us work
                Baby, tell me I will do whatever
                It feels like nobody ever knew me until you knew me
                Feels like nobody ever loved me until you loved me
                Feels like nobody ever touched me until you touched me
                Baby, nobody, nobody until you`
            }
        }
    ]
}

export { getRoom };