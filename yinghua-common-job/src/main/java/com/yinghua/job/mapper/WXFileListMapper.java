package com.yinghua.job.mapper;

import org.mapstruct.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import com.yinghua.job.domain.wx.WXFileList;

import java.util.List;

@Mapper
public interface WXFileListMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WXFileList queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param wxfilelist 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<WXFileList> queryAllByLimit(@Param("wxfilelist") WXFileList wxfilelist, @Param("pageable") Pageable pageable);

    /**
     * 查询数据
     *
     * @param wxfilelist 查询条件
     * @return 对象列表
     */
    List<WXFileList> query(WXFileList wxfilelist);

    /**
     * 统计总行数
     *
     * @param wxfilelist 查询条件
     * @return 总行数
     */
    long count(WXFileList wxfilelist);

    /**
     * 新增数据
     *
     * @param wxfilelist 实例对象
     * @return 影响行数
     */
    int insert(WXFileList wxfilelist);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Wxfilelist> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WXFileList> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Wxfilelist> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WXFileList> entities);

    /**
     * 修改数据
     *
     * @param wxfilelist 实例对象
     * @return 影响行数
     */
    int update(WXFileList wxfilelist);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}