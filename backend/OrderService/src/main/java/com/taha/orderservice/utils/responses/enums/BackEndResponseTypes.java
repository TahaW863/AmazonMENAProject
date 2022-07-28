package com.taha.orderservice.utils.responses.enums;

import com.taha.orderservice.utils.responses.model.BackEndResponse;

public enum BackEndResponseTypes {
    SUCCESS("SUCCESS"),
    ERROR("ERROR");
    String message;
    BackEndResponseTypes(String message) {
        this.message = message;
    }
}
