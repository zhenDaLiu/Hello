package yuntongxun.admin.service.sys;


import com.github.pagehelper.PageInfo;
import com.yuntongxun.ytx.pojo.dto.req.sys.*;
import com.yuntongxun.ytx.pojo.po.sys.SysUser;
import com.yuntongxun.ytx.pojo.vo.sys.SysUserVo;

public interface ISysUserService {

    RepUserDto login(ReqLoginDto reqLoginDto);


    /**
     * 根据登录账号获取用户信息
     * @param username
     * @return
     */
    SysUser findByUserName(String username);

    void assign(ReqUserRoleDto reqUserRoleDto);

    PageInfo<SysUserVo> selectPage(ReqUserPageDto reqUserPageDto);

    Integer add(ReqUserDto reqUserDto);

    Integer del(ReqIdDto reqIdDto);

    Integer disable(ReqStatusDto reqStatusDto);

    Integer updatePwd(ReqUserPwdDto reqUserPwdDto);

}
