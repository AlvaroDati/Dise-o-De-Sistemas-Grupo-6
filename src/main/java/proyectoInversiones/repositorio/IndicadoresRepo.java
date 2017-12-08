package proyectoInversiones.repositorio;


import javax.persistence.EntityManager;
import java.util.List;
import proyectoInversiones.Indicador;



public class IndicadoresRepo extends Repositorio {
    IndicadoresRepo(EntityManager emanager) {
		super(emanager);
	}

	public Indicador buscarPorId(Long id) {
		return emanager.find(Indicador.class, id);
	}

	public void persistir(Indicador unIndicador) {
		emanager.getTransaction().begin();
		emanager.persist(unIndicador);
		emanager.getTransaction().commit();
	}
}