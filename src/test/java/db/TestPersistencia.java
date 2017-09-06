package db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;



import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.Cuenta;
import proyectoInversiones.Indicador;

import proyectoInversiones.repositorio.Repositorio;

import proyectoInversiones.indicadores.ArmadorIndicador;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPersistencia {
	private static final String PERSISTENCE_UNIT_NAME = "dds";
	private EntityManagerFactory emFactory;
	private Repositorio repositorio;
	
	
	@Before
	public void setUp() throws Exception {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
	}
	
	
	@Test
	public void persistirConJson() {
		NuevoLeerArchivo archivo = new NuevoLeerArchivo();
		ArrayList<Empresa> empresa = archivo.leerArchivo();
		Empresa unaEmpresa = new Empresa("America Movil");
		
		Indicador indicador = new Indicador();
		indicador.setNombre("Ingreso Neto");
		indicador.setEmpresa(unaEmpresa);
		indicador.setPeriodo(2006);
		
		ArmadorIndicador armarIndicador = new ArmadorIndicador(indicador);
		
		indicador.setValorIndicador(armarIndicador.obtenerValorIndicadorSegun(indicador)); 
		//Indicador indicador = new Indicador();
		for (int i = 0; i < empresa.size(); i++) {
			for (int j = 0; j < empresa.get(i).getPeriodos().size(); j++) {
				
				empresa.get(i).addIndicador(indicador);
				empresa.get(i).getPeriodos().get(j).setEmpresa(empresa.get(i));
				repositorio.empresasRepo().persistir(empresa.get(i)); 
				/*En vez de perisistir solo empresas, se podria persistir indicadores y metodologias, 
				Ya que al no estar en json, siempre van a tiran el mismo error 
			
			Caused by: org.hibernate.MappingException: Could not determine type for: proyectoInversiones.NuevoLeerArchivo, at table: Indicador, for columns: [org.hibernate.mapping.Column(archivoEmpresas)]
				Esto lo tira, porque en el json, no hay un campo Indicador/Metodologia 
				Capaz si hacemos un persistir(Indicador/Metodologia) indicando la empresa a la que esta relacionada
				Va a ser mas llevadero esto, y se pueden hacer mejores tests
				 	*/
			}
			
		}
	}
	
	@Test
	public void buscarEmpresaNombre(){
		List<Empresa> empresasFiltradas = repositorio.empresasRepo().buscarEmpresaPorNombre("Amer");
		for(Empresa unaEmpresa : empresasFiltradas){
			System.out.println(unaEmpresa.getNombre());
		
		}
		Assert.assertFalse(empresasFiltradas.isEmpty());
	}
	
	/*
	@Test
	public void  persistirEmpresaConPeriodos() {
		Periodo unPeriodo = new Periodo(2014);
		Periodo otroPeriodo = new Periodo(2010);
		Periodo p3 = new Periodo(2000);
		Empresa unaEmpresa = new Empresa();
		unaEmpresa.setNombre("IBM");
		unPeriodo.setEmpresa(unaEmpresa);
		otroPeriodo.setEmpresa(unaEmpresa);
		p3.setEmpresa(unaEmpresa);
		unaEmpresa.addPeriodo(unPeriodo);
		unaEmpresa.addPeriodo(otroPeriodo);
		unaEmpresa.addPeriodo(p3);
	    repositorio.empresasRepo().persistir(unaEmpresa);
		unaEmpresa.getPeriodos().forEach(periodo -> System.out.println(periodo.getAnio()));
	}*/
	
	
/*	@Test
	public void  buscarEmpresaPorNombre() {
		List<Empresa> unasEmpresas = repositorio.empresasRepo().buscarEmpresaPorNombre("IB");
		for(Empresa unaEmpresa : unasEmpresas){
		System.out.println(unaEmpresa.getNombre());	
		System.out.println(unaEmpresa.getPeriodos());
		
		}
	}*/
	
	
//	@Test
//	public void  persistirEmpresaConPeriodosYCuentas() {
//		Empresa unaEmpresa = new Empresa("America Movil");
//        Periodo unPeriodo = new Periodo(2006);
//        Cuenta unaCuenta = new Cuenta();
//        unaCuenta.setEbitda(200);
//        unPeriodo.setCuentas(unaCuenta);
//        unaCuenta.setPeriodoVinculado(unPeriodo);
//        unaEmpresa.addPeriodo(unPeriodo);
//        unPeriodo.setEmpresa(unaEmpresa);
//		//NuevoLeerArchivo archivo = new NuevoLeerArchivo();
//		
//		
//		//Set<Periodo> periodos = archivo.getPeriodos(unaEmpresa);
//       // periodos.forEach(unPeriodo -> unPeriodo.setEmpresa(unaEmpresa));
//       /* for(Periodo head:periodos){
//        	head.setEmpresa(unaEmpresa);
//        }*/
//		repositorio.empresasRepo().persistir(unaEmpresa);
//		unaEmpresa.getPeriodos().forEach(periodo -> System.out.println(periodo.getAnio()));
//	}
//	
	
	
/*	@Test
	public void persistirIndicador(){
		Empresa unaEmpresa = new Empresa("IBM");
		Indicador indicador = new Indicador("Ingreso Neto");
		indicador.ingresoNeto(unaEmpresa);
		unaEmpresa.addIndicador(indicador);
		repositorio.empresasRepo().persistir(unaEmpresa);
	}*/
	
	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
		emFactory.close();
	}
}
	


