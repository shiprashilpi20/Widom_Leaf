package com.wisdomleaf.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wisdomleaf.assignment.exception.InvalidTimeException;
import com.wisdomleaf.assignment.service.ClockService;

@RestController
public class SpeakingClockController {

	@Autowired
	private ClockService service;
	
	@GetMapping("/getTimeInText")
	public ResponseEntity<String> convert(@RequestParam String time){
		try {
			String result = service.convert(time);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}catch(InvalidTimeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
