package cn.ddzu.shop.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class BaseController {

    static final int PAGE_SIZE = 10;
    static final Message SUCCESS_MESSAGE = new Message(1, "success");

    void finish(HttpServletResponse response, Message message) throws IOException {
        finish(response, message, null);
    }

    void finish(HttpServletResponse response, Message message, JsonElement data) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("code", message.code);
        json.addProperty("msg", message.msg);
        if (data != null) {
            json.add("data", data);
        }
        response.getWriter().write(json.toString());
        response.getWriter().close();
    }

    static class Message {
        private int code;
        private String msg;

        Message(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
