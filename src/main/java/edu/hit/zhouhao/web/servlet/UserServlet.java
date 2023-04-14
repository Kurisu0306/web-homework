package edu.hit.zhouhao.web.servlet;

import edu.hit.zhouhao.domain.ResultInfo;
import edu.hit.zhouhao.domain.User;
import edu.hit.zhouhao.service.UserService;
import edu.hit.zhouhao.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    protected UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login running");
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
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
                session.setAttribute("user", exist_user);
//                System.out.println(session);

            } else {
                info.setFlag(false);
                info.setErrorMsg("登录失败！用户未激活");
            }
        } else {
            info.setFlag(false);
            info.setErrorMsg("登录失败！用户不存在");
        }

        writeJsonValue(response, info);
    }

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("regist running");
        // 0. 处理编码
        request.setCharacterEncoding("utf-8");
        // 1. 处理数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 2. 封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 3. 调用service完成注册
        boolean flag = userService.registUser(user);
        ResultInfo info = new ResultInfo();
        // 4. 响应结果
        if (flag) {
            // 注册成功
            info.setFlag(true);
        } else {
            // 注册失败

            info.setFlag(false);
            info.setErrorMsg("注册失败！用户名已存在");
        }

        writeJsonValue(response, info);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("findOne running");
        HttpSession userSession = request.getSession();
//        System.out.println(userSession);

        Object user = userSession.getAttribute("user");

        writeJsonValue(response, user);
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("exit running");
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/zhouhao/index.html");
    }
}
