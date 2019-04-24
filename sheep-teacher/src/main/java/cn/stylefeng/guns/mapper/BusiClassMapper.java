package cn.stylefeng.guns.mapper;

import cn.stylefeng.guns.entity.BusiClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/23 22:15
 */
public interface BusiClassMapper extends BaseMapper<BusiClass> {

    Page<Map<String, Object>> list(@Param("page")Page page, @Param("busiClass") BusiClass busiClass);
}
