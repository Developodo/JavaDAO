package basededatos.carlosserrano.com;

import basededatos.carlosserrano.com.model.DAO.SedeDAO;
import basededatos.carlosserrano.com.model.Entity.Sede;

public class Test2 {
	public static void main(String[] args) {
		SedeDAO sdao = new SedeDAO(1);
		System.out.println(sdao);
		
		/***/
		Sede newSede = new Sede(-1,"Jaén");
		SedeDAO newSedeDAO = new SedeDAO(newSede);
		newSedeDAO.save();
	}
}
