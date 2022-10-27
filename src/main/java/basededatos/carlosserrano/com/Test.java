package basededatos.carlosserrano.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	public static void main(String[] args) {
		String uri = "jdbc:mariadb://localhost:3306/olimpiadas2";
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(uri,"root","");
			//Conexión establecida
			Statement st = con.createStatement();
			String sql = "SELECT id,nombre FROM sede";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt("id")+
						" , "+rs.getString("nombre"));
			}
			rs.close();
			st.close();
			
			PreparedStatement pst = con.prepareStatement("SELECT id,nombre FROM complejo WHERE id_sede = ?");
			int id=1;
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("id")+
						" , "+rs.getString("nombre"));
			}
			rs.close();
			pst.close();
		}catch (SQLException e) {
			e.printStackTrace();  //quitar en producción
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();  //quitar en producción
			}
		}
	}
}
