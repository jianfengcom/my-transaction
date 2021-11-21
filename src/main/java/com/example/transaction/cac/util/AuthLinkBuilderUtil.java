package com.example.transaction.cac.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class AuthLinkBuilderUtil {

    public static String buildFtpAuthLink(String ftpLink, String secret, int expires) throws MalformedURLException {
        if (ftpLink == null) {
            System.out.println("ftp link is blank");
            throw new MalformedURLException("ftp link is blank");
        } else {
            URL url = new URL(ftpLink);
            String host = url.getHost();
            int port = url.getPort();
            // 我站地址
            if (host.toLowerCase().contains("pconline.com.cn") && host.toLowerCase().contains("ftp")) {
                // 强制https
                String path = url.getPath();
                try {
                    path = URLDecoder.decode(path, "UTF-8"); // URL转码解析
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // 有效时间戳
                String time = String.valueOf((System.currentTimeMillis() / 1000) + expires);
                // 注意密文前面有个空格
                String md5 = Base64.encodeBase64URLSafeString(DigestUtils.md5(time + path + " " + secret));
                return "https://" + host  +  (port == -1 ? "" : ":" + port)  + url.getPath() + "?md5=" + md5 + "&expires=" + time;
            } else {
                return ftpLink;
            }
        }
    }

}
