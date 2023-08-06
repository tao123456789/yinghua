package com.yinghua.mq.feign;

import com.yinghua.core.domain.bo.SettingBO;
import com.yinghua.core.domain.bo.WXAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "yinghua-common-core")
public interface CommonCoreFeignService {

    @PostMapping("/common/setting/getSetting")
    List<SettingBO> getSetting(@RequestBody SettingBO settingBO);

    @PostMapping("/common/setting/updateSettingByName")
    Boolean updateSettingByName(@RequestBody SettingBO settingBO);
}
