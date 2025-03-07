package dev.spring.petclinic.global.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static String DB_URL;
	private static String USER;
	private static String PASSWORD;

	static {
		try {
			// JDBC 드라이버 로드
			Class.forName(DRIVER_NAME);

			// application.properties 파일 읽기
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/application.properties"));

			// 속성 값 가져오기
			DB_URL = properties.getProperty("spring.datasource.url");
			USER = properties.getProperty("spring.datasource.username");
			PASSWORD = properties.getProperty("spring.datasource.password");

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASSWORD);
	}

	public static void close(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// DB 연결 테스트
		try (Connection conn = getConnection()) {
			if (conn != null) {
				System.out.println("✅ DB 연결 성공!");
			} else {
				System.out.println("❌ DB 연결 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
