
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
    years,
    months,
    days,
    hours,
    minutes,
    seconds: secs,
  };
}

export function formatTimeLeft(seconds: number): string {
  const different = calculateTimeLeft(seconds);
  const result: string[] = [];

  for (const key in different) {
    const value: number = different[key];
    if (!!value)
      result.push(`${value} ${TimeUnit[key]}`);
  }

  if (!result.length) {
    result.push(`0 ${TimeUnit.seconds}`);
  }

  return result.join(' ');
}