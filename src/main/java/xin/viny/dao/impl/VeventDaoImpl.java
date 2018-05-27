package xin.viny.dao.impl;

import java.sql.Connection;

import xin.viny.bean.Vevent;
import xin.viny.utils.JDBCUtils;

public class VeventDaoImpl extends JdbcDaoImpl<Vevent> {

	public Vevent selectVeventById(int id) {
		Connection connection = null;
		Vevent event = null;
		try {
			connection = JDBCUtils.getConnection();
			String sql = "SELECT id,info,vdate,vtime,priority,is_done,u_id,label_id FROM vevent WHERE id = ?";
			event = get(connection, sql, id); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.releaseDB(connection);
		}
		return event;
	}
}
