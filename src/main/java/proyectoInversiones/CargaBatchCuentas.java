package proyectoInversiones;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import proyectoInversiones.repositorio.Repositorio;
import proyectoInversiones.usuarios.Usuario;

public class CargaBatchCuentas extends TimerTask {
	
	int nroDeArchivosPersistidos = 0;
	int sumaHashPersistidos = 0;
	private static final String PERSISTENCE_UNIT_NAME = "db";
	private EntityManagerFactory emFactory;
	@PersistenceContext(unitName = "db", type = PersistenceContextType.EXTENDED)
	private static EntityManager emanager;
	private Repositorio repositorio;

	public CargaBatchCuentas() {
		super();
	}

	@Override
	public void run() {
		try {
			this.persistirArchivosDrive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void persistirArchivosDrive() throws IOException {

		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		emanager = emFactory.createEntityManager();
		repositorio = new Repositorio(emanager);

		DescargaDrive lectorDrive = new DescargaDrive();
		lectorDrive.obtenerEmpresas();
		
		ArrayList<Empresa> empresas = lectorDrive.getTodasLasEmpresas();
		ArrayList<Integer> hashcodes = lectorDrive.getHashcodesDeArchivos();
		
		int sumaHash = 0;
		
		for (Integer hash:hashcodes){
			sumaHash = sumaHash + hash;
		}
		
		int nroDeArchivos = lectorDrive.getCantidadDeArchivos();
		
		if (nroDeArchivosPersistidos == nroDeArchivos && sumaHash == sumaHashPersistidos) {

			System.out.printf("\nEl/los archivos ya fue/ron cargado/s\n");
			repositorio.cerrar();
			emFactory.close();

		} else {
			System.out.printf("\nPersistiendo archivo\n");
			System.out.printf("///////////////////////\n");
			for (int i = 0; i < empresas.size(); i++) {
				for (int j = 0; j < empresas.get(i).getPeriodos().size(); j++) {
					empresas.get(i).getPeriodos().get(j).setEmpresa(empresas.get(i));
					empresas.get(i).getPeriodos().get(j).getCuentas()
							.setPeriodoVinculado(empresas.get(i).getPeriodos().get(j));
					repositorio.empresasRepo().persistir(empresas.get(i));
				}
			}

			sumaHashPersistidos = sumaHash;
			nroDeArchivosPersistidos = nroDeArchivos;
			repositorio.cerrar();
			emFactory.close();
		}
	}

}
