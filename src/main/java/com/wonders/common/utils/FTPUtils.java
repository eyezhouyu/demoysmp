package com.wonders.common.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.InputStream;
import java.util.Random;

public class FTPUtils {

    private final static String host = "112.74.54.21";
    private final static Integer port = 21;
    private final static String username = "ftpydwlkj";
    private final static String password = "ydwlkj2019";
    private final static String path = "/home/ftp/ftpydwlkj";
    private final static String img_url = "https://www.liangxunkeji.com/ftpydwlkj/";


    /**
     * @param filename
     * @param input
     * @return
     */
    public static boolean uploadFile(String filename, InputStream input) {

        boolean flag = false;


        FTPClient ftp = new FTPClient();

        try {

            int reply = 1;
            ftp.connect(host, port);
            ftp.login(username, password);
            reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return flag;
            }


            //切换到上传目录
//            if (!ftp.changeWorkingDirectory(basePath + filePath)) {
//                //如果目录不存在创建目录
//                String[] dirs = filePath.split("/");
//                String tempPath = basePath;
//                for (String dir : dirs) {
//                    if (null == dir || "".equals(dir)) continue;
//                    tempPath += "/" + dir;
//                    if (!ftp.changeWorkingDirectory(tempPath)) {
//                        if (!ftp.makeDirectory(tempPath)) {
//                            return flag;
//                        } else {
//                            ftp.changeWorkingDirectory(tempPath);
//                        }
//                    }
//                }
//            }

            ftp.changeWorkingDirectory(path);


            //设置为被动模式
            ftp.enterLocalPassiveMode();
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return flag;
            }
            input.close();
            ftp.logout();
            flag = true;


        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


        return flag;
    }



    /**
     * 生成随机图片名
     */
    public static String randomnumber() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);
        return str;
    }
}