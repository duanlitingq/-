package com.yunchao.hsh.controller.common;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.utils.superdir.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * 图片上传到FastDFS
 * @Authher:Zhai Qing
 * @Date: 2018/12/29 14:14
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Controller
@RequestMapping(value = {"/page/admin/"})
public class FastUploadFile extends BaseController {

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    @RequestMapping("fastUploadFile")
    @ResponseBody
    public Result uploadFile(HttpServletRequest request, HttpServletResponse response) {
        log.info("接收的图片请求");
        MultipartHttpServletRequest Murequest = (MultipartHttpServletRequest) request;
        //得到文件map对象
        Map<String, MultipartFile> files = Murequest.getFileMap();
        // 检验图片格式
        boolean isLegal = false;
        StringBuilder sb = new StringBuilder();
        for (MultipartFile file : files.values()) {
            for (String type : IMAGE_TYPE) {
                if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                    isLegal = true;
                    break;
                }
            }
            if (isLegal) {
                try {
                    String originalFilename = file.getOriginalFilename();
                    String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

                    FastDFSClient client = new FastDFSClient("classpath:system.properties");
                    String fileImg = client.uploadFile(file.getBytes(), extName);
                    sb.append(fileImg).append(";");
                    return Result.ok(sb.toString());
                } catch (Exception e) {
                    log.error("图片上传失败");
                    return Result.build("图片上传失败");
                }
            }
        }
        return null;
    }

}
