package com.taha.callsmsrealtimeservice.service;

import com.taha.callsmsrealtimeservice.config.SmsCallConfig;
import com.taha.callsmsrealtimeservice.config.TwilioConfig;
import com.taha.callsmsrealtimeservice.interfaces.CallCustomer;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.Twiml;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.twilio.type.PhoneNumber;

@Service
@RequiredArgsConstructor
@Slf4j
public class CallTwilio implements CallCustomer {
    private final SmsCallConfig smsCallConfig;
    @Override
    public void callCustomer(String phoneNumber, String message) {
        log.info("Call customer with phone number: {} and message: {}", phoneNumber, message);
        Call call = Call.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(smsCallConfig.getTWILIO_NUMBER()),
                new Twiml(
                        "<Response>"
                                + "<Say>Hello, this is a call from Twilio</Say>"
                                + "<Say>You have a message from: " + message + "</Say>"
                                + "<Say>Goodbye</Say>"
                        + "</Response>"
                )
        ).create();
    }
}
