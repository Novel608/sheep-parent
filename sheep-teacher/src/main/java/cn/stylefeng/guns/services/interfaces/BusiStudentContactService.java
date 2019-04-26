package cn.stylefeng.guns.services.interfaces;

import cn.stylefeng.guns.entity.BusiStudentContact;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/25 0:13
 */
public interface BusiStudentContactService {
    Page<Map<String, Object>> list(Integer studentId);

    boolean saveBatch(List<BusiStudentContact> studentContactList, int size);
}
