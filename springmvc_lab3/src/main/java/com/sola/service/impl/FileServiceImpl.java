package com.sola.service.impl;

import com.sola.mapper.*;
import com.sola.pojo.FileMessage;
import com.sola.pojo.FileSpace;
import com.sola.pojo.FileType;
import com.sola.service.FileService;
import com.sola.utils.MapperTools;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    FileMessageMapper fileMessageMapper() {
        return MapperTools.getMapper(FileMessageMapper.class);
    }

    FileTypeMapper fileTypeMapper() {
        return MapperTools.getMapper(FileTypeMapper.class);
    }

    FileSpaceMapper fileSpaceMapper() {
        return MapperTools.getMapper(FileSpaceMapper.class);
    }

    @Override
    public List<FileMessage> getAllFileList() {
        return fileMessageMapper().selectAll();
    }

    @Override
    public void upload(String uuid, double size, Integer userId, String filename, String typeName) {

        // 当前用户的空间
        FileSpace fileSpace = fileSpaceMapper().selectByUserId(userId);
        // 文件类型
        FileType fileTypeByName = getTypeByName(typeName);
        // 文件路径
        String filePath = "/file/download/" + typeName + "/";
        // 组装文件信息
        // FileMessage fileMessage = new FileMessage();
        // fileMessage.setId(uuid); // 文件编号
        // fileMessage.setSpaceId(fileSpace.getId());  // 上传空间
        // fileMessage.setFileName(filename); // 文件名
        // fileMessage.setFilePath(filePath); // 文件路径
        // fileMessage.setUploaderId(userId); // 上传者
        // fileMessage.setUpdateTime(new Date()); // 上传时间
        // fileMessage.setFileSize( size); // 文件大小
        // fileMessage.setDownloadCount(0); // 下载次数
        // fileMessage.setTypeId(fileTypeByName.getId()); // 文件类型
        FileMessage fileMessage = new FileMessage(
                uuid, fileSpace.getId(), filename, filePath,
                userId, new Date(), size, 0, fileTypeByName
        );
        fileMessageMapper().insert(fileMessage);
        // 占用空间
        fileSpaceMapper().updateSpaceSize(fileMessage.getSpaceId(), -size);
    }

    @Override
    public FileSpace getFileSpaceIdByUserId(Integer userId) {
        return fileSpaceMapper().selectByUserId(userId);
    }

    @Override
    public FileType getTypeByName(String typeName) {
        FileType type = fileTypeMapper().selectByTypeName(typeName);
        // 没找到则新建
        if (type == null) {
            FileType fileType = new FileType(null, typeName);
            fileTypeMapper().insert(fileType);
            type = fileTypeMapper().selectByTypeName(typeName);
        }
        return type;
    }

    @Override
    public FileMessage getFileMessageById(String fileId) {
        return fileMessageMapper().selectByPrimaryKey(fileId);
    }

    @Override
    public void fileDownload(String fileId) {
        fileMessageMapper().incDownCount(fileId);
    }

    @Override
    public int deleteFile(String fileId) {
        FileMessage fileMessage = getFileMessageById(fileId);
        int i = fileMessageMapper().deleteByPrimaryKey(fileId);
        // 释放空间
        int j = fileSpaceMapper().updateSpaceSize(fileMessage.getSpaceId(), +fileMessage.getFileSize());
        if (i > 0 && j > 0) {
            return i + j;
        }
        return 0;
    }


}
