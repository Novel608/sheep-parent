package cn.stylefeng.guns.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.entity.BusiClass;
import cn.stylefeng.guns.entity.Teacher;
import cn.stylefeng.guns.model.BusiClassDto;
import cn.stylefeng.guns.services.interfaces.BusiClassService;
import cn.stylefeng.guns.services.interfaces.TeacherService;
import cn.stylefeng.guns.wrapper.BusiClassWrapper;
import cn.stylefeng.guns.wrapper.TeacherWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/23 22:28
 */
@Controller
@RequestMapping("/busiClass")
public class busiClassController extends BaseController {

    private static final String PREFIX = "/modular/busi_class/";

    @Resource
    private BusiClassService busiClassService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/add")
    public String addClassPage(){
        return PREFIX + "busi_class_add.html";
    }

    @RequestMapping("/addClass")
    @ResponseBody
    public Object addClass(BusiClass busiClass){
        busiClass.setCreateTime(new Date());
        busiClassService.save(busiClass);
        return SUCCESS_TIP;
    }

    @RequestMapping("/edit")
    public String editClassPage(){
        return PREFIX + "busi_class_edit.html";
    }

    @RequestMapping("/editClass")
    @ResponseBody
    public Object editClass(BusiClass busiClass){
        busiClass.setUpdateTime(new Date());
        busiClassService.update(busiClass);
        return SUCCESS_TIP;
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Object getClassDetail(String classId){
        BusiClass busiClass = busiClassService.getById(classId);
        BusiClassDto busiClassDto = new BusiClassDto();
        if (busiClass != null) {
            BeanUtil.copyProperties(busiClass,busiClassDto);
            Teacher teacher = teacherService.getById(busiClass.getHeadMaster());
            busiClassDto.setHeadMasterName(teacher.getName());
        }
        return busiClassDto;
    }

    @RequestMapping("/deleteClass")
    @ResponseBody
    public Object deleteClass(String classId){
        BusiClass busiClass = busiClassService.getById(classId);
        if (busiClass != null) {
            busiClass.setStatus(0);
            busiClass.setUpdateTime(new Date());
            busiClassService.update(busiClass);
        }
        return SUCCESS_TIP;
    }


    @RequestMapping("/list")
    public String listPage(){
        return PREFIX + "busi_class.html";
    }

    @RequestMapping("/getListInfo")
    @ResponseBody
    public Object getListInfo(BusiClass busiClass){
        Page<Map<String, Object>> result = busiClassService.list(busiClass);
        Page<Map<String, Object>> wrap = new BusiClassWrapper(result).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }
}
