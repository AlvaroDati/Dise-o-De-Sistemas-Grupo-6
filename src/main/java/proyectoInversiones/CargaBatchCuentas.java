package proyectoInversiones;

import java.util.ArrayList;
import java.util.TimerTask;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoInversiones.repositorio.Repositorio;


public class CargaBatchCuentas extends TimerTask {
  
	private static final String PERSISTENCE_UNIT_NAME = "dds";
	private EntityManagerFactory emFactory;
	private Repositorio repositorio;
	
	public CargaBatchCuentas() {
		super();
	}

    @Override
    public void run() {
		try {
			this.persistirConJson(); 
		}
		catch (Exception e) {
			e.printStackTrace();
	    }		
	}
    
    public void persistirConJson() {
    	emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		NuevoLeerArchivo archivo = new NuevoLeerArchivo();
		ArrayList<Empresa> empresas = archivo.leerArchivo();
		
		
		for (int i = 0; i < empresas.size(); i++) {
			for (int j = 0; j < empresas.get(i).getPeriodos().size(); j++) {
				empresas.get(i).getPeriodos().get(j).setEmpresa(empresas.get(i));
				empresas.get(i).getPeriodos().get(j).getCuentas().setPeriodoVinculado(empresas.get(i).getPeriodos().get(j));
				repositorio.empresasRepo().persistir(empresas.get(i)); 
				}
		}
		repositorio.cerrar();
		emFactory.close();
    }
    
    }
    
    
