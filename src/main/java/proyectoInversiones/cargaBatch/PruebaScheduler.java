package proyectoInversiones.cargaBatch;

import javax.persistence.EntityManager;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class PruebaScheduler implements org.quartz.Job {

	public void execute(JobExecutionContext context) throws JobExecutionException{
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		System.out.println("Ejecutando el JOHAN BACH!");

	}
}
