package xin.viny.utilsTest;

import java.sql.Connection;

import org.junit.Test;

import xin.viny.utils.JDBCUtils;

public class UtilsTest {
	@Test
	public void testGetConn() {
		try {
			Connection conn = JDBCUtils.getConnection();
			System.out.println(conn);
			JDBCUtils.releaseDB(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
