package com.example.transaction.cac.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class ByteToFileUtil {
    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(String target, int type){
        byte[] buffer = null;
        try {
            FileInputStream fis = null;
            InputStream in = null;
            try {

                if (type == 1) {
                    //url解码
                    URL url = new URL(URLDecoder.decode(target, "UTF-8"));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置超时间为3秒
                    conn.setConnectTimeout(3 * 1000);
                    //防止屏蔽程序抓取而返回403错误
                    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                    //得到输入流
                    in = conn.getInputStream();
                } else if (type == 2) {
                    File file = new File(target);
                    fis = new FileInputStream(file);
                    in = fis;
                }

            } catch (IOException e) {

            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = in.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            bos.close();
            in.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void buildFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}


