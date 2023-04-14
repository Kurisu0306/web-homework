package edu.hit.zhouhao.dao;

import edu.hit.zhouhao.domain.Route;

import java.util.List;

public interface RouteDAO {
    int findTotalRoute(int cid);
    List<Route> findRouteByPage(int cid, int start, int pageSize);
}
