package cn.ddzu.shop.util;

import java.text.DecimalFormat;

public class StringUtils {

    public static String priceFormat(Float price) {
        if (price > 10000) {
            price = price / 10000;
            return new DecimalFormat("#.#").format(price) + "万";
        }
        return price.toString();
    }
}
