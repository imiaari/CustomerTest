package com.exam.mobile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MobileInfoDTO {
	private String countryCode;
	private String countryName;
	private String operatorName;

	
}