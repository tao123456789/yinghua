package com.yinghua.core.service.Impl;

import com.yinghua.core.domain.bo.Wxaddress;
import com.yinghua.core.mapper.WxaddressMapper;
import com.yinghua.core.service.WxaddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WxaddressServiceImpl implements WxaddressService {

    @Resource
    WxaddressMapper wxaddressMapper;

    @Override
    public int deleteByPrimaryKey (Integer id) {
        return wxaddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert (Wxaddress record) {
        return wxaddressMapper.insert(record);
    }

    @Override
    public int insertSelective (Wxaddress record) {
        return wxaddressMapper.insertSelective(record);
    }

    @Override
    public Wxaddress selectByPrimaryKey (Integer id) {
        return wxaddressMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective (Wxaddress record) {
        return wxaddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey (Wxaddress record) {
        return wxaddressMapper.updateByPrimaryKey(record);
    }
}
