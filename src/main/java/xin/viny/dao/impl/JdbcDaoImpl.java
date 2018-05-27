package xin.viny.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import xin.viny.dao.DAO;
import xin.viny.utils.ReflectionUtils;

public class JdbcDaoImpl<T> implements DAO<T> {

	private QueryRunner queryRunner = null;
	private Class<T> type;
	
	public JdbcDaoImpl() {
		queryRunner = new QueryRunner();
		type = ReflectionUtils.getSuperGenericType(getClass());
	}
	
	@SuppressWarnings("unchecked")
	public <E> E getForValue(Connection connection, String sql, Object... args) throws SQLException {
		return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
	}

	public List<T> getForList(Connection connection, String sql, Object... args) throws SQLException {
		return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
	}

	public T get(Connection connection, String sql, Object... args) throws SQLException {
		return queryRunner.query(connection, sql, new BeanHandler<>(type), args);
	}

	public void update(Connection connection, String sql, Object... args) throws SQLException {
		queryRunner.update(connection, sql, args);
	}

}
