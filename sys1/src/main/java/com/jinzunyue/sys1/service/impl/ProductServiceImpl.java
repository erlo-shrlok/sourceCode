package com.jinzunyue.sys1.service.impl;

import com.jinzunyue.sys1.bean.Product;
import com.jinzunyue.sys1.mapper.ProductMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinzunyue.sys1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-06-12
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public String create(Product product) {
        // 根据商品信息创建溯源码
        String traceCode = "";

        productMapper.insert(product);
        return "success";
    }
}
