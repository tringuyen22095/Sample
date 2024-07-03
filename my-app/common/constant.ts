import moment from 'moment';

// ------------------------------------------------------------------------------
//
// Constant
//
// ------------------------------------------------------------------------------

const DATETIME_FORMAT = 'MMM DD, yyyy HH:mm';
const VN_DATETIME_FORMAT = 'DD/MM/yyyy';

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
            src: '/gallery/GIN00021.jpgs',
            backgroundPositionOverride: 'center',
            backgroundRepeatOverride: 'no-repeat'
        },
        {
            src: '/gallery/GIN09835.jpgs',
            backgroundRepeatOverride: 'no-repeat'
        },
        {
            src: '/gallery/GIN02339.jpgs',
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
    description: 'From this day forward, you\'ll never walk alone.',
    menSrc: 'https://i.etsystatic.com/9193132/r/il/e4b433/1777078144/il_794xN.1777078144_g0ow.jpg',
    menSummary: {
        fullName: 'Nguyễn Thành Trí',
        dob: '22-09',
        zodiac: 'Virgo',
        slogan: 'slogan'
    },
    womanSrc: 'https://i.etsystatic.com/9193132/r/il/082ee3/4089910818/il_1140xN.4089910818_3psa.jpg',
    womanSummary: {
        fullName: 'Chu Thị Hải Anh',
        dob: '26-08',
        zodiac: 'Virgo',
        slogan: 'slogan'
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
        imgSrc: '/first-meet.jpg',
        date: moment('14/04/2023', VN_DATETIME_FORMAT).toDate(),
        description: 'First met</br>at Company Trip',
        type: 'portrait'
    },
    {
        imgSrc: '/first-trip.JPG',
        date: moment('06/08/2023', VN_DATETIME_FORMAT).toDate(),
        description: 'Our first trip</br>Phu Yen city',
        type: 'portrait'
    },
    {
        imgSrc: '/HPNY-together.JPG',
        date: moment('01/01/2024', VN_DATETIME_FORMAT).toDate(),
        description: 'Ring in the New Year together',
        type: 'landscape',
        backgroundPositionOverride: 'bottom'
    },
    {
        imgSrc: '/da-nang-trip.JPG',
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

type GALLERY_TYPE = {
    src: string,
    gridColumn?: string,
    gridRow?: string,
    imgType: 'VERTICAL' | 'HORIZONTAL'
}

const GALLERY_TEMPLATE: GALLERY_TYPE[] = [
    {
        src: '/gallery/GIN00021.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN00031.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN00131.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN00311.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN00365.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN00422.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN00465.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN00515_1.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN00658.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN00731_1.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN00769.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN00845.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN00934.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN00962.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN00994.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN01115.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN01463.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN01750.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN01822.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN01842_1.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN01982.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02025.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02059_1.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02093.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02110.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02311.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02324.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN02339.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN02365.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02400.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02405.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02409.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02454.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02472.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02534.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02614.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02624.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02638.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN02684.jpg',
        imgType: 'VERTICAL'
    },
    {
        src: '/gallery/GIN09835.jpg',
        imgType: 'HORIZONTAL'
    },
    {
        src: '/gallery/GIN09892.jpg',
        imgType: 'HORIZONTAL'
    },
];

export {
    DATETIME_FORMAT,
    HEADER_NAVIGATION_TEMPLATE,
    HOMEPAGE_IMAGE_SRC_TEMPLATE,
    ABOUT_US_TEMPLATE,
    OUR_STORY_TEMPLATE,
    GALLERY_TEMPLATE
};
