package com.jinzunyue.sys1.bean;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-06-12
 */
@Getter
@Setter
  @Accessors(chain = true)
  public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 产品的唯一标识符
     */
        private Integer id;

      /**
     * 溯源码
     */
      private String traceCode;

      /**
     * 产地
     */
      private String locationName;

      /**
     * 企业简称(四字)
     */
      private String companyName;

      /**
     * 产品名
     */
      private String productName;

      /**
     * 生产日期
     */
      private LocalDate productionDate;

      /**
     * 认证类型
     */
      private String certificationTypeName;


}
