package com.ik.hhkt.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ik.hhkt.model.order.OrderInfo;
import com.ik.hhkt.order.service.OrderInfoService;
import com.ik.hhkt.result.Result;
import com.ik.hhkt.vo.order.OrderInfoQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 订单表 订单表 前端控制器
 * </p>
 *
 * @author wsh
 * @since 2022-07-13
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping(value="/admin/order/orderInfo")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@ApiParam(name = "page", value = "页码", defaultValue = "1", required = true) @PathVariable("page") Long page,
                        @ApiParam(name = "limit", value = "每页数据", required = true) @PathVariable("limit") Long limit,
                        OrderInfoQueryVo orderInfoQueryVo){
        Page<OrderInfo> orderInfoPage = new Page<>(page,limit);
        Map map = orderInfoService.findPage(orderInfoPage, orderInfoQueryVo);
        return Result.ok(map);
    }
    @GetMapping("/test")
    public String test(){
        return "abc";
    }
}

