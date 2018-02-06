package proyectoInversiones.server;


import proyectoInversiones.controllers.CuentasController;
import proyectoInversiones.controllers.IndicadoresController;
import proyectoInversiones.controllers.LoginController;
import proyectoInversiones.controllers.MetodologiasController;
import proyectoInversiones.spark.BooleanHelper;
import proyectoInversiones.spark.HandlebarsTemplateEngineBuilder;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.Spark;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder.create().withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue).build();

		Spark.staticFiles.location("/public");

		Spark.get("/", new LoginController()::login, engine);
		Spark.post("/", new LoginController()::validate);
		Spark.get("/cuentas", CuentasController::listar, engine);
		Spark.post("/cuentas", CuentasController::setearEmpresa, engine);
		Spark.get("/indicadores", new IndicadoresController()::listar, engine);
		Spark.post("/indicadores", IndicadoresController::setearEmpresa, engine);
		Spark.get("/indicadores/new", IndicadoresController::nuevoFormulario,engine);
		Spark.post("/indicadores/new", IndicadoresController::nuevoInd, engine);
		Spark.get("/metodologias", new MetodologiasController()::listar, engine);
		Spark.post("/metodologias", new MetodologiasController()::setearMetodologia);
		Spark.get("/metodologias/show", new MetodologiasController()::listarEmpresas, engine);
		
		Spark.get("/*", (req, res) -> {
			Spark.halt(400, "Bad Request");
			return null;
		});
	}
}