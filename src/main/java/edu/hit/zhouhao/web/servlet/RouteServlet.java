package edu.hit.zhouhao.web.servlet;

import edu.hit.zhouhao.domain.PageBean;
import edu.hit.zhouhao.domain.Route;
import edu.hit.zhouhao.service.RouteService;
import edu.hit.zhouhao.service.impl.RouteServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.print.PrinterGraphics;
import java.io.IOException;
import java.util.Map;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Scid = request.getParameter("cid");
        String ScurrentPage = request.getParameter("currentPage");
        String SpageSize = request.getParameter("pageSize");
//        String rname = request.getParameter("rname");//不需要

        //默认种类
        int cid = 0;
        if (Scid != null && Scid.length() > 0) {
            cid = Integer.parseInt(Scid);
        }

        //默认跳转页面
        int currentPage = 5;
        if (ScurrentPage != null && ScurrentPage.length() > 0) {
            currentPage = Integer.parseInt(ScurrentPage);
        }

        //默认页面大小
        int pageSize = 12;
        if(SpageSize!=null && SpageSize.length()>0){
            pageSize = Integer.parseInt(SpageSize);
        }

        PageBean<Route> pageBean = routeService.queryPageBean(cid, currentPage, pageSize);
        writeJsonValue(response,pageBean);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
