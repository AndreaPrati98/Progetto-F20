package model.component.constraint;

import java.util.List;

import model.component.Component;
//Cic
public class EqualsConstraint implements Constraint {

	private String name;
	private String value;

	public EqualsConstraint(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public String getConstraintName() {
		// TODO Auto-generated method stub
		return name;
	}
		
	//Controlla se esiste almeno un vincolo che abbia nome uguale ma 
	//valore diverso rispetto al me-vincolo.
	//Se esiste --> False
	//Se non esiste --> True
	@Override
	public boolean checkList(List<Component> components) {
		
		String myName = this.getConstraintName();
	
		//Ciclo esterno per prendere ogni componente della lista
		for(Component comp : components) {			
			List<Constraint> constraints = comp.getConstraints();
			//Ciclo interno per controllare i vincoli dell'i-esimo componente
			for(Constraint constr : constraints) {
				//Controllo se io (vincolo) e l'i-esimo vincolo abbiamo lo stesso nome
				if(constr.getConstraintName().equals(myName)){
					//Se abbiamo lo stesso nome, ma attributi divesi (verificabile
					//col metodo equals di object che controlla l'uguaglianza tra tutti 
					//gli attributi), se siamo diversi ho trovato almeno un vincolo
					//con cui non sono compatibile
					if(!constr.equals(this)) {
						return false;
					}					
				}						
		}
			
		//Se ho controllato tutti e nessuno è incompatibile con i miei vincoli
		//allora è ok
		return true;			
	}
		
		
		
		return false;
	}


}
