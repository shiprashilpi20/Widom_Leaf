package com.wisdomleaf.assignment.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.wisdomleaf.assignment.exception.InvalidTimeException;

@Service
public class ClockServiceImpl implements ClockService {

	private static final String[] UNITS_ARRAY = { "zero", "one", "two", "three", "four", "five", "six", "seven",
			"eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
			"eighteen", "nineteen" };
	private static final String[] TENS_ARRAY = { "", "", "twenty", "thirty", "forty", "fifty" };

	@Override
	public String convert(final String time) throws InvalidTimeException {
		this.validate(time);

		StringBuilder sb = new StringBuilder("It's ");

		if (time.equals("00:00")) {
			sb.append("Midnight");
		} else if (time.equals("12:00")) {
			sb.append("Midday");
		} else {
			String[] timeSplit = time.split(":");

			Integer hours = Integer.parseInt(timeSplit[0]);
			String hoursStr = this.convertInt(hours);
			sb.append(hoursStr + " ");

			Integer minutes = Integer.parseInt(timeSplit[1]);
			String minutesStr = this.convertInt(minutes);
			sb.append(minutesStr);
		}
		return sb.toString();
	}
	
	private String convertInt(final Integer number) {
		if (number < 20)
			return UNITS_ARRAY[number];
		return TENS_ARRAY[number / 10] + ((number % 10 > 0) ? " " + convertInt(number % 10) : "");
	}
	
	@SuppressWarnings("deprecation")
	private void validate(final String time) throws InvalidTimeException {

		if (StringUtils.isBlank(time)) {
			throw new InvalidTimeException("Time is blank");
		}

		if (time.length() != 5 || !time.contains(":")) {
			throw new InvalidTimeException("Invalid Time format, should be HH:mm");
		}

		String[] timeSplit = time.split(":");

		Integer hours = new Integer(0);
		try {
			hours = Integer.parseInt(timeSplit[0]);
		} catch (NumberFormatException e) {
			throw new InvalidTimeException("Invalid Hours");
		}
		if (hours < 0 || hours > 23) {
			throw new InvalidTimeException("Invalid Hours");
		}

		Integer minutes = new Integer(0);
		try {
			minutes = Integer.parseInt(timeSplit[1]);
		} catch (NumberFormatException e) {
			throw new InvalidTimeException("Invalid Minutes");
		}

		if (minutes < 0 || minutes > 59) {
			throw new InvalidTimeException("Invalid Minutes");
		}
	}
}
