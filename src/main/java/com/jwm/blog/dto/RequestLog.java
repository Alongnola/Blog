package com.jwm.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

//记录日志的内容,日志内容字符串化
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestLog {
    private String url;
    private String ip;
    private String classMethod;
    private Object[] args;

    @Override
    public String toString() {
        return "{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
