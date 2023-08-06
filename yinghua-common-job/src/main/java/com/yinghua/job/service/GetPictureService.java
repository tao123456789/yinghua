package com.yinghua.job.service;

import com.yinghua.common.utils.dateUtils.DateUtils;
import com.yinghua.common.utils.fileUtils.FileUtils;
import com.yinghua.job.domain.wx.WXFileList;
import com.yinghua.job.service.impl.WxfilelistServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yinghua.common.utils.httpUtils.HttpUtils;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class GetPictureService {


    @Resource
    WxfilelistServiceImpl wxfilelistService;

    HttpUtils httpUtils=new HttpUtils();
    DateUtils dateUtils=new DateUtils();

    public String getWBHotMessage(){
        HashMap hashMap=new HashMap();
        hashMap.put("cookie","SUB=1");
        String response=httpUtils.getMethod("https://s.weibo.com/top/summary?cate=realtimehot",hashMap);
        List<String> content= Arrays.asList(StringUtils.substringsBetween(response, "target=\"_blank\">", "</a>"));
//        List<String> level= Arrays.asList(StringUtils.substringsBetween(response, "<td class=\"td-01 ranktop\">", "</td>"));
        String WBhotMessage="";
        for(int i=0;i<content.size();i++){
            WBhotMessage=WBhotMessage+content.get(i)+"<br>";
        }
        return WBhotMessage;
    }

    public String getWallhavenPic() throws Exception {
        String response=httpUtils.getMethod("https://wallhaven.cc/random");
        List<String> content= Arrays.asList(StringUtils.substringsBetween(response, "<a class=\"preview\" href=\"", "\"  target=\"_blank\"  >"));
        List<String> content2=new ArrayList<>();
        for(String item:content){
            System.out.println("正在获取页面地址..."+item);
            try {
                String response2=httpUtils.getMethod(item);
                content2.add(Arrays.asList(StringUtils.substringsBetween(response2, "<img id=\"wallpaper\" src=\"", "\" alt=\"")).get(0));
            }catch (Exception e){
                System.out.println("获取失败"+item);
            }
        }
        System.out.println("共获取到"+content2.size()+"张壁纸");
        String WallhavenPic="共获取到"+content2.size()+"张壁纸<br>";
        for(String item:content2){
            System.out.println("获取到对应壁纸地址："+item);
            try {
                getFileInputStream(item);
            }catch (Exception e){
                System.out.println("获取资源失败："+e);
            }
            WallhavenPic=WallhavenPic+"<br>"+item+"<br><img src=\""+item+"\">";
        }
//        System.out.println(WallhavenPic);
        return WallhavenPic;
    }

    public void getFileInputStream(String urls) throws Exception {

        //创建一个文件对象用来保存图片，默认保存当前工程根目录，起名叫Copy.jpg
        String filename="壁纸"+dateUtils.getNowFormat1()+"-"+new Date().getTime();
        String filURL="picture\\"+filename +".jpg";
        File imageFile = new File(filURL);
        try{
            creatFilePath(filURL);
        }catch (Exception e){
            System.out.println("路徑已存在");
        }
        WXFileList wxfilelist=new WXFileList();

        wxfilelist.setCreatename("root");
        wxfilelist.setFilename(filename +".jpg");
        wxfilelist.setCreatetime(new DateUtils().getNowFormat1());
        wxfilelist.setRemark("N");
        try {
            wxfilelistService.insert(wxfilelist);
        }catch (Exception e){
            System.out.println("插入失败："+e);
        }

        System.out.println("获取链接文件中......");
        URL url=new URL(urls);
        //打开连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为10秒
        conn.setConnectTimeout(10 * 1000);
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        conn.setRequestProperty("cookie","SUB=1");
        //通过输入流获取图片数据
        InputStream is=null;
        is = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(is);

        //创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        //写入数据
        outStream.write(data);
        //关闭输出流，释放资源
        outStream.close();
        is.close();
    }

    public File creatFilePath(String pathname) throws IOException {
        File uploadFile = new File(pathname).getCanonicalFile();
        if (!uploadFile.getParentFile().exists()){
            if (!uploadFile.getParentFile().mkdir()) {
                System.out.println("创建文件目录失败！！！");
                throw new IOException("创建文件目录失败！！！");
            }
        } else {
            System.out.println(pathname + "文件路径已存在");
            throw new IOException(pathname + "文件路径已存在");
        }
        return uploadFile;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[60240];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len;
        System.out.println("开始读取");
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
