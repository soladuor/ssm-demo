package com.sola.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileMessage {


    private String id;    // 文件编号

    private Integer spaceId;    // 空间 外键

    private String fileName;    // 真实文件名

    private String filePath;    // 存放文件名（路径）

    private Integer uploaderId;    // 上传人 外键

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date updateTime;    // 上传时间

    private Double fileSize;    // 文件大小（单位字节）

    private Integer downloadCount;    // 下载次数

    private FileType fileType;    // 文件类型 外键

}
