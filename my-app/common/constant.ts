import moment from 'moment';

// ------------------------------------------------------------------------------
//
// Constant
//
// ------------------------------------------------------------------------------

const DATETIME_FORMAT = 'MMM DD, yyyy HH:mm';
const VN_DATETIME_FORMAT = 'DD/MM/yyyy';
const ANIMATION_KEYS: string[] = ['flip', 'bottomFloatIn', 'topFloatIn', 'leftFloatIn', 'rightFloatIn'];
const TIMELINE_SIZE = 120;
const HTTP_HEADERS = {
    'Content-Type': 'application/json; charset=utf-8'
}
export type ErrorModel = {
    error: string;
};

// ------------------------------------------------------------------------------
//
// Header template
//
// ------------------------------------------------------------------------------

export type HEADER_NAVIGATION_TYPE = {
    redirect: string,
    text: string
};

const HEADER_NAVIGATION_TEMPLATE: HEADER_NAVIGATION_TYPE[] = [
    { redirect: '#homePage', text: 'Home' },
    { redirect: '#aboutUs', text: 'About Us' },
    { redirect: '#ourStory', text: 'Our Story' },
    { redirect: '#gallery', text: 'Gallery' },
    { redirect: '#guestBook', text: 'Guest Book' }
];

// ------------------------------------------------------------------------------
//
// Home page template
//
// ------------------------------------------------------------------------------

export type HOMEPAGE_IMAGE_SRC_TYPE = {
    src: string,
    backgroundPositionOverride?: 'center' | 'bottom' | 'top' | 'left' | 'right',
    backgroundRepeatOverride?: 'no-repeat',
    backgroundSizeOverride?: 'auto' | 'contain' | 'cover'
};

const HOMEPAGE_IMAGE_SRC_TEMPLATE: HOMEPAGE_IMAGE_SRC_TYPE[] = [
    {
        src: '/gallery/GIN00021.jpg',
        backgroundPositionOverride: 'center',
        backgroundRepeatOverride: 'no-repeat'
    },
    {
        src: '/gallery/GIN09835.jpg',
        backgroundRepeatOverride: 'no-repeat'
    },
    {
        src: '/gallery/GIN02339.jpg',
        backgroundRepeatOverride: 'no-repeat'
    }
];

// ------------------------------------------------------------------------------
//
// About us template
//
// ------------------------------------------------------------------------------

export type Profile = {
    fullName: string
    dob: string,
    zodiac: string,
    slogan: string
}

const ABOUT_US_TEMPLATE: {
    description: string,
    menSrc: string,
    menSummary: Profile,
    womanSrc: string,
    womanSummary: Profile
} = {
    description: '',
    menSrc: 'https://i.etsystatic.com/9193132/r/il/e4b433/1777078144/il_794xN.1777078144_g0ow.jpg',
    menSummary: {
        fullName: 'Nguyễn Thành Trí',
        dob: '22-09',
        zodiac: 'Virgo',
        slogan: 'From this day forward, you\'ll never walk alone.'
    },
    womanSrc: 'https://i.etsystatic.com/9193132/r/il/082ee3/4089910818/il_1140xN.4089910818_3psa.jpg',
    womanSummary: {
        fullName: 'Chu Thị Hải Anh',
        dob: '26-08',
        zodiac: 'Virgo',
        slogan: 'When you realize that you want to spend your life with someone, you just want to start as soon as possible.'
    }
};

// ------------------------------------------------------------------------------
//
// Our story template
//
// ------------------------------------------------------------------------------

type OurStoryItemType = {
    imgSrc: string,
    date: Date,
    description: string,
    type: 'landscape' | 'portrait',
    backgroundPositionOverride?: 'center' | 'bottom' | 'top' | 'left' | 'right'
};

const OUR_STORY_TEMPLATE: OurStoryItemType[] = [
    {
        imgSrc: '/firstMet.jpg',
        date: moment('14/04/2023', VN_DATETIME_FORMAT).toDate(),
        description: 'First met</br>at Company Trip',
        type: 'portrait'
    },
    {
        imgSrc: '/firstTrip.JPG',
        date: moment('06/08/2023', VN_DATETIME_FORMAT).toDate(),
        description: 'Our first trip</br>Phu Yen city',
        type: 'portrait'
    },
    {
        imgSrc: '/HPNYTogether.JPG',
        date: moment('01/01/2024', VN_DATETIME_FORMAT).toDate(),
        description: 'Ring in the New Year together',
        type: 'landscape',
        backgroundPositionOverride: 'bottom'
    },
    {
        imgSrc: '/daNangTrip.JPG',
        date: moment('05/04/2024', VN_DATETIME_FORMAT).toDate(),
        description: 'Da Nang Trip',
        type: 'landscape',
        backgroundPositionOverride: 'left'
    },
    {
        imgSrc: '/engaged.jpeg',
        date: moment('11/05/2024', VN_DATETIME_FORMAT).toDate(),
        description: `and She said</br><span class='special'>YES</span>`,
        type: 'portrait',
        backgroundPositionOverride: 'bottom'
    }
];

// ------------------------------------------------------------------------------
//
// Gallery template
//
// ------------------------------------------------------------------------------

export type GALLERY_TYPE = {
    src: string,
    imgType: 'VERTICAL' | 'HORIZONTAL',
    objectFit?: 'contain' | 'cover' | 'fill' | 'none' | 'scale-down',
    objectPosition?: 'center' | 'bottom' | 'top' | 'left' | 'right',
    display?: true | false,
    animation?: string
}

const GALLERY_TEMPLATE: GALLERY_TYPE[] = [
    {
        src: '/gallery/GIN00021.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00031.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00131.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00311.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00365.jpg',
        imgType: 'HORIZONTAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00422.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00465.jpg',
        imgType: 'HORIZONTAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00515_1.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00658.jpg',
        imgType: 'HORIZONTAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00769.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00934.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN00994.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN01115.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN01463.jpg',
        imgType: 'HORIZONTAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN01750.jpg',
        imgType: 'HORIZONTAL',
        objectFit: 'cover',
        display: true
    },
    {
        src: '/gallery/GIN01982.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02059_1.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02093.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02311.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02339.jpg',
        imgType: 'HORIZONTAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02365.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02400.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02405.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02472.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02534.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02614.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02638.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN02684.jpg',
        imgType: 'VERTICAL',
        objectFit: 'cover'
    },
    {
        src: '/gallery/GIN09835.jpg',
        imgType: 'HORIZONTAL',
        objectFit: 'cover'
    }
];

export const wishesSuggest: { label: string }[] = [
    { label: 'Chúc mừng hạnh phúc! Chúc hai bạn trăm năm hạnh phúc!' },
    { label: 'Chúc mừng ngày trọng đại tới hai bạn. Hạnh phúc bền lâu và trọn vẹn nhé!' },
    { label: 'Chúc hai bạn ngày vui hạnh phúc. Hãy yêu thương nhau thật nhiều và sống thật hạnh phúc nha!' },
    { label: 'Thật vui vì cuối cùng ngày này cũng tới với bạn. Tôi thành tâm chúc hai bạn thật nhiều hạnh phúc và sống đời vui vẻ cùng nhau mãi mãi!' },
    { label: 'Một chương mới đã mở ra với hai bạn rồi. Tôi mong cuộc sống mới của cả hai sẽ tràn ngập hy vọng, hạnh phúc và niềm vui!' },
    { label: 'Chia vui cùng bạn trong ngày trọng đại này. Cầu mong cuộc sống sau này của 2 vợ chồng thật thuận hòa, may mắn, làm ăn phát tài nha.' },
    { label: 'Tôi mong tình yêu của hai bạn thật bền chặt, gắn bó để xây dựng tổ ấm yên bình, hạnh phúc!' },
    { label: 'Gửi lời chúc mừng chân thành nhất tới bạn của tôi. Chúc hai bạn một cuộc sống thật tuyệt vời, hòa thuận, gắn bó son sắt, thủy chung, hạnh phúc lâu dài.' },
    { label: 'Nơi nào có yêu thương nơi đó chắc chắn hạnh phúc. Hai bạn đã tìm được nơi đủ đầy yêu thương rồi, hãy cùng nắm tay nhau đi hết cuộc đời nhé!' },
    { label: 'Hai trở thành một: Một giường, một điều khiển từ xa, một phòng tắm! Xin chúc mừng đám cưới hai bạn.' },
    { label: 'Chúc đôi trai tài gái sắc nhà mình hạnh phúc vẹn tròn, cung hỷ cung hỷ!' },
    { label: 'Chúc anh/chị/em trăm năm hạnh phúc, thuận vợ thuận chồng.' },
    { label: 'Hôm nay chú rể đẹp trai, cô dâu xinh gái. Chúc mừng ngày thành hôn hai bạn tôi!' },
    { label: 'Chúc mừng đôi bạn trẻ nhé! Mãi yêu thương nhau đến đầu bạc răng long bạn nha!' }
];

export {
    DATETIME_FORMAT,
    HEADER_NAVIGATION_TEMPLATE,
    HOMEPAGE_IMAGE_SRC_TEMPLATE,
    ABOUT_US_TEMPLATE,
    OUR_STORY_TEMPLATE,
    GALLERY_TEMPLATE,
    ANIMATION_KEYS,
    VN_DATETIME_FORMAT,
    TIMELINE_SIZE,
    HTTP_HEADERS
};
