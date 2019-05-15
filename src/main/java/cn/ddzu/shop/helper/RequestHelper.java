package cn.ddzu.shop.helper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class RequestHelper {

    private HttpServletRequest request;

    public RequestHelper(HttpServletRequest request) {
        this.request = request;
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        String str = request.getParameter(key);
        if (str == null || str.isEmpty()) {
            return defaultValue;
        }
        return str;
    }

    public Integer getInt(String key) {
        return getInt(key, null);
    }

    public Integer getInt(String key, Integer defaultValue) {
        String str = request.getParameter(key);
        if (str == null || str.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ignore) {
            return defaultValue;
        }
    }

    public Long getLong(String key) {
        return getLong(key, null);
    }

    public Long getLong(String key, Long defaultValue) {
        String str = request.getParameter(key);
        if (str == null || str.isEmpty()) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException ignore) {
            return defaultValue;
        }
    }

    public Float getFloat(String key) {
        return getFloat(key, null);
    }

    public Float getFloat(String key, Float defaultValue) {
        String str = request.getParameter(key);
        if (str == null || str.isEmpty()) {
            return defaultValue;
        }
        try {
            return Float.valueOf(str);
        } catch (NumberFormatException ignore) {
            return defaultValue;
        }
    }

    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        String str = request.getParameter(key);
        if (str == null || str.isEmpty()) {
            return defaultValue;
        }
        try {
            return Boolean.valueOf(str);
        } catch (NumberFormatException ignore) {
            return defaultValue;
        }
    }

    public List<Long> getLongList(String key) {
        return getLongList(key, new ArrayList<Long>());
    }

    public List<Long> getLongList(String key, List<Long> defaultValue) {
        String str = request.getParameter(key);
        if (str == null || str.isEmpty()) {
            return defaultValue;
        }
        try {
            Type type = new TypeToken<List<Long>>() {
            }.getType();
            return new Gson().fromJson(str, type);
        } catch (JsonSyntaxException ignore) {
            return defaultValue;
        }
    }

    public <T> List<T> getList(String key) {
        return getList(key, new ArrayList<T>());
    }

    public <T> List<T> getList(String key, List<T> defaultValue) {
        String str = request.getParameter(key);
        if (str == null || str.isEmpty()) {
            return defaultValue;
        }
        try {
            Type type = new TypeToken<List<T>>() {
            }.getType();
            return new Gson().fromJson(str, type);
        } catch (JsonSyntaxException ignore) {
            return defaultValue;
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(request.getParameterMap());
    }
}
