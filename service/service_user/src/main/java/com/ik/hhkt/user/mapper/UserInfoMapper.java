package com.ik.hhkt.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ik.hhkt.model.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wsh
 * @since 2022-07-14
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
