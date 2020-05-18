package yuntongxun.admin.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqIdDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqMenuDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqMenuPageDto;
import com.yuntongxun.ytx.pojo.dto.rsp.sys.SysMenuRspDto;
import com.yuntongxun.ytx.pojo.po.sys.SysMenu;
import com.yuntongxun.ytx.pojo.vo.sys.Message;
import com.yuntongxun.ytx.pojo.vo.sys.Tree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import yuntongxun.admin.service.sys.ISysMenuService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author: luocc
 * @Date: 2018/9/14
 * @Description:菜单控制器
 */
@Api(tags = "03-菜单模块")
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    @RequiresPermissions("menu:list")
    @ApiOperation(
            value = "菜单-列表",
            notes = "1、pageSize 请求页码数 必填<br>" +
                    "2、pageNum 每页多少条 必填<br>" +
                    "3、menuName 角色名称 非必填"
    )
    @PostMapping("list")
    public Message list(
            @ApiParam(name = "reqMenuPageDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqMenuPageDto reqMenuPageDto
    ) {

        PageInfo<SysMenu> page = sysMenuService.selectPage(reqMenuPageDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("page", page);
    }

    @ApiOperation(
            value = "菜单-树形",
            notes = "1、获取全部的菜单树形数据<br>"
    )
    @PostMapping("tree")
    public Message tree() {

        // 树形菜单
        Tree<SysMenu> tree = sysMenuService.loadMenuTree();

        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("tree", tree);
    }

    @ApiOperation(value = "获取用户可查看菜单列表")
    @PostMapping("userMenus")
    public Message userMenus(){
        List<SysMenuRspDto> sysMenus = sysMenuService.selectUserMenus();
        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("page", sysMenus);
    }

    @PostMapping("userMenuTree")
    @ApiOperation(
            value = "菜单-树形",
            notes = "1、登录之后的左侧菜单树<br>"
    )
    public Message userMenuTree(){
        // 树形菜单
        Tree<SysMenuRspDto> tree = sysMenuService.selectUserMenuTree();
        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("tree", tree);
    }

    @ApiOperation(
            value = "菜单-树形",
            notes = "1、供前端特定格式使用<br>"
    )
    @PostMapping("tree2")
    public Message tree2() {

        // 树形菜单
        Map<String, Object> options = sysMenuService.loadMenuTree2();

        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("options", options);
    }

    @RequiresPermissions("menu:add")
    @ApiOperation(
            value = "菜单-新增",
            notes = "1、parentId 父级菜单ID 备注：如果新增顶级菜单, parentId = 0 必填<br>" +
                    "2、sortNum 排序号 大于0的整数 数值越大排序越靠前"
    )
    @PostMapping("/add")
    public Message add(
            @ApiParam(name = "reqMenuDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqMenuDto reqMenuDto
    ) {

        // 菜单-新增
        sysMenuService.add(reqMenuDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

    @RequiresPermissions("menu:del")
    @ApiOperation(
            value = "菜单-删除",
            notes = "1、id 行数据id 必填<br>"
    )
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Message del(
            @ApiParam(name = "reqIdDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqIdDto reqIdDto
    ) {

        sysMenuService.del(reqIdDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

}
