package cn.stylefeng.guns.mapper;

import cn.stylefeng.guns.entity.BusiStudentContact;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/25 0:14
 */
public interface BusiStudentContactMapper extends BaseMapper<BusiStudentContact> {
    Page<Map<String, Object>> listByStudentId(Integer studentId);
}
