package com.ty.firebasemobileotp;


import com.google.common.collect.ImmutableMap;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YourService {

    private final FirebaseApp firebaseApp;
    private final FirebaseAuth firebaseAuth;

    @Autowired
    public YourService(FirebaseApp firebaseApp) {
        this.firebaseApp = firebaseApp;
        this.firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
    }

    // Your other service logic using Firebase

    public String generateOtp(String phoneNumber) {
        try {
            // Generate a 6-digit OTP
            String otp = String.format("%06d", (int) (Math.random() * 1000000));

            // Create or update user with the provided phone number
            UserRecord userRecord = getUserByPhoneNumber(phoneNumber);
            if (userRecord == null) {
                userRecord = createUser(phoneNumber);
            }

            // Set the custom attribute to store the OTP
            firebaseAuth.setCustomUserClaims(userRecord.getUid(), ImmutableMap.of("otp", otp));

            return otp;
        } catch (FirebaseAuthException e) {
            // Handle FirebaseAuthException appropriately
            e.printStackTrace();
            return null;
        }
    }

    private UserRecord createUser(String phoneNumber) throws FirebaseAuthException {
        return firebaseAuth.createUser(
                new UserRecord.CreateRequest()
                        .setPhoneNumber(phoneNumber)
                        .setEmailVerified(false)  // Set emailVerified to false for phone number verification
        );
    }

    private UserRecord getUserByPhoneNumber(String phoneNumber) throws FirebaseAuthException {
        try {
            return firebaseAuth.getUserByPhoneNumber(phoneNumber);
        } catch (FirebaseAuthException e) {
            // User not found, return null
            return null;
        }
    }
}
