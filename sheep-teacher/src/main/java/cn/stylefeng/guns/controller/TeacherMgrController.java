package cn.stylefeng.guns.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.entity.Teacher;
import cn.stylefeng.guns.model.TeacherDto;
import cn.stylefeng.guns.services.interfaces.TeacherService;
import cn.stylefeng.guns.wrapper.TeacherWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author zhangjq
 * @date 2019/4/22 21:31
 */
@Controller
@RequestMapping("/teacher")
public class TeacherMgrController extends BaseController {

    private static final String PREFIX = "/modular/teacher/";

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return PREFIX + "teacher.html";
    }

    /**
     * 列表数据信息
     * @param name 条件
     * @param teacherId 教师ID
     * @return 教师列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "teacherId", required = false) String teacherId){
        Page<Map<String, Object>> list = this.teacherService.list(name, teacherId);
        Page<Map<String, Object>> wrap = new TeacherWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    @RequestMapping("/add")
    public String add(){
        return PREFIX + "teacher_add.html";
    }

    /**
     * 添加教师信息
     * @param teacher
     * @return
     */
    @RequestMapping("/addTeacher")
    @ResponseBody
    public Object addTeacher(Teacher teacher){
        this.teacherService.save(teacher);
        return SUCCESS_TIP;
    }

    @RequestMapping("/teacher_edit")
    public String teacherEdit(){
        return PREFIX + "teacher_edit.html";
    }

    @RequestMapping("/getTeacherInfo")
    @ResponseBody
    public TeacherDto getTeacherInfo(String teacherId){
        Teacher teacher = teacherService.getById(teacherId);
        TeacherDto teacherDto = new TeacherDto();
        BeanUtil.copyProperties(teacher,teacherDto);
        return teacherDto;
    }
    /**
     * 编辑教师信息
     * @param teacher 教师信息
     * @return
     */
    @RequestMapping("/editTeacher")
    @ResponseBody
    public Object editTeacher(Teacher teacher){
        this.teacherService.update(teacher);
        return SUCCESS_TIP;
    }

    /**
     * 删除教师信息
     * @param teacherId 教师ID编号数据
     * @return
     */
    @RequestMapping("/deleteTeacher")
    @ResponseBody
    public Object deleteTeacher(String teacherId){
        Teacher teacher = this.teacherService.getById(teacherId);
        if (teacher != null) {
            teacher.setStatus(1);
            this.teacherService.update(teacher);
        }
        return SUCCESS_TIP;
    }

}
