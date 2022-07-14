package com.ik.hhkt.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ik.hhkt.model.user.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户登陆记录表 Mapper 接口
 * </p>
 *
 * @author wsh
 * @since 2022-07-14
 */
@Mapper
public interface UserLoginLogMapper extends BaseMapper<UserLoginLog> {

}
