package com.wonders.business.user.utils;

import com.wonders.common.utils.FTPUtils;
import com.wonders.common.utils.JacksonUtil;
import com.wonders.common.utils.RequestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class WechatQrCodeUtils {

    /**
     * 永久二维码
     * @param accessToken
     * @param userId
     * @return
     */
    public static void createForeverTicket(String accessToken, String userId, HttpServletResponse response) throws Exception{

        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);  //参数
        params.put("page", "pages/index"); //位置
        params.put("width", 430);

        ResponseEntity<byte[]> responseEntity = RequestUtils.httpRequest("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+accessToken,params,null, HttpMethod.POST,byte[].class);
        byte[] qrcodeBytes = responseEntity.getBody();
        response.setContentType("image/jpeg");
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token="+accessToken);
//        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
//        String body = JacksonUtil.toJSon(params);
//        StringEntity entity = new StringEntity(body);
//        entity.setContentType("image/jpg");
//        httpPost.setEntity(entity);
//        HttpResponse response = httpClient.execute(httpPost);
//        InputStream inputStream = response.getEntity().getContent();

        OutputStream os = new FileOutputStream("D://"+userId+".jpeg");
//        String fileName = userId+".jpg";
//        boolean uploadFlag = FTPUtils.uploadFile(fileName,inputStream);
//        System.out.println(uploadFlag);
//
//        saveToImgByInputStream(inputStream,"D:\\",name);

        os.write(qrcodeBytes);
        os.flush();
        os.close();
    }



    /**
     * 将二进制转换成文件保存
     * @param instreams 二进制流
     * @param imgPath 图片的保存路径
     * @param imgName 图片的名称
     * @return
     *      1：保存正常
     *      0：保存失败
     */
    public static int saveToImgByInputStream(InputStream instreams,String imgPath,String imgName){
        int stateInt = 1;
        if(instreams != null){
            try {
                File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos=new FileOutputStream(file);
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }
}
