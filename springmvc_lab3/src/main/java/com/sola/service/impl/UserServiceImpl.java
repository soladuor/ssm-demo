package com.sola.service.impl;

import com.sola.mapper.FileSpaceMapper;
import com.sola.mapper.FileUserMapper;
import com.sola.pojo.FileSpace;
import com.sola.pojo.FileUser;
import com.sola.service.UserService;
import com.sola.utils.MapperTools;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    FileUserMapper fileUserMapper() {
        return MapperTools.getMapper(FileUserMapper.class);
    }

    FileSpaceMapper fileSpaceMapper() {
        return MapperTools.getMapper(FileSpaceMapper.class);
    }

    @Override
    public FileUser signup(FileUser user) {
        FileUser findUser = fileUserMapper().selectByPrimaryKey(user.getId());
        // 用户不存在
        if (findUser == null) {
            // 用户信息
            fileUserMapper().insert(user);
            // 开辟空间
            System.out.println(createFileSpace(user.getId()) > 1 ? "用户开辟空间" : "开辟空间失败");
            return user;
        }
        // 用户已存在
        return null;
    }

    @Override
    public FileUser login(FileUser user) {
        FileUser fileUser = fileUserMapper().selectByPrimaryKey(user.getId());
        // 判断用户是否存在
        if (fileUser != null) {
            // 判断密码是否正确
            if (fileUser.getPassword().equals(user.getPassword())) {
                return fileUser;
            }
        }
        return null;
    }

    @Override
    public FileSpace getFileSpace(Integer userId) {
        return fileSpaceMapper().selectByUserId(userId);
    }

    @Override
    public Integer createFileSpace(Integer userId) {
        FileSpace fileSpace = new FileSpace();// id自增
        fileSpace.setSpaceSize(5 * 1024 * 1024d); // 5*1024*1024B=5*1024KB=5MB
        fileSpace.setUserId(userId);
        return fileSpaceMapper().insert(fileSpace);
    }
}
