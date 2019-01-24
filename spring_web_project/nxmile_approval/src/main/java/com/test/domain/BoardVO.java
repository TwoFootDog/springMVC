package com.test.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private Date registDate;
    private Date updateDate;
}
