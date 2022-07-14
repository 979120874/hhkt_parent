package com.ik.hhkt.activity.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ik.hhkt.activity.mapper.CouponInfoMapper;
import com.ik.hhkt.activity.service.CouponInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ik.hhkt.activity.service.CouponUseService;
import com.ik.hhkt.feign.UserInfoFeignClient;
import com.ik.hhkt.model.activity.CouponInfo;
import com.ik.hhkt.model.activity.CouponUse;
import com.ik.hhkt.model.user.UserInfo;
import com.ik.hhkt.vo.activity.CouponUseQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author wsh
 * @since 2022-07-14
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Resource
    CouponUseService couponUseService;
    @Resource
    UserInfoFeignClient userInfoFeignClient;

    @Override
    public IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo) {
        //封装查询条件
        Long couponId = couponUseQueryVo.getCouponId();
        String couponStatus = couponUseQueryVo.getCouponStatus();
        String getTimeBegin = couponUseQueryVo.getGetTimeBegin();
        String getTimeEnd = couponUseQueryVo.getGetTimeEnd();
        QueryWrapper<CouponUse> couponUseQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(couponId)){
            couponUseQueryWrapper.eq("coupon_id",couponId);
        }
        if (!StringUtils.isEmpty(couponStatus)){
            couponUseQueryWrapper.eq("coupon_status",couponStatus);
        }
        if (!StringUtils.isEmpty(getTimeBegin)){
            couponUseQueryWrapper.ge("get_time",getTimeBegin);
        }
        if (!StringUtils.isEmpty(getTimeEnd)){
            couponUseQueryWrapper.le("get_time",getTimeEnd);
        }
        Page<CouponUse> couponUsePage = couponUseService.page(pageParam, couponUseQueryWrapper);
        List<CouponUse> records = couponUsePage.getRecords();
        records.stream().forEach(item->this.getUserInfoBycouponUse(item));
        return couponUsePage;
    }
    //封装用户昵称和手机号
    public CouponUse getUserInfoBycouponUse(CouponUse couponUse){
        Long userId = couponUse.getUserId();
        //通过Feign调用service—user模块的查询用户功能
        UserInfo userInfo = userInfoFeignClient.getById(userId);
        if (userInfo != null) {
            couponUse.getParam().put("nickName", userInfo.getNickName());
            couponUse.getParam().put("phone", userInfo.getPhone());
        }
        return couponUse;
    }
}
