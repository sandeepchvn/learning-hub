/*
 * OtpController.java
 * 
 * Author: Sandeep Chavan
 */
package com.ty.mobileotp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@RestController
@RequestMapping("/api")
public class OtpController {

	// Twilio credentials
	@Value("${twilio.accountSid}")
	private String accountSid;

	@Value("${twilio.authToken}")
	private String authToken;

	@Value("${twilio.phoneNumber}")
	private String twilioPhoneNumber;

	@PostMapping("/generate-otp")
	public String generateOtp(@RequestParam String mobileNumber) {
		// Generate a random 6-digit OTP
		String otp = generateRandomOtp();

		// Send the OTP via SMS using Twilio
		sendOtpViaSms(mobileNumber, otp);

		// In a real application, you would typically store this OTP in a database or
		// cache
		// along with the mobile number to verify later.

		// For simplicity, we'll just print it here.
		System.out.println("Generated OTP for " + mobileNumber + ": " + otp);

		// Return the generated OTP
		return otp;
	}

	private String generateRandomOtp() {
		// Generate a random 6-digit OTP
		int otpValue = 100_000 + (int) (Math.random() * 900_000); // Generates a number between 100000 and 999999
		return String.valueOf(otpValue);
	}

	private void sendOtpViaSms(String mobileNumber, String otp) {
		// Initialize Twilio
		Twilio.init(accountSid, authToken);

		// Send SMS using Twilio
		Message message = Message.creator(new com.twilio.type.PhoneNumber("+91" + mobileNumber), // Assuming you're in
																								// the INDIA; update the
																								// country code
																								// accordingly
				new com.twilio.type.PhoneNumber(twilioPhoneNumber), "Your OTP is: " + otp).create();

		System.out.println("SMS sent: " + message.getSid());
	}
}
