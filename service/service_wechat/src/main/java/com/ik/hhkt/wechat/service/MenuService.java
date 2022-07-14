package com.ik.hhkt.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ik.hhkt.model.wechat.Menu;
import com.ik.hhkt.vo.wechat.MenuVo;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author wsh
 * @since 2022-07-14
 */
public interface MenuService extends IService<Menu> {
    List<MenuVo> findMenuInfo();
}
