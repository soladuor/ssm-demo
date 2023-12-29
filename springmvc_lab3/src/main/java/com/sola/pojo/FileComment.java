package com.sola.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileComment {


    private Integer id;    // 评论id

    private String fileId;    // 文件id

    private String message;    // 评论信息

}
