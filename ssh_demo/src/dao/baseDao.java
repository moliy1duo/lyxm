package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class baseDao {

	public static final String url = "=jdbc:oracle:thin:@//132.77.74.87:1521/lyxt";
	//// 驱动程序名：@主机名/IP：端口号：数据库实例名
	public static final String name = "oracle.jdbc.driver.OracleDriver";
	public static final String user = "admin";
	public static final String password = "admin";

	public static Connection conn = null;
	public static PreparedStatement pst = null;

	public baseDao(String sql) {
		try {
			Class.forName(name);// 加载及注册驱动程序
			conn = DriverManager.getConnection(url, user, password);// 建立连接
			pst = conn.prepareStatement(sql);// 鍑嗗鎵ц璇彞
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static public int queryForObject(String sql) {
		baseDao db = new baseDao(sql);
		ResultSet ret = null;
		int o = -1;
		try {
			ret = db.pst.executeQuery();
			while (ret.next()) {

				String udate = ret.getString("userid");

				if (udate != null) {
					o = Integer.parseInt(udate);
				} else {
					return -1;
				}
			} // 鏄剧ず鏁版嵁
			ret.close();
			db.close();// 鍏抽棴杩炴帴
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

	static public void update(String sql) {
		baseDao db = new baseDao(sql);
		ResultSet ret = null;
		List result = new ArrayList();
		int o = -1;
		try {
			System.out.println(sql);
			db.pst.executeUpdate();

			db.close();// 鍏抽棴杩炴帴
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	
	// 检查是否存在临时表
	public static boolean validateTableExist(String tableName) throws SQLException {
		int result = 0;
		String sql = "select count(*) from user_tables where table_name= '" + tableName + "'";
		// Connection conn = this.getSession().connection();
		
		baseDao db = new baseDao(sql);
		ResultSet rs = null;
		try {
			System.out.println(sql);
			rs = db.pst.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			db.close();
		}
		return result == 0 ? false : true;
	}

	
	static public List queryForList(String sql) {
		baseDao db = new baseDao(sql);
		ResultSet ret = null;
		List result = new ArrayList();
		int o = -1;
		try {
			System.out.println(sql);
			ret = db.pst.executeQuery();
			result = resultSetToList(ret);
			ret.close();
			db.close();// 鍏抽棴杩炴帴
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] arg) {
		System.out.println("hello word");
		baseDao db = new baseDao("select * from user");
		ResultSet ret = null;
		try {
			ret = db.pst.executeQuery();
			while (ret.next()) {
				String uid = ret.getString("yhbh");
				String ufname = ret.getString("yhxm");
				String ulname = ret.getString("password");
				String udate = ret.getString("qx");
				System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate);
			} // 鏄剧ず鏁版嵁
			ret.close();
			db.close();// 鍏抽棴杩炴帴
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List resultSetToList(ResultSet rs) throws java.sql.SQLException {
		if (rs == null)
			return Collections.EMPTY_LIST;
		ResultSetMetaData md = rs.getMetaData(); // 寰楀埌缁撴灉闆�rs)鐨勭粨鏋勪俊鎭紝姣斿瀛楁鏁般�瀛楁鍚嶇瓑
		int columnCount = md.getColumnCount(); // 杩斿洖姝�ResultSet 瀵硅薄涓殑鍒楁暟
		List list = new ArrayList();
		Map rowData = new HashMap();
		while (rs.next()) {
			rowData = new HashMap(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(rowData);
			System.out.println("list:" + list.toString());
		}
		return list;
	}

	public static void createTable(String sql) {
		// TODO Auto-generated method stub
		baseDao db = new baseDao(sql);
		ResultSet ret = null;
//		PreparedStatement stmt;
		try {
			System.out.println(sql);
			ret = db.pst.executeQuery();
			ret.close();
			db.close();//
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
