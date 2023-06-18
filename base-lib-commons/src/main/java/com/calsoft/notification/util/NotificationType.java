package com.calsoft.notification.util;

public enum NotificationType {
    EMAIL("EMAIL"),
    OTP("OTP");

    private String typeDescrption;

    NotificationType(String typeDescrption){
        this.typeDescrption=typeDescrption;
    }

}
