package com.ik.hhkt.wechat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ik.hhkt.model.wechat.Menu;
import com.ik.hhkt.vo.wechat.MenuVo;
import com.ik.hhkt.wechat.mapper.MenuMapper;
import com.ik.hhkt.wechat.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单明细 菜单明细 服务实现类
 * </p>
 *
 * @author wsh
 * @since 2022-07-14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Resource
    MenuMapper menuMapper;
    @Autowired
    private WxMpService wxMpService;

    @Override
    public List<MenuVo> findMenuInfo() {
        List<MenuVo> list = new ArrayList<>();
        //查询全部的菜单
        List<Menu> allMenus = menuMapper.selectList(null);
        //得到一级标题
        List<Menu> oneMenus = allMenus.stream().filter(menu -> menu.getParentId().longValue() == 0).collect(Collectors.toList());
        //遍历一级标题
        for (Menu menu : oneMenus) {
            MenuVo oneMenuVo = new MenuVo();
            BeanUtils.copyProperties(menu, oneMenuVo);
            //通过过滤得到一级标题下面的二级标题列表 menu2.getParentId().longValue() == oneMenuVo.getId()
            List<Menu> twoMenus = allMenus.stream().filter(menu2 -> menu2.getParentId().longValue() == oneMenuVo.getId()).sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList());
            ArrayList<MenuVo> twoMenuVoList = new ArrayList<>();
            //遍历二级标题，把Menu转为MenuVo，并全部放到一个List
            for (Menu twoMenu : twoMenus) {
                MenuVo twoMenuVo = new MenuVo();
                BeanUtils.copyProperties(twoMenu, twoMenuVo);
                twoMenuVoList.add(twoMenuVo);
            }
            //把二级标题列表放到一级标题里面
            oneMenuVo.setChildren(twoMenuVoList);
            list.add(oneMenuVo);
        }
        return list;
    }

    @Override
    public void syncMenu() {
        List<MenuVo> menuInfo = this.findMenuInfo();
        //菜单
        JSONArray buttonList = new JSONArray();
        for (MenuVo oneMenuVo : menuInfo) {
            JSONObject oneMenuVoJson = new JSONObject();
            oneMenuVoJson.put("name",oneMenuVo.getName());
            JSONArray twoMenuVoJsonList = new JSONArray();
            for (MenuVo twoMenuVo : oneMenuVo.getChildren()) {
                JSONObject twoMenuVoJson = new JSONObject();
                twoMenuVoJson.put("type",twoMenuVo.getType());
                if ("view".equals(twoMenuVo.getType())){
                    twoMenuVoJson.put("name",twoMenuVo.getName());
                    twoMenuVoJson.put("url", "https://39au046856.zicp.fun"
                            +twoMenuVo.getUrl());
                }else {
                    twoMenuVoJson.put("name",twoMenuVo.getName());
                    twoMenuVoJson.put("key",twoMenuVo.getMeunKey());
                }
                twoMenuVoJsonList.add(twoMenuVoJson);
            }
            oneMenuVoJson.put("sub_button",twoMenuVoJsonList);
            buttonList.add(oneMenuVoJson);
        }
        //菜单
        JSONObject button = new JSONObject();
        button.put("button", buttonList);
        try {
            String menuId = this.wxMpService.getMenuService().menuCreate(button.toJSONString());
            System.out.println("menuId="+menuId);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMenu() {
        try {
            wxMpService.getMenuService().menuDelete();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
