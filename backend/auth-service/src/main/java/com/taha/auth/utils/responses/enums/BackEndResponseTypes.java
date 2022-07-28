package com.taha.auth.utils.responses.enums;


public enum BackEndResponseTypes {
    SUCCESS("SUCCESS"),
    ERROR("ERROR");
    String message;
    BackEndResponseTypes(String message) {
        this.message = message;
    }
}
