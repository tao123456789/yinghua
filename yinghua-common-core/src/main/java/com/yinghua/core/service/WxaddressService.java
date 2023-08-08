package com.yinghua.core.service;

import com.yinghua.core.domain.bo.Wxaddress;

public interface WxaddressService {
    int deleteByPrimaryKey(Integer id);

    int insert(Wxaddress record);

    int insertSelective(Wxaddress record);

    Wxaddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Wxaddress record);

    int updateByPrimaryKey(Wxaddress record);
}
