import React, { useState, useEffect } from 'react';
import "./slider.scss";

const images = [
  '313867300_2383766001771143_2444263358168521620_n.jpg',
  '316669365_724633742357034_5863068706667161352_n.jpg',
  '386651082_2524980611004258_4861727356813546910_n.jpg',
];

const Slider: React.FC = () => {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) =>
        prevIndex === images.length - 1 ? 0 : prevIndex + 1
      );
    }, 5000);

    return () => {
      clearInterval(interval);
    };
  }, []);

  return (
    <div className='slider'>
      <div className='walkthrough show reveal'>
        <div className='walkthrough-body'>
          <ul className='screens animate'>
            <li className='screen active'>
            </li>
            <li className='screen'>
            </li>
            <li className='screen'>
            </li>
            <li className='screen'>
            </li>
            <li className='screen'>
            </li>
          </ul>
        </div>
        <div className='walkthrough-pagination'>
          <a className='dot active'></a>
          <a className='dot'></a>
          <a className='dot'></a>
          <a className='dot'></a>
          <a className='dot'></a>
        </div>
      </div>
    </div>
  );
};

export default Slider;
