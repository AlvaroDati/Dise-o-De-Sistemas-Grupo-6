package proyectoInversiones.repositorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import proyectoInversiones.Indicador;
import proyectoInversiones.Periodo;
import proyectoInversiones.DescargaDrive;
import proyectoInversiones.usuarios.Usuario;
import proyectoInversiones.Empresa;

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
	
	
	/*
	 * 
	 * INICIO
	 * MÉTODO DE PERSISTENCIA
	 * 
	 */
	public ArrayList<Empresa> obtenerEmpresasPersistidas(ArrayList<Empresa> empresas){
		System.out.println("Vericando empresas a persistir");
		System.out.println("Vericando empresas a persistir");
		ArrayList<Empresa> empresasAPersistir = new ArrayList<Empresa>();
		
		for(int i=0;i<empresas.size();i++){
			System.out.println("Empresas: " +empresas.get(i).getNombre());
			List<Empresa> empresasEnLaDB = new ArrayList<Empresa>();
			empresasEnLaDB= emanager.createNamedQuery("buscarEmpresaPorNombre").setParameter("filtro", empresas.get(i).getNombre()).getResultList();
			if(empresasEnLaDB.size() == 0){
				System.out.println("No se encontró la empresa:" + empresas.get(i).getNombre()+"en la base de datos");
				empresasAPersistir.add(empresas.get(i));
			}
		}
		System.out.println("Cantidad de empresas a persistir: " + empresasAPersistir.size());
		return empresasAPersistir;
	}
	
	public void persistirEmpresa(Empresa unaEmpresa){
		EntityManager em = this.getEmanager();
		em.getTransaction().begin();
		em.persist(unaEmpresa);
		em.getTransaction().commit();
	}
	
	public void persistir(ArrayList<Empresa> empresas) throws IOException {
		System.out.println("Cantidad de empresas a persisitir: " + empresas.size());
		ArrayList<Empresa> empresasAPersistir = this.obtenerEmpresasPersistidas(empresas);
		
		
		System.out.println("Empresas persistidas: ");
		List<Periodo> periodos = new ArrayList<Periodo>();
		for(int i = 0;i<empresasAPersistir.size();i++){
			periodos.addAll(empresasAPersistir.get(i).getPeriodos());
		}
		
		for(int i = 0;i<empresasAPersistir.size();i++){
			
			for(int j = 0;j<empresasAPersistir.get(i).getPeriodos().size();j++){
				empresasAPersistir.get(i).getPeriodos().get(j).setEmpresa(empresas.get(i));
				empresasAPersistir.get(i).getPeriodos().get(j).getCuentas().setPeriodoVinculado(empresasAPersistir.get(i).getPeriodos().get(j));
			}
		}
	
		for(int i = 0;i<empresasAPersistir.size();i++){
			this.persistirEmpresa(empresasAPersistir.get(i));
		}
	}

	/*
	 * 
	 * FIN 
	 * MÉTODOS DE PERSISTENCIA
	 * 
	 */
	
	
	/*
	 * 
	 * INICIO
	 * MÉTODOS DE QUERYS
	 * 
	 */
	
	public List<Empresa> obtenerTodasLasEmpresas(){
		return emanager.createQuery("FROM Empresa",Empresa.class).getResultList();
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
	
	
	
	/*
	 * 
	 * FIN
	 * MÉTODOS DE QUERYS
	 * 
	 */
	
	
	/*
	 * 
	 * INICIO
	 * MÉTODOS SIN ENTITY
	 * 
	 */
	public List<Periodo> obtenerTodosLosPeriodos(Empresa empresa){
		List<Periodo> periodos = new ArrayList<Periodo>();
		
		String empresaAsoc = empresa.getNombre();
		
		List<Empresa> listaEmpresas = this.obtenerTodasLasEmpresas();

		for (int i = 0; i < listaEmpresas.size(); i++) {
			if (listaEmpresas.get(i).getNombre().equals(empresaAsoc)) {
				periodos = listaEmpresas.get(i).getPeriodos();
			}
		}
		return periodos;
	}
	
	
	
	/*
	 * 
	 * FIN
	 * MÉTODOS SIN ENTITY
	 * 
	 */
	
	
	
	
}
