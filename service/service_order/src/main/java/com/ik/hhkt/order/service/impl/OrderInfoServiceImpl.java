package com.ik.hhkt.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ik.hhkt.model.order.OrderDetail;

import com.ik.hhkt.model.order.OrderInfo;
import com.ik.hhkt.order.mapper.OrderInfoMapper;
import com.ik.hhkt.order.service.OrderDetailService;
import com.ik.hhkt.order.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ik.hhkt.vo.order.OrderInfoQueryVo;
import com.ik.hhkt.vo.order.OrderInfoVo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author wsh
 * @since 2022-07-13
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderDetailService orderDetailService;

    //订单列表
    @Override
    public Map findPage(Page<OrderInfo> page, OrderInfoQueryVo orderInfoQueryVo) {
        //orderInfoQueryVo获取查询条件
        Long userId = orderInfoQueryVo.getUserId();
        String outTradeNo = orderInfoQueryVo.getOutTradeNo();//订单交易编号（第三方支付用)
        String phone = orderInfoQueryVo.getPhone();
        String createTimeEnd = orderInfoQueryVo.getCreateTimeEnd();
        String createTimeBegin = orderInfoQueryVo.getCreateTimeBegin();
        Integer orderStatus = orderInfoQueryVo.getOrderStatus();
        QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(orderStatus)){
            orderInfoQueryWrapper.eq("order_status",orderStatus);
        }
        if (!StringUtils.isEmpty(userId)){
            orderInfoQueryWrapper.eq("user_id",userId);
        }
        if (!StringUtils.isEmpty(outTradeNo)){
            orderInfoQueryWrapper.eq("out_trade_no",outTradeNo);
        }
        if (!StringUtils.isEmpty(phone)){
            orderInfoQueryWrapper.eq("phone",phone);
        }
        if(!StringUtils.isEmpty(createTimeBegin)) {
            orderInfoQueryWrapper.ge("create_time",createTimeBegin);
        }
        if(!StringUtils.isEmpty(createTimeEnd)) {
            orderInfoQueryWrapper.le("create_time",createTimeEnd);
        }
        //调用实现条件分页查询
        Page<OrderInfo> orderInfoPage = orderInfoMapper.selectPage(page, orderInfoQueryWrapper);
        long totalCount = orderInfoPage.getTotal();
        long totalPage = orderInfoPage.getPages();
        List<OrderInfo> orderInfos = orderInfoPage.getRecords();
        //订单里面包含详情内容，封装详情数据，根据订单id查询详情
        orderInfos.stream().forEach(item -> {
            this.getOrderDetail(item);
        });

        Map<String, Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",orderInfos);
        return map;
    }
    //查询订单详情数据
    private OrderInfo getOrderDetail(OrderInfo orderInfo) {
        //订单id
        Long id = orderInfo.getId();
        //查询订单详情
        OrderDetail orderDetail = orderDetailService.getById(id);
        if(orderDetail != null) {
            String courseName = orderDetail.getCourseName();
            orderInfo.getParam().put("courseName",courseName);
        }
        return orderInfo;
    }
}
