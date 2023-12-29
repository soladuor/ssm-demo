package com.sola.mapper;

import com.sola.pojo.FileType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FileTypeMapper {
    /**
     * 删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 增加
     */
    int insert(FileType record);

    /**
     * 查询
     */
    FileType selectByPrimaryKey(Integer id);

    /**
     * 根据类型名查询
     */
    @Select("SELECT id, type_name FROM file_type WHERE type_name= #{typeName}")
    FileType selectByTypeName(String typeName);

    /**
     * 查询全部
     */
    List<FileType> selectAll();

    /**
     * 修改
     */
    int updateByPrimaryKey(FileType record);
}