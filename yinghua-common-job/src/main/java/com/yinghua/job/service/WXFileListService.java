package com.yinghua.job.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.yinghua.job.domain.wx.WXFileList;

import java.util.List;

/**
 * (Wxfilelist)表服务接口
 *
 * @author makejava
 * @since 2022-12-28 11:12:52
 */

public interface WXFileListService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WXFileList queryById(Integer id);

    /**
     * 分页查询
     *
     * @param wxfilelist 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<WXFileList> queryByPage(WXFileList wxfilelist, PageRequest pageRequest);

    /**
     * 查询
     *
     * @param wxfilelist 筛选条件
     * @return 查询结果
     */
    List<WXFileList> query(WXFileList wxfilelist);

    /**
     * 新增数据
     *
     * @param wxfilelist 实例对象
     * @return 实例对象
     */
    WXFileList insert(WXFileList wxfilelist);

    /**
     * 修改数据
     *
     * @param wxfilelist 实例对象
     * @return 实例对象
     */
    WXFileList update(WXFileList wxfilelist);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}
