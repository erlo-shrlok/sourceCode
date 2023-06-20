package com.jinzunyue.sys1.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产地code映射表
 * </p>
 *
 * @author 
 * @since 2023-06-14
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("area_code")
public class AreaCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

      /**
     * 产地名
     */
      private String name;

      /**
     * 6位产地code
     */
      private String code;


}
