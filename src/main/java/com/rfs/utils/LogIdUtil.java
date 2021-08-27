package com.rfs.utils;

import org.slf4j.MDC;

import java.util.Random;

/**
 * @author zhaochao53
 */
public class LogIdUtil {
    public static final String LOG_ID = "logId";
    public static String generate() {
        int len = 8;
        StringBuffer id = new StringBuffer(len);
        Random random = new Random();

        for (int i = 0; i < len; ++i) {
            char s = 0;
            int j = random.nextInt(3) % 4;
            switch (j) {
                case 0:
                    s = (char) (random.nextInt(57) % 10 + 48);
                    break;
                case 1:
                    s = (char) (random.nextInt(90) % 26 + 65);
                    break;
                default:
                    s = (char) (random.nextInt(122) % 26 + 97);
            }

            id.append(s);
        }

        return id.toString();
    }

    public static String setCurrentLogId(String logId) {
        if (logId == null || "".equals(logId)) {
            logId = generate();
        }
        MDC.put(LOG_ID, logId);
        return logId;
    }

    public static String getCurrentLogId() {
        return MDC.get(LOG_ID);
    }
    public static void clear() {
        MDC.clear();
    }
}
