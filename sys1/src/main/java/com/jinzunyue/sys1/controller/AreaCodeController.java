package com.jinzunyue.sys1.controller;

import com.jinzunyue.sys1.bean.AreaCode;
import com.jinzunyue.sys1.service.AreaCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 产地code映射表 前端控制器
 * </p>
 *
 * @author 
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/areaCode")
public class AreaCodeController {

    @Autowired
    private AreaCodeService areaCodeService;

    @PostMapping("add")
    public String add(@RequestBody AreaCode areaCode){
        areaCodeService.add(areaCode);
        return "success";
    }

}
