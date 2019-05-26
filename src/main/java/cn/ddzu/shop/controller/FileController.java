package cn.ddzu.shop.controller;

import cn.ddzu.shop.config.Config;
import cn.ddzu.shop.enums.ResultCode;
import com.google.gson.JsonObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    @RequestMapping("/upload")
    public void upload(@RequestParam(name = "uploadImage", required = false) MultipartFile pic, HttpServletResponse response) throws IOException {
        //图片名称生成策略
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
        StringBuilder nameBuilder = new StringBuilder(dateFormat.format(new Date()));
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            nameBuilder.append(random.nextInt(10));
        }
        //获取文件扩展名的专用方法
        String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
        nameBuilder.append(".").append(extension);
        String name = nameBuilder.toString();

        File dir = new File(Config.SAVE_FILE_PATH);
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }

        OutputStream oStream = null;
        ResultCode resultCode;
        JsonObject json;

        try {
            File file = new File(dir, name);
            oStream = new FileOutputStream(file);
            oStream.write(pic.getBytes());
            oStream.flush();
            Runtime runtime = Runtime.getRuntime();
            String command = "chmod 777 " + file.getAbsolutePath();
            Process process = runtime.exec(command);
            process.waitFor();

            resultCode = ResultCode.SUCCESS;
            json = new JsonObject();
            json.addProperty("src", Config.HOST + Config.IMAGE_PATH + "/" + name);
        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
            resultCode = ResultCode.ERROE_UPLOAD_FAILED;
            json = null;
        } finally {
            if (oStream != null) {
                try {
                    oStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        finish(response, resultCode, json);
    }
}
