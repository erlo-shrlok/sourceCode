package com.jinzunyue.sys1.controller;

import com.jinzunyue.sys1.bean.Product;
import com.jinzunyue.sys1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-06-12
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("add")
    public Object add(@RequestBody Product product){
        productService.create(product);
        return "success";
    }

}
