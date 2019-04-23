package cn.stylefeng.guns.mapper;

import cn.stylefeng.guns.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/22 22:29
 */
public interface TeacherMapper extends BaseMapper<Teacher> {
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("name") String name, @Param("teacherId") String teacherId);
}
