package yuntongxun.admin.service.sys;


import com.github.pagehelper.PageInfo;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqIdDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqPermissionDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqPermissionPageDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqUserIdDto;
import com.yuntongxun.ytx.pojo.po.sys.SysPermission;

import java.util.List;

public interface ISysPermissionService {

	List<SysPermission> findByUserId(ReqUserIdDto reqUserIdDto);

    PageInfo<SysPermission> selectPage(ReqPermissionPageDto reqPermissionPageDto);

	void clearPermissionCacheByUserId(String userId);

    Integer save(ReqPermissionDto reqPermissionDto);

    Integer del(ReqIdDto reqIdDto);

}
