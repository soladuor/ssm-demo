package com.sola.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileSpace {


    private Integer id;    // 空间id

    private Integer userId;    // 用户 外键

    private Double spaceSize;    // 空间大小（单位字节）

}
