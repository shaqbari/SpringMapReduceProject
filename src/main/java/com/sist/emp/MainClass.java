package com.sist.emp;

import java.io.FileWriter;
import java.sql.*;

import org.rosuda.REngine.Rserve.RConnection;


/*
 * 	오라클 (emp) ==> R ==> 그래프 ==> 웹에 출력
 * */
public class MainClass {
	
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@211.238.142.123:1521:ORCL";
			Connection conn=DriverManager.getConnection(url, "scott", "tiger");
			if (conn==null) {
				System.out.println("접속실패");
			}else {
				System.out.println("접속성공");
			}
			
			String sql="Select ename, sal From emp";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			String data="";
			
			while (rs.next()) {
				//System.out.println(rs.getString(1)+" "+rs.getString(2));
				data+=rs.getString(1)+" "+rs.getInt(2)+"\n";
				
			}
			rs.close();
			
			//hdfs
			FileWriter fw=new FileWriter("/home/sist/emp.txt");
			fw.write(data.substring(0, data.lastIndexOf("\n")));//맨마지막 \n을 지운다.
			fw.close();
			
			rGraph();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void rGraph() {
		try {
			RConnection rc=new RConnection();
			rc.voidEval("data<-read.table(\"/home/sist/emp.txt\")");//r수행하는 명령어 내린다
			rc.voidEval("png(\"/home/sist/emp.png\", width=1000, height=700)");
			rc.voidEval("barplot(data$V2, names.arg=data$V1, col=rainbow(15))");
			rc.voidEval("dev.off()");
			rc.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
