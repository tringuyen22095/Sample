/** @type {import('next').NextConfig} */
const nextConfig = {
    async rewrites() {
        return [
            {
                source: '/api/:path*',
                destination: 'https://tringuyen-haianh-invitation.vercel.app/:path*',
            }
        ]
    }
};

export default nextConfig;
