import { BaseRes, MessageType } from "./constant";

export interface RoomItem extends BaseRes {
    id: string;
    name: string;
    lastMessage: Message;
}

export interface Message extends BaseRes {
    id: string;
    content?: string;
    type: "TEXT" | "AUDIO" | "FILE" | "IMAGE";
    documents?: Document[];
}

export interface Document extends BaseRes {
    id: string;
    bData: string;
    fileType: string;
    fileName: string;
}