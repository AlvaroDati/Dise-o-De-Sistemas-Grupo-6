package proyectoInversiones.repositorio;



import javax.persistence.EntityManager;

public class Repositorio {
	private PeriodosRepo periodos;
	private EmpresasRepo empresas;
	private IndicadoresRepo indicadores;
	private UsuariosRepo usuarios;
	protected EntityManager emanager;

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