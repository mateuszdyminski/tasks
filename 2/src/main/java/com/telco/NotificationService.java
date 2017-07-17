package com.telco;

public class NotificationService {

    private static NotificationService notificationService;

    public static NotificationService getInstance() {
        if (notificationService == null) {
            notificationService = new NotificationService();
        }

        return notificationService;
    }

    public void sendEmail(String to, String subject, String body) {
        // send email here
    }
}
