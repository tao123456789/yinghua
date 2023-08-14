package com.yinghua.job.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yinghua.common.utils.dateUtils.DateUtils;
import com.yinghua.common.utils.httpUtils.HttpUtils;
import com.yinghua.core.domain.bo.SettingBO;
import com.yinghua.job.feign.CommonCoreFeignService;
import com.yinghua.job.feign.MQFeignService;
import com.yinghua.job.feign.RedisFeignService;
import com.yinghua.job.service.GetPictureService;
import com.yinghua.job.service.WXFileListService;
import com.yinghua.mq.domain.po.EmailPO;
import com.yinghua.redis.domain.RedisPO;
import com.yinghua.job.domain.wx.WXArticlesPO;
import com.yinghua.job.domain.wx.WXAuth;
import com.yinghua.job.domain.wx.WXDraft;
import com.yinghua.job.domain.wx.WXFileList;
import com.yinghua.job.service.WXDraftService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class WXService {

    @Value("${WX.gettokenURL}")
    String gettokenURL;

    @Value("${WX.getmaterialcountURL}")
    String getmaterialcountURL;

    @Value("${WX.batchget_materialURL}")
    String batchget_materialURL;

    @Value("${WX.uploadimgURL}")
    String uploadimgURL;

    @Value("${WX.addDraftURL}")
    String addDraftURL;

    @Value("${WX.draftSubmitURL}")
    String draftSubmitURL;

    HttpUtils httpUtils=new HttpUtils();
    @Resource
    RedisFeignService redisService;

    DateUtils dateUtils=new DateUtils();

    @Autowired
    CommonCoreFeignService settingService;
    @Autowired
    WXFileListService wxFileListService;
    @Autowired
    WXDraftService wxDraftService;
    @Autowired
    GetPictureService getPictureService;
    @Autowired
    MQFeignService mqFeignService;

    /**
     *
     * @param wxAuth
     * @throws Exception
     */
    public String getToken(WXAuth wxAuth) throws Exception {
        String WXToken="";
        if(wxAuth.getWXappid()=="" || wxAuth.getWXsecret() == ""){
            throw new Exception("获取微信认证信息失败!");
        }
        if(redisService.hasKey("WXToken-" + wxAuth.getWXappid())){
            WXToken=redisService.getCacheObject("WXToken-" + wxAuth.getWXappid());
            System.out.println("查询到缓存token："+WXToken);
        }else {
            HashMap hashMap = new HashMap<>();
            hashMap.put("appid", wxAuth.getWXappid());
            hashMap.put("secret", wxAuth.getWXsecret());
            hashMap.put("grant_type", "client_credential");
            String rs = (String) httpUtils.getMethod2(gettokenURL, hashMap);

            System.out.println("获取微信token结果：" + rs);
            if (JSONObject.parseObject(rs).getString("access_token").isEmpty()) {
                throw new Exception("获取微信token失败!");
            } else {
                WXToken = JSONObject.parseObject(rs).getString("access_token");
                System.out.println("请求到token：" + WXToken);
                RedisPO redisPO=new RedisPO();
                redisPO.setKey("WXToken-" + wxAuth.getWXappid());
                redisPO.setValue(WXToken);
                redisPO.setTime(2);
                redisPO.setTimeUnit(TimeUnit.HOURS);
                redisService.setCacheObject(redisPO);
            }
        }
        return WXToken;
    }

    /**
     * 获取微博热搜数据
     * @return
     */
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

    /**
     * 获取壁纸数据
     * @return
     * @throws Exception
     */
    public void getWallhavenPic() throws Exception {
        getPictureService.getWallhavenPic();
    }

    public void uploadWXImg(String token){
        WXFileList wxfilelist=new WXFileList();
        wxfilelist.setCreatetime(dateUtils.getNowFormat1());
        wxfilelist.setRemark("N");
        List<WXFileList> wxfilelistPage = wxFileListService.query(wxfilelist);

        SettingBO settingBO=new SettingBO();
        settingBO.setName("WXLocalPicPath");
        String path=settingService.getSetting(settingBO).get(0).getCode();
//        String path="H:\\IdeaProjects\\YingHua\\picture\\";

        for (WXFileList wxfilelist1:wxfilelistPage){
            System.out.println("获取到的数据："+wxfilelist1);
            try{
                JSONObject jsonObject=this.upload(token,path+wxfilelist1.getFilename());
                System.out.println("上传结果："+jsonObject.toString());
                WXFileList updatewxfilelist=wxfilelist1;
                updatewxfilelist.setItem(jsonObject.getString("item"));
                updatewxfilelist.setMediaId(jsonObject.getString("media_id"));
                updatewxfilelist.setUrl(jsonObject.getString("url"));
                if(jsonObject.getString("media_id").isEmpty()){
                    updatewxfilelist.setRemark("N");
                }else {
                    updatewxfilelist.setRemark("Y");
                }
                wxFileListService.update(updatewxfilelist);
            }catch (Exception e){
                System.out.println("上传失败！！!" + e);
            }
        }
    }
    /**
     * 文件上传到微信服务器的执行者
     * @return JSONObject
     * @throws Exception
     */
    public JSONObject upload(String token,String fileURL) throws Exception {

        String result=null;

        File file=new File(fileURL);
        if (file.exists()) {
            System.out.println("文件存在");
        }
        /**
         * 第一部分
         */
        URL urlObj = new URL(uploadimgURL+"?access_token="+token+"&type=image");
        System.out.println("请求地址："+urlObj);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false); // post方式不能使用缓存
        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);
        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
//        sb.append("Content-Disposition: form-data;name=\"media\";filename=\""+ multipartFile.getName() + "\"\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\";filename=\"a836b.jpg\"\r\n");
        sb.append("Content-Type:image/jpeg\r\n\r\n");
//        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);
        // 文件正文部分
        // 把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                buffer.append(line);
            }
            if(result==null){
                result = buffer.toString();
            }
        } catch (IOException e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
            throw new IOException("数据读取异常");
        } finally {
            if(reader!=null){
                reader.close();
            }
        }
        JSONObject jsonObj =JSONObject.parseObject(result);
        return jsonObj;
    }

    /**
     * 微信新建草稿
     */
    public void addDraft(String token) throws Exception {
        List<WXArticlesPO> wxArticlesPOList=new ArrayList<>();
        WXArticlesPO wxArticlesPO=new WXArticlesPO();
        wxArticlesPO.setAuthor("XiaoPH");
        wxArticlesPO.setDigest("DIGEST");
        wxArticlesPO.setNeed_open_comment("0");
        wxArticlesPO.setOnly_fans_can_comment("0");
        wxArticlesPO.setTitle(new DateUtils().getNowFormat2()+"Wallpaper");
        String content="Welcome To My Manor!\n";

        WXFileList wxfilelist=new WXFileList();
        wxfilelist.setCreatetime(dateUtils.getNowFormat1());
        wxfilelist.setRemark("Y");
        List<WXFileList> wxfilelistPage=wxFileListService.query(wxfilelist);
        System.out.println("获取到今日壁纸："+wxfilelistPage.size());

        for(WXFileList wxfilelist1:wxfilelistPage){
            if(wxfilelist1.getUrl()!="") {
                content = content + "<img src=\"" + wxfilelist1.getUrl() + "\">\n";
            }
        }
        wxArticlesPO.setContent(content+"There are more wonderful piccture be pushed to you after you focus on me!");

        //封面图片的ID
        int i=0;
        do {
            if(!wxfilelistPage.get(i).getMediaId().isEmpty()){
                wxArticlesPO.setThumb_media_id(wxfilelistPage.get(i).getMediaId());
            }else {
                i++;
            }
        }
        while (i<wxfilelistPage.size()&&wxArticlesPO.getThumb_media_id().isEmpty());
        if(wxfilelistPage.get(i).getMediaId().isEmpty()){
            wxArticlesPO.setThumb_media_id("0bfoTL2Ml9HJIIeDXLdiAL9EqABaNJgOX_6A01mkYkKRbYVIH-oofpMKQxqzYbeF");
        }

        wxArticlesPOList.add(wxArticlesPO);
        System.out.println("新增数据======="+wxArticlesPOList);

        String media_id=addDraft(token,wxArticlesPOList);


        for(WXFileList wxfilelist2:wxfilelistPage){
            wxfilelist2.setDraftId(media_id);
            wxFileListService.update(wxfilelist2);
        }
    }

    /**
     *
     * @param wxArticlesPOList
     * @return
     * @throws Exception
     */
    public String addDraft(String token,List<WXArticlesPO> wxArticlesPOList) throws Exception {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("articles",wxArticlesPOList);

        String rs=HttpUtils.postMethod(addDraftURL+"?access_token="+token,jsonObject);
        String media_id=null;
        try{
            media_id= JSON.parseObject(rs).get("media_id").toString();
        }catch (Exception e){
            media_id="feil to get media_id";
        }
        WXDraft wxdraft=new WXDraft();
        wxdraft.setSubmit(0);
        wxdraft.setIssubmit(0);
        wxdraft.setMediaId(media_id);
        wxdraft.setData(new DateUtils().getNowFormat1());
        wxDraftService.insert(wxdraft);
        return media_id;
    }

    public void submitDraft(String token) throws Exception {
        WXDraft wxdraft=wxDraftService.queryByData(new DateUtils().getNowFormat1());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("media_id",wxdraft.getMediaId());
        HttpUtils.postMethod(draftSubmitURL+"?access_token="+token,jsonObject);
        wxdraft.setIssubmit(1);
        wxDraftService.update(wxdraft);
        EmailPO emailPO=new EmailPO();
        emailPO.setContent(wxdraft.getMediaId()+"草稿发布成功！！！");
        emailPO.setSubject("微信定时任务");
        emailPO.setTitle("微信草稿后台生成通知");
        mqFeignService.emailSendToAdmin(emailPO);
    }
}
