package com.tlyun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tlyun.mapper.SubjectMapper;
import com.tlyun.pojo.Subject;
import com.tlyun.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/dispatcher")
public class DispatcherController {

    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private SubjectMapper subjectMapper;
    //到后台主页
    @RequestMapping("/toMain")
    public String toMain(){
        return "main";
    }
    //到欢迎页面toWelcome
    @RequestMapping("/toWelcome")
    public String toWelcome(){
        return "welcome";
    }

    //到专题管理页面
    @RequestMapping("/subject")
    public String subject(){
        return "subject";
    }

    int picture;
    int subject_id;
    //获取专题列表集合
    @RequestMapping("/getSubjectList")
    @ResponseBody
    public Object getSubjectList(Integer page,Integer limit,Subject subject){
        //page 当前页 limit 分页单位
        PageHelper.startPage(page,limit);
        List<Subject> slist = subjectService.getSubjectList(subject);
        System.out.println(slist);
        PageInfo<Subject> pageInfo = new PageInfo<>(slist);
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("msg","");
        resultMap.put("count",pageInfo.getTotal());
        resultMap.put("data",pageInfo.getList());
        return resultMap;
    }
    @ResponseBody
    @RequestMapping("/file")
    public Object upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest req) {
        int temp = (int)(Math.random()*1000);
        System.out.println(temp);
        Integer code=0;
        // File folder = new File(realPath + format);
        String property = System.getProperty("user.dir");
        System.out.println(property);
        File folder = new File(property+"/springboot-layui/src/main/resources/static/files/");
        if (!folder.isDirectory()) {
            if (!folder.mkdirs()) {
                return code;
            }
        }
        String oldName = multipartFile.getOriginalFilename();
        assert oldName != null;
        String newName = String.valueOf(temp) + oldName.substring(oldName.lastIndexOf("."));
        picture=temp;
        try {
            multipartFile.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //以上都是普通代码, 这里的/files/ 对应的就是你在WebMvcConfig设置的地址映射
//        System.out.println(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/files"  + "/" + newName);
        String pictureUrl = "/files/"  + "/" + newName;
        code=-1;
        return code;
    }
    @RequestMapping("/addSubjectList")
    @ResponseBody
    public String addSubjectList(Subject subject){

        System.out.println(subject);
        System.out.println(picture);
        subject.setSubject_banner(picture);
        Subject subject1 = subjectMapper.getlistById(picture);
        System.out.println(subject1);
        if (subject1==null){
            subjectMapper.insertlist(subject);
        }
//        subject.setSubject_banner();
        return null;
    }
    @RequestMapping("/giveMessage")
    public String toEdit(Subject subject, Model model){
        model.addAttribute("subject",subject);
        subject_id=subject.getSubject_id();
        return "edit";
    }
    @RequestMapping("/updatesubject")
    @ResponseBody
    public  String updatesubject(Subject subject){
        subject.setSubject_id(subject_id);
        subject.setSubject_banner(picture);
        int i= subjectMapper.updatesubejct(subject);
        return null;
    }
}
