
package pachang.gsz.utils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * 数据库操作
 * 
 * @title SQLHelper
 * @author guosuzhou
 *
 * @data2017年12月29日下午8:36:38
 *
 */
public class SQLHelper {
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	/**
	 * 
	 * @param driverClassName 驱动 默认mysql
	 * @param url 链接
	 * @param username 用户名 默认root
	 * @param password 密码 默认 root
	 * @throws Exception 
	 */
	public SQLHelper(String driverClassName,String url,String username,String password) throws Exception{
		if(driverClassName==null||driverClassName.equals("")){
			this.driverClassName="com.mysql.jdbc.Driver";
		}else{
			this.driverClassName=driverClassName;
		}
		if(url==null||url.equals("")){
			throw new Exception("链接不能空");
		}else{
			this.url=url;
		}
		if(username==null||username.equals("")){
			this.username="root";
		}else{
			this.username=username;
		}
		if(password==null||password.equals("")){
			this.password="root";
		}else{
			this.password=password;
		}
		List<String > list =new ArrayList<>();
		list.add("driverClassName="+this.driverClassName);
		list.add("url="+this.url);
		list.add("username="+this.username);
		list.add("password="+this.password);
		list.add("initialSize=5");
		list.add("maxActive=50");
		list.add("maxIdle=10");
		list.add("minIdle=3");
		list.add("maxWait=80000");
		list.add("connectionProperties=useUnicode=true;characterEncoding=utf8");
		list.add("defaultAutoCommit=true");
		try {
			IOUtils.createHideFile("./dbcpCfg.properties",list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建表
	 * 
	 * @author guosuzhou
	 * @param sqlString
	 * @return
	 * @throws Exception
	 * 
	 * @data 2017年12月29日下午8:37:15
	 */
	public static int createTable(String sqlString) throws Exception{
		
		return updateBySQL(sqlString);
	}
	
	/**
	 * 插入
	 * 
	 * @author guosuzhou
	 * @param sqlString
	 * @return
	 * @throws Exception 
	 * 
	 * @data 2017年12月29日下午8:37:27
	 */
	public static int insertBySQL(String sqlString) throws Exception {
		return updateBySQL(sqlString);
	}

	/**
	 * 插入
	 * 
	 * @author guosuzhou
	 * @param sqlString 
	 * @param parms 插入的数据
	 * @return
	 * @throws SQLException
	 * 
	 * @data 2017年12月29日下午8:37:41
	 */
	public static int insertBySQL(String sqlString, Object[] parms ) throws SQLException {
		return updateBySQL(sqlString, parms);
	}
	/**
	 * 插入
	 * 
	 * @author guosuzhou
	 * @param sqlString
	 * @param parms
	 * @param types
	 * @return
	 * @throws SQLException 
	 * 
	 * @data 2017年12月29日下午8:38:12
	 */
	public static int insertBySQL(String sqlString, Object[] parms, int[] types) throws SQLException {
		return updateBySQL(sqlString, parms, types);
	}
	
	/**
	 * 插入
	 * 
	 * @author guosuzhou
	 * @param sqlString
	 * @param cps
	 * @return
	 * @throws SQLException
	 * 
	 * @data 2017年12月29日下午8:43:46
	 */
	public static int insertBySQL(String sqlString, CompletePrepareStatementI cps) throws SQLException {
		return updateBySQL(sqlString, cps);
	}
	
	/**
	 * 删除信息
	 * 
	 * @author guosuzhou
	 * @param sqlString
	 * @return
	 * @throws SQLException
	 * 
	 * @data 2017年12月29日下午8:45:48
	 */
	public static int deleteBySql(String sqlString)throws SQLException{
		return deleteBySQL(sqlString);
	}
	
	private static String selectBySQLString(String sqlString) throws SQLException{
		String string = null;
		Connection conn = DbUtil.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			conn.setAutoCommit(false);
			prepareStatement = conn.prepareStatement(sqlString);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next())
				string = resultSet.getString("path");
			conn.commit();
		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
			throw e;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.closeSource(resultSet, prepareStatement, conn);
		}
		return string;
	}
	
	private static ArrayList<HashMap<String, Object>> selectBySQL(String sqlString) throws SQLException {
		ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
		Connection conn = DbUtil.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		ResultSetMetaData metaData = null;
		try {
			conn.setAutoCommit(false);
			prepareStatement = conn.prepareStatement(sqlString);
			resultSet = prepareStatement.executeQuery();
			metaData = resultSet.getMetaData();    //获取此 ResultSet 对象的列的编号、类型和属性。 
			int columnCount = metaData.getColumnCount();
			for (; resultSet.next();) {
				HashMap<String, Object> row = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					String columnName = metaData.getColumnName(i + 1);
					row.put(columnName, resultSet.getObject(i + 1));
				}
				rows.add(row);
			}
			conn.commit();
		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
			throw e;
		} finally {
			try {
				//System.out.println(rows);
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.closeSource(resultSet, prepareStatement, conn);
		}
		return rows;
	}
   
	private static int selectColumnCount(String sqlString) throws SQLException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		int columnCount = 0;
		try {
			conn.setAutoCommit(false);
			prepareStatement = conn.prepareStatement(sqlString);
			resultSet = prepareStatement.executeQuery();
			for (; resultSet.next();) {
				columnCount++;
			}
			conn.commit();
		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
			throw e;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.closeSource(resultSet, prepareStatement, conn);
		}
		return columnCount;
	}
	
	private static ArrayList<HashMap<String, Object>> selectBySQL(String sqlString, Object[] parms, int[] types) throws SQLException {
		if (parms == null || types == null || parms.length != types.length) {
			throw new RuntimeException("我也不知道这是什么异常！");
		}
		ArrayList<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
		Connection conn = DbUtil.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		ResultSetMetaData metaData = null;
		try {
			conn.setAutoCommit(false);
			prepareStatement = conn.prepareStatement(sqlString);
			for (int len = parms.length, i = 0; i < len; i++) {
				prepareStatement.setObject(i + 1, parms[i], types[i]);
			}
			resultSet = prepareStatement.executeQuery();
			metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (; resultSet.next();) {
				HashMap<String, Object> row = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					String columnName = metaData.getColumnName(i + 1);
					row.put(columnName, resultSet.getObject(i + 1));
				}
				rows.add(row);
			}
			conn.commit();
		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
			throw e;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.closeSource(resultSet, prepareStatement, conn);
		}
		return rows;
	}

	private static int updateBySQL(String sqlString) throws Exception {
		Connection conn = DbUtil.getConnection();
		PreparedStatement prepareStatement = null;
		int result = 0;
		try {
			conn.setAutoCommit(false);
			prepareStatement = conn.prepareStatement(sqlString);
			result = prepareStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
			throw e;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.closeSource(null, prepareStatement, conn);
		}
		return result;
	}
	private static int deleteBySQL(String sqlString) throws SQLException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement prepareStatement = null;
		int result = 0;
		try {
			conn.setAutoCommit(false);
			prepareStatement = conn.prepareStatement(sqlString);
			result = prepareStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
			throw e;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.closeSource(null, prepareStatement, conn);
		}
		return result;
	}

	private static int updateBySQL(String sqlString, Object[] parms )throws SQLException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement prepareStatement = null;
		int result = 0;
		try {
			conn.setAutoCommit(false);
			prepareStatement = conn.prepareStatement(sqlString);
			for (int len = parms.length, i = 0; i < len; i++) {
				prepareStatement.setObject(i + 1, parms[i]);
			}
			result = prepareStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			//System.out.println(sqlString);
//			e.printStackTrace();
			throw e;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.closeSource(null, prepareStatement, conn);
		}
		return result;
	}
	
	private static int updateBySQL(String sqlString, Object[] parms, int[] types) throws SQLException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement prepareStatement = null;
		int result = 0;
		try {
			conn.setAutoCommit(false);
			prepareStatement = conn.prepareStatement(sqlString);
			for (int len = parms.length, i = 0; i < len; i++) {
				prepareStatement.setObject(i + 1, parms[i], types[i]);
			}
			result = prepareStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
			throw e;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.closeSource(null, prepareStatement, conn);
		}
		return result;
	}

	private static int updateBySQL(String sqlString, CompletePrepareStatementI cps) throws SQLException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement prepareStatement = null;
		int result = 0;
		try {
			conn.setAutoCommit(false);
			prepareStatement = conn.prepareStatement(sqlString);
			prepareStatement = cps.completeIt(prepareStatement);
			result = prepareStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
			throw e;
		} finally {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DbUtil.closeSource(null, prepareStatement, conn);
		}
		return result;
	}
	
	public static void main(String[] args) {
		try {
			SQLHelper sqlHelper=new SQLHelper(null, "jdbc:mysql://192.168.1.103:3306/test", null, null);
			sqlHelper.createTable("create table t1(id int not null,name char(20));");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
