package proyectoInversiones.repositorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	static RepositorioGeneral repositorioGeneral;
	
	public RepositorioServicio() {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		emanager = emFactory.createEntityManager();
		repositorio = new Repositorio(emanager);
		repositorioGeneral = new RepositorioGeneral();
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
	 * M�TODO DE PERSISTENCIA
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
				System.out.println("No se encontr� la empresa:" + empresas.get(i).getNombre()+"en la base de datos");
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
	 * M�TODOS DE PERSISTENCIA
	 * 
	 */
	
	
	/*
	 * 
	 * INICIO
	 * M�TODOS DE QUERYS
	 * 
	 */
	
	
	public List<Empresa> obtenerTodasLasEmpresas(){
		repositorioGeneral.setEmpresas(emanager.createQuery("FROM Empresa",Empresa.class).getResultList());
		return repositorioGeneral.getEmpresas();
	}
	
	
	public Usuario buscarUsuarioPorNombre(String nombre) {
		List<Usuario> usuarioALoguearse = new ArrayList<Usuario>();
		
		usuarioALoguearse = emanager.createNamedQuery("buscarUsuarioPorNombre").setParameter("filtro", "%" + nombre + "%").getResultList();
		
		System.out.println("Cantidad de usuarios extra�dos de la bd: " + usuarioALoguearse.size());
		Usuario usuarioALoguearse2 = null;
		for (int i = 0; i < usuarioALoguearse.size(); i++) {
			if (usuarioALoguearse.get(i).getUserTag().equals(nombre)) {
				usuarioALoguearse2 = usuarioALoguearse.get(i);
			}
		}
		
		return usuarioALoguearse2;
	}

	public List<String> buscarIndicadorPorUsuario(String nombre) {
		List<Indicador> indicador = new ArrayList<Indicador>();
		List<String> expresionIndicadores = new ArrayList<String>();
		Usuario us = this.buscarUsuarioPorNombre(nombre);
		String query = "select  expresion from indicadores where usuario_id=" + us.getId() + ";";

		expresionIndicadores = emanager.createNativeQuery(query).getResultList();

		return expresionIndicadores;
	}
	
	public List<Indicador> buscarIndicadorPorUsuario2(String nombre) {
		List<Indicador> indicadores = new ArrayList<Indicador>();
		Usuario us = this.buscarUsuarioPorNombre(nombre);

		System.out.println("El usuario: " + us.getUserTag() + " de ID: " + us.getId()
				+ " desea extraer sus indicadores de la base de datos");
		
		Query query = emanager.createQuery("FROM Indicador i WHERE i.usuario.id=" + us.getId(), Indicador.class);
	indicadores= 	query.getResultList();
		for (int i = 0; i < indicadores.size(); i++) {
			System.out.println("Indicadores extraidos de la base de datos: " + indicadores.get(i).getNombre()+ " expresion: "+indicadores.get(i).getExpresion());
		}
		return indicadores;
	}
	
	
	public void cambiarExpresionDeIndicador(String expresionACambiar,long id,String nombreIndicador){
		System.out.println("Expresion a cambiar: "+ expresionACambiar+ " del ID: "+ id);
		Query query = emanager.createQuery("UPDATE Indicador i SET i.expresion='" +expresionACambiar+ "' WHERE i.usuario.id="+id+" AND nombre='"+nombreIndicador+"'");
//query.setParameter("expr", expresionACambiar);
//query.setParameter("usuario", id);
		emanager.getTransaction().begin();
		query.executeUpdate();
		emanager.getTransaction().commit();
		
	}
	
	
	
	public void modificarExpresionIndicador(String expresionACambiar,long id, String nombreIndicador){
		Indicador indicadorACambiar = new Indicador();
		
		List<Indicador> indicadores = emanager.createQuery("FROM Indicador i WHERE nombre='"+nombreIndicador+"'").getResultList();
		indicadorACambiar = indicadores.get(0);
		Indicador aux = new Indicador();
			aux=	emanager.find(Indicador.class, indicadorACambiar.getId());
		emanager.getTransaction().begin();
		aux.setExpresion(expresionACambiar);
		emanager.getTransaction().commit();
	}
	
	/*
	 * 
	 * FIN
	 * M�TODOS DE QUERYS
	 * 
	 */
	
	
	/*
	 * 
	 * INICIO
	 * M�TODOS SIN ENTITY
	 * 
	 */
	
	public List<Empresa> obtenerEmpresas(){
		if(repositorioGeneral == null){
			repositorioGeneral = new RepositorioGeneral();
			this.obtenerTodasLasEmpresas();
		}
		if(repositorioGeneral.getEmpresas() == null || repositorioGeneral.getEmpresas().size()==0){
			this.obtenerTodasLasEmpresas();
		}
		return repositorioGeneral.getEmpresas();
	}
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
	 * M�TODOS SIN ENTITY
	 * 
	 */
	
	
	
	
}
