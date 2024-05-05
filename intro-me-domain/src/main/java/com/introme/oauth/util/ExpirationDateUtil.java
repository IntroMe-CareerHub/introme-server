package com.introme.oauth.util;

import java.util.Date;

public class ExpirationDateUtil {
    public static Date getExpirationDate(long expirationPeriod) {
        Date now = new Date();

        return new Date(now.getTime() + expirationPeriod);
    }
}
