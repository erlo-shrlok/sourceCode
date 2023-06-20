package com.jinzunyue.sys1.service;

import com.jinzunyue.sys1.bean.AreaCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 产地code映射表 服务类
 * </p>
 *
 * @author 
 * @since 2023-06-14
 */
public interface AreaCodeService extends IService<AreaCode> {

    void add(AreaCode areaCode);
}
