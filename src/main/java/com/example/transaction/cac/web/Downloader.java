package com.example.transaction.cac.web;

import com.alibaba.fastjson.JSONObject;
import com.example.transaction.cac.util.AuthLinkBuilderUtil;
import com.example.transaction.cac.util.ByteToFileUtil;
import com.example.transaction.cac.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/d")
public class Downloader {

    // 密文
    public static final String secret = "pcgroup";
    // 默认 15分钟下载链接有效
    public static final int default_expires = 60 * 15;

    @RequestMapping("/show")
    @ResponseBody
    public JSONObject show(@RequestBody JSONObject obj) {
        return obj;
    }

    /*
        重定向 - 直接下载
        网页的url 还是终端页
     */
    @RequestMapping("/rs")
    public void sendRedirect(
            @RequestParam(required = false) String ftpLink,
            HttpServletResponse response
    ) throws IOException {

        if (ftpLink == null) {
            ftpLink = "https://ftp-new-apk.pconline.com.cn/pub/download/202110/pconline1634897416186.apk";
        }

        String result = AuthLinkBuilderUtil.buildFtpAuthLink(ftpLink, secret, default_expires);
        response.sendRedirect(result);
    }

    @RequestMapping("/w")
    @ResponseBody
    public byte[] write(
            @RequestParam(required = false) String ftpLink
    ) throws IOException {

        if (ftpLink == null) {
            ftpLink = "https://ftp-new-apk.pconline.com.cn/pub/download/202110/pconline1634897416186.apk";
        }

        // 文件名称
        String fn = StringUtil.getFileName(ftpLink);

        ftpLink = AuthLinkBuilderUtil.buildFtpAuthLink(ftpLink, secret, default_expires);
        byte[] bytes = ByteToFileUtil.getBytes(ftpLink, 1);

        // 生成文件
        System.out.println("生成文件start");
        ByteToFileUtil.buildFile(bytes, "/data", fn);
        System.out.println("生成文件end");
        return bytes;
    }

}
