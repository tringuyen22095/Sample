'use server'

import './style.scss';

type Props = {
    snowElementCount?: number
}

const generateSnowElement = (props: Props) => {
    const snowCount = props.snowElementCount ?? Math.floor(Math.random() * 12);
    return Array.from({ length: snowCount })
        .map((v, i) => <div key={`snowEle-${i}}`} className="snow"></div>);
};

const SnowFallGenerate = (props: Props) => (
    <div className='snowfall-background'>
        {generateSnowElement(props)}
    </div>
);

export default SnowFallGenerate;