package proyectoInversiones;


import java.io.Serializable;
import java.util.List;

//import java.beans.Transient;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@SuppressWarnings("serial")
@Entity
@Table(name = "cuenta") 
//@NamedQuery(name = "buscarCuentaPorNombreYEmpresa", query = "SELECT cuenta FROM Cuenta cuenta WHERE cuenta.nombre LIKE :filtro")
public class Cuenta implements Serializable {
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
//	@Column(name = "nombre")
//	protected String nombre;
	
	
	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "periodo_id",referencedColumnName = "id")
	private Periodo periodovinculado;
	
	@Column(name = "ebitda")
	private float ebitda;
    @Column(name = "fds")
	private float fds;
    @Column(name = "fcashflow")
	private float fCashFlow;
    @Column(name = "ingnetoopcont")
	private float ingNetoOpCont;
    @Column(name = "ingnetoopdisc")
	private float ingNetoOpDiscont;
    @Column(name = "deuda")
	private float deuda;
    @Column(name = "capitaltotal")
	private float capitalTotal;
    
//    @Transient
//    private String empresaAsoc;// = periodoVinculado.getEmpresa().getNombre();
//    @Transient
//    private List<Empresa> empresas;
//    public List<Empresa> getEmpresas() {
//		return empresas;
//	}
//
//	public void setEmpresas(List<Empresa> empresas) {
//		this.empresas = empresas;
//	}
//
//	public String getEmpresaAsoc(){
//    	//empresaAsoc = periodoVinculado.getEmpresa().getNombre();
//    	return empresaAsoc;
//    }
//    
//    public void setEmpresaAsoc(String unaEmpresa){
//    	empresaAsoc = unaEmpresa;
//    }
    
    public Long getId() {
		return id;
	}


	public Periodo getPeriodovinculado() {
		return periodovinculado;
	}


	public float getfCashFlow() {
		return fCashFlow;
	}


	public float getCapitalTotal() {
		return capitalTotal;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setPeriodovinculado(Periodo periodovinculado) {
		this.periodovinculado = periodovinculado;
	}


	public void setfCashFlow(float fCashFlow) {
		this.fCashFlow = fCashFlow;
	}


	public void setCapitalTotal(float capitalTotal) {
		this.capitalTotal = capitalTotal;
	}


	public Periodo getPeriodoVinculado() {
		return periodovinculado;
	}
    
    
	public void setPeriodoVinculado(Periodo periodoVinculado) {
		this.periodovinculado = periodoVinculado;
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
	public float getFCashFlow() {
		return fCashFlow;
	}
	public void setFCashFlow(float fCashFlow) {
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
		capitalTotal = this.getEbitda() + this.getFCashFlow() + this.getFds() + this.getIngNetoOpCont() + this.getIngNetoOpDiscont() - this.getDeuda();
		return capitalTotal;
	}
	

 }