package proyectoInversiones.controllers;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import proyectoInversiones.repositorio.Repositorio;
import proyectoInversiones.repositorio.RepositorioServicio;
import proyectoInversiones.repositorio.UsuariosRepo;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class LoginController {
	private static final String PERSISTENCE_UNIT_NAME = "db";
	private EntityManagerFactory emFactory;
	private Repositorio repositorio;
	private RepositorioServicio repositorioServicio;
	protected EntityManager emanager;
	
	public void setUp() throws Exception {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
	}
	public ModelAndView login(Request req, Response res) throws Exception {
	//	this.setUp();
		RepositorioServicio.getInstance().buscarUsuarioPorNombre("ivan");
		return new ModelAndView(null, "Index.html");
	}
	
	
	public Void validate(Request req, Response res) {
		
		String userTag = req.queryParams("userTag");
		String password = req.queryParams("password"); 
		System.out.printf("Usuario: %s queriendose conectar con contraseña: %s !\n", userTag,password);
		repositorioServicio = RepositorioServicio.getInstance();
		try{
			Usuario usuarioLoggeado = repositorioServicio.buscarUsuarioPorNombre(userTag);
		
//			UsuariosRepo repo = new UsuariosRepo(emanager);
			Long idUsuario =  usuarioLoggeado.getId();
			
			res.cookie("userTag", userTag);
			res.cookie("usuario", userTag);
			res.cookie("idUsuario", idUsuario.toString());
			res.redirect("/cuentas");
		} catch ( Exception e ){
			res.removeCookie("userTag");
			res.removeCookie("usuario");
			res.removeCookie("idUsuario");
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/");
		}
		return null;
	}		
}
