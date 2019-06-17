package cn.ddzu.shop.controller;

import cn.ddzu.shop.entity.*;
import cn.ddzu.shop.enums.ResultCode;
import cn.ddzu.shop.helper.RequestHelper;
import cn.ddzu.shop.manager.LoginManager;
import cn.ddzu.shop.service.*;
import cn.ddzu.shop.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/db")
public class DBController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private DBService dbService;

    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("db-resetUser", helper);

        dbService.updateDB();

        finish(response, ResultCode.SUCCESS);
    }

    /**
     * 获取所有同步数据
     * <p>
     * username 用户名
     * password 密码
     */
    @RequestMapping("/getAll")
    public void syncAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("edit-getAll", helper);

        String username = helper.getString("username");
        String password = helper.getString("password");

        String tokenId = LoginManager.getInstance().login(userService, username, password);
        if (tokenId == null) {
            finish(response, ResultCode.ERROR_WRONG_PASSWORD);
            return;
        }

        Map<String, List> dataMap = dbService.getAll();
        JsonObject data = new Gson().toJsonTree(dataMap).getAsJsonObject();

        finish(response, ResultCode.SUCCESS, data);
    }

    @RequestMapping("/sync")
    public void sync(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        RequestHelper helper = new RequestHelper(request);
        Log.d("db-sync", helper);
        String city = helper.getString("city", "sh");

        StringBuilder s = new StringBuilder();
        String url = "http://47.96.165.78/" + city + "/db/getAll"
                + "?username=edwin&password=edwin";
        URL syncAll = new URL(url);
        URLConnection syncAllConn = syncAll.openConnection();
        InputStream is = syncAllConn.getInputStream();
        Reader r = new InputStreamReader(is, StandardCharsets.UTF_8);
        int c;
        while ((c = r.read()) > 0) {
            s.append((char) c);
        }

        Map<String, List> data = new HashMap<>();
        JsonObject json = new JsonParser().parse(s.toString()).getAsJsonObject().get("data").getAsJsonObject();
        data.put("area_range", get(json, "area_range", new TypeToken<List<AreaRange>>() {
        }));
        data.put("building", get(json, "building", new TypeToken<List<Building>>() {
        }));
        data.put("decoration", get(json, "decoration", new TypeToken<List<Decoration>>() {
        }));
        data.put("district", get(json, "district", new TypeToken<List<District>>() {
        }));
        data.put("metro", get(json, "metro", new TypeToken<List<Metro>>() {
        }));
        data.put("news", get(json, "news", new TypeToken<List<News>>() {
        }));
        data.put("news_tag", get(json, "news_tag", new TypeToken<List<NewsTag>>() {
        }));
        data.put("office", get(json, "office", new TypeToken<List<Office>>() {
        }));
        data.put("price_range", get(json, "price_range", new TypeToken<List<PriceRange>>() {
        }));
        data.put("type", get(json, "type", new TypeToken<List<Type>>() {
        }));
        data.put("user", get(json, "user", new TypeToken<List<User>>() {
        }));
        data.put("zone", get(json, "zone", new TypeToken<List<Zone>>() {
        }));
        data.put("note", get(json, "note", new TypeToken<List<Note>>() {
        }));

        dbService.setAll(data);

        finish(response, ResultCode.SUCCESS);
    }

    private <T> List<T> get(JsonObject json, String key, TypeToken<List<T>> typeToken) {
        return new Gson().fromJson(json.get(key), typeToken.getType());
    }
}
