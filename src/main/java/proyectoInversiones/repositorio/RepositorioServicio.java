package proyectoInversiones.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import proyectoInversiones.Indicador;
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

		System.out.println("Cantidad de usuarios extraídos de la bd: " + usuarioALoguearse.size());
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
	
	
	public List<String> buscarIndicadorPorUsuario(String nombre){
		List<Indicador> indicador = new ArrayList<Indicador>();
		List<String> expresionIndicadores = new ArrayList<String>();
		Usuario us = this.buscarUsuarioPorNombre(nombre);
		String query = "select  expresion from indicadores where usuario_id="+us.getId()+";";
//		System.out.println("Query: "+query);
		//indicador = emanager.createQuery(query).getResultList();
	//	indicador = emanager.createNamedQuery("buscarIndicadorPorUsuario").setParameter("filtro", us.getId()).getResultList();
expresionIndicadores =		emanager.createNativeQuery(query).getResultList();
//		System.out.println("Indicadores extraidos de la bd: "+expresionIndicadores);
//		System.out.println("Cantidad de indicadores extraidos de la bd: "+indicador.size());
//		
//		for(int i = 0;i<expresionIndicadores.size();i++){
//		//	System.out.println("Expresion indicador: "+indicador.get(i).getNombre());
//			System.out.println("asdf");
//			System.out.println("Expresion indicador: "+expresionIndicadores.get(i));
//		}
		return expresionIndicadores;
	}
	
	
	
	
}
