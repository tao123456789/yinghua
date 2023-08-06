package com.yinghua.core.mapper;

import com.yinghua.core.domain.bo.WXAccount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WXAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WXAccount record);

    int insertSelective(WXAccount record);

    WXAccount selectByPrimaryKey(Integer id);

    List<WXAccount> selectBy(WXAccount record);

    int updateByPrimaryKeySelective(WXAccount record);

    int updateByPrimaryKey(WXAccount record);
}