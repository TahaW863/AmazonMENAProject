package com.taha.callsmsrealtimeservice.config;

import com.twilio.Twilio;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {
    public TwilioConfig(SmsCallConfig smsCallConfig) {
        Twilio.init(smsCallConfig.getACCOUNT_SID(), smsCallConfig.getAUTH_TOKEN());
    }
}
