package com.matei.customeraccountaggregation.messaging;

import com.matei.customeraccountaggregation.dto.EmailAlert;
import com.matei.customeraccountaggregation.util.NotificationUtil;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @JmsListener(containerFactory = "containerFactory",
            destination = "emailConsumer")
    public void alertSMS(EmailAlert emailAlert) {
        NotificationUtil.prettyPrintEmail(emailAlert.getName(), emailAlert.getEmail(), emailAlert.getSum());
    }
}
