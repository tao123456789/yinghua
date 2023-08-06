package com.yinghua.auth.feign;

import com.yinghua.core.domain.bo.ModuleBO;
import com.yinghua.core.domain.bo.SettingBO;
import com.yinghua.core.domain.bo.UserBO;
import com.yinghua.core.domain.bo.WXAccount;
import com.yinghua.core.domain.vo.UserModuleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "yinghua-common-core")
public interface CommonCoreFeignService {

    @PostMapping("/common/setting/getSetting")
    List<SettingBO> getSetting(@RequestBody SettingBO settingBO);

    @PostMapping("/common/setting/updateSettingByName")
    Boolean updateSettingByName(@RequestBody SettingBO settingBO);



    @PostMapping("/common/core/GetAllUser")
    List<UserBO> GetAllUser(@RequestBody UserBO userBO);

    @PostMapping("/common/core/GetUserByUserId")
    UserBO GetUserByUserId(@RequestParam("userid")int userid);

    @PostMapping("/common/core/GetUserByUserName")
    UserBO GetUserByUserName(@RequestParam("username") String username);

    @PostMapping("/common/core/insertUser")
    int insertUser(@RequestBody UserBO user);

    @PostMapping("/common/core/deleteUser")
    int deleteUser(int userid);

    @PostMapping("/common/core/updateUser")
    int updateUser(@RequestBody UserBO user);

    @PostMapping("/common/core/updateUserInfo")
    int updateUserInfo(@RequestBody UserBO user);

    @PostMapping("/common/core/getUserModuleByUserId")
    List<UserModuleVO> getUserModuleByUserId(@RequestParam("userid")int userid);

    @PostMapping("/common/core/removeModuleByID")
    Boolean removeModuleByID(@RequestParam("id")int id);

    @PostMapping("/common/core/getAllModuleList")
    List<ModuleBO> getAllModuleList();

    @PostMapping("/common/core/insertUserModule")
    Boolean insertUserModule(@RequestBody UserModuleVO userModuleVO);

    @GetMapping("/common/core/getCurrentUserInfo")
    UserBO getCurrentUserInfo();

}
