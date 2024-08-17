package com.xiyan.xiyanapiclientsdk.client;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.xiyan.xiyanapiclientsdk.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.xiyan.xiyanapiclientsdk.utils.SignUtils.genSign;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 调用第三方接口的客户端
 * 用于请求第三方接口的HTTP客户端
 *
 * @author xiyan
 */
public class XiYanApiClient {

    private static final String EXTRA_BODY = "userInfoXiyanAPI";

    private static final String GATEWAY_HOST = "http://localhost:8090";  // 请求网关，网关再将请求转发到真实接口地址

    private String accessKey;
    private String secretKey;

    public XiYanApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * 随机获取一句毒鸡汤
     * @return
     */
    public String getPoisonousChickenSoup() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/poisonousChickenSoup")
                .addHeaders(headerMap(EXTRA_BODY))
                .body(EXTRA_BODY)
                .execute();
        return httpResponse.body();
    }

    /**
     * 随机壁纸
     * @return
     */
    public String getRandomWallpaper() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/randomWallpaper")
                .addHeaders(headerMap(EXTRA_BODY))
                .body(EXTRA_BODY)
                .execute();
        return httpResponse.body();
    }

    /**
     * 随机土味情话
     * @return
     */
    public String getLoveTalk() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/loveTalk")
                .addHeaders(headerMap(EXTRA_BODY))
                .body(EXTRA_BODY)
                .execute();
        return httpResponse.body();
    }

    /**
     * 每日一句励志英语
     * @return
     */
    public String getDailyEnglish() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/en")
                .addHeaders(headerMap(EXTRA_BODY))
                .body(EXTRA_BODY)
                .execute();
        return httpResponse.body();
    }

    /**
     * 随机笑话
     * @return
     */
    public String getRandomJoke() {
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/joke")
                .addHeaders(headerMap(EXTRA_BODY))
                .body(EXTRA_BODY)
                .execute();
        return httpResponse.body();
    }

    /**
     * 获取输入的名称接口
     * @param user
     * @return
     */
    public String getNameByJSON(User user) {
        String userStr = JSONUtil.toJsonStr(user);// 将 user 转为 JSON 格式的字符串进行参数的传递
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name")
                .addHeaders(headerMap(userStr))// 客户端在请求头中携带签名
                .body(userStr)
                .execute();
        System.out.println(httpResponse.getStatus());
        String body = httpResponse.body();
        System.out.println(body);
        return body;
    }

    /**
     * 将参数添加到请求头 map
     * @return
     */
    private Map<String, String> headerMap(String body) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("accessKey", accessKey);
        // 一定不能发送给后端！
//        hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(3));
        hashMap.put("body", URLEncodeUtil.encode(body, UTF_8));// 解决中文乱码
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", genSign(body, secretKey));
        return hashMap;
    }

//    public String getNameByGet(String name) {
//        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("name", name);
//        String result = HttpUtil.get(GATEWAY_HOST + "/api/name/", paramMap);
//        System.out.println(result);
//        return result;
//    }
//
//    public String getNameByPost(String name) {
//        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("name", name);
//        String result = HttpUtil.post(GATEWAY_HOST + "/api/name/", paramMap);
//        System.out.println(result);
//        return result;
//    }
//
//    private Map<String, String> getHeaderMap(String body){
//        Map<String, String> hashMap = new HashMap<>();
//        hashMap.put("accessKey", accessKey);
//        // 一定不能直接发送给后端（服务端）
////        hashMap.put("secretKey", secretKey);
//        hashMap.put("nonce", RandomUtil.randomNumbers(4));  // 随机数
//        hashMap.put("body", body);  // 用户传入参数
//        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000)); // 时间戳
//        hashMap.put("sign", genSign(body, secretKey));
//        return hashMap;
//    }
//
//    public String getUsernameByPost(User user) {
//        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
//        String json = JSONUtil.toJsonStr(user);
////        String result = HttpRequest.post("http://localhost:8123/api/name/user")
////                .body(json).execute().body();
//        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
//                .addHeaders(getHeaderMap(json))
//                .body(json)
//                .execute();
//        System.out.println(httpResponse.getStatus());
//        String result = httpResponse.body();
//        System.out.println(result);
//        return result;
//    }
}
