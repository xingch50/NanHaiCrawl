package com.ab.crawl.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <Description>
 *
 * @author tongziqi
 * @version 1.0
 * @createDate 2019/01/29 14:49
 * @see com.ab.crawl.util
 */
public class WindDownload {
    private static final String configDir = PropertiesConfig.INSTANCE.getProPerties("dir");
    public boolean downloadFile(String fileUrl,String name) throws Exception {
        boolean flag=false;
        URL url = new URL(fileUrl);
        HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
        urlCon.setConnectTimeout(6000);
        urlCon.setReadTimeout(6000);
        int code = urlCon.getResponseCode();
        if (code != HttpURLConnection.HTTP_OK) {
            throw new Exception("文件读取失败");
        }
        //读文件流
        DataInputStream in = new DataInputStream(urlCon.getInputStream());

        File outDir =new File("D:\\ftpsend\\product\\WRF\\SCS\\");
        outDir.mkdirs();


        DataOutputStream out = new DataOutputStream(new FileOutputStream("D:\\ftpsend\\product\\WRF\\SCS\\"+name));
        byte[] buffer = new byte[2048];
        int count = 0;
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        try {
            if(out!=null) {
                out.close();
            }
            if(in!=null) {
                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        flag=true;
        return flag;
    }
}
