package dao;

import java.sql.SQLException;

public class fpDao {

	String Temp = "TEMP_TABLE";
	public void addFpHeader(String fpCode, String fapiaoriqi, String mingcheng) {
		System.out.println("������ʱ��");
		
		//�����ʱ���Ƿ����
		try {
			if(!baseDao.validateTableExist(Temp)){//��ʱ������
				baseDao.createTable("create table "+ Temp  + "(" +
									"lyno VARCHAR2(32) not null," +
									"lyname VARCHAR2(132)," +
									"lylb  VARCHAR2(64)," +
									"constraint pk_id primary key (lyno))");
				System.out.println("��ʱ�������");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*baseDao.update(" INSERT INTO fapiaocontent "
				+ "(fpCode,fapiaoriqi,mingcheng,nashuirenshibiehao,dizhidianhua,kaihuhang) " + "VALUES('" + fpCode
				+ "','" + fapiaoriqi + "','" + mingcheng + "'); ");
		System.out.println("���¼�����");*/
	}

	public void addFpRow(double no, String guige, String zhujiliang) {
		System.out.println("��Ϣ¼��");
		baseDao.update(" INSERT INTO " + Temp 
				+ "(lyno,lyname,lylb) " + "VALUES('" + no 
				+ "','" + guige + "','" + zhujiliang + "')");
		System.out.println("��Ϣ¼�����");
	}

}
