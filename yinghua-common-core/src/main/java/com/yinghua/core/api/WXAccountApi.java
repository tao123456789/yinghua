package com.yinghua.core.api;

import com.yinghua.core.domain.bo.WXAccount;
import com.yinghua.core.service.Impl.WXAccountServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/common/account")
public class WXAccountApi {
    @Resource
    WXAccountServiceImpl wxAccountService;

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey (Integer id) {
        return wxAccountService.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert (WXAccount record) {
        return wxAccountService.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective (WXAccount record) {
        return wxAccountService.insertSelective(record);
    }

    @PostMapping("/selectByPrimaryKey")
    public WXAccount selectByPrimaryKey (Integer id) {
        return wxAccountService.selectByPrimaryKey(id);
    }

    @PostMapping("/selectBy")
    public List<WXAccount> selectBy (WXAccount record) {
        return wxAccountService.selectBy(record);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective (WXAccount record) {
        return wxAccountService.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey (WXAccount record) {
        return wxAccountService.updateByPrimaryKey(record);
    }
}
