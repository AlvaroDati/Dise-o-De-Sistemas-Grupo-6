package proyectoInversiones;


//import java.beans.Transient;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "cuenta") 
//@NamedQuery(name = "buscarCuentaPorNombreYEmpresa", query = "SELECT cuenta FROM Cuenta cuenta WHERE cuenta.nombre LIKE :filtro")
public class Cuenta extends AlgoPersistible{
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "periodo_id", referencedColumnName = "id")
	private Periodo periodoVinculado;
	
	@Column(name = "ebitda")
	private float ebitda;
    @Column(name = "fds")
	private float fds;
    @Column(name = "fCashflow")
	private float fCashFlow;
    @Column(name = "ingNetoOpCont")
	private float ingNetoOpCont;
    @Column(name = "ingNetoOpDisc")
	private float ingNetoOpDiscont;
    @Column(name = "deuda")
	private float deuda;
    @Column(name = "capitalTotal")
	private float capitalTotal;
    
    @Transient
    private String empresaAsoc;// = periodoVinculado.getEmpresa().getNombre();
    
    public String getEmpresaAsoc(){
    	//empresaAsoc = periodoVinculado.getEmpresa().getNombre();
    	return empresaAsoc;
    }
    
    public void setEmpresaAsoc(String unaEmpresa){
    	empresaAsoc = unaEmpresa;
    }
    
    public Periodo getPeriodoVinculado() {
		return periodoVinculado;
	}
    
    
	public void setPeriodoVinculado(Periodo periodoVinculado) {
		this.periodoVinculado = periodoVinculado;
	}
	
    
	public float getDeuda() {
		return deuda;
	}
	public void setDeuda(float deuda) {
		this.deuda = deuda;
	}
	public float getEbitda() {
		return ebitda;
	}
	public void setEbitda(float ebitda) {
		this.ebitda = ebitda;
	}
	public float getFds() {
		return fds;
	}
	public void setFds(float fds) {
		this.fds = fds;
	}
	public float getfCashFlow() {
		return fCashFlow;
	}
	public void setfCashFlow(float fCashFlow) {
		this.fCashFlow = fCashFlow;
	}
	public float getIngNetoOpCont() {
		return ingNetoOpCont;
	}
	public void setIngNetoOpCont(float ingNetoOpCont) {
		this.ingNetoOpCont = ingNetoOpCont;
	}
	public float getIngNetoOpDiscont() {
		return ingNetoOpDiscont;
	}
	public void setIngNetoOpDiscont(float ingNetoOpDiscont) {
		this.ingNetoOpDiscont = ingNetoOpDiscont;
	}
		
	public float capitalTotal(){
		capitalTotal = this.getEbitda() + this.getfCashFlow() + this.getFds() + this.getIngNetoOpCont() + this.getIngNetoOpDiscont() - this.getDeuda();
		return capitalTotal;
	}
	

 }