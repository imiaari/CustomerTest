package com.exam.mobile;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.exam.mobile.dto.MobileInfoDTO;
import com.exam.mobile.exception.MobileValidationException;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

@Service
public class MobileNumberService {

	public MobileInfoDTO getMobileNumberDetails(String mobileNumber) throws MobileValidationException {
		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
		try {
			Phonenumber.PhoneNumber parsedNumber = phoneNumberUtil.parse(mobileNumber, "");
			if (phoneNumberUtil.isValidNumber(parsedNumber)) {
				String countryCode = String.valueOf(parsedNumber.getCountryCode());
				String operatorName = phoneNumberUtil.getRegionCodeForNumber(parsedNumber);
				Locale localCountry = new Locale("", operatorName);
				String countryName = localCountry.getDisplayCountry();
				return new MobileInfoDTO(countryCode, countryName, operatorName);
			} else
				throw new MobileValidationException("Invalid Phone number");

		} catch (NumberParseException e) {
			throw new MobileValidationException("Erorr while parsing phone number" , e);
		}

	}
}
