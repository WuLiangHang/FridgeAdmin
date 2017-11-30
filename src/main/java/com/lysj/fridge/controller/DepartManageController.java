package com.lysj.fridge.controller;

import cn.soul.util.web.ParamUtil;
import cn.soul.util.web.Resp;
import com.lysj.base.BaseContent;
import com.lysj.fridge.domain.Department;
import com.lysj.fridge.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by maohang on 2017/11/20.
 */
@RestController
@RequestMapping("/departManager")
public class DepartManageController extends BaseContent {

    @Autowired
    DepartmentRepository departmentRepository;

    //添加部门
    @PostMapping(value = "/add")
    public Resp add(Department model,
                    @RequestParam("file") MultipartFile file) {
        if (ParamUtil.isBlack(model.getName())) {
            return new Resp(Resp.PARAM_ERROR, "请输入部门名称!");
        }
        if (ParamUtil.isBlack(model.getStaffId())) {
            return new Resp(Resp.PARAM_ERROR, "请输入负责人工号!");
        }
        if (ParamUtil.isBlack(model.getMobile())) {
            return new Resp(Resp.PARAM_ERROR, "请输入部门联系电话!");
        }
        if(departmentRepository.findByName(model.getName())!=null){
            return new Resp(Resp.PARAM_ERROR, "部门已存在!");
        }

        Department department=new Department();
        department.setName(model.getName());
        department.setStaffId(model.getStaffId());
        department.setMobile(model.getMobile());
        department.setPictureAddress(model.getPictureAddress());
        departmentRepository.save(department);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return new Resp("添加成功!");
    }

    //查询所有部门
    @GetMapping(value="/list")
    public Resp list(){
        return new Resp("200","success",departmentRepository.findAll());
    }

    //删除单个部门
    @DeleteMapping(value="/delete/{departmentId}")
    public Resp  delete(@PathVariable("departmentId") Long departmentId){
        departmentRepository.delete(departmentId);
        return new Resp("删除成功");
    }
    //更改一个部门
    @PostMapping(value="/edit")
    public Resp edit(Department model){
        //model为输入后的数据
        Department department=departmentRepository.findOne(model.getDepartmentId());
        if (department == null) {
            return new Resp(Resp.PARAM_ERROR, "部门错误!");
        }
        if (ParamUtil.isBlack(model.getName())) {
            return new Resp(Resp.PARAM_ERROR, "请输入部门名称!");
        }
        if (ParamUtil.isBlack(model.getStaffId())) {
            return new Resp(Resp.PARAM_ERROR, "请输入负责人工号!");
        }
        if (ParamUtil.isBlack(model.getMobile())) {
            return new Resp(Resp.PARAM_ERROR, "请输入部门联系电话!");
        }
        department.setName(model.getName());
        department.setStaffId(model.getStaffId());
        department.setMobile(model.getMobile());

        departmentRepository.save(department);
        return new Resp("修改成功");
    }
}
