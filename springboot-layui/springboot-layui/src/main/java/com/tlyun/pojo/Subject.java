package com.tlyun.pojo;

import lombok.Data;

@Data
public class Subject {
    private Integer subject_id;
    private String subject_title;
    private String subject_desc;
    private Integer subject_banner;
    private Integer order_by;
    private Integer subject_status;
    private String remark;
}
