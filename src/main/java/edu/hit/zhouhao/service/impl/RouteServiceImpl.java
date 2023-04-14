package edu.hit.zhouhao.service.impl;

import edu.hit.zhouhao.dao.RouteDAO;
import edu.hit.zhouhao.dao.impl.RouteDAOImpl;
import edu.hit.zhouhao.domain.PageBean;
import edu.hit.zhouhao.domain.Route;
import edu.hit.zhouhao.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDAO routeDAO = new RouteDAOImpl();

    @Override
    public PageBean<Route> queryPageBean(int cid, int currentPage, int pageSize) {
        PageBean<Route> pageBean = new PageBean<Route>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        int totalSize = routeDAO.findTotalRoute(cid);
        pageBean.setTotalCount(totalSize);

        int start = (currentPage - 1) * pageSize;//计算start角标
        List<Route> routeByPage = routeDAO.findRouteByPage(cid, start, pageSize);
        pageBean.setList(routeByPage);

        //总页数是否能整除
        int totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        pageBean.setTotalPage(totalPage);
        return pageBean;

    }
}
