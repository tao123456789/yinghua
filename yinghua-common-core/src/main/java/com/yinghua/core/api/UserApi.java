package com.yinghua.core.api;

import com.yinghua.core.domain.bo.ModuleBO;
import com.yinghua.core.domain.bo.UserBO;
import com.yinghua.core.domain.vo.UserModuleVO;
import com.yinghua.core.mapper.ModuleMapper;
import com.yinghua.core.mapper.UserMapper;
import com.yinghua.core.utils.userUtil.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/common/core/user")
public class UserApi{
    @Resource
    UserMapper userMapper;
    @Resource
    ModuleMapper moduleMapper;
    @Resource
    UserUtil userUtil;

    @PostMapping("/getAllUser")
    public List<UserBO> GetAllUser(@RequestBody UserBO userBO){
        return userMapper.GetAllUser(userBO);
    }

    @GetMapping("/getUserByUserId")
    public UserBO GetUserByUserId(int userid){
        return userMapper.GetUserByUserId(userid);
    }

    @PostMapping("/getUserByUserName")
    public UserBO GetUserByUserName(@RequestBody String username) {
        System.out.println("获取用户信息："+userMapper.GetUserByUserName(username).toString());
        return userMapper.GetUserByUserName(username);
    }
    public int deleteUser(int nameid){
        return userMapper.deleteUser(nameid);
    }

    @PostMapping("/updateUser")
    public int updateUser(@RequestBody UserBO user){
        System.out.println(user.toString());
        return userMapper.updateUser(user);
    }
    @PostMapping("/updateUserInfo")
    public int updateUserInfo(@RequestBody UserBO user){
        return userMapper.updateUserInfo(user);
    }

    @PostMapping("/insertUser")
    public int insertUser(@RequestBody UserBO user){
        return userMapper.insertUser(user);
    }

    @PostMapping("/getUserModuleByUserId")
    public List<UserModuleVO> getUserModuleByUserId(@RequestBody int userid) {
        System.out.println(userid+"获取用户模块数据");
        return moduleMapper.getUserModuleByUserId(userid);
    }

    @GetMapping("/getModule")
    public List<UserModuleVO> GetModule() {
        System.out.println("获取当前用户模块数据");
        int userid=userUtil.getCurrentUserInfo().getId();
        //获取用户模块权限
        return moduleMapper.getUserModuleByUserId(userid);
    }

    @PostMapping("/removeModuleByID")
    public Boolean removeModuleByID (@RequestBody int id) {
        return moduleMapper.removeModuleByID(id);
    }

    @PostMapping("/getAllModuleList")
    public List<ModuleBO> getAllModuleList () {
        return moduleMapper.getAllModuleList();
    }

    @PostMapping("/insertUserModule")
    public Boolean insertUserModule (UserModuleVO userModuleVO) {
        return moduleMapper.insertUserModule(userModuleVO.getUserid(),userModuleVO.getModuleid());
    }

    @GetMapping("/getCurrentUserInfo")
    public UserBO getCurrentUserInfo () {
        return userUtil.getCurrentUserInfo();
    }
}
