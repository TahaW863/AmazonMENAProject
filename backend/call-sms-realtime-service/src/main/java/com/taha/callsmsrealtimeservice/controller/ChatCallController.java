package com.taha.callsmsrealtimeservice.controller;

import com.taha.callsmsrealtimeservice.interfaces.CallCustomer;
import com.taha.callsmsrealtimeservice.interfaces.SmsService;
import com.taha.callsmsrealtimeservice.model.SmsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/twilio")
@Slf4j
@RequiredArgsConstructor
public class ChatCallController {
    private final SmsService smsService;
    private final CallCustomer callCustomer;
    @PostMapping("/sms")
    public ResponseEntity<String> sendSms(@RequestBody SmsRequest smsRequest) {
        try{
            smsService.sendSms(smsRequest);
            return ResponseEntity.ok("Sms sent successfully");
        } catch (Exception e) {
            log.error("Error sending sms: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/call")
    public ResponseEntity<String> callCustomer(@RequestBody SmsRequest smsRequest) {
        try{
            callCustomer.callCustomer(smsRequest.getToNumber(), smsRequest.getMessage());
            return ResponseEntity.ok("Call sent successfully");
        } catch (Exception e) {
            log.error("Error sending call: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
