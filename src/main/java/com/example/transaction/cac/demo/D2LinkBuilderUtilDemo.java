package com.example.transaction.cac.demo;

import com.example.transaction.cac.util.D2LinkBuilderUtil;
import org.junit.Test;

public class D2LinkBuilderUtilDemo {

    /**
     * 下载来源：pc端本地下载
     */
    public static final int DL_FROM_PC_LOCAL = 1;

    /**
     * https://dlc2.pconline.com.cn/download.jsp?target=0bK4dpLgLp56RZzqwOp
     */
    @Test
    public void dlc2() {
        long masterId = 2810048L;
        long linkId = 13995471L;
        int location = DL_FROM_PC_LOCAL;
        String result = "https://dlc2.pconline.com.cn/download.jsp?target=" + D2LinkBuilderUtil.buildSimpleToken(masterId, linkId, location);
        System.out.println(result); // https://dlc2.pconline.com.cn/download.jsp?target=0bK4dpLgLp56RZBo9TR
    }

}
