package cn.stylefeng.guns.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjq
 * @date 2019/4/23 16:51
 */
@Data
public class BusiClassDto implements Serializable {

    private Integer id;

    private String name;

    private String shortName;

    private String headMaster;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Integer status;
}
