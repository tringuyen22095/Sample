import { NextPage } from "next";
import { PropsWithChildren } from "react";
import { LayoutKeys } from "./layouts";

export type MyPage<P = {}, IP = P> = NextPage<P, IP> & { LayoutKey?: LayoutKeys; };
export type MyAppProps = PropsWithChildren & { LayoutKey: LayoutKeys };
