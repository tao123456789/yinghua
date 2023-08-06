package com.yinghua.core.service;

import com.yinghua.core.domain.bo.SettingBO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SettingService {
    List<SettingBO> getSetting(@RequestBody SettingBO settingBO);
    Boolean updateSettingByName(@RequestBody SettingBO settingBO);
}
