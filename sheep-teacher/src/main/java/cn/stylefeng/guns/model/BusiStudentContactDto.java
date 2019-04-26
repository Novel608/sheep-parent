package cn.stylefeng.guns.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjq
 * @date 2019/4/25 0:01
 */
@Data
public class BusiStudentContactDto implements Serializable {
    private Integer id;

    private Integer studentId;

    private String contactName;

    private String contactTypeName;

    private String contactMobile;

    private Date createTime;

    private Date updateTime;

    private Integer status;
}
