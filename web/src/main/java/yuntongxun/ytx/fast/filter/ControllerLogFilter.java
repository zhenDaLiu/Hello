package yuntongxun.ytx.fast.filter;

import cn.hutool.core.date.SystemClock;
import com.yuntongxun.ytx.constants.EnumConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * controller 日志请求唯一编码 Filter
 * @author tangxy
 * @date 2019-05-27
 **/
@Slf4j
@Component
@ServletComponentScan
public class ControllerLogFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        MDC.put(EnumConstants.SystemLog.THREAD_ID.name(), String.valueOf(SystemClock.now()));
        filterChain.doFilter(servletRequest, servletResponse);
        MDC.remove(EnumConstants.SystemLog.THREAD_ID.name());
    }
}
