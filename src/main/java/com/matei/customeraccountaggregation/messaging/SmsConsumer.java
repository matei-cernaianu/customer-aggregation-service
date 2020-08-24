package com.matei.customeraccountaggregation.messaging;

import com.matei.customeraccountaggregation.dto.SmsAlert;
import com.matei.customeraccountaggregation.util.NotificationUtil;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SmsConsumer {

    @JmsListener(containerFactory = "containerFactory",
            destination = "smsConsumer")
    public void alertSMS(SmsAlert smsAlert) {
        NotificationUtil.prettyPrintSms(smsAlert.getName(), smsAlert.getSum());
    }
}
