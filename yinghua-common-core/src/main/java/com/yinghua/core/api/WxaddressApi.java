package com.yinghua.core.api;

import com.yinghua.core.domain.bo.Wxaddress;
import com.yinghua.core.service.Impl.WxaddressServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/common/address")
public class WxaddressApi {

    @Resource
    WxaddressServiceImpl wxaddressServiceImpl;

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey (Integer id) {
        return wxaddressServiceImpl.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert (Wxaddress record) {
        return wxaddressServiceImpl.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective (Wxaddress record) {
        return wxaddressServiceImpl.insertSelective(record);
    }

    @PostMapping("/selectByPrimaryKey")
    public Wxaddress selectByPrimaryKey (Integer id) {
        return wxaddressServiceImpl.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective (Wxaddress record) {
        return wxaddressServiceImpl.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey (Wxaddress record) {
        return wxaddressServiceImpl.updateByPrimaryKey(record);
    }
}
