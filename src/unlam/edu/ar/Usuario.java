package unlam.edu.ar;

import java.util.Objects;

public abstract class Usuario implements IContraseniaValidator{

	private String nombreDeUsuario;
	private String contrasenia;
	private Boolean estaLogueado;
	
	public Usuario(String nombreDeUsuario, String contrasenia) {
		this.nombreDeUsuario = nombreDeUsuario;
		this.contrasenia = contrasenia;
		this.setEstaLogueado(false);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreDeUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombreDeUsuario, other.nombreDeUsuario);
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Boolean getEstaLogueado() {
		return estaLogueado;
	}

	public void setEstaLogueado(Boolean estaLogueado) {
		this.estaLogueado = estaLogueado;
	}

}
