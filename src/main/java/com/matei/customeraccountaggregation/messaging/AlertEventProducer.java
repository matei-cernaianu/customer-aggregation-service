package com.matei.customeraccountaggregation.messaging;

import com.matei.customeraccountaggregation.dto.EmailAlert;
import com.matei.customeraccountaggregation.dto.SmsAlert;
import com.matei.customeraccountaggregation.entity.User;
import com.matei.customeraccountaggregation.enums.AlertType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AlertEventProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendAlert(User user, UUID accountId, Long sum, AlertType alertType) {
        if (alertType.equals(AlertType.SMS)) {
            jmsTemplate.convertAndSend("smsConsumer", buildSmsAlert(user, accountId, sum));
        } else {
            jmsTemplate.convertAndSend("emailConsumer", buildEmailAlert(user, accountId, sum));
        }
    }

    private SmsAlert buildSmsAlert(User user, UUID accountId, Long sum) {
        return SmsAlert.builder()
                .phoneNumber(user.getPhoneNumber())
                .name(user.getUserName())
                .sum(sum)
                .build();
    }

    private EmailAlert buildEmailAlert(User user, UUID accountId, Long sum) {
        return EmailAlert.builder()
                .name(user.getUserName())
                .email(user.getEmail())
                .sum(sum)
                .build();
    }
}
