// components/common/Layouts.tsx
import AdminLayout from "../layout/admin-layout";
import AnonymousLayout from "../layout/anonymous-layout";
import AuthenticationLayout from "../layout/authentication-layout";

export const Layouts = {
  Admin: AdminLayout,
  Anonymous: AnonymousLayout,
  Authen: AuthenticationLayout
};

export type LayoutKeys = keyof typeof Layouts;
