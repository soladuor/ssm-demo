package com.sola.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileUser {


    private Integer id;    // 用户id

    private String username;    // 用户名

    private String password;    // 密码

}
