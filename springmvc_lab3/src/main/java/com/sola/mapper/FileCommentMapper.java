package com.sola.mapper;

import com.sola.pojo.FileComment;

import java.util.List;

public interface FileCommentMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 增加
     */
    int insert(FileComment record);

    /**
     * 查询
     */
    FileComment selectByPrimaryKey(Integer id);

    /**
     * 查询全部
     */
    List<FileComment> selectAll();

    /**
     * 修改
     */
    int updateByPrimaryKey(FileComment record);
}