package com.wisdomleaf.assignment.service;

import com.wisdomleaf.assignment.exception.InvalidTimeException;

public interface ClockService {

	String convert(final String time)throws InvalidTimeException;
}
