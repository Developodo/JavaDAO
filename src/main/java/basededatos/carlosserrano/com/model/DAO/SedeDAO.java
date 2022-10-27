package basededatos.carlosserrano.com.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import basededatos.carlosserrano.com.model.Conection.MariaDBConnection;
import basededatos.carlosserrano.com.model.Entity.Sede;

//SedeDAOImplMariaDB
public class SedeDAO extends Sede{
//Las consultas MariaDB de este DAO
	private final static String INSERT = "INSERT INTO Sede (nombre) VALUES (?)";
	private final static String UPDATE = "UPDATE Sede SET nombre=? WHERE id=?";
	private final static String DELETE = "DELETE FROM Sede WHERE id=?";
	private final static String SELECTBYID = "SELECT id,nombre FROM Sede WHERE id=?";
	private final static String SELECTALL = "SELECT id,nombre FROM Sede";

	
	//Fin de las consultas
	public SedeDAO(int id,String nombre){ super(id,nombre);}
	public SedeDAO(){	super(); }
	public SedeDAO(Sede sede){	super(sede.getId(),sede.getNombre());}
	public SedeDAO(int id){
		this.getById(id);
	}

	public void save() {
		if(id==-1) {
			//INSERT
			Connection conn = MariaDBConnection.getConnection();
			if(conn != null) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, this.nombre);
					ps.executeUpdate();  //devuelve 1 si todo ok
					ResultSet rs = ps.getGeneratedKeys();
					if(rs.next()) {
						this.id = rs.getInt(1);
					}
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	public void update() {
		if(id!=-1) {
			//UPDATE
			Connection conn = MariaDBConnection.getConnection();
			if(conn != null) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(UPDATE);
					ps.setString(1, this.nombre);
					ps.setInt(2, this.id);
					ps.executeUpdate();  //devuelve 1 si todo ok
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	public void delete() {
		if(id!=-1) {
			//UPDATE
			Connection conn = MariaDBConnection.getConnection();
			if(conn != null) {
				PreparedStatement ps;
				try {
					ps = conn.prepareStatement(DELETE);
					ps.setInt(1, this.id);
					if(ps.executeUpdate()==1) {
						this.id=-1;
					}	
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	private void getById(int id) {
		Connection conn = MariaDBConnection.getConnection();
		if(conn != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SELECTBYID);
				ps.setInt(1, id);
				if(ps.execute()) {
					ResultSet rs = ps.getResultSet();
					if(rs.next()) {
						this.id = rs.getInt("id");
						this.nombre = rs.getString("nombre");
					}
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static List<Sede> getAll(){
		List<Sede> result = new ArrayList<Sede>();
		Connection conn = MariaDBConnection.getConnection();
		if(conn != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SELECTALL);
				if(ps.execute()) {
					ResultSet rs = ps.getResultSet();
					while(rs.next()) {
						Sede s = new Sede(rs.getInt("id"),
									rs.getString("nombre"));
						result.add(s);
					}
					rs.close();
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
