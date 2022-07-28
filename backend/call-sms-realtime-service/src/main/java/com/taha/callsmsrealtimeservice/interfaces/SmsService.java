package com.taha.callsmsrealtimeservice.interfaces;

import com.taha.callsmsrealtimeservice.model.SmsRequest;

public interface SmsService {
    void sendSms(SmsRequest smsRequest);
}
