package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zhangjq
 * @date 2019/4/26 10:42
 */
@Controller
@RequestMapping("/fileController")
public class FileController extends BaseController {

    @RequestMapping("/uploadFile")
    @BussinessLog(value = "文件上传")
    @ResponseBody
    public Object uploadFile(MultipartHttpServletRequest request) throws IOException {
        MultipartFile file = request.getFile("file");
        if (file == null) {
            return new ErrorResponseData("未上传任何文件信息");
        }
        String saveFilePath = request.getServletContext().getRealPath("upload");
        String fileName = UUID.randomUUID().toString().replace("-","");
        String originalFilename = file.getOriginalFilename();
        String ext = "";
        if (!StringUtils.isEmpty(originalFilename) && originalFilename.contains(".")) {
            ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        File directorFile = new File(saveFilePath);
        if (!directorFile.exists()) {
            directorFile.mkdirs();
        }
        String absoluteFilePath = saveFilePath + File.separator + fileName + ext;
        file.transferTo(new File(absoluteFilePath));
        SuccessResponseData successResponseData = new SuccessResponseData();
        String projectPath = request.getServletContext().getRealPath("/");
        successResponseData.setData(absoluteFilePath.replace(projectPath,""));
        return successResponseData;
    }
}
