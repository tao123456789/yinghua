package com.yinghua.job.schedule;

import com.yinghua.core.domain.bo.WXAccount;
import com.yinghua.job.controller.WXService;
import com.yinghua.job.domain.wx.WXAuth;
import com.yinghua.job.feign.CommonCoreFeignService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class WXClient {

    @Resource
    WXService wxService;
    @Resource
    CommonCoreFeignService commonCoreFeignService;

    public void WXScheduleService() throws Exception {

        WXAccount wxAccount = new WXAccount();
        wxAccount.setType(1);
        List<WXAccount> wxAccountList=commonCoreFeignService.selectBy(wxAccount);
        WXAuth wxAuth=new WXAuth();
        if(!wxAccountList.isEmpty()||wxAccountList.size()!=0) {
            wxService.getWallhavenPic();//获取图片
            for(WXAccount wxAccount1:wxAccountList){
                System.out.println("开始发送公众号账号："+wxAccount1.getAppid());
                wxAuth.setWXappid(wxAccount1.getAppid());
                wxAuth.setWXsecret(wxAccount1.getSecret());

                String token=wxService.getToken(wxAuth);

                wxService.uploadWXImg(token);//上传图片
                wxService.addDraft(token);//添加草稿
                wxService.submitDraft(token);//发布草稿
            }
        }else{
            System.out.println("没有微信公众号账号，无需获取");
        }

    }
}
