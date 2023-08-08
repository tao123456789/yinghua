package com.yinghua.core.mapper;

import com.yinghua.core.domain.bo.Wxaddress;
import org.mapstruct.Mapper;

@Mapper
public interface WxaddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wxaddress record);

    int insertSelective(Wxaddress record);

    Wxaddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Wxaddress record);

    int updateByPrimaryKey(Wxaddress record);
}