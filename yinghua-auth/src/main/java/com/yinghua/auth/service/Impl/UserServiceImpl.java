package com.yinghua.auth.service.Impl;

import com.yinghua.auth.config.Interface.Authority;
import com.yinghua.auth.feign.CommonCoreFeignService;
import com.yinghua.auth.service.UserService;
import com.yinghua.core.domain.bo.ModuleBO;
import com.yinghua.core.domain.bo.UserBO;
import com.yinghua.core.domain.vo.UserModuleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    CommonCoreFeignService commonCoreFeignService;

    @Override
    public List<UserBO> GetAllUser (UserBO userBO) {
        return commonCoreFeignService.GetAllUser(new UserBO());
    }

    @Override
    public UserBO GetUserByUserId (int userid) {
        return null;
    }

    @Override
    public UserBO GetUserByUserName (String username) {
        return null;
    }

    @Override
    public int insertUser (UserBO user) {
        return 0;
    }

    @Override
    public int deleteUser (int userid) {
        return 0;
    }

    @Override
    public int updateUser (UserBO user) {
        return 0;
    }

    @Override
    public int updateUserInfo (UserBO user) {
        return 0;
    }

    @Override
    public List<UserModuleVO> getUserModuleByUserId (int userid) {
        return null;
    }

    @Override
    public Boolean removeModuleByID (int id) {
        return null;
    }

    @Override
    public List<ModuleBO> getAllModuleList () {
        return null;
    }

    @Override
    public Boolean insertUserModule (UserModuleVO userModuleVO) {
        return null;
    }

    @Override
    public UserBO getCurrentUserInfo () {
        return null;
    }
}
