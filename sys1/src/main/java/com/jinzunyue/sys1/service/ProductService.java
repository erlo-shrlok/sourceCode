package com.jinzunyue.sys1.service;

import com.jinzunyue.sys1.bean.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-06-12
 */
public interface ProductService extends IService<Product> {
    String create(Product product);
}
