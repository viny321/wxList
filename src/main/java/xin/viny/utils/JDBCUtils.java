package xin.viny.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;


public class JDBCUtils {

		private static DataSource dataSource = null;
		
		//数据库连接池应只被初始化一次. 
		static{    
            // java 7中的资源打开方式   
		    ClassLoader classLoader = JDBCUtils.class.getClassLoader();  
            try (InputStream is = classLoader.getResourceAsStream("/druid.properties")) {  
                 Properties properties = new Properties();
    			properties.load(is);
    			dataSource = DruidDataSourceFactory.createDataSource(properties);
            } catch (Exception e ) {  
                e.printStackTrace();
            } 
			//dataSource = new ComboPooledDataSource("helloc3p0");
		}
		//获取connection 、getConnection
		public static Connection getConnection() throws Exception {
			return dataSource.getConnection();
		}

		//处理数据库事务的
			//提交事务
			public static void commit(Connection connection){
				if(connection != null){
					try {
						connection.commit();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			//回滚事务
			public static void rollback(Connection connection){
				if(connection != null){
					try {
						connection.rollback();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			//开始事务
			public static void beginTx(Connection connection){
				if(connection != null){
					try {
						connection.setAutoCommit(false);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		
		//关闭connection资源
		public static void releaseDB(Connection connection) {
			if (connection != null) {
				try {
					//数据库连接池的 Connection 对象进行 close 时
					//并不是真的进行关闭, 而是把该数据库连接会归还到数据库连接池中. 
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}
