package com.ik.hhkt.activity.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ik.hhkt.activity.service.CouponInfoService;
import com.ik.hhkt.model.activity.CouponInfo;
import com.ik.hhkt.model.activity.CouponUse;
import com.ik.hhkt.result.Result;
import com.ik.hhkt.vo.activity.CouponUseQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author wsh
 * @since 2022-07-14
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/admin/activity/couponInfo")
public class CouponInfoController {
    @Resource
    CouponInfoService couponInfoService;

    @ApiOperation(value = "优惠券分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<CouponInfo> couponInfoPage = new Page<>(page,limit);
        Page<CouponInfo> couponInfoPageDate = couponInfoService.page(couponInfoPage);
        return Result.ok(couponInfoPageDate);
    }

    @ApiOperation(value = "获取优惠券")
    @GetMapping("get/{id}")
    public Result getCoupon(@PathVariable String id) {
        CouponInfo couponInfo = couponInfoService.getById(id);
        return Result.ok(couponInfo);
    }

    @ApiOperation(value = "新增优惠券")
    @PostMapping("save")
    public Result saveCoupon(@RequestBody CouponInfo couponInfo){
        boolean save = couponInfoService.save(couponInfo);
        if (!save){
            return Result.fail("添加失败");
        }
        return Result.ok("添加成功");
    }
    @ApiOperation(value = "修改优惠券")
    @PutMapping("update")
    public Result updateCoupon(@RequestBody CouponInfo couponInfo) {
        boolean update = couponInfoService.updateById(couponInfo);
        if (!update){
            return Result.fail("修改失败");
        }
        return Result.ok("修改成功");
    }

    @ApiOperation(value = "删除优惠券")
    @DeleteMapping("delete/{id}")
    public Result deleteCoupon(@PathVariable String id) {
        boolean remove = couponInfoService.removeById(id);
        if (!remove){
            return Result.fail("删除失败");
        }
        return Result.ok("删除成功");
    }

    @ApiOperation(value = "批量删除优惠券")
    @DeleteMapping("batchRemove")
    public Result batchRemoveCoupon(@RequestBody List<Long> idList) {
        boolean remove = couponInfoService.removeByIds(idList);
        if (!remove){
            return Result.fail("批量删除失败");
        }
        return Result.ok("批量删除成功");
    }

    @ApiOperation(value = "获取已经使用优惠券列表（条件查询）")
    @GetMapping("couponUse/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "couponUseVo", value = "查询对象", required = false)
                    CouponUseQueryVo couponUseQueryVo) {
        Page<CouponUse> pageParam = new Page<>(page,limit);
        IPage<CouponUse> pageModel = couponInfoService.selectCouponUsePage(pageParam, couponUseQueryVo);
        return Result.ok(pageModel);
    }
}

