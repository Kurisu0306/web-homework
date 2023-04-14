package edu.hit.zhouhao.service;

import edu.hit.zhouhao.domain.PageBean;
import edu.hit.zhouhao.domain.Route;

public interface RouteService {
    PageBean<Route> queryPageBean(int cid,int currentPage,int pageSize);
}
