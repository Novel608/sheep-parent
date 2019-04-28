package cn.stylefeng.guns.services.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.entity.BusiStudentContact;
import cn.stylefeng.guns.mapper.BusiStudentContactMapper;
import cn.stylefeng.guns.services.interfaces.BusiStudentContactService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
    public Page<Map<String, Object>> list(Long studentId) {
        Page page = LayuiPageFactory.defaultPage();
        return contactMapper.listByStudentId(page,studentId);
    }

    @Override
    public boolean saveBatch(List<BusiStudentContact> studentContactList, int size) {
        return super.saveBatch(studentContactList,size > 30? 30 : size);
    }

    @Override
    public void saveUpdateBatch(List<BusiStudentContact> contactList, Long studentId) {
        List<BusiStudentContact> tempContactList = contactMapper.selectList(studentId);
        if (!CollectionUtil.isEmpty(tempContactList)) {
            List<Long> deleteContactIdList = new ArrayList<>();
            Map<Long,Long> tempMap = new HashMap<>();
            for (BusiStudentContact contact : contactList){
                if (contact == null) {
                    continue;
                }
                if (contact.getStatus() == null) {
                    contact.setStudentId(studentId);
                    contactMapper.insert(contact);
                } else {
                    tempMap.put(contact.getId(),contact.getId());
                    contactMapper.updateById(contact);
                }
            }
            for (BusiStudentContact contact : tempContactList){
                if (!tempMap.containsKey(contact.getId())) {
                    deleteContactIdList.add(contact.getId());
                }
            }
            if (!CollectionUtil.isEmpty(deleteContactIdList)) {
                contactMapper.deleteBatchIds(deleteContactIdList);
            }
        } else {
            for(BusiStudentContact contact : contactList){
                contact.setStudentId(studentId);
            }
            saveBatch(contactList);
        }

    }
}
