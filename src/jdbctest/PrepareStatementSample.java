package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//http://localhost:8080/2017_1st_intern/
public class PrepareStatementSample {
	private static final String CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/dbstudy";
	private static final String ID = "jeong";
	private static final String PASSWORD = "jeong";

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySqlJdbcDriver find Error" + e);
		}

		try (Connection connection = DriverManager.getConnection(CONNECTION_URL, ID, PASSWORD);
			PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM departments WHERE dept_no = ?");) {
			preparedStatement.setInt(1, 10);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					System.out.println("부서 no :" + result.getInt("dept_no") + " 부서명 :" + result.getString("dept_name")
						+ " 지역 :" + result.getString(3));
				}
			}
		} catch (SQLException e) {
			System.out.println("MySqlJdbcSample error" + e);
		}
	}
}
