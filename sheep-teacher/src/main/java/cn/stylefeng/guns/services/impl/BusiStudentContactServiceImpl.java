package cn.stylefeng.guns.services.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.entity.BusiStudentContact;
import cn.stylefeng.guns.mapper.BusiStudentContactMapper;
import cn.stylefeng.guns.services.interfaces.BusiStudentContactService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/25 0:13
 */
@Service
public class BusiStudentContactServiceImpl extends ServiceImpl<BusiStudentContactMapper, BusiStudentContact> implements BusiStudentContactService {
    @Resource
    private  BusiStudentContactMapper contactMapper;
    @Override
    public Page<Map<String, Object>> list(Integer studentId) {
        Page page = LayuiPageFactory.defaultPage();
        return contactMapper.listByStudentId(studentId);
    }

    @Override
    public boolean saveBatch(List<BusiStudentContact> studentContactList, int size) {
        return super.saveBatch(studentContactList,size > 30? 30 : size);
    }
}
