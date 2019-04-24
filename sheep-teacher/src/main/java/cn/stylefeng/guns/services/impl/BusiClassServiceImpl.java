package cn.stylefeng.guns.services.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.entity.BusiClass;
import cn.stylefeng.guns.entity.Teacher;
import cn.stylefeng.guns.mapper.BusiClassMapper;
import cn.stylefeng.guns.services.interfaces.BusiClassService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/23 22:21
 */
@Service
public class BusiClassServiceImpl extends ServiceImpl<BusiClassMapper, BusiClass> implements BusiClassService {

    @Resource
    private BusiClassMapper busiClassMapper;

    @Override
    public Page<Map<String, Object>> list(String name, String shortName) {
        Page page = LayuiPageFactory.defaultPage();
        BusiClass busiClass = new BusiClass();
        busiClass.setName(name);
        busiClass.setShortName(shortName);
        return busiClassMapper.list(page,busiClass);
    }

    @Override
    public BusiClass getById(String classId) {
        return busiClassMapper.selectById(classId);
    }

    @Override
    public void update(BusiClass busiClass) {
        super.updateById(busiClass);
    }

    @Override
    public Page<Map<String, Object>> list(BusiClass busiClass) {
        Page page = LayuiPageFactory.defaultPage();
        return busiClassMapper.list(page,busiClass);
    }


}
