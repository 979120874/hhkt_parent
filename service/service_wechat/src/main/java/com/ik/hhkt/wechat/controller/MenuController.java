package com.ik.hhkt.wechat.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ik.hhkt.model.wechat.Menu;
import com.ik.hhkt.result.Result;
import com.ik.hhkt.vo.wechat.MenuVo;
import com.ik.hhkt.wechat.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @GetMapping("get")
    @ApiOperation("查看菜单")
    public Result getMenu(){
        List<Menu> menuList = menuService.list();
        return Result.ok(menuList);
    }
    //u
    @PostMapping("update")
    @ApiOperation("修改菜单")
    public Result updateMenu(@RequestBody Menu menu){
        boolean update = menuService.updateById(menu);
        if (update){
            return Result.ok("菜单修改成功");
        }
        return Result.fail("菜单修改失败");
    }
    //d
    @PostMapping("update/{id}")
    @ApiOperation("删除菜单")
    public Result deleteMenu(@PathVariable Long id){
        boolean delete = menuService.removeById(id);
        if (delete){
            return Result.ok("菜单删除成功");
        }
        return Result.fail("菜单删除失败");
    }
    //根据id列表删除
    @PostMapping("batchRemove")
    @ApiOperation("根据id列表删除")
    public Result deleteMenu(@RequestBody List<Long> idList){
        boolean delete = menuService.removeByIds(idList);
        if (delete){
            return Result.ok("根据id列表删除菜单成功");
        }
        return Result.fail("根据id列表删除菜单失败");
    }
}

