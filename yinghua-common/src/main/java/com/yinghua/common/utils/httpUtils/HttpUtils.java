package com.yinghua.common.utils.httpUtils;

import com.alibaba.fastjson2.JSONObject;
import com.yinghua.core.domain.bo.UserBO;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;

@Component
public class HttpUtils {
    /**
     * 通过HttpServletRequest返回IP地址
     *
     * @param request HttpServletRequest
     * @return ip String
     * @throws Exception
     */
    public static String getIpAddr(HttpServletRequest request) {
//        System.out.println("x-forwarded-for:"+request.getHeader("x-forwarded-for"));
//        System.out.println("Proxy-Client-IP:"+request.getHeader("Proxy-Client-IP"));
//        System.out.println("WL-Proxy-Client-IP:"+request.getHeader("WL-Proxy-Client-IP"));
//        System.out.println("HTTP_CLIENT_IP:"+request.getHeader("HTTP_CLIENT_IP"));
//        System.out.println("HTTP_X_FORWARDED_FOR:"+request.getHeader("HTTP_X_FORWARDED_FOR"));
//        System.out.println("RemoteAddr:"+request.getRemoteAddr());
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip) || "127.0.0.1".equals(ip)) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        return ip;
    }


    public static String postMethod(String url, JSONObject json){
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            //发送json数据需要设置contentType
            s.setContentType("application/x-www-form-urlencoded");
            post.setEntity(s); //设置请求参数
            HttpResponse response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == statusCode){
                //返回String
                String res = EntityUtils.toString(response.getEntity());
                System.out.println(res);
                return res;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMethod(String url, HashMap hashMap){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        try{
            //这里可以设置请求参数，token等
            get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");

            if(hashMap.get("cookie") != null){
                get.addHeader("cookie",hashMap.get("cookie").toString());
            }
            if(hashMap.get("token") != null) {
                get.addHeader("token", hashMap.get("token").toString());
            }
            HttpResponse response = httpClient.execute(get);//执行获取响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//根据状态码处理
                //返回字符串
                String res = EntityUtils.toString(response.getEntity());
//                System.out.println(res);
                return res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMethod(String url){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        try{
            //这里可以设置请求参数，token等
            get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            HttpResponse response = httpClient.execute(get);//执行获取响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//根据状态码处理
                //返回字符串
                String res = EntityUtils.toString(response.getEntity());
//                System.out.println(res);
                return res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getMethod2(String url,HashMap hashMap){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        url=url+"?";
        Iterator i = hashMap.keySet().iterator();
        while (i.hasNext()) {
            Object key=i.next();
            url=url+key.toString()+"="+hashMap.get(key)+"&";
        }
        System.out.println("请求地址===========》》》"+url);
        HttpGet get = new HttpGet(url);
        try{
            HttpResponse response = httpClient.execute(get);//执行获取响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//根据状态码处理
                //返回字符串
                Object res = EntityUtils.toString(response.getEntity());
                System.out.println("返回数据===========》》》"+res);
                return res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通过UserAgent获取登录信息
     */
    public static UserBO getLoginInfo() {
        UserBO userBO = new UserBO();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取请求头中的User-Agent
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        userBO.setIp(request.getRemoteAddr());
        userBO.setBrower(userAgent.getBrowser().toString());
        userBO.setOs(userAgent.getOperatingSystem().toString());
        return userBO;
    }
}
