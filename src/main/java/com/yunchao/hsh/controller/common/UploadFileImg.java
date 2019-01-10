package com.yunchao.hsh.controller.common;

import bsh.commands.dir;
import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.superdir.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 图片上传到upload
 * @ClassName: UploadFileImg
 * @Description: TODO
 * @Author: ZHAI Q
 * @Email:hkwind959@google.com
 * @Date: 2018/11/9 15:09
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = {"/page/admin/"})
public class UploadFileImg extends BaseController {

    @RequestMapping("upload")
    @ResponseBody
    public Result uploadFile(HttpServletRequest request, HttpServletResponse response) {
        FileOutputStream out = null;
        log.info("接收的图片请求");
        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) request;
        //得到文件map对象
        Map<String, MultipartFile> files = Murequest.getFileMap();
        //文件上传路径
        String uploadDir = request.getSession().getServletContext().getRealPath("upload");
        Result result;
        File dir = new File(uploadDir);
        if (dir.exists()) {
            //存在
            result = uploadImg(files, out, uploadDir);
        } else {
            //不存在
            dir.mkdirs();
            result = uploadImg(files, out, uploadDir);
        }
        return result;
    }

    private Result uploadImg(Map<String, MultipartFile> map, FileOutputStream out, String uploadDir) {
        StringBuilder sb = new StringBuilder();
        for (MultipartFile file : map.values()) {
            //获取文件名
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.indexOf("."));
            //保存文件的名称
            String saveFileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + File.separator + CommonUtil.$getRandom(8, 4) + suffix;
            File targetFile = new File(uploadDir + File.separator + saveFileName);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdir();
            }
            try {
                byte[] bytes = file.getBytes();
                out = new FileOutputStream(targetFile);
                out.write(bytes);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //返回图片路径
            String retPath = File.separator + "upload" + File.separator + saveFileName;
            sb.append(retPath + ";");
        }
        log.info("图片返回路径{}", sb.toString());
        return Result.ok(sb.toString());
    }
}
