package proyectoInversiones.server;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import proyectoInversiones.controllers.CuentasController;
import proyectoInversiones.controllers.IndicadoresController;
import proyectoInversiones.controllers.LoginController;
import proyectoInversiones.controllers.MetodologiasController;
import proyectoInversiones.repositorio.Repositorio;
import proyectoInversiones.spark.BooleanHelper;
import proyectoInversiones.spark.HandlebarsTemplateEngineBuilder;
import proyectoInversiones.usuarios.Usuario;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.Spark;

public class Router {
	 private static final String PERSISTENCE_UNIT_NAME = "db";
	 private static EntityManagerFactory emFactory;
	 @PersistenceContext(unitName = "db", type = PersistenceContextType.EXTENDED)
	 private static EntityManager emanager;
	 private static Repositorio repositorio;

	public static void configure() {
		
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue).build();

		Spark.staticFiles.location("/public");
		Spark.before(ControladorDeSesion::validarLoggeo);
		

		Spark.get("/", new LoginController()::login, engine);
		Spark.post("/", new LoginController()::validate);
		Spark.get("/cuentas", CuentasController::listar, engine);
		Spark.post("/cuentas", CuentasController::setearEmpresa, engine);
		Spark.get("/cuentas/seleccionar", CuentasController::nuevasCuentas, engine);
		Spark.post("/cuentas/seleccionar", new CuentasController()::setearArchivo);
		Spark.get("/indicadores", new IndicadoresController()::listar, engine);
		Spark.post("/indicadores", IndicadoresController::setearEmpresa, engine);
		Spark.get("/indicadores/new", IndicadoresController::nuevoFormulario, engine);
		Spark.post("/indicadores/new", IndicadoresController::nuevoInd, engine);
		Spark.get("/metodologias", new MetodologiasController()::listar, engine);
		Spark.post("/metodologias", new MetodologiasController()::setearMetodologia);
		Spark.get("/metodologias/show", new MetodologiasController()::listarEmpresas, engine);

		Spark.get("/*", (req, res) -> {
			Spark.halt(400, "Bad Request");
			return null;
		});
	

	}

	public static void setUpDataBase() {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		emanager = emFactory.createEntityManager();
		repositorio = new Repositorio(emanager);
	}
	
	public static void closeDataBase(){
		repositorio.cerrar();
		emFactory.close();
	}
	
	
    public static List<Usuario> obtenerUsuariosSegunNombre(String nombre){
    	List<Usuario> usuarioALoguearse = new ArrayList<Usuario>();
    	
	usuarioALoguearse = emanager.createNamedQuery("buscarUsuarioPorNombre").setParameter("filtro","%" + nombre + "%").getResultList();


//		System.out.println("Usuario: " + usuarioALoguearse.get(0).getPassword());
//		Usuario usuarioALoguearse2 = null;
//		for (int i = 0; i < usuarioALoguearse.size(); i++) {
//			if (usuarioALoguearse.get(i).getUserTag().equals(nombre)) {
//				usuarioALoguearse2 = usuarioALoguearse.get(i);
//			}
//		}

		return usuarioALoguearse;
	
    }
}