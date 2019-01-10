package com.yunchao.hsh.controller.admin;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.model.SysDict;
import com.yunchao.hsh.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = {"/page/admin/sysdict/"})
public class SysDictController extends BaseController{

    @Autowired
    private ISysDictService sysDictService;

    @RequestMapping("getUnitAll")
    @ResponseBody
    public List<SysDict> getDictList(){
        List<SysDict> list = this.sysDictService.findDictList();
        return list;
    }

}
