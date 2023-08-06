package com.yinghua.job.feign;

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


    /**
     * 微信公众号账号
     * @param id
     * @return
     */
    @PostMapping("/common/account/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestBody Integer id);

    @PostMapping("/common/account/insert")
    int insert(@RequestBody WXAccount record);

    @PostMapping("/common/account/insertSelective")
    int insertSelective(@RequestBody WXAccount record);

    @PostMapping("/common/account/selectByPrimaryKey")
    WXAccount selectByPrimaryKey(@RequestBody Integer id);

    @PostMapping("/common/account/selectBy")
    List<WXAccount> selectBy(@RequestBody WXAccount record);

    @PostMapping("/common/account/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(@RequestBody WXAccount record);

    @PostMapping("/common/account/updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody WXAccount record);
}
