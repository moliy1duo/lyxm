package dao;

import java.sql.SQLException;

public class fpDao {

	String Temp = "TEMP_TABLE";
	public void addFpHeader(String fpCode, String fapiaoriqi, String mingcheng) {
		System.out.println("建立临时表");
		
		//检查临时表是否存在
		try {
			if(!baseDao.validateTableExist(Temp)){//临时表不存在
				baseDao.createTable("create table "+ Temp  + "(" +
									"lyno VARCHAR2(32) not null," +
									"lyname VARCHAR2(132)," +
									"lylb  VARCHAR2(64)," +
									"constraint pk_id primary key (lyno))");
				System.out.println("临时表建立完成");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*baseDao.update(" INSERT INTO fapiaocontent "
				+ "(fpCode,fapiaoriqi,mingcheng,nashuirenshibiehao,dizhidianhua,kaihuhang) " + "VALUES('" + fpCode
				+ "','" + fapiaoriqi + "','" + mingcheng + "'); ");
		System.out.println("表格录入完成");*/
	}

	public void addFpRow(double no, String guige, String zhujiliang) {
		System.out.println("信息录入");
		baseDao.update(" INSERT INTO " + Temp 
				+ "(lyno,lyname,lylb) " + "VALUES('" + no 
				+ "','" + guige + "','" + zhujiliang + "')");
		System.out.println("信息录入完成");
	}

}
