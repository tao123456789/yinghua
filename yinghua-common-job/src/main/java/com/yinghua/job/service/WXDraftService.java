package com.yinghua.job.service;

import com.yinghua.job.domain.wx.WXDraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Wxdraft)表服务接口
 *
 * @author makejava
 * @since 2023-02-08 22:16:31
 */
public interface WXDraftService {

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
     * 分页查询
     *
     * @param wxdraft 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<WXDraft> queryByPage(WXDraft wxdraft, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param wxdraft 实例对象
     * @return 实例对象
     */
    WXDraft insert(WXDraft wxdraft);

    /**
     * 修改数据
     *
     * @param wxdraft 实例对象
     * @return 实例对象
     */
    WXDraft update(WXDraft wxdraft);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}
