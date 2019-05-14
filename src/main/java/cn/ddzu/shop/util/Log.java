package cn.ddzu.shop.util;

import com.sun.istack.internal.Nullable;

import java.util.Calendar;
import java.util.TimeZone;

public class Log {

    public static void d(String log) {
        d(null, log);
    }

    public static void d(@Nullable String tag, String log) {
        StringBuilder logBuilder = new StringBuilder("------");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-8:00"));
        logBuilder.append(calendar.get(Calendar.HOUR_OF_DAY)).append(":")
                .append(calendar.get(Calendar.MINUTE)).append(":")
                .append(calendar.get(Calendar.SECOND)).append(".")
                .append(calendar.get(Calendar.MILLISECOND)).append(" d/");
        if (tag == null || tag.isEmpty()) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement trace : stackTrace) {
                String className = trace.getClassName();
                className = className.substring(className.lastIndexOf(".") + 1);
                String methodName = trace.getMethodName();
                if (className.equals("StackTraceElement")) continue;
                if (className.equals("Thread")) continue;
                if (className.equals("Log")) continue;

                logBuilder.append(className).append(".")
                        .append(methodName).append("()");
                break;
            }
        } else {
            logBuilder.append(tag);
        }
        logBuilder.append(":").append(log);
        System.out.println(logBuilder.toString());
    }
}
