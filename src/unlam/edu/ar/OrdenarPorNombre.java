package unlam.edu.ar;

import java.util.Comparator;

public class OrdenarPorNombre implements Comparator<Usuario> {

	@Override
	public int compare(Usuario o1, Usuario o2) {
		return o1.getNombreDeUsuario().compareTo(o2.getNombreDeUsuario());
	}

	

	
}