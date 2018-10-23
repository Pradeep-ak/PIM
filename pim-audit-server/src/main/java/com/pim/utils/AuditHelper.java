package com.pim.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by pkulkar4 on 8/11/18.
 */

@Component
public class AuditHelper {

    @Value("${com.audit.timezone:America/Chicago}")
    private String timeZoneStr;

    public Date getNowTimeWitoutMicroSec() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.MILLISECOND, 0);
        now.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
        return now.getTime();
    }
}
