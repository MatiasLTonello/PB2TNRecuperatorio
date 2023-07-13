package unlam.edu.ar;

public class UsuarioAdministrador extends Usuario implements IContraseniaValidator {

	public UsuarioAdministrador(String nombreDeUsuario, String contrasenia) {
		super(nombreDeUsuario, contrasenia);
		// TODO Auto-generated constructor stub
	}

	public Boolean validarContrasenia() {
	    return this.getContrasenia().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[*%&=!]).+$") &&
	            !this.getContrasenia().matches(".*(.)\\1{3}.*");
	}

}
