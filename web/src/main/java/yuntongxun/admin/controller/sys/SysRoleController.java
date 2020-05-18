package yuntongxun.admin.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yuntongxun.admin.service.sys.ISysMenuService;
import com.yuntongxun.ytx.pojo.dto.req.sys.*;
import com.yuntongxun.ytx.pojo.dto.rsp.sys.SysMenuRspDto;
import com.yuntongxun.ytx.pojo.po.sys.SysPermission;
import com.yuntongxun.ytx.pojo.vo.sys.Message;
import com.yuntongxun.ytx.pojo.vo.sys.SysRoleVo;
import com.yuntongxun.admin.service.sys.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: luocc
 * Date: 2018/9/14
 * Description:角色控制器
 */
@Api(tags = "01-角色模块")
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysMenuService sysMenuService;

    @RequiresPermissions("role:list")
    @ApiOperation(
            value = "角色-列表",
            notes = "1、pageSize 请求页码数 必填<br>" +
                    "2、pageNum 每页多少条 必填<br>" +
                    "3、roleName 角色名称 非必填"
    )
    @PostMapping("list")
    public Message list(
            @ApiParam(name = "reqRolePageDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqRolePageDto reqRolePageDto
    ) {

        PageInfo<SysRoleVo> page = sysRoleService.selectPage(reqRolePageDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("page", page);
    }

    @RequiresPermissions("role:assign")
    @ApiOperation(value = "角色-赋权", notes = "给角色分配权限")
    @PostMapping("/assign")
    public Message assign(
            @ApiParam(name = "reqRolePermissionDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqRolePermissionDto reqRolePermissionDto
    ) {

        // 分配权限
        sysRoleService.assign(reqRolePermissionDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

    @RequiresPermissions("role:permission")
    @ApiOperation(
            value = "角色-查权",
            notes = "说明：查询角色下拥有的权限集合<br>" +
                    "1、id 行数据ID即角色ID 必填"
    )
    @PostMapping("/permission")
    public Message permission(
            @ApiParam(name = "reqIdDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqIdDto reqIdDto
    ) {

        // 分配权限
        List<SysPermission> sysPermissions = sysRoleService.findPermissionByRoleId(reqIdDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("sysPermissions", sysPermissions);
    }

    @RequiresPermissions("role:add")
    @ApiOperation(
            value = "角色-新增", notes = "新增角色")
    @PostMapping("/add")
    public Message add(
            @ApiParam(name = "reqRoleDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqRoleDto reqRoleDto
    ) {

        // 角色-新增
        sysRoleService.add(reqRoleDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

    @RequiresPermissions("role:del")
    @ApiOperation(
            value = "角色-删除",
            notes = "1、id 行数据id 必填<br>"
    )
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Message del(
            @ApiParam(name = "reqIdDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqIdDto reqIdDto
    ) {

        sysRoleService.del(reqIdDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

    @RequiresPermissions("role:disable")
    @ApiOperation(
            value = "角色-启用/禁用",
            notes = "1、id 行数据id 必填<br>" +
                    "2、status 行数据状态 1-有效 2-无效 必填"
    )
    @RequestMapping(value="/disable", method= RequestMethod.POST)
    public Message disable(
            @ApiParam(name = "reqStatusDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqStatusDto reqStatusDto
    ) {

        sysRoleService.disable(reqStatusDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

    @RequiresPermissions("role:list")
    @ApiOperation(value = "保存角色菜单关系")
    @PostMapping("roleMenu")
    public Message roleMenu(@Valid @RequestBody ReqRoleMenuDto reqRoleMenuDto){
        sysRoleService.updateRoleMenu(reqRoleMenuDto);
        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

    @RequiresPermissions("role:list")
    @ApiOperation(value = "获取当前角色已选择的菜单列表",notes = "id为当前角色id，必传")
    @PostMapping("getRoleMenuList")
    public Message getRoleMenuList(@Valid @RequestBody ReqIdDto reqIdDto){
        List<SysMenuRspDto> sysMenus = sysMenuService.getRoleMenus(reqIdDto.getId());
        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("page", sysMenus);
    }

}
