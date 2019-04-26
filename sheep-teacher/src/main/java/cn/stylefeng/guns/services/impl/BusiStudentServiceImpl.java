package cn.stylefeng.guns.services.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.entity.BusiStudent;
import cn.stylefeng.guns.mapper.BusiStudentMapper;
import cn.stylefeng.guns.services.interfaces.BusiStudentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/25 0:11
 */
@Service
public class BusiStudentServiceImpl extends ServiceImpl<BusiStudentMapper, BusiStudent> implements BusiStudentService {
    @Override
    public Page<Map<String, Object>> list(BusiStudent student) {
        Page page = LayuiPageFactory.defaultPage();
        return studentMapper.list(page,student);
    }

    @Resource
    private BusiStudentMapper studentMapper;
}
