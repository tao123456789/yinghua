package com.yinghua.job.mapper;

import com.yinghua.job.domain.wx.WXDraft;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface WXDraftMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WXDraft queryById(Integer id);

    /**
     * 通过data查询单条数据
     *
     * @param data 主键
     * @return 实例对象
     */
    WXDraft queryByData(String data);

    /**
     * 查询指定行数据
     *
     * @param wxdraft 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<WXDraft> queryAllByLimit(WXDraft wxdraft, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param wxdraft 查询条件
     * @return 总行数
     */
    long count(WXDraft wxdraft);

    /**
     * 新增数据
     *
     * @param wxdraft 实例对象
     * @return 影响行数
     */
    int insert(WXDraft wxdraft);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Wxdraft> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WXDraft> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Wxdraft> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WXDraft> entities);

    /**
     * 修改数据
     *
     * @param wxdraft 实例对象
     * @return 影响行数
     */
    int update(WXDraft wxdraft);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
}