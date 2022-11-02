package basededatos.carlosserrano.com.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import basededatos.carlosserrano.com.model.Conection.MariaDBConnection;
import basededatos.carlosserrano.com.model.Entity.Complejo;
import basededatos.carlosserrano.com.model.Entity.Sede;

public class ComplejoDAO extends Complejo{
	//Las consultas MariaDB de este DAO
		private final static String INSERT = "INSERT INTO Complejo (nombre,superficie,id_sede) VALUES (?,?,?)";
		private final static String UPDATE = "UPDATE Complejo SET nombre=?,superficie=?,id_sede=? WHERE id=?";
		private final static String DELETE = "DELETE FROM Complejo WHERE id=?";
		private final static String SELECTBYID = "SELECT id,nombre,superficie,id_sede FROM Complejo WHERE id=?";
		private final static String SELECTALL = "SELECT id,nombre,superficie FROM Complejo";
		private final static String SELECTBYSEDE = "SELECT id,nombre,superficie,id_sede FROM Complejo WHERE id_sede=?";
	
	public ComplejoDAO(int id, String nombre, int superficie) {
		super(id,nombre,superficie);
	}
	public ComplejoDAO() {
		super();
	}
	public ComplejoDAO(Complejo d) {
		this(d.getId(),d.getNombre(),d.getSuperficie());
		this.sede = d.getSede();
	}
	public ComplejoDAO(int id) {
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
					System.out.println(this.sede);
					ps.setInt(3, this.sede.getId());
					ps.setInt(2, this.superficie);
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
					ps.setInt(2, this.superficie);
					ps.setInt(3, this.sede.getId());
					ps.setInt(4, this.id);
					ps.executeUpdate();  //devuelve 1 si todo ok
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	public void remove() {
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
						this.superficie = rs.getInt("superficie");
						int id_sede = rs.getInt("id_sede");
						//eager
						this.sede = new SedeDAO(id_sede);
					}
				}
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static List<Complejo> getAll(){
		List<Complejo> result = new ArrayList<Complejo>();
		Connection conn = MariaDBConnection.getConnection();
		if(conn != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SELECTALL);
				if(ps.execute()) {
					ResultSet rs = ps.getResultSet();
					while(rs.next()) {
						Complejo c = new Complejo(rs.getInt("id"),
									rs.getString("nombre"),
									rs.getInt("superficie"));
						result.add(c);
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
	public static List<Complejo> getComplejosOfSede(Sede s){
		List<Complejo> result = new ArrayList<Complejo>();
		Connection conn = MariaDBConnection.getConnection();
		if(conn != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SELECTBYSEDE);
				ps.setInt(1, s.getId());
				if(ps.execute()) {
					ResultSet rs = ps.getResultSet();
					while(rs.next()) {
						Complejo c = new Complejo(rs.getInt("id"),
									rs.getString("nombre"),
									rs.getInt("superficie"));
						c.setSede(new Sede(rs.getInt("id_sede")));
						result.add(c);
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
