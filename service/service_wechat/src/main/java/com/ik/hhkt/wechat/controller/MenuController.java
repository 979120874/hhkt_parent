package com.ik.hhkt.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ik.hhkt.model.wechat.Menu;
import com.ik.hhkt.result.Result;
import com.ik.hhkt.vo.wechat.MenuVo;
import com.ik.hhkt.wechat.service.MenuService;
import com.ik.hhkt.wechat.utils.ConstantPropertiesUtil;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 菜单明细 菜单明细 前端控制器
 * </p>
 *
 * @author wsh
 * @since 2022-07-14
 */
@RestController
@RequestMapping("/admin/wechat/menu")
public class MenuController {

    @Resource
    private MenuService menuService;
    @Resource
    RestTemplate restTemplate;

    //获取所有菜单，按照一级和二级菜单封装
    @GetMapping("findMenuInfo")
    public Result findMenuInfo(){
        List<MenuVo> MenuInfoList =menuService.findMenuInfo();
        return Result.ok(MenuInfoList);
    }


    //获取所有一级菜单
    @GetMapping("findOneMenuInfo")
    public Result getLevelOneMenu(){
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.eq("parent_id",0);
        List<Menu> oneMenuList = menuService.list(menuQueryWrapper);
        return Result.ok(oneMenuList);
    }
    //c
    @PostMapping("add")
    @ApiOperation("添加菜单")
    public Result addMenu(@RequestBody Menu menu){
        boolean save = menuService.save(menu);
        if (save){
            return Result.ok("菜单添加成功");
        }
        return Result.fail("菜单添加失败");
    }
    //r
    @GetMapping("get/{id}")
    @ApiOperation("查看菜单")
    public Result getMenu(@PathVariable Long id){
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.eq("id",id);
        List<Menu> menuList = menuService.list(menuQueryWrapper);
        return Result.ok(menuList);
    }
    //u
    @PutMapping("update")
    @ApiOperation("修改菜单")
    public Result updateMenu(@RequestBody Menu menu){
        boolean update = menuService.updateById(menu);
        if (update){
            return Result.ok("菜单修改成功");
        }
        return Result.fail("菜单修改失败");
    }
    //d
    @DeleteMapping("remove/{id}")
    @ApiOperation("删除菜单")
    public Result deleteMenu(@PathVariable Long id){
        boolean delete = menuService.removeById(id);
        if (delete){
            return Result.ok("菜单删除成功");
        }
        return Result.fail("菜单删除失败");
    }
    //根据id列表删除
    @DeleteMapping("batchRemove")
    @ApiOperation("根据id列表删除")
    public Result deleteMenu(@RequestBody List<Long> idList){
        boolean delete = menuService.removeByIds(idList);
        if (delete){
            return Result.ok("根据id列表删除菜单成功");
        }
        return Result.fail("根据id列表删除菜单失败");
    }

    //获取access_token
    @GetMapping("getAccessToken")
    public Result getAccessToken(){
        //通过拼接得到请求地址 url=https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
        String buffer="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        String url = String.format(buffer, ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        //发送http请求
        System.out.println("url="+url);
        String tokenString = restTemplate.getForObject(url, String.class);
        //获取access_token
        JSONObject jsonObject = JSONObject.parseObject(tokenString);
        String access_token = jsonObject.getString("access_token");
        System.out.println("access_token="+access_token);
        //返回
        return Result.ok(access_token);

    }
    //https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html 创建菜单，并同步菜单
    @ApiOperation(value = "同步菜单")
    @GetMapping("syncMenu")
    public Result createMenu() throws WxErrorException {
        menuService.syncMenu();
        return Result.ok(null);
    }
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("removeMenu")
    public Result removeMenu() {
        menuService.removeMenu();
        return Result.ok(null);
    }
}

