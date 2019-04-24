package cn.stylefeng.guns.wrapper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/23 23:22
 */
public class BusiClassWrapper extends BaseControllerWrapper {
    public BusiClassWrapper(Map<String, Object> single) {
        super(single);
    }

    public BusiClassWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public BusiClassWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public BusiClassWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
