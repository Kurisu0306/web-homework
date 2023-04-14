package edu.hit.zhouhao.dao.impl;

import edu.hit.zhouhao.dao.RouteDAO;
import edu.hit.zhouhao.domain.Route;
import edu.hit.zhouhao.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDAOImpl implements RouteDAO {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalRoute(int cid) {
        String sql = "select count(*) from tab_route where cid = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, cid);
    }

    @Override
    public List<Route> findRouteByPage(int cid, int start, int pageSize) {
        /*
        * select * from tableName limit i,n
            # tableName：表名
            # i：为查询结果的索引值(默认从0开始)，当i=0时可省略i
            # n：为查询结果返回的数量
            # i与n之间使用英文逗号","隔开

            #
            limit n 等同于 limit 0,n

        * */
        String sql = "select * from tab_route where cid = ? limit ?,?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class),cid,start,pageSize);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}
