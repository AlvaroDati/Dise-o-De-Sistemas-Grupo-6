package proyectoInversiones;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/*@Entity
@Table(name = "Cuenta") */
public class Cuenta extends AlgoPersistible{
/*	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "periodo_id", referencedColumnName = "id")*/
	private Periodo periodoVinculado;
	
	
//	@Column(name = "ebitda")
	private float ebitda;
//	@Column(name = "fds")
	private float fds;
//	@Column(name = "fCashflow")
	private float fCashFlow;
//	@Column(name = "ingNetoOpCont")
	private float ingNetoOpCont;
//	@Column(name = "ingNetoOpDisc")
	private float ingNetoOpDiscont;
//	@Column(name = "deuda")
	private float deuda;
//	@Column(name = "capitalTotal")
	private float capitalTotal;
	
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
	
	public static void main(String args[]){
	}
 }