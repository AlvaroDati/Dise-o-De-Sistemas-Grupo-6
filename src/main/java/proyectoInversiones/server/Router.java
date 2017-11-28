package proyectoInversiones.server;


import proyectoInversiones.controllers.*;
import proyectoInversiones.spark.BooleanHelper;
import proyectoInversiones.spark.HandlebarsTemplateEngineBuilder;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.ModelAndView;
import spark.Spark;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue).build();

		Spark.staticFiles.location("/public");

		Spark.get("/", new LoginController()::login, engine);
		Spark.post("/", new LoginController()::validate);
//		Spark.get("/home", HomeController::home, engine);
		Spark.get("/cuentas", CuentasController::listar, engine);
//		Spark.get("/empresas/:id", EmpresasController::mostrar, engine);
		Spark.get("/indicadores", new IndicadoresController()::listar, engine);
//		Spark.get("/indicadores/new", new IndicadoresController()::newForm, engine);
//		Spark.post("/indicadores/new", new IndicadoresController()::create);
//		Spark.get("/metodologias", MetodologiasController::listar, engine);
//		Spark.get("/metodologias/:id", MetodologiasController::mostrar, engine);
		Spark.get("/*", (req, res) -> {
			Spark.halt(400, "Bad Request");
			return null;
		});
	}

}