package com.yinghua.core.service.Impl;

import com.yinghua.core.mapper.SettingMapper;
import com.yinghua.core.domain.bo.SettingBO;
import com.yinghua.core.service.SettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {
    @Resource
    SettingMapper settingMapper;

    @Override
    public Boolean updateSettingByName (SettingBO settingBO) {
        return settingMapper.updateSettingByName(settingBO);
    }

    @Override
    public List<SettingBO> getSetting (SettingBO settingBO) {
        System.out.println("获取配置变量："+settingBO.getName()+" : "+settingBO.getCode());
        return settingMapper.getSetting(settingBO);
    }
}
