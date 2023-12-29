package com.sola.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileType {


    private Integer id;    // 类型id

    private String typeName;    // 类型名

}
