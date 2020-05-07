package model.component.constraint;

import java.util.List;

/**
 * 
 * @author Guglielmo Cassini
 *
 */
public interface Constraint {
	
	String getConstraintName();
	/**
	 * Controlla che i Constraint accettati come parametri siano tutti compatibili
	 * con il contraint che sta eseguendo questo metodo
	 * 
	 * @param constraints - lista ({@link List}) di vincoli che vogliamo controllare fra loro
	 * 
	 * @return true - se sono tutti compatibili <br>
	 * 			false - se non tutti i vincoli sono compatibili
	 */
	boolean checkList(List<Constraint> constraints);
	String getValue();
	ConstraintType getConstraintType();
	
}
