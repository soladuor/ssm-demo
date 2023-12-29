package com.sola.mapper;

import com.sola.pojo.FileUser;

import java.util.List;

public interface FileUserMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 增加
     */
    int insert(FileUser record);

    /**
     * 查询
     */
    FileUser selectByPrimaryKey(Integer id);

    /**
     * 查询全部
     */
    List<FileUser> selectAll();

    /**
     * 修改
     */
    int updateByPrimaryKey(FileUser record);
}