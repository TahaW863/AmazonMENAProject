package com.taha.callsmsrealtimeservice.service;

import com.taha.callsmsrealtimeservice.config.SmsCallConfig;
import com.taha.callsmsrealtimeservice.interfaces.SmsService;
import com.taha.callsmsrealtimeservice.model.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsTwilio implements SmsService {
    private final SmsCallConfig smsCallConfig;
    @Override
    public void sendSms(SmsRequest smsRequest) {
        Message.creator(
                new PhoneNumber(smsRequest.getToNumber()),
                new PhoneNumber(smsCallConfig.getTWILIO_NUMBER()),
                smsRequest.getMessage()
        ).create();
    }
}
