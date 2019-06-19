package cn.ddzu.shop.controller;

import cn.ddzu.shop.enums.ResultCode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

class BaseController {

    static final int PAGE_SIZE = 10;
    static final Type LONG_LIST_TYPE = new TypeToken<List<Long>>() {
    }.getType();

    void finish(HttpServletResponse response, ResultCode resultCode) throws IOException {
        finish(response, resultCode, null);
    }

    void finish(HttpServletResponse response, ResultCode resultCode, JsonElement data) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("code", resultCode.getCode());
        json.addProperty("msg", resultCode.getMsg());
        json.addProperty("city", "上海");
        if (data != null) {
            json.add("data", data);
        }
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }
}
