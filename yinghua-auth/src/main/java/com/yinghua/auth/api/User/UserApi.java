package com.yinghua.auth.api.User;


import com.yinghua.auth.config.Interface.Authority;
import com.yinghua.auth.service.Impl.UserServiceImpl;
import com.yinghua.core.domain.bo.ModuleBO;
import com.yinghua.core.domain.bo.UserBO;
import com.yinghua.core.domain.vo.UserModuleVO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Authority
@RestController
@RequestMapping("/auth/user")
public class UserApi {
    @Resource
    private UserServiceImpl userService;

    @GetMapping("/getAllUser")
    public List<UserBO> GetAllUser(){
        UserBO userBO=new UserBO();
        List<UserBO> userBOList = userService.GetAllUser(userBO);
        if (userBOList.isEmpty()) {
            System.out.println("查无此人！！！");
        }
        return userBOList;
    }

    @GetMapping("/getUserByUserId")
    public UserBO GetUserByUserId(){
        return userService.GetUserByUserId(userService.getCurrentUserInfo().getId());
    }

    @GetMapping("/getUserByUserName/{username}")
    public UserBO GetUserByUesrName(@PathVariable String username){
//        System.out.println("将要获取的username:"+id);
//        System.out.println("获取到的username:"+userService.GetUserByName(id));
        return userService.GetUserByUserName(username);
    }

    @PostMapping("/updateUserInfo")
    public Boolean updateUserInfo(@RequestBody UserBO userBO){
        if(userBO.getUserPasswd()==""){
            userBO.setUserPasswd(null);
        }
        userBO.setId(userService.getCurrentUserInfo().getId());
        int i=userService.updateUserInfo(userBO);
        System.out.println("更新？："+i);
        return (i==1);
    }

    @GetMapping("/GetModule")
    public List<UserModuleVO> GetModule(){
        int userid=userService.getCurrentUserInfo().getId();
        //获取用户模块权限
        List<UserModuleVO> userModuleVOList=userService.getUserModuleByUserId(userid);
        System.out.println("用户获取模块权限："+userModuleVOList.toString());
        return userModuleVOList;
    }

    @GetMapping("/GetAllModuleList")
    public List<ModuleBO> GetAllModuleList(){
        List<ModuleBO> AllModuleList=userService.getAllModuleList();
        System.out.println("模块列表："+AllModuleList.toString());
        return AllModuleList;
    }

    @GetMapping("/GetModuleByUserId")
    public List<UserModuleVO> GetModuleByUserId(int userid){
        //获取用户模块权限
        List<UserModuleVO> userModuleVOList=userService.getUserModuleByUserId(userid);
        System.out.println("用户获取模块权限："+userModuleVOList.toString());
        return userModuleVOList;
    }

    @GetMapping("/removeModule")
    public Boolean removeModule(int id){
        return userService.removeModuleByID(id);
    }

    @GetMapping("/insertUserModule")
    public Boolean insetUserModule(UserModuleVO userModuleVO){
        return userService.insertUserModule(userModuleVO);
    }
}
