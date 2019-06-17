package cn.ddzu.shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    private static Logger logger = LoggerFactory.getLogger("");

    public static void d(Object log) {
        d(null, log);
    }

    public static void d(String tag, Object log) {
        logger.debug(tag, log);
    }
}
