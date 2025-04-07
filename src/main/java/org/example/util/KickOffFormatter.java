package org.example.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class KickOffFormatter {

    public static String getDateAndTimeFromKickOff(long kickOff) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'UTC'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(kickOff);
    }
}
