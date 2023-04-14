package edu.hit.zhouhao.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hit.zhouhao.service.CategoryService;
import edu.hit.zhouhao.service.impl.CateGoryServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/category/*")
public class CateGoryServlet extends BaseServlet {
    private CategoryService categoryService = new CateGoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("findAll running");
        //获取List集合
        Object categories = categoryService.findAll();
//        System.out.println(categories);
        //写成json传回去
        writeJsonValue(response,categories);
    }


}
