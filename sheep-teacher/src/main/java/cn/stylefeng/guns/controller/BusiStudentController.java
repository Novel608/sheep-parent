package cn.stylefeng.guns.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.entity.BusiClass;
import cn.stylefeng.guns.entity.BusiStudent;
import cn.stylefeng.guns.entity.BusiStudentContact;
import cn.stylefeng.guns.model.BusiStudentDto;
import cn.stylefeng.guns.services.interfaces.BusiClassService;
import cn.stylefeng.guns.services.interfaces.BusiStudentContactService;
import cn.stylefeng.guns.services.interfaces.BusiStudentService;
import cn.stylefeng.guns.wrapper.BusiClassWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/24 23:50
 */
@Controller
@RequestMapping("/studentController")
public class BusiStudentController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(BusiStudentController.class);

    @RequestMapping("/deleteStudent")
    @ResponseBody
    public Object deleteStudent(String studentId){
        BusiStudent student = studentService.getById(studentId);
        if (student == null) {
            return new ErrorResponseData("学生信息不存在");
        }
        Long studentIdNum = Long.valueOf(studentId);
        contactService.deleteByStudentId(studentIdNum);
        studentService.delete(studentIdNum);
        return SUCCESS_TIP;
    }

    /**
     * 根据学生ID获取学生信息
     * @param studentId 学生ID
     * @return
     */
    @RequestMapping("/getStudentInfo")
    @ResponseBody
    public Object getStudentInfo(String studentId){
        BusiStudent student = studentService.getById(studentId);
        if (student == null) {
            return new ErrorResponseData("学生信息不存在");
        }
        BusiStudentDto studentDto = new BusiStudentDto();
        BeanUtils.copyProperties(student,studentDto);
        BusiClass classInfo = classService.getById("" + studentDto.getClassId());
        studentDto.setClassName(classInfo.getName());
        return studentDto;
    }

    /**
     * 修订学生信息
     * @param studentStr 学生信息字符串
     * @param contactListStr 学生联系信息字符串内容
     * @return
     */
    @RequestMapping("/editStudent")
    @ResponseBody
    public Object editStudent(String studentStr, String contactListStr){
        BusiStudent busiStudent = JSONObject.parseObject(studentStr, BusiStudent.class);
        if (busiStudent == null) {
            return new ErrorResponseData("学生信息不存在");
        }
        studentService.update(busiStudent);
        List<BusiStudentContact> contactList = JSONArray.parseArray(contactListStr,BusiStudentContact.class);
        contactService.saveUpdateBatch(contactList, busiStudent.getId());
        return SUCCESS_TIP;
    }

    @RequestMapping("/edit")
    public String edit(){
        return PREFIX + "student_edit.html";
    }


    @RequestMapping("/addStudent")
    @ResponseBody
    public Object addStudent(String student,String contactList){
        BusiStudent busiStudent = JSONObject.parseObject(student, BusiStudent.class);
        studentService.save(busiStudent);
        Long studentId = busiStudent.getId();
        logger.info("studentId:{}===={}", studentId, contactList);
        List<BusiStudentContact> studentContactList = JSONArray.parseArray(contactList, BusiStudentContact.class);
        for (BusiStudentContact temp : studentContactList){
            if (temp == null) {
                continue;
            }
            temp.setStudentId(studentId);
        }
        contactService.saveBatch(studentContactList,studentContactList.size());
        return SUCCESS_TIP;
    }

    /**
     * 获取学生对应的联系信息
     * @param studentId 学生ID
     * @return
     */
    @RequestMapping("/getContactList")
    @ResponseBody
    public Object getContactList(@RequestParam String studentId){
        if ("null".equals(studentId)) {
            Page<Map<String, Object>> result = new Page<>();
            return LayuiPageFactory.createPageInfo(result);
        }
        Long studentIdNum = Long.valueOf(studentId);
        Page<Map<String, Object>> result = contactService.list(studentIdNum);
        Page<Map<String, Object>> wrap = new BusiClassWrapper(result).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    @RequestMapping("/add")
    public String add(){
        return PREFIX + "student_add.html";
    }

    @RequestMapping("/list")
    public String list(){
        return PREFIX + "student_list.html";
    }

    /**
     * 获取学生列表数据信息
     * @param student 学生信息对应的查询条件
     * @return
     */
    @RequestMapping("/getListInfo")
    @ResponseBody
    public Object getListInfo(BusiStudent student){
        Page<Map<String, Object>> result = studentService.list(student);
        Page<Map<String, Object>> wrap = new BusiClassWrapper(result).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    private static final String PREFIX = "/modular/student/";

    @Resource
    private BusiStudentService studentService;
    @Resource
    private BusiStudentContactService contactService;
    @Resource
    private BusiClassService classService;
}
