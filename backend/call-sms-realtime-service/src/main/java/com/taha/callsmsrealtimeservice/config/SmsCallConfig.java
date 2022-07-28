package com.taha.callsmsrealtimeservice.config;

import com.twilio.Twilio;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class SmsCallConfig {
    @Value("${twilio.account_sid}")
    private String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    @Value("${twilio.auth_token}")
    private String AUTH_TOKEN = "your_auth_token";
    @Value("${twilio.twilio_number}")
    private String TWILIO_NUMBER = "+15555555555";
}
