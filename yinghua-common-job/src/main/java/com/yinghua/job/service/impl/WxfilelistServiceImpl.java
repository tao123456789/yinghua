package com.yinghua.job.service.impl;

import com.yinghua.job.mapper.WXFileListMapper;
import com.yinghua.job.domain.wx.WXFileList;
import com.yinghua.job.service.WXFileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Wxfilelist)表服务实现类
 *
 * @author makejava
 * @since 2022-12-28 11:12:54
 */
@Service
public class WxfilelistServiceImpl implements WXFileListService {
    @Autowired
    WXFileListMapper wxfilelistMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WXFileList queryById(Integer id) {
        return this.wxfilelistMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param wxfilelist 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<WXFileList> queryByPage(WXFileList wxfilelist, PageRequest pageRequest) {
        long total = this.wxfilelistMapper.count(wxfilelist);
        return new PageImpl<>(this.wxfilelistMapper.queryAllByLimit(wxfilelist, pageRequest), pageRequest, total);
    }

    /**
     * 查询
     *
     * @param wxfilelist 筛选条件
     * @return 查询结果
     */
    @Override
    public List<WXFileList> query(WXFileList wxfilelist) {
        System.out.println("查询条件>>>>>>>>>>"+wxfilelist.getRemark());
        return this.wxfilelistMapper.query(wxfilelist);
    }

    /**
     * 新增数据
     *
     * @param wxfilelist 实例对象
     * @return 实例对象
     */
    @Override
    public WXFileList insert(WXFileList wxfilelist) {
        System.out.println("文件信息："+wxfilelist);
        wxfilelistMapper.insert(wxfilelist);
        return wxfilelist;
    }

    /**
     * 修改数据
     *
     * @param wxfilelist 实例对象
     * @return 实例对象
     */
    @Override
    public WXFileList update(WXFileList wxfilelist) {
        this.wxfilelistMapper.update(wxfilelist);
        return this.queryById(wxfilelist.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.wxfilelistMapper.deleteById(id) > 0;
    }
}
