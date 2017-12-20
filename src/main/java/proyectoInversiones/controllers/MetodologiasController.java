package proyectoInversiones.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proyectoInversiones.Indicador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController {
	public ModelAndView listar(Request req, Response res) throws IOException {

		String usuario = req.cookie("userTag");
		Map<String, List<Indicador>> model = new HashMap<>();
		return new ModelAndView(model, "Metodologias.html");
	}
}
