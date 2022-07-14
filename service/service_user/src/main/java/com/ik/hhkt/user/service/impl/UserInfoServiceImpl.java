package com.ik.hhkt.user.service.impl;

import com.ik.hhkt.model.user.UserInfo;
import com.ik.hhkt.user.mapper.UserInfoMapper;
import com.ik.hhkt.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wsh
 * @since 2022-07-14
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
