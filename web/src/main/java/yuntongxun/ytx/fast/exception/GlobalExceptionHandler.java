package yuntongxun.ytx.fast.exception;


import com.yuntongxun.ytx.pojo.dto.rsp.ClientBaseBodyRspDto;
import com.yuntongxun.ytx.pojo.dto.rsp.ClientBaseRspDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zc
 * Created by zc on 2018/4/10.
 * 全局异常处理
 */

@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements ErrorController {

    private static final String ERROR_PATH = "/error";

    //参数错误 400(bean 校验)
    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handServletIllegalExceptionBean(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMesssage = new StringBuilder("参数错误:");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage.append(fieldError.getDefaultMessage()).append(";");
        }
        return callBackFail(HttpStatus.BAD_REQUEST.value(),errorMesssage.toString());
    }

    //参数错误 400
    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handServletIllegalException(ServletRequestBindingException e) {
        return callBackFail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    //参数错误 400
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return callBackFail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    //参数错误 400 手动
    @ExceptionHandler(IllegalException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handServletIllegalException(IllegalException e) {
        return callBackFail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    // 未登陆 401
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Object handUnauthorizedException(AuthenticationException e) {
        return callBackFail(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    // 权限不足 403
    @ExceptionHandler({AuthorizationException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Object handIllegalException(AuthorizationException e) {
        return callBackFail(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    // 权限不足 403
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Object handUnauthorizedException(UnauthorizedException e) {
        return callBackFail(HttpStatus.FORBIDDEN.value(), "权限不足");
    }

    // 未找到资源 404
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Object handNotFoundException(NotFoundException e) {
        return callBackFail(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }


    //请求方法不对 405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public Object handServletIllegalException(HttpRequestMethodNotSupportedException e) {
        return callBackFail(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }


    //资源已存在 409
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Object handIllegalException(ConflictException e) {
        return callBackFail(HttpStatus.CONFLICT.value(), e.getMessage());
    }


    //默认异常 500(默认)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object defaultErrorHandler(Exception e) {
        return callBackFail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    // org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument at index 0 in method: public com.yuntongxun.collocation.pojo.vo.Message com.yuntongxun.collocation.controller.SysRoleController.permission(com.yuntongxun.collocation.pojo.dto.ReqIdDto), with 1 error(s): [Field error in object 'reqIdDto' on field 'id': rejected value [null]; codes [NotNull.reqIdDto.id,NotNull.id,NotNull.java.lang.Integer,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [reqIdDto.id,id]; arguments []; default message [id]]; default message [行数据id不能为空]]
    // hibernate validation 改为抛出业务异常
    // 实际的开发场景中，异常是区分很多类别的，不同类别的异常需要给用户不同的反馈。有使用到注解式参数校验，但校验不通过原因并没有以有效的方式告之给前端应用
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handMethodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException  e) {

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        MethodParameter parameter = e.getParameter();
        final String name = parameter.getMethod().getName();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            ObjectError objectError = errors.get(0);
            tips = objectError.getDefaultMessage();
            log.info("url:{},错误信息:{}",name,objectError.toString());
        }
        response.setStatus(200);
        return callBackFail(600, tips);
    }

    //业务异常 600
    @ExceptionHandler(BusinessException.class)
    public Object handBusinessException(HttpServletResponse response, Exception e) {

        response.setStatus(200);
        return callBackFail(600,  e.getMessage());
    }

    /**
     * 客户端业务异常
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(ClientBusinessException.class)
    public Object handClientBusinessException(HttpServletResponse response, ClientBusinessException e){
        response.setStatus(200);
        if(e.getCode() != null){
            return callBackFail(e.getCode(),e.getMessage());
        }
        if(null != e.getInfo()){
            return callBackFail(600,e.getMessage(),e.getInfo());
        }
        return callBackFail(600,e.getMessage());
    }

    //重写404错误
    @RequestMapping(value = ERROR_PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Object handleError() {
        return callBackFail(HttpStatus.NOT_FOUND.value(), "Request resource not found");
    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping("/401")
    public void handle401(ServletRequest request) {
        Object error = request.getAttribute("error");
        if (error != null) {
            throw (RuntimeException) error;
        } else {
            throw new AuthenticationException("未登录/验证失败");
        }
    }

    /**
     * 客户端返回 失败返回，返回状态码、返回失败描述
     * @param code
     * @param msg
     * @return
     */
    protected  ClientBaseRspDto callBackFail(Integer code,String msg){
        ClientBaseRspDto clientBaseRspDto = ClientBaseRspDto.builder().build().ok();
        ClientBaseBodyRspDto bodyRspDto = ClientBaseBodyRspDto.builder().build().fail(code, msg);
        clientBaseRspDto.setData(bodyRspDto);
        return clientBaseRspDto;
    }

    /**
     * 客户端返回 失败返回，返回状态码、返回失败描述
     * @param code
     * @param msg
     * @return
     */
    protected  ClientBaseRspDto callBackFail(Integer code,String msg,Object info){
        ClientBaseRspDto clientBaseRspDto = ClientBaseRspDto.builder().build().ok();
        ClientBaseBodyRspDto bodyRspDto = ClientBaseBodyRspDto.builder().build().fail(code, msg);
        bodyRspDto.setInfo(info);
        clientBaseRspDto.setData(bodyRspDto);
        return clientBaseRspDto;
    }
}
