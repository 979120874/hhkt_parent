package com.ik.hhkt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表 订单表
 * </p>
 *
 * @author wsh
 * @since 2022-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    private String nickName;

    private String phone;

    /**
     * 原始金额
     */
    private BigDecimal originAmount;

    /**
     * 优惠券减免
     */
    private BigDecimal couponReduce;

    /**
     * 最终金额
     */
    private BigDecimal finalAmount;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 订单交易编号（第三方支付用)
     */
    private String outTradeNo;

    /**
     * 订单描述(第三方支付用)
     */
    private String tradeBody;

    /**
     * session id
     */
    private String sessionId;

    /**
     * 地区id
     */
    private String province;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 失效时间
     */
    private LocalDateTime expireTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDeleted;


}
