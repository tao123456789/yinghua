package com.yinghua.job.service.impl;

import com.yinghua.job.domain.wx.WXDraft;
import com.yinghua.job.mapper.WXDraftMapper;
import com.yinghua.job.service.WXDraftService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Wxdraft)表服务实现类
 *
 * @author makejava
 * @since 2023-02-08 22:16:31
 */
@Service
public class WxdraftServiceImpl implements WXDraftService {
    @Resource
    private WXDraftMapper wxdraftDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WXDraft queryById (Integer id) {
        return this.wxdraftDao.queryById(id);
    }

    /**
     * 通过data查询单条数据
     *
     * @param data 主键
     * @return 实例对象
     */
    @Override
    public WXDraft queryByData (String data) {
        return this.wxdraftDao.queryByData(data);
    }

    /**
     * 分页查询
     *
     * @param wxdraft     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<WXDraft> queryByPage (WXDraft wxdraft, PageRequest pageRequest) {
        long total = this.wxdraftDao.count(wxdraft);
        return new PageImpl<>(this.wxdraftDao.queryAllByLimit(wxdraft, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param wxdraft 实例对象
     * @return 实例对象
     */
    @Override
    public WXDraft insert (WXDraft wxdraft) {
        this.wxdraftDao.insert(wxdraft);
        return wxdraft;
    }

    /**
     * 修改数据
     *
     * @param wxdraft 实例对象
     * @return 实例对象
     */
    @Override
    public WXDraft update (WXDraft wxdraft) {
        this.wxdraftDao.update(wxdraft);
        return this.queryById(wxdraft.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById (Integer id) {
        return this.wxdraftDao.deleteById(id) > 0;
    }
}