package com.calsoft.notification.service;

import com.calsoft.notification.vo.NotificationVO;

public interface NotificationService {

    public boolean invoke(NotificationVO notificationVO);
}
