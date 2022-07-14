package com.ik.hhkt.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ik.hhkt.model.order.OrderInfo;
import com.ik.hhkt.vo.order.OrderInfoQueryVo;
import com.ik.hhkt.vo.order.OrderInfoVo;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author wsh
 * @since 2022-07-13
 */
public interface OrderInfoService extends IService<OrderInfo> {

    //订单列表
    Map findPage(Page<OrderInfo> page, OrderInfoQueryVo orderInfoQueryVo);
}
