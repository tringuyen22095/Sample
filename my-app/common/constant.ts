import moment from 'moment';

const DATETIME_FORMAT = 'MMM DD, yyyy HH:mm';
const VN_DATETIME_FORMAT = 'DD/MM/yyyy';

const HEADER_NAVIGATION = [
    { redirect: '#homePage', text: 'Home' },
    { redirect: '#aboutUs', text: 'About Us' },
    { redirect: '#ourStory', text: 'Our Story' },
    { redirect: '#gallery', text: 'Gallery' },
    { redirect: '#guestbook', text: 'Guest Book' }
];

const OUR_STORY_TEMPLATE: OurStoryItemType[] = [
    {
        imgSrc: '/first-meet.jpg',
        date: moment('14/04/2023', VN_DATETIME_FORMAT).toDate(),
        description: 'First met</br>at Company Trip',
        type: 'portrait'
    },
    {
        imgSrc: '/introducedito-future-in-laws.JPG',
        date: moment('11/06/2023', VN_DATETIME_FORMAT).toDate(),
        description: 'Introduced to</br>future in-laws',
        type: 'portrait'
    },
    {
        imgSrc: '/first-trip.JPG',
        date: moment('06/08/2023', VN_DATETIME_FORMAT).toDate(),
        description: 'Our first trip</br>Phu Yen city',
        type: 'portrait'
    },
    {
        imgSrc: '/family-gathering.JPG',
        date: moment('17/09/2023', VN_DATETIME_FORMAT).toDate(),
        description: 'Big family gathering',
        type: 'landscape',
        backgroundPositionOverride: 'bottom'
    },
    {
        imgSrc: '/families-meeting.jpeg',
        date: moment('27/04/2024', VN_DATETIME_FORMAT).toDate(),
        description: 'Families meeting',
        type: 'landscape',
        backgroundPositionOverride: 'bottom'
    },
    {
        imgSrc: '/engaged.jpeg',
        date: moment('11/05/2024', VN_DATETIME_FORMAT).toDate(),
        description: `and She said</br><span class='special'>YES</span>`,
        type: 'portrait',
        backgroundPositionOverride: 'bottom'
    }
];

type OurStoryItemType = {
    imgSrc: string,
    date: Date,
    description: string,
    type: 'landscape' | 'portrait',
    backgroundPositionOverride?: 'center' | 'bottom' | 'top' | 'left' | 'right'
};

export {
    DATETIME_FORMAT,
    HEADER_NAVIGATION,
    OUR_STORY_TEMPLATE
};

