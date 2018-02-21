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
	private static Repositorio repo;
	
	public Repositorio(){
		
	}
	
	public Repositorio(EntityManager emanager) {
		
		this.emanager = emanager;
	}
	
	public static Repositorio getInstance(){
		if(repo == null){
			repo = new Repositorio();
		}
		return repo;
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
	
	public EntityManager getEM(){
		
		return this.emanager;
	}


	public void cerrar() {
		emanager.close();
	}
}