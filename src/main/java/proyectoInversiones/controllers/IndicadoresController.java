package proyectoInversiones.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.indicadores.IndVisitor;
import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.CalculoIndicadores;
import proyectoInversiones.DescargaDrive;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {
	static String usuarioActivo;
	static String rutaArchivo = "target/classes/public/IndicadoresDelUsuario";
	static List<Indicador> repoIndicadores = new ArrayList<Indicador>();
		
    static DescargaDrive lectorDrive = new DescargaDrive();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ModelAndView listar(Request req, Response res) throws IOException {
		System.out.println(req.queryParams("userTag"));
		System.out.println("web" + req.cookie("usuario"));
		Map<String, List<Empresa>> model = new HashMap<>();
		lectorDrive.obtenerEmpresas();
		ArrayList<Empresa> listaEmpresas = lectorDrive.getTodasLasEmpresas();

		model.put("empresasAMostrar", listaEmpresas);

		String usuario = req.cookie("usuario");
		usuarioActivo = usuario;
		return new ModelAndView(model, "Indicadores2.html");
	}

	
	public static ModelAndView setearEmpresa(Request req, Response res) {
		String nombreEmpresa = req.queryParams("Empresa");
		Empresa empresa = new Empresa(nombreEmpresa);
		try {
			Map<String, List<Indicador>> model = new HashMap<>();
			
			List<Periodo>periodosEmpresa =  lectorDrive.getPeriodos(empresa);
			CalculoIndicadores operadorIndicadores = new CalculoIndicadores(usuarioActivo);
			List<Indicador> indicadoresDeEmpresa = operadorIndicadores.setearListaIndicadores(periodosEmpresa, empresa);
			List<Indicador> indicadoresUsuario = operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa,empresa);
			repoIndicadores = operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa, empresa);
			return new ModelAndView(model, "Indicadores2.html");
		} catch (Exception e) {
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/cuentas");
		}

		return null;

	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static ModelAndView nuevoFormulario(Request req, Response res) {
		return new ModelAndView(null, "IndicadoresNuevo.html");
	}

	public static ModelAndView nuevoInd(Request req, Response res) {
		String usuario = req.cookie("usuario");
		String nombreIndicador = req.queryParams("nombreIndicador");
		String empresaSeleccionada = req.queryParams("Empresa");
		String expresionIndicador = req.queryParams("valorIndicador");
		
		
		Stream<Indicador> filtro = repoIndicadores.stream().filter(ind -> ind.getNombre().equals(nombreIndicador));
		System.out.println("cantidad de indicadores"+repoIndicadores.size());

		Long contador = filtro.count();

		
		if (contador == 0) {
			if (nombreIndicador != null  && expresionIndicador != null) {
				try {
					System.out.println("cantidad de indicadores 2 "+repoIndicadores.size());
					String archivoUsuario = rutaArchivo + usuario;
					System.out.println("archivo del usuario " + archivoUsuario);
					File file = new File(archivoUsuario);

					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file, true);
					BufferedWriter bw = new BufferedWriter(fw);
					//bw.write(empresaSeleccionada.toString() + "(" + nombreIndicador + ")" + "=");
					bw.write(nombreIndicador  + "=");
					bw.write(expresionIndicador + "\n"); // numero +
					bw.close();
					res.redirect("/indicadores");

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				// Tirar excepcion en spark
			}
		}else{
			return new ModelAndView(null,"IndicadoresError.html");
		}
		return null;
	}
}
