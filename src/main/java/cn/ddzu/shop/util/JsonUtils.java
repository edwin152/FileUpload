package cn.ddzu.shop.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static JsonArray strToStringArray(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        List<String> imgList = new Gson().fromJson(str, new TypeToken<List<String>>() {
        }.getType());
        if (imgList == null || imgList.isEmpty()) {
            return null;
        }
        return new Gson().toJsonTree(imgList).getAsJsonArray();
    }

    public static JsonArray strToMapArray(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        List<Map<String, String>> map = new Gson().fromJson(str, new TypeToken<List<Map<String, String>>>() {
        }.getType());
        if (map == null || map.isEmpty()) {
            return null;
        }
        return new Gson().toJsonTree(map).getAsJsonArray();
    }
}
