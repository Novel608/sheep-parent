package cn.stylefeng.guns.mapper;

import cn.stylefeng.guns.entity.BusiStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/25 0:10
 */
public interface BusiStudentMapper extends BaseMapper<BusiStudent> {
    Page<Map<String, Object>> list(Page page, @Param("student") BusiStudent student);
}
