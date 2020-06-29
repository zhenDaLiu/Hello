package com.yuntongxun.admin.controller.sys;

import com.github.pagehelper.PageInfo;
import com.yuntongxun.ytx.pojo.dto.req.sys.*;
import com.yuntongxun.ytx.pojo.vo.sys.Message;
import com.yuntongxun.ytx.pojo.vo.sys.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.yuntongxun.admin.service.sys.ISysUserService;
import com.yuntongxun.ytx.controller.base.BaseController;

import javax.validation.Valid;

/**
 * @Author: luocc
 * @Date: 2018/9/14
 * @Description: 账户控制器
 */
@Api(tags = "00-账户模块")
@RestController
@RequestMapping("/user")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/login")
    @ApiOperation(
            value = "账户-登陆",
            notes = "1、username 账户 必填<br>" +
                    "3、password 密码(Base64加密传输) 必填")
    public Message login(
            @ApiParam(name = "reqLoginDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqLoginDto reqLoginDto
    ) {

        RepUserDto repUserDto = sysUserService.login(reqLoginDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("user", repUserDto);
    }

    @RequiresPermissions("user:list")
    @ApiOperation(
            value = "账户-列表",
            notes = "1、pageSize 请求页码数 必填<br>" +
                    "2、pageNum 每页多少条 必填<br>" +
                    "3、username 账户名称 非必填"
    )
    @PostMapping("list")
    public Message list(
            @ApiParam(name = "reqUserPageDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqUserPageDto reqUserPageDto
    ) {

        PageInfo<SysUserVo> page = sysUserService.selectPage(reqUserPageDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success")
                .addData("page", page);
    }

    @RequiresPermissions("user:assign")
    @ApiOperation(value = "账户-分配角色", notes = "给用户赋予角色")
    @PostMapping("/assign")
    public Message assign(
            @ApiParam(name = "reqUserRoleDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqUserRoleDto reqUserRoleDto
    ) {

        // 分配角色
        sysUserService.assign(reqUserRoleDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

    @RequiresPermissions("user:add")
    @ApiOperation(
            value = "账户-新增", notes = "新增账户")
    @PostMapping("/add")
    public Message add(
            @ApiParam(name = "reqUserDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqUserDto reqUserDto
    ) {

        // 账户-新增
        sysUserService.add(reqUserDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

    @RequiresPermissions("user:del")
    @ApiOperation(
            value = "账户-删除",
            notes = "1、id 行数据id 必填<br>"
    )
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Message del(
            @ApiParam(name = "reqIdDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqIdDto reqIdDto
    ) {

        sysUserService.del(reqIdDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

    @RequiresPermissions("user:disable")
    @ApiOperation(
            value = "账户-启用/禁用",
            notes = "1、id 行数据id 必填<br>" +
                    "2、status 行数据状态 1-有效 2-无效 必填"
    )
    @RequestMapping(value="/disable", method= RequestMethod.POST)
    public Message disable(
            @ApiParam(name = "reqStatusDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqStatusDto reqStatusDto
    ) {

        sysUserService.disable(reqStatusDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }


//    @RequiresPermissions("user:updatePwd")
    @ApiOperation(
            value = "账户-修改密码",
            notes = "1、userId 账户ID 必填<br>" +
                    "2、pwdOld 旧密码 必填<br>" +
                    "2、pwdNew 新密码 必填<br>"
    )
    @RequestMapping(value="/updatePwd", method= RequestMethod.POST)
    public Message updatePwd(
            @ApiParam(name = "reqUserPwdDto", value = "请求封装对象", required = true)
            @Valid @RequestBody ReqUserPwdDto reqUserPwdDto
    ) {

        sysUserService.updatePwd(reqUserPwdDto);

        return new Message()
                .ok(HttpStatus.OK.value(), "success");
    }

}
