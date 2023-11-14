export interface BaseRes {
    createdOn: string;
    createdBy: string;
    updatedOn: string;
    updatedBy: string;
}

export enum MessageType {
    TEXT = 'TEXT',
    AUDIO = 'AUDIO',
    FILE = 'FILE',
    IMAGE = 'IMAGE'
}