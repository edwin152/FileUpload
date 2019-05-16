package cn.ddzu.shop.helper;

import com.google.gson.Gson;
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
        return (String) get(key, defaultValue, new TypeToken<String>() {
        }.getType());
    }

    public Integer getInt(String key) {
        return getInt(key, null);
    }

    public Integer getInt(String key, Integer defaultValue) {
        return (Integer) get(key, defaultValue, new TypeToken<Integer>() {
        }.getType());
    }

    public Long getLong(String key) {
        return getLong(key, null);
    }

    public Long getLong(String key, Long defaultValue) {
        return (Long) get(key, defaultValue, new TypeToken<Long>() {
        }.getType());
    }

    public Float getFloat(String key) {
        return getFloat(key, null);
    }

    public Float getFloat(String key, Float defaultValue) {
        return (Float) get(key, defaultValue, new TypeToken<Float>() {
        }.getType());
    }

    public Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return (Boolean) get(key, defaultValue, new TypeToken<Boolean>() {
        }.getType());
    }

    public <T> List<T> getList(String key) {
        return getList(key, new ArrayList<>());
    }

    public <T> List<T> getList(String key, Type type) {
        return getList(key, new ArrayList<>(), type);
    }

    public <T> List<T> getList(String key, List<T> defaultValue) {
        return getList(key, defaultValue, new TypeToken<List<T>>() {
        }.getType());
    }

    public <T> List<T> getList(String key, List<T> defaultValue, Type type) {
        //noinspection unchecked
        return (List<T>) get(key, defaultValue, type);
    }

    private Object get(String key, Object defaultValue, Type type) {
        String str = request.getParameter(key);
        if (str == null || str.isEmpty()) {
            return defaultValue;
        }
        String typeName = type.getTypeName();
        Class clz;
        try {
            clz = Class.forName(typeName);
        } catch (ClassNotFoundException ignore) {
            clz = null;
        }
        try {
            if (String.class.equals(clz)) {
                return str;
            } else if (Long.class.equals(clz)) {
                return Long.parseLong(str);
            } else if (Integer.class.equals(clz)) {
                return Integer.parseInt(str);
            } else if (Boolean.class.equals(clz)) {
                return Boolean.valueOf(str);
            } else if (Float.class.equals(clz)) {
                return Float.valueOf(str);
            } else {
                return new Gson().fromJson(str, type);
            }
        } catch (RuntimeException ignore) {
            return defaultValue;
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(request.getParameterMap());
    }
}
