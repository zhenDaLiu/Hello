package yuntongxun.admin.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqIdDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqPermissionDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqPermissionPageDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqUserIdDto;
import com.yuntongxun.ytx.pojo.po.sys.SysPermission;
import com.yuntongxun.ytx.pojo.vo.sys.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import yuntongxun.admin.service.sys.ISysPermissionService;

import javax.validation.Valid;
import java.util.List;
/**
 * 权限模块
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017/9/16 22:20
 */
@Api(tags = "02-权限模块")
@RestController
@RequestMapping("/permission")
public class SysPermissionController {

    @Autowired
    private ISysPermissionService sysPermissionService;

    @RequiresPermissions("permission:list")
    @ApiOperation(
            value = "权限-列表",
            notes = "1、pageSize 请求页码数 必填<br>" +
                    "2、pageNum 每页多少条 必填<br>" +
                    "3、menuName 菜单名称 非必填" +
                    "3、permissionName 权限名称 非必填"
    )
    @PostMapping("list")
    public Message list(
            @ApiParam(name = "reqRolePageDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqPermissionPageDto reqPermissionPageDto
    ) {

        PageInfo<SysPermission> page = sysPermissionService.selectPage(reqPermissionPageDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("page", page);
    }

    @RequiresPermissions("permission:user")
    @ApiOperation(value = "权限-用户", notes = "根据userId获取权限列表")
    @PostMapping("/user")
    public Message getPermissionListMe(
            @ApiParam(name = "reqUserIdDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqUserIdDto reqUserIdDto
    ) {
        List<SysPermission> permissions = sysPermissionService.findByUserId(reqUserIdDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("permissions", permissions);
    }

//    @ApiOperation(value = "获取我的菜单列表", notes = "根据我的userId获取我能访问的菜单项")
//    @GetMapping("/permission/menu/me")
//    public ServerResponse<List<PermissionMenuVo>> getPermissionMenuMe() {
//        List<PermissionMenuVo> permissionMenuByUserId = permissionService.getPermissionMenuByUserId(JWTUtil.getUserId());
//        return ServerResponse.createBySuccess(permissionMenuByUserId);
//    }

    @RequiresPermissions("permission:add")
    @ApiOperation(
            value = "权限-新增", notes = "新增一项权限")
    @PostMapping("/add")
    public Message add(
            @ApiParam(name = "reqPermissionDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqPermissionDto reqPermissionDto
    ) {

        // 权限-新增
        sysPermissionService.save(reqPermissionDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

    @RequiresPermissions("permission:del")
    @ApiOperation(
            value = "权限-删除",
            notes = "1、id 行数据id 必填<br>"
    )
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Message del(
            @ApiParam(name = "reqIdDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqIdDto reqIdDto
    ) {

        sysPermissionService.del(reqIdDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }
}
