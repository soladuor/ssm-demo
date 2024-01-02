package com.sola.mapper;

import com.sola.pojo.FileMessage;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FileMessageMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(String id);

    /**
     * 增加
     */
    int insert(FileMessage record);

    /**
     * 查询
     */
    FileMessage selectByPrimaryKey(String id);

    /**
     * 查询全部
     */
    List<FileMessage> selectAll();

    /**
     * 修改
     */
    int updateByPrimaryKey(FileMessage record);

    /**
     * 文件下载次数加1
     */
    @Update("UPDATE file_message SET download_count=download_count+1 WHERE id=#{id}")
    void incDownCount(String id);
}