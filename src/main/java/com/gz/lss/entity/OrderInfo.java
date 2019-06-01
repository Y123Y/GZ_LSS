package com.gz.lss.entity;

import lombok.Data;


/**
 * @ClassName OrderInfo
 * @Author Y
 * @Date 2019/5/25 19:09
 * @Description TODO
 */
@Data
public class OrderInfo {
    private Integer order_id;
    private String login_name;
    private String create_time;
    private String content;
    private String name;
    private String tel;
    private String remarks;
    private Integer state;
}
