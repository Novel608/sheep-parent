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

    @Override
    public void update(BusiStudent busiStudent) {
        updateById(busiStudent);
    }

    @Override
    public BusiStudent getById(String studentId) {
        Long studentIdNum = Long.valueOf(studentId);
        return studentMapper.selectById(studentIdNum);
    }

    @Resource
    private BusiStudentMapper studentMapper;
}
