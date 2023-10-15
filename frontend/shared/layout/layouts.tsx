// components/common/Layouts.tsx
import AdminLayout from "./admin-layout";
import AnonymousLayout from "./anonymous-layout";
import AuthenticationLayout from "./authentication-layout";

export const Layouts = {
  Admin: AdminLayout,
  Anonymous: AnonymousLayout,
  Authen: AuthenticationLayout
};

export type LayoutKeys = keyof typeof Layouts;
