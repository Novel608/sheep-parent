package cn.stylefeng.guns.wrapper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/22 23:31
 */
public class TeacherWrapper extends BaseControllerWrapper {
    public TeacherWrapper(Map<String, Object> single) {
        super(single);
    }

    public TeacherWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public TeacherWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public TeacherWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
