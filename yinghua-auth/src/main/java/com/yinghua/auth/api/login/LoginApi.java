package com.yinghua.auth.api.login;

import com.yinghua.auth.feign.RedisFeignService;
import com.yinghua.common.utils.httpUtils.HttpUtils;
import com.yinghua.auth.feign.CommonCoreFeignService;
import com.yinghua.common.basicenum.ResultCode;
import com.yinghua.common.utils.httpUtils.domain.BasicResponse;
import com.yinghua.core.domain.bo.UserBO;
import com.yinghua.core.domain.vo.UserModuleVO;
import com.yinghua.mq.domain.po.EmailPO;
import com.yinghua.redis.domain.RedisPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/yinghua-auth")
public class LoginApi {
    @Value("${server.port}")
    private String port;

    private int sum=0;

    @Autowired
    private CommonCoreFeignService commonCoreFeignService;
    @Autowired
    RedisFeignService redisFeignService;
//    @Autowired
//    private EmailMQFeign emailMQFeign;

    HttpUtils httpUtil=new HttpUtils();
    @Resource
    HttpServletRequest httpServletRequest;

    //登录
    @PostMapping ("/login")
    @ResponseBody
    public BasicResponse login(@RequestBody UserBO userBO) {
        BasicResponse basicResponse=new BasicResponse(ResultCode.SUCCESS,"处理成功");
        UserBO userBean = new UserBO();
        System.out.println(userBO.toString());
        userBean = commonCoreFeignService.GetUserByUserName(userBO.getUserName());
        System.out.println("获取当前用户信息："+userBean);
        //验证登录，获取token
        if(userBean.getUserPasswd().equals(userBO.getUserPasswd())){
            userBO.setId(userBean.getId());
            userBO.setRealName(userBean.getRealName());
            userBO.setIp(httpUtil.getIpAddr(httpServletRequest));
            userBO.setBrower(httpUtil.getLoginInfo().getBrower());
            userBO.setOs(httpUtil.getLoginInfo().getOs());
            //生成token
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            try{
                RedisPO redisPO=new RedisPO();
                redisPO.setKey(token);
                redisPO.setTime(1);
                redisPO.setTimeUnit(TimeUnit.HOURS);
                redisPO.setValue(userBO.getId());
                //保存token,key为token,value为id,有效期为1个小时
                redisFeignService.setCacheObject(redisPO);
                //更新登录信息
                if(commonCoreFeignService.updateUser(userBO)==1){
                    System.out.println("用户登录信息更新成功！");
                }else{
                    System.out.println("用户登录信息更新失败！");
                };
                String content="【登录提醒】尊敬的管理员，您好，用户： "+ userBO.getRealName()+"("+ userBO.getUserName()+") 正使用IP地址： 【"+ HttpUtils.getLoginInfo().getIp()
                        + "】 于 【"+ userBO.getLogintime()+"】 位于 【"+ userBO.getArea()+"】 区域使用 【"
                        + HttpUtils.getLoginInfo().getOs()+"】 操作系统的 【"+ HttpUtils.getLoginInfo().getBrower()+"】 浏览器登录您的系统！";
                EmailPO emailPO=new EmailPO();
                emailPO.setContent(content);
                emailPO.setSubject("【登录提醒】");
//                emailMQFeign.emailSendToAdmin(emailPO);
                basicResponse.setData(token);
                return basicResponse;
            }catch (Exception e){
                System.out.println(e);
            }
        }else{
            basicResponse.setMsg("登录密码错误！！！");
            basicResponse.setCode(ResultCode.USER_LONGIN_ERROR.getCode());
            System.out.println("登录密码错误！！！");
        }
        return basicResponse;
    }

    @PostMapping("/register")
    @ResponseBody
    public BasicResponse register(@RequestBody UserBO userBO) throws Exception {
        System.out.println(userBO.toString());
        UserBO userBO1=new UserBO();
        userBO1.setInviteAuth(userBO.getBeinviteauth());
        if(commonCoreFeignService.GetAllUser(userBO1).isEmpty()){
            return new BasicResponse(ResultCode.ERROR,"邀请码不存在！！！");
        }else{
            userBO1.setInviteAuth(null);
            userBO1.setUserName(userBO.getUserName());
            if(!commonCoreFeignService.GetAllUser(userBO1).isEmpty()){
                return new BasicResponse(ResultCode.ERROR,"用户名已存在！！！");
            }else {
                String password=UUID.randomUUID().toString().replaceAll("-", "");
                userBO.setIp(httpUtil.getIpAddr(httpServletRequest));
                userBO.setBrower(HttpUtils.getLoginInfo().getBrower());
                userBO.setOs(HttpUtils.getLoginInfo().getOs());
                userBO.setRealName("游客");
                userBO.setUserPasswd(password);
                userBO.setInviteAuth(UUID.randomUUID().toString().replaceAll("-", ""));
                System.out.println(userBO);
                if ((commonCoreFeignService.insertUser(userBO) == 1)) {
                    UserModuleVO userModuleVO=new UserModuleVO();
                    System.out.println("==================="+commonCoreFeignService.GetUserByUserName(userBO.getUserName()).getId());
                    userModuleVO.setUserid(commonCoreFeignService.GetUserByUserName(userBO.getUserName()).getId());
                    //初始化只赋予个人模块的权限
                    userModuleVO.setModuleid(1);
                    commonCoreFeignService.insertUserModule(userModuleVO);
                    return new BasicResponse(ResultCode.SUCCESS,password);
                }
            }
        }
        return new BasicResponse(ResultCode.ERROR,"注册失败！！！");
    }

    @GetMapping("/getMessage")
    public String getMessage() throws Exception {
        EmailPO emailPO=new EmailPO();
//        emailMQFeign.emailSendToAdmin(emailPO);
        synchronized(this) {
            sum = sum + 1;
            System.out.println(sum + "你已通过验证：端口号为：" + port);
        }
        return "你已通过验证：端口号为："+ port;
    }
}
