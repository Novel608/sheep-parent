package cn.stylefeng.guns.services.interfaces;

import cn.stylefeng.guns.entity.BusiClass;
import cn.stylefeng.guns.entity.Teacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/23 22:21
 */
public interface BusiClassService {
    Page<Map<String, Object>> list(String name, String shortName);

    boolean save(BusiClass busiClass);

    BusiClass getById(String classId);

    void update(BusiClass busiClass);

    Page<Map<String, Object>> list(BusiClass busiClass);
}
