package com.xiyan.xiyanapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 签名工具
 */
public class SignUtils {

    /**
     * 生成签名
     * @param body
     * @param secretKey
     * @return
     */
    public static String genSign(String body, String secretKey) {
        // https://doc.hutool.cn/pages/Digester/#digester，加密算法
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + '.' + secretKey;
        return md5.digestHex(content);
    }
}
