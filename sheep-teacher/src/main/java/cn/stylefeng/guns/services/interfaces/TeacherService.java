package cn.stylefeng.guns.services.interfaces;

import cn.stylefeng.guns.entity.Teacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/22 22:22
 */
public interface TeacherService {
    Page<Map<String, Object>> list(String name, String teacherId);

    boolean save(Teacher teacher);

    Teacher getById(String teacherId);

    void update(Teacher teacher);
}
