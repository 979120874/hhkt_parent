package com.ik.hhkt.user.service.impl;

import com.ik.hhkt.model.user.UserLoginLog;
import com.ik.hhkt.user.mapper.UserLoginLogMapper;
import com.ik.hhkt.user.service.UserLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登陆记录表 服务实现类
 * </p>
 *
 * @author wsh
 * @since 2022-07-14
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService {

}
