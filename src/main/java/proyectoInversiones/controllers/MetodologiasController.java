package proyectoInversiones.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proyectoInversiones.DescargaDrive;
import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;
import proyectoInversiones.Metodologia;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.IndicadorAuxiliar;
import proyectoInversiones.metodologias.Antiguedad;
import proyectoInversiones.metodologias.CondicionPrioritaria;
import proyectoInversiones.metodologias.CondicionTaxativa;
import proyectoInversiones.metodologias.OperacionAgregacion;
import proyectoInversiones.metodologias.OperacionRelacional;
import proyectoInversiones.metodologias.OperandoCondicion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController {
	
    static DescargaDrive lectorDrive = new DescargaDrive();

	public ModelAndView listar(Request req, Response res) throws IOException {

		Map<String, List<Metodologia>> model = new HashMap<>();
		List<Metodologia> metodologias = setearMetodologias();

		model.put("metodologias", metodologias);
		return new ModelAndView(model, "Metodologias.html");
	}
	
	
	public Void setearMetodologia(Request req, Response res) {
		String metodologia = req.queryParams("metodologia");
		System.out.printf("\nMetodologia buscada: %s", metodologia);		
		
		try{
			res.cookie("metodologia", metodologia);
			res.redirect("/metodologias/show");
		} catch ( Exception e ){
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/");
		}
		return null;
	}	
	
	
	public ModelAndView listarEmpresas(Request req, Response res) throws IOException {

		Map<String, List<Empresa>> model = new HashMap<>();
		String metodologiaBuscada = req.cookie("metodologia");

		List<Metodologia> metodologias = setearMetodologias();
		
		lectorDrive.obtenerEmpresas();
		
		List<Empresa> empresas = lectorDrive.getTodasLasEmpresas();
		
		Metodologia metodologiaFiltrada = new Metodologia(metodologiaBuscada);
		
		for(int i=0;i<metodologias.size();i++){
			if (metodologias.get(i).getNombre().equals(metodologiaBuscada)){
				metodologiaFiltrada = metodologias.get(i);
			}
		}
		
		System.out.printf("\nMetodologia encontrada: %s", metodologiaFiltrada.getNombre());
		
		List<Empresa> empresasEvaluadas = metodologiaFiltrada.evaluarPara(empresas);
		for(int i=0;i<empresasEvaluadas.size();i++){
			System.out.printf("\nEmpresa que cumple la metodologia: %s", empresasEvaluadas.get(i).getNombre());
		}
		
		model.put("empresas", empresasEvaluadas);
		return new ModelAndView(model, "MostrarMetodologias.html");
	}
	
	
	
	
	
	public List<Metodologia> setearMetodologias(){
		
		Metodologia payback = new Metodologia("Pay-back");
		payback.agregarCondicionPrioritaria(new CondicionPrioritaria(new OperandoCondicion(OperacionAgregacion.Ultimo, new Antiguedad(), 1), OperacionRelacional.Mayor));
		payback.agregarCondicionTaxativa(new CondicionTaxativa(new OperandoCondicion(OperacionAgregacion.Ultimo, new Antiguedad(), 1), OperacionRelacional.Menor, 10));
		Metodologia van = new Metodologia("VAN");
		van.agregarCondicionPrioritaria(new CondicionPrioritaria(new OperandoCondicion(OperacionAgregacion.Ultimo, new Antiguedad(), 6), OperacionRelacional.Mayor));
		van.agregarCondicionTaxativa(new CondicionTaxativa(new OperandoCondicion(OperacionAgregacion.Ultimo, new Antiguedad(), 6), OperacionRelacional.Menor, 6));
		IndicadorAuxiliar propDeuda = new IndicadorAuxiliar();
		propDeuda.setExpresion("PropDeuda=DEUDA/CAPITALTOTAL");
		Metodologia minDeuda = new Metodologia("MinimizarDeuda");
		minDeuda.agregarCondicionPrioritaria(new CondicionPrioritaria(new OperandoCondicion(OperacionAgregacion.Ultimo,propDeuda,1), OperacionRelacional.Menor));
		
		List<Metodologia> metodologias = new ArrayList<Metodologia>();
		metodologias.add(van);
		metodologias.add(payback);
		metodologias.add(minDeuda);
		
		return metodologias;
	}
	
	
	
}
