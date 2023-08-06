package com.yinghua.core.api;

import com.yinghua.core.domain.bo.SettingBO;
import com.yinghua.core.service.Impl.SettingServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/common/setting")
public class SettingApi {

    @Resource
    SettingServiceImpl settingService;

    @PostMapping("/getSetting")
    public List<SettingBO> getSetting (@RequestBody SettingBO settingBO) {
        return settingService.getSetting(settingBO);
    }

    @PostMapping("/updateSettingByName")
    public Boolean updateSettingByName (@RequestBody SettingBO settingBO) {
        return settingService.updateSettingByName(settingBO);
    }
}
