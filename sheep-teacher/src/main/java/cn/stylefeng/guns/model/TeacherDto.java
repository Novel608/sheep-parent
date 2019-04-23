package cn.stylefeng.guns.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangjq
 * @date 2019/4/22 22:30
 */
@Data
public class TeacherDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;

    private String name;

    private String career;

    private String remark;

    private String mobile;
}
