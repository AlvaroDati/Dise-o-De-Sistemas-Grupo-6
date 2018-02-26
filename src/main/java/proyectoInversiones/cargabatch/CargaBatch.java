package proyectoInversiones.cargabatch;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.repositorio.Repositorio;
import proyectoInversiones.repositorio.RepositorioServicio;
import proyectoInversiones.usuarios.Usuario;

public class CargaBatch extends TimerTask {

	String rutaAnterior = "";
	Long ultimaMod = null;
	private static RepositorioServicio repositorioServicio;
	public CargaBatch() {
		super();
	}

	@Override
	public void run() {
		try {
			this.persistirJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void persistirJson() {
		repositorioServicio = RepositorioServicio.getInstance();
		persistirEmpresas();
		persistirIndicadores();
		persistirMetodologias();
		
	}
	
	
	public void persistirEmpresas(){
		
	}
	
	public void persistirIndicadores(){
		
	}
	
	public void persistirMetodologias(){
		
	}
	
}

