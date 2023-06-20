package com.jinzunyue.share.config;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jinzunyue.share.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author 李忠文
 * @since 2022-08-06
 */

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        this.setFieldValByName("createTime", DateUtil.now(), metaObject);
        this.setFieldValByName("isDelete", CommonConstant.EXIST, metaObject);
        this.setFieldValByName("delFlag", CommonConstant.EXISTS, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
        this.setFieldValByName("updateTime", DateUtil.now(), metaObject);
    }
}
