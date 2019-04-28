package cn.stylefeng.guns.services.interfaces;

import cn.stylefeng.guns.entity.BusiStudent;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/25 0:11
 */
public interface BusiStudentService {
    Page<Map<String, Object>> list(BusiStudent student);

    boolean save(BusiStudent student);

    void update(BusiStudent busiStudent);

    BusiStudent getById(String studentId);
}
