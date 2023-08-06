package com.yinghua.core.mapper;

import com.yinghua.core.domain.bo.SettingBO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface SettingMapper {
    List<SettingBO> getSetting(SettingBO settingBO);
    Boolean updateSettingByName(SettingBO settingBO);
}
