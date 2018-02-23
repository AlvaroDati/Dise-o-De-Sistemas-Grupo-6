package proyectoInversiones.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import proyectoInversiones.usuarios.Usuario;

public class RepositorioServicio {
	private static final String PERSISTENCE_UNIT_NAME = "db";
	private EntityManagerFactory emFactory;
	private Repositorio repositorio;
	private EntityManager emanager;
	public static RepositorioServicio repositorioServicio;
	
	
	public RepositorioServicio() {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		emanager = emFactory.createEntityManager();
		repositorio = new Repositorio(emanager);
	}
	
	
	public static RepositorioServicio getInstance(){
		if(repositorioServicio == null){
			repositorioServicio = new RepositorioServicio();
		}
		return repositorioServicio;
	}
	
	public Usuario buscarUsuarioPorNombre(String nombre) {
		List<Usuario> usuarioALoguearse = new ArrayList<Usuario>();
		 
	usuarioALoguearse = emanager.createNamedQuery("buscarUsuarioPorNombre").setParameter("filtro", "%" + nombre + "%").getResultList();

		System.out.println("Usuario: " + usuarioALoguearse.get(0).getPassword());
		Usuario usuarioALoguearse2 = null;
		for (int i = 0; i < usuarioALoguearse.size(); i++) {
			if (usuarioALoguearse.get(i).getUserTag().equals(nombre)) {
				usuarioALoguearse2 = usuarioALoguearse.get(i);
			}
		}

		return usuarioALoguearse2;
	}


	public Repositorio getRepositorio() {
		return repositorio;
	}


	public void setRepositorio(Repositorio repositorio) {
		this.repositorio = repositorio;
	}


	public EntityManager getEmanager() {
		return emanager;
	}


	public void setEmanager(EntityManager emanager) {
		this.emanager = emanager;
	}
	
	
	
	
	
}
