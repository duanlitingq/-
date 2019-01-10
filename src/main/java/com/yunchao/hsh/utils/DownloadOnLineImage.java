package com.yunchao.hsh.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadOnLineImage {
    /**
     * @param args
     * @throws Exception
     * @Author: 隗鹏
     * @CreateDate: 2018/11/16 17:35
     * @Description: 下载网络上的图片
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
    //    downloadOnLineImage("http://ui.51bi.com/opt/siteimg/images/fanbei0923/Mid_07.jpg", "51bi.gif", "d:\\image\\");
    }

    public static void downloadOnLineImage(String urlString,String realPath) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf = new File(realPath);
        if (!sf.getParentFile().exists()) {
            sf.getParentFile().mkdirs();
        }
        OutputStream os = new FileOutputStream(realPath);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
}
