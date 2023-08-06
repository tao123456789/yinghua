package com.yinghua.core.service.Impl;

import com.yinghua.core.domain.bo.WXAccount;
import com.yinghua.core.mapper.WXAccountMapper;
import com.yinghua.core.service.WXAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WXAccountServiceImpl implements WXAccountService {

    @Resource
    WXAccountMapper wxAccountMapper;

    @Override
    public int deleteByPrimaryKey (Integer id) {
        return wxAccountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert (WXAccount record) {
        return wxAccountMapper.insert(record);
    }

    @Override
    public int insertSelective (WXAccount record) {
        return wxAccountMapper.insertSelective(record);
    }

    @Override
    public List<WXAccount> selectBy (WXAccount record) {
        return wxAccountMapper.selectBy(record);
    }

    @Override
    public WXAccount selectByPrimaryKey (Integer id) {
        return wxAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective (WXAccount record) {
        return wxAccountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey (WXAccount record) {
        return wxAccountMapper.updateByPrimaryKey(record);
    }
}
