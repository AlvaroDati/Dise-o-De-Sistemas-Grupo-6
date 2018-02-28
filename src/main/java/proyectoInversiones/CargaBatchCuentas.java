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

import proyectoInversiones.DescargaDrive;
import proyectoInversiones.repositorio.EmpresasRepo;
import proyectoInversiones.repositorio.Repositorio;
import proyectoInversiones.repositorio.RepositorioServicio;
import proyectoInversiones.usuarios.Usuario;

public class CargaBatchCuentas extends TimerTask {
	
	int nroDeArchivosPersistidos = 0;
	int sumaHashPersistidos = 0;
	
	private Repositorio repositorio;

	public CargaBatchCuentas() {
		super();
	}

	@Override
	public void run() {
		try {
			new EmpresasRepo();
			this.persistirArchivosDrive();
			System.out.println("Se persistieron los archivos");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void persistirArchivosDrive() throws IOException {


		RepositorioServicio repo = RepositorioServicio.getInstance();
		DescargaDrive lectorDrive = new DescargaDrive();
		
		
		ArrayList<Empresa> empresas = lectorDrive.obtenerEmpresas();
		ArrayList<Integer> hashcodes = lectorDrive.getHashcodesDeArchivos();
		
		
		int sumaHash = 0;
		
		for (Integer hash:hashcodes){
			sumaHash = sumaHash + hash;
		}
		
		
		if ( sumaHash == sumaHashPersistidos) {

			System.out.printf("\nEl/los archivos ya fue/ron cargado/s\n");
			repositorio.cerrar();

		} else {
			System.out.printf("\nSe llama a una instancia de repositorioServicio para persitir los archivos del drive\n");
			
			repo.persistir(empresas);
			System.out.println("Se persistieron los nuevos archivos");
			sumaHashPersistidos = sumaHash;
		
		}
	}

}
