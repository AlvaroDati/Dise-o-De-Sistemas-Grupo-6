package db;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Indicador;
import proyectoInversiones.Metodologia;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import proyectoInversiones.repositorio.*;
import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.indicadores.IndicadorAuxiliar;
import proyectoInversiones.metodologias.Antiguedad;
import proyectoInversiones.metodologias.CondicionPrioritaria;
import proyectoInversiones.metodologias.CondicionTaxativa;
import proyectoInversiones.metodologias.OperacionAgregacion;
import proyectoInversiones.metodologias.OperacionRelacional;
import proyectoInversiones.metodologias.OperandoCondicion;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPersistencia {
	private static final String PERSISTENCE_UNIT_NAME = "db";
	private EntityManagerFactory emFactory;
	private Repositorio repositorio;
	private NuevoLeerArchivo archivo = new NuevoLeerArchivo();
	private ArrayList<Empresa> empresasDelJson = new ArrayList<Empresa>();
	
	@Before
	public void setUp() throws Exception {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
		empresasDelJson = archivo.leerArchivo();
	}
	
	
	@Test
	public void  persistir1UsuariosYverificarUno() {
		LeerUsuarios archivoUsuarios = new LeerUsuarios();
		ArrayList<Usuario> usuarios = archivoUsuarios.leerArchivo();
		
		
		for (Usuario head:usuarios){
			
			if(repositorio.usuariosRepo().buscarPorId(head.getId()) == null){
				repositorio.usuariosRepo().persistir(head);			
			}else{
				System.out.println("asdfsd");
				if(repositorio.usuariosRepo().buscarPorId(head.getId()).getUserTag().equals(head.getUserTag()))
					Assert.assertEquals(repositorio.usuariosRepo().buscarPorId((long)1).getUserTag(), "ivan");
				else
					repositorio.usuariosRepo().persistir(head);
			}
		}
		
		Assert.assertEquals(repositorio.usuariosRepo().buscarPorId((long)2).getUserTag(), "alvitovito");
	}


	
	
	@Test
	public void persistir2ConJson() {
		NuevoLeerArchivo archivo = new NuevoLeerArchivo();
		ArrayList<Empresa> empresas = archivo.leerArchivo();
		ArmadorIndicador armarIndicador = new ArmadorIndicador();
//		ArrayList<Float> ingresoNeto = new ArrayList<Float>();

		for (int i = 0; i < empresas.size(); i++) {
	
			for (int j = 0; j < empresas.get(i).getPeriodos().size(); j++) {
				
				Indicador indicador = new Indicador();

				indicador.setNombre("Ingreso Neto");
				indicador.setEmpresaAsoc(empresas.get(i).getNombre());
				indicador.setPeriodo(empresas.get(i).getPeriodos().get(j).getAnio());
				indicador.setValorIndicador(armarIndicador.calcularIngresoNeto(empresas.get(i)).get(j));
								
//				empresas.get(i).addIndicador(indicador);
				empresas.get(i).getPeriodos().get(j).setEmpresa(empresas.get(i));
				empresas.get(i).getPeriodos().get(j).getCuentas().setPeriodoVinculado(empresas.get(i).getPeriodos().get(j));
				repositorio.indicadoresRepo().persistir(indicador);
				repositorio.empresasRepo().persistir(empresas.get(i)); 
			}
			
		}
		
		for (int i = 0; i < empresas.size(); i++) {
			
			for (int j = 0; j < empresas.get(i).getPeriodos().size(); j++) {
				
				Indicador indicador = new Indicador();

				indicador.setNombre("ROE");
				indicador.setEmpresaAsoc(empresas.get(i).getNombre());
				indicador.setPeriodo(empresas.get(i).getPeriodos().get(j).getAnio());
				indicador.setValorIndicador(armarIndicador.calcularRoe(empresas.get(i)).get(j));
								
			//	empresas.get(i).addIndicador(indicador);
				empresas.get(i).getPeriodos().get(j).setEmpresa(empresas.get(i));
				
				repositorio.indicadoresRepo().persistir(indicador);
				repositorio.empresasRepo().persistir(empresas.get(i)); 
			}
			
		}
	}
	
	@Test
	public void persistir3IndicadoresDefinidosPorUsuario(){
		Empresa empresa = new Empresa("America Movil");
		Usuario usuario = new Usuario("ivan","ivan");
		ArmadorIndicador armadorIndicador = new ArmadorIndicador();
		List<Indicador> indicadores = new ArrayList<Indicador>();
		String archivo = "IndicadoresDelUsuarioivan";
		try{
			/*indicadores = armadorIndicador.getIndicadoresUsuario(archivo);*/
		}catch(Exception e){
			
		}
		
		usuario.setIndicadoresUsuario(indicadores);
		for(Indicador head:indicadores){
			repositorio.indicadoresRepo().persistir(head);
		}
	}
	
	
	
	
	

	@Test
	public void persistirMetodologia(){
		Metodologia unaMetodologia = new Metodologia("Una Metodologia");
		List<Empresa> empresasParaComparacionConMetodologias = new ArrayList<Empresa>();
		Empresa ivanCompany = new Empresa("IvanCompany");
		Empresa gulloCompany = new Empresa("GulloCompany");
		ivanCompany.setInicioActividad(2000);
		gulloCompany.setInicioActividad(1980);
		IndicadorAuxiliar indicadorLoco = new IndicadorAuxiliar();
		indicadorLoco.setEmpresaAsoc(ivanCompany.getNombre());
		indicadorLoco.setExpresion("hola=DEUDA+500");
		indicadorLoco.setNombre("indicadorLoco");
		
		CondicionTaxativa unaCondicionTaxativa = new CondicionTaxativa(
												 new OperandoCondicion(OperacionAgregacion.Promedio,
																					indicadorLoco,
																					2), 
																	  OperacionRelacional.Mayor, 
																	  100);
		if(unaMetodologia.evaluarPara(empresasParaComparacionConMetodologias).size() == 0){
			unaMetodologia.agregarCondicionTaxativa(unaCondicionTaxativa);
			System.out.println("2");
		}
		Antiguedad antiguedad = new Antiguedad();
		antiguedad.setEmpresaAsoc("Soy un antiguedad");
		antiguedad.setPeriodos("2999");
		OperandoCondicion opCond = new OperandoCondicion(OperacionAgregacion.Ultimo, antiguedad, 1);
		CondicionPrioritaria unaCondicionPrioritaria = new CondicionPrioritaria(opCond, OperacionRelacional.Mayor);		
		if(unaCondicionPrioritaria.esMejorQue(gulloCompany, ivanCompany)){
			unaMetodologia.agregarCondicionPrioritaria(unaCondicionPrioritaria);
			System.out.println("3");
		}
		repositorio.metodologiasRepo().persistir(unaMetodologia);
		
		
		
	}
	




	
	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
		emFactory.close();
	}
}
	


