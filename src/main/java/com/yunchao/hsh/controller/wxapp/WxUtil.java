package com.yunchao.hsh.controller.wxapp;

import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.utils.*;
import com.yunchao.hsh.utils.redis.RedisUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 付鹏 on 2018/11/15.
 */
@Controller
@RequestMapping("/")
public class WxUtil {
    ///获取token地址 GET请求
    private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?";
    //获取微信小程序二维码  用于邀请
    private static final String GET_WXAPP_CODE_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
    private static final String APPID = SystemConfig.instants().getValue("wx_appId");
    private static final String SECRET = SystemConfig.instants().getValue("wx_appSecret");

    static Logger logger = Logger.getLogger("WxUtil.class");
    /**
     * 获取小程序accessToken凭证
     * @return accessToken
     */
    public static String getAccessToken(){
        try {
            RedisUtil redisUtil = RedisUtil.getInstance();
            String accessToken = (String) redisUtil.getFromRedis("wxapp_access_token");
            System.out.println("redis ====== " + accessToken);
            if(StringUtils.isBlank(accessToken)){
                String getTokenUrl = GET_ACCESS_TOKEN_URL;
                Map<String,String> tokenParams = new HashMap<String,String>();
                tokenParams.put("grant_type","client_credential");
                tokenParams.put("appid",APPID);
                tokenParams.put("secret",SECRET);
                String str = HttpClientUtils.sendGet(getTokenUrl,tokenParams);
                JSONObject obj = JSONObject.fromObject(str);
                accessToken = obj.getString("access_token");
                System.out.println("getAccessToken ======= " + str);
                redisUtil.putInRedis("wxapp_access_token",accessToken,7200);
            }
            return accessToken;
        }catch (Exception e){
            logger.info("获取 wx_access_token 异常 : " + e.getMessage());
        }

        return null ;
    }


    /**
     * 创建小程序邀请码
     * @param customer  邀请人
     * @param request
     * @param result
     * @return
     */
    public static Result createInvitationCode(Customer customer, HttpServletRequest request,Result result){

        String accessToken = getAccessToken();
//        String accessToken = "15_RPdORaRGwiufCTO8c9Z3Jg6ejC_OX13Q_7mKpyFE3UnTqb9l7tF_zn7rxziu9OMfRmWaOJrHs6HAI3m6jnvGBLlbdqc3bFnCaFqORbCChGtF49dtfe-IIrexbEAEv2O96BG7h0YTZuOfS1QsCGFhAJAOSR";
        if(StringUtils.isNotBlank(accessToken)){
            String getAppCodeUrl = GET_WXAPP_CODE_URL+accessToken;
            String scene = "customerId="+customer.getId(); //传入的参数 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符
            String page = "pages/login/login";  //扫码后跳转的小程序页面 注意:前面不加/
            Map<String,Object> appCodeParams = new HashMap<String,Object>();
            appCodeParams.put("scene",customer.getId());
            appCodeParams.put("page",page);
            result = getInvitationCodePath(getAppCodeUrl, JsonUtils.objectToJson(appCodeParams),request,result);
        }else {
            logger.info("微信access_token 为空!");
        }
        return result ;

    }

    /**
     * 返回图片链接
     * @param url
     * @param json
     * @param request
     * @param result
     * @return
     */
    public static Result getInvitationCodePath(String url, String json, HttpServletRequest request,Result result) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = null;
        // 检测是否是https请求
        if (url.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(HttpClientUtils.createSSLConnSocketFactory())
                    .setConnectionManager(HttpClientUtils.connMgr).setDefaultRequestConfig(HttpClientUtils.requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        CloseableHttpResponse response = null;
        String retPath = "";
        InputStream in = null;
        OutputStream out = null;
        InputStream fileIn = null;
        ByteArrayOutputStream bout = null;

        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            entity.setContentType("image/jpg");
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
                httpPost.abort();
                throw new RuntimeException(
                        "网络请求错误: " + response.getStatusLine().getStatusCode());
            }
//

            in = response.getEntity().getContent();

            String uploadDir = request.getSession().getServletContext().getRealPath("upload");
            String saveFileName =  getFileName("jpg"); //保存的文件名
            File targetFile = new File(uploadDir + File.separator + "InvitationCode" +  File.separator +saveFileName);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdir();
            }
            out = new FileOutputStream(targetFile);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes,0,1024)) != -1){
                out.write(bytes,0,len);
            }
            out.flush();
            in.close();
            out.close();

            //读取数据是否是错误信息
//            System.out.println("in  ======== " + IOUtils.toString(in));
            fileIn = new FileInputStream(targetFile);
            bout = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileIn.read(buffer)) != -1) {
                bout.write(buffer, 0, length);
            }
            fileIn.close();
            bout.close();
            String res = bout.toString();
//            System.out.println(res);
            //生成图片错误
            if(StringUtils.isBlank(res) || res.indexOf("errcode") > -1){
                logger.info("生成邀请小程序码错误 == " +res);
                //删除错误文件
                targetFile.delete();
                return result.setF("",res);
            }
            //返回图片地址
            retPath = File.separator + "upload" + File.separator + "InvitationCode" +  File.separator +saveFileName;
            result.setS("",retPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileIn != null){
                    fileIn.close();
                }
                if(bout != null){
                    bout.close();
                }
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
                if(response != null){
                    response.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("testInvitationCode")
    public Result testInvitationCode(HttpServletRequest request){
        Result result = new Result();
        Customer customer =new Customer();
        customer.setId(100L);
        createInvitationCode(customer,request,result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        System.out.println(jsonObject.get("data"));
        return result;
    }
    /**
     * @param :用户,二维码链接     (收款码)
     * @return : 返回的result.data是生成的二维码图片的保存路径
     * @Description: 生成用户收款码
     * @Author: 隗鹏
     * @CreateDate: 2018/11/17 13:40
     * @UpdateUser:
     * @UpdateDate:
     * @UpdateRemark:
     * @Version: 1.0
     */
    public static Result createReceivablesCode(Customer customer, String url, HttpServletRequest request,Result result) {
        try {
            //如果用户已经生成了二维码,不再生成
            if (ObjectUtils.isNotEmpty(customer.getReceivablesCode())) {
                result.setF("不可重复生成");
                return result;
            }
            String logoPath = CommonUtil.getLogoPath(request); //默认logo
            if (ObjectUtils.isNotEmpty(customer.getImg())) {//用户头像为空,二维码中间给默认图片
                //用户头像不为空,下载用户头像到本地
                String headImgName = getFileName("jpg");
                String uploadHeadImgPath = getUploadRealPath("headImg", request);
                DownloadOnLineImage.downloadOnLineImage(customer.getImg(), uploadHeadImgPath + headImgName);
                //logo换成用户的头像
                logoPath = uploadHeadImgPath + headImgName;
            }
            String qrFileName = getFileName("jpg");
            String qrPath = getUploadRealPath("qrCode", request);//二维码地址
            //生成用户二维码
            QRCodeUtil.encode(url, 400, 400, logoPath, qrPath + qrFileName);
            result.setS("操作成功");
            result.setData(File.separator + "upload" + File.separator + "uploadQrCode" + File.separator + qrFileName);
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("系统异常");
        }
        return result;
    }

    //拼接上传图片的路径(仅用于生成收款码)
    public static String getUploadRealPath(String flag, HttpServletRequest request) {
        ServletContext context = request.getSession().getServletContext();
        String realPath = context.getRealPath("/");
        //根目录
        String uploadRealPath = "";
        if ("headImg".equals(flag)) {
            uploadRealPath = realPath + "upload" + File.separator + "customerHeadImg" + File.separator;

        } else if ("qrCode".equals(flag)) {
            uploadRealPath = realPath + "upload" + File.separator + "uploadQrCode" + File.separator;
        }
        return uploadRealPath;
    }

    public static String getFileName(String suffix) {
        String fileName = DateUtils.dateToTimeStamp(new Date()) + CommonUtil.$getRandom(8, 4) + "." + suffix;
        return fileName;
    }

    public static void main(String[] args) {
        String  s = getAccessToken();

    }


}
