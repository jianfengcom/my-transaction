package com.example.transaction.cac.demo;

import com.example.transaction.cac.util.CACEncrypt;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CACEncryptDemo {
    /*
        数据加密: 核查账号接口加密

        one=db06c78d1e24cf708a14ce81c9b617ec
        two=1eb43e5183c95734be835ccdd08908a5
     */
    @Test
    public void testMD5() {
        String one = org.springframework.util.DigestUtils.md5DigestAsHex(("测试").getBytes());
        System.out.println(one);

        String two = org.springframework.util.DigestUtils.md5DigestAsHex((one + "测试").getBytes());
        System.out.println(two);
    }

    @Test
    public void testKey() throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        String cKey = "00c61bd99857871bd39a7fb04b8cfcca24277abe5a4c7120";

        // 需要加密的字串
        String cSrc = "测试";

        // 加密
        String enString = CACEncrypt.Encrypt(cSrc, cKey);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        System.out.println("时间：" + simpleDateFormat.format(new Date()) + "加密后的字串是：" + enString);
        // 解密
        String DeString = CACEncrypt.Decrypt(enString, cKey);
        System.out.println("时间：" + simpleDateFormat.format(new Date()) + "解密后的字串是：" + DeString);
    }
}
