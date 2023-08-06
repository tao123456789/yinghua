package com.yinghua.core.service;

import com.yinghua.core.domain.bo.WXAccount;

import java.util.List;

public interface WXAccountService {
    int deleteByPrimaryKey(Integer id);

    int insert(WXAccount record);

    int insertSelective(WXAccount record);

    WXAccount selectByPrimaryKey(Integer id);

    List<WXAccount> selectBy(WXAccount record);

    int updateByPrimaryKeySelective(WXAccount record);

    int updateByPrimaryKey(WXAccount record);
}
