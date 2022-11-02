package basededatos.carlosserrano.com;

import basededatos.carlosserrano.com.model.DAO.ComplejoDAO;
import basededatos.carlosserrano.com.model.DAO.SedeDAO;
import basededatos.carlosserrano.com.model.Entity.Complejo;
import basededatos.carlosserrano.com.model.Entity.Sede;

public class Test2 {
	public static void main(String[] args) {
		SedeDAO sdao = new SedeDAO(2);
		System.out.println(sdao);
		
		ComplejoDAO cdao = new ComplejoDAO(6);
		System.out.println(cdao);
		
		sdao.addComplejo(cdao);

		


		/*ComplejoDAO cdao = new ComplejoDAO(1);
		System.out.println(cdao);*/
	}
}
