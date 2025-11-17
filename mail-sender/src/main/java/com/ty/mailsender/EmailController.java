/*
 * EmailService.java
 * 
 *  Author: Sandeep Chavan
 */
package com.ty.mailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {


    private static final String UPI_BASE_URL = "upi://pay?";
    private static final String CURRENCY = "INR";
    private static final String PAYEE_VPA = "yourmerchantvpa@bankupi"; // Replace with your VPA
    private static final String PAYEE_NAME = "Your%20Merchant%20Name"; // URL encoded name

    @GetMapping("/api/generate-upi-link")
    public String generateUpiLink( @RequestParam("amount") double amount, @RequestParam("orderId") String orderId, @RequestParam(value = "note", required = false) String note) {
        // Use StringBuilder for efficient string concatenation
        StringBuilder linkBuilder = new StringBuilder(UPI_BASE_URL);

        // 1. Mandatory Parameters (pa, pn)
        linkBuilder.append("pa=").append(PAYEE_VPA)
                   .append("&pn=").append(PAYEE_NAME);

        // 2. Amount and Currency (am, cu)
        // Format the amount to two decimal places
        String formattedAmount = String.format("%.2f", amount);
        linkBuilder.append("&am=").append(formattedAmount)
                   .append("&cu=").append(CURRENCY);

        // 3. Transaction Reference (tr) - Crucial for tracking
        // This MUST be URL encoded, though simple alphanumeric IDs often don't need it.
        String encodedOrderId = URLEncoder.encode(orderId, StandardCharsets.UTF_8);
        linkBuilder.append("&tr=").append(encodedOrderId);

        // 4. Transaction Note (tn) - Optional, but good practice
        if (note != null && !note.isEmpty()) {
            String encodedNote = URLEncoder.encode(note, StandardCharsets.UTF_8);
            linkBuilder.append("&tn=").append(encodedNote);
        }

        return linkBuilder.toString();
    }



    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        emailService.sendEmail(to, subject, text);
        return "Email sent successfully!";
    }
}
