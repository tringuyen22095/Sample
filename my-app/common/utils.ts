'use client'

enum TimeUnit {
  years = "năm",
  months = "tháng",
  days = "ngày",
  hours = "giờ",
  minutes = "phút",
  seconds = "giây"
}

function calculateTimeLeft(seconds: number): {
  years: number,
  months: number,
  days: number,
  hours: number,
  minutes: number,
  seconds: number
} {
  const years = Math.floor(seconds / (365 * 24 * 60 * 60));
  seconds %= 365 * 24 * 60 * 60;
  const months = Math.floor(seconds / (30 * 24 * 60 * 60));
  seconds %= 30 * 24 * 60 * 60;
  const days = Math.floor(seconds / (24 * 60 * 60));
  seconds %= 24 * 60 * 60;
  const hours = Math.floor(seconds / 3600);
  seconds %= 3600;
  const minutes = Math.floor(seconds / 60);
  const secs = seconds % 60;

  return {
    years: years !== 0 ? years : null,
    months,
    days,
    hours,
    minutes,
    seconds: secs
  };
}

function formatTimeLeft(seconds: number): string {
  const different = calculateTimeLeft(seconds);
  const result: string[] = [];

  for (const key in different) {
    const value: number = different[key];
    if (value !== null)
      result.push(`${(value + '').padStart(2, '0')} ${TimeUnit[key]}`);
  }

  return result.join(' ');
}

export {
  formatTimeLeft
}