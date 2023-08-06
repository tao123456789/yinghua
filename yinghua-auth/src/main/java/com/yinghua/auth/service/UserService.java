package com.yinghua.auth.service;

import com.yinghua.core.domain.bo.ModuleBO;
import com.yinghua.core.domain.bo.UserBO;
import com.yinghua.core.domain.vo.UserModuleVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserService {
    List<UserBO> GetAllUser(@RequestBody UserBO userBO);

    UserBO GetUserByUserId(@RequestParam("userid")int userid);

    UserBO GetUserByUserName(@RequestParam("username") String username);

    int insertUser(@RequestBody UserBO user);

    int deleteUser(int userid);

    int updateUser(@RequestBody UserBO user);

    int updateUserInfo(@RequestBody UserBO user);

    List<UserModuleVO> getUserModuleByUserId(@RequestParam("userid")int userid);

    Boolean removeModuleByID(@RequestParam("id")int id);

    List<ModuleBO> getAllModuleList();

    Boolean insertUserModule(@RequestBody UserModuleVO userModuleVO);

    UserBO getCurrentUserInfo();
}
