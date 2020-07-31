package com.springboot.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InterviewData {
    String interviewer;
    String place;
    String time;
}
