package cn.stylefeng.guns.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangjq
 * @date 2019/4/25 0:02
 */
@Data
public class BusiStudentDto implements Serializable {
    private Long id;

    private String name;

    private String beforeName;

    private Integer age;

    private Integer classId;

    @TableField(exist = false)
    private String className;

    private String address;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private String picUrl;
}
