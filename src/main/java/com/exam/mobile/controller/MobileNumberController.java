package com.exam.mobile.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.mobile.MobileNumberService;
import com.exam.mobile.dto.MobileInfoDTO;
import com.exam.mobile.exception.MobileValidationException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mobile-operations")
public class MobileNumberController {

	private final MobileNumberService mobileNumberService;

	@GetMapping
	public ResponseEntity<MobileInfoDTO> getMobileNumberDetails(@RequestParam("mobileNumber") String mobileNumber)
			throws MobileValidationException {

		String decodedMobileNumber = URLDecoder.decode(mobileNumber, StandardCharsets.UTF_8);

		decodedMobileNumber = decodedMobileNumber.replace("%2B", "+");

		MobileInfoDTO details = mobileNumberService.getMobileNumberDetails(decodedMobileNumber);
		return ResponseEntity.ok(details);

	}
}
