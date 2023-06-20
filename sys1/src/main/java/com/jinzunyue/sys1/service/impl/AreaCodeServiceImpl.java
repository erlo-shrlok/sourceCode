package com.jinzunyue.sys1.service.impl;

import com.jinzunyue.share.tools.RandomUtil;
import com.jinzunyue.sys1.bean.AreaCode;
import com.jinzunyue.sys1.mapper.AreaCodeMapper;
import com.jinzunyue.sys1.service.AreaCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产地code映射表 服务实现类
 * </p>
 *
 * @author 
 * @since 2023-06-14
 */
@Service
public class AreaCodeServiceImpl extends ServiceImpl<AreaCodeMapper, AreaCode> implements AreaCodeService {

    @Autowired
    private AreaCodeMapper areaCodeMapper;

    @Override
    public void add(AreaCode areaCode) {
        areaCode.setCode(RandomUtil.randomNumeric(6));
        areaCodeMapper.insert(areaCode);
    }
}
