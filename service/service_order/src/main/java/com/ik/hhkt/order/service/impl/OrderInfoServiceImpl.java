package com.ik.hhkt.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ik.hhkt.order.entity.OrderInfo;
import com.ik.hhkt.order.mapper.OrderInfoMapper;
import com.ik.hhkt.order.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ik.hhkt.vo.order.OrderInfoQueryVo;
import com.ik.hhkt.vo.order.OrderInfoVo;
import io.swagger.annotations.ApiModelProperty;
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
        Page<OrderInfo> orderInfoPage = orderInfoMapper.selectPage(page, orderInfoQueryWrapper);
        long totalCount = orderInfoPage.getTotal();
        long totalPage = orderInfoPage.getPages();
        List<OrderInfo> orderInfos = orderInfoPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",orderInfos);
        return map;
    }
}
