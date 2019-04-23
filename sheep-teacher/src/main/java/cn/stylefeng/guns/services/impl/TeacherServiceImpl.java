package cn.stylefeng.guns.services.impl;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.entity.Teacher;
import cn.stylefeng.guns.mapper.TeacherMapper;
import cn.stylefeng.guns.services.interfaces.TeacherService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author zhangjq
 * @date 2019/4/22 22:22
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Page<Map<String, Object>> list(String name, String teacherId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, name, teacherId);
    }

    @Override
    public Teacher getById(String teacherId) {
        return this.teacherMapper.selectById(teacherId);
    }

    @Override
    public void update(Teacher teacher) {
        this.teacherMapper.updateById(teacher);
    }

    @Override
    public boolean saveBatch(Collection<Teacher> entityList) {
        super.saveBatch(entityList);
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Teacher> entityList) {
        return false;
    }

    @Override
    public boolean update(Wrapper<Teacher> updateWrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Teacher> entityList) {
        return false;
    }

    @Override
    public Teacher getOne(Wrapper<Teacher> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Teacher> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public List<Teacher> list() {
        return null;
    }

    @Override
    public IPage<Teacher> page(IPage<Teacher> page) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return null;
    }

    @Override
    public List<Object> listObjs() {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public List<Object> listObjs(Wrapper<Teacher> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<Teacher> page) {
        return null;
    }

    @Override
    public QueryChainWrapper<Teacher> query() {
        return null;
    }

    @Override
    public LambdaQueryChainWrapper<Teacher> lambdaQuery() {
        return null;
    }

    @Override
    public UpdateChainWrapper<Teacher> update() {
        return null;
    }

    @Override
    public LambdaUpdateChainWrapper<Teacher> lambdaUpdate() {
        return null;
    }
}
