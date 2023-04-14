package edu.hit.zhouhao.web.servlet.sub;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hit.zhouhao.domain.ResultInfo;
import edu.hit.zhouhao.domain.User;
import edu.hit.zhouhao.service.UserService;
import edu.hit.zhouhao.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理编码
        request.setCharacterEncoding("utf-8");
        //处理数据并且封装对象
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService userService = new UserServiceImpl();
        User exist_user = userService.login(user);
        ResultInfo info = new ResultInfo();
        if (exist_user != null) {
            boolean status_flag = userService.checkStatus(user);
            if (status_flag) {
                info.setFlag(true);
                //登陆成功，设置session
                HttpSession session = request.getSession();
                session.setAttribute("user",exist_user);
//                System.out.println(session);

            }else {
                info.setFlag(false);
                info.setErrorMsg("登录失败！用户未激活");
            }
        } else {
            info.setFlag(false);
            info.setErrorMsg("登录失败！用户不存在");
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
