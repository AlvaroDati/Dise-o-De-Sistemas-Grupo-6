package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import proyectoInversiones.Cuenta;
import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;



public class TestPersistencia {
	
	
	@Test
	public void getEmpresa() {
		EntityManager entityManager = 
				PerThreadEntityManagers.
				getEntityManager();
		NuevoLeerArchivo archivo = new NuevoLeerArchivo();
	
		Empresa empresa = entityManager.find(Empresa.class, 1);
	//	assertEquals(1, empresa.getId());  Error de comparacion entre objetos no es clara para el assert ?)
		entityManager.clear();
		Empresa otraEmpresa = entityManager.find(Empresa.class, 1);
 	    Assert.assertTrue(empresa == otraEmpresa);
		
 	    EntityTransaction transaction = entityManager.getTransaction();
 	    
	
		
		
		otraEmpresa.setInicioActividad(1997);
		
		transaction.commit();

		
	}

}
