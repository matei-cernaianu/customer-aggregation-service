package com.matei.customeraccountaggregation.util;


import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public final class NotificationUtil {

    private static final String SMS_MESSAGE = "\n\n SMS\n\t Hello {0}, new transaction has been registered for total of {1} .\n\n";
    private static final String EMAIL_MESSAGE = "\n\n EMAIL\n\t to: {0} \n\t Subject: New transaction \n\t Body: Hello {1}, new transaction has been registered for total of {2} .\n\n";

    public static void prettyPrintEmail(String name, String email, Long sum) {
        log.info(MessageFormat.format(EMAIL_MESSAGE, email, name.toLowerCase(), sum.toString()));
    }

    public static void prettyPrintSms(String name, Long sum) {
        log.info(MessageFormat.format(SMS_MESSAGE, name, sum.toString()));
    }
}
