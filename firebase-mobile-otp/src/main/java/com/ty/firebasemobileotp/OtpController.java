package com.ty.firebasemobileotp;



import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OtpController {

    private final YourService yourService;

    @Autowired
    public OtpController(YourService yourService) {
        this.yourService = yourService;
    }

    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestParam String phoneNumber) {
        try {
            String otp = yourService.generateOtp(phoneNumber);
            if (otp != null) {
                return new ResponseEntity<>(otp, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to generate OTP", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();  // Print the exception for debugging purposes
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

