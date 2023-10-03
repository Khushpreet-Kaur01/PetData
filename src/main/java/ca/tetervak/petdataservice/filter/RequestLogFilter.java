package ca.tetervak.petdataservice.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestLogFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestUri = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        log.info("received " + method + " request for " + requestUri);
        if(method.equals("GET")){
            log.info("processing " + method + " request for " + requestUri);
            filterChain.doFilter(servletRequest, servletResponse);
            log.info("response send for requested " + requestUri);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            log.info("access denied send for " + method + " request for " + requestUri);
        }
    }
}