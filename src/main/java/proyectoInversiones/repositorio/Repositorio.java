package proyectoInversiones.repositorio;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;


public class Repositorio {
	private PeriodosRepo periodos;
	private EmpresasRepo empresas;
	private IndicadoresRepo indicadores;
	private UsuariosRepo usuarios;
	private MetodologiasRepo metodologias;
	@PersistenceContext(unitName = "db", type = PersistenceContextType.EXTENDED)
	protected
	static EntityManager emanager;
	
	public Repositorio(){
		
	}
	
	public Repositorio(EntityManager emanager) {
		this.emanager = emanager;
	}
	
	
	
	public EmpresasRepo empresasRepo(){
		if (empresas == null) {
			empresas = new EmpresasRepo(emanager);
		}
		return empresas;
	}

	public PeriodosRepo periodosRepo() {
		if (periodos == null) {
			periodos = new PeriodosRepo(emanager);
		}
		return periodos;
	}
	
	public IndicadoresRepo indicadoresRepo() {
		if (indicadores == null) {
			indicadores = new IndicadoresRepo(emanager);
		}
		return indicadores;
	}
	
	public MetodologiasRepo metodologiasRepo() {
		if (metodologias == null) {
			metodologias = new MetodologiasRepo(emanager);
		}
		return metodologias;
	}
	
	public UsuariosRepo usuariosRepo() {
		if (usuarios == null) {
			usuarios = new UsuariosRepo(emanager);
		}
		return usuarios;
	}
	


	public void cerrar() {
		emanager.close();
	}
}