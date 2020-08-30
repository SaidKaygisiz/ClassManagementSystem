/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entity.kullanici;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author s_a-i
 */
@WebFilter("/*")
public class FilterLogin implements Filter {

   

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse) response;
        String url=req.getRequestURI();
        kullanici k = (kullanici) req.getSession().getAttribute("valid_user");
        
        if(k==null){
            if(url.contains("admin")|| url.contains("ogretmen")){
                res.sendRedirect(req.getContextPath()+"/dogrulama.xhtml");
            }else 
                
            chain.doFilter(request, response);
            
            
        }else
            if(url.contains("dogrulama")){
                res.sendRedirect(req.getContextPath()+"admin/adminPaneli.xhtml");
            }
        
        chain.doFilter(request, response);
        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
