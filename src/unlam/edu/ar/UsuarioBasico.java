package unlam.edu.ar;

public class UsuarioBasico extends Usuario implements IContraseniaValidator {

	private Boolean estaBloqueado;
	private Boolean fueEliminado;
	private Integer intentosDeLogeo;
	
	public UsuarioBasico(String nombreDeUsuario, String contrasenia) {
		super(nombreDeUsuario, contrasenia);
		this.fueEliminado = false;
		this.estaBloqueado = false;
		this.intentosDeLogeo = 0;
	}

	public Boolean getEstaBloqueado() {
		return estaBloqueado;
	}

	public void setEstaBloqueado(Boolean estaBloqueado) {
		this.estaBloqueado = estaBloqueado;
	}

	public Boolean getFueEliminado() {
		return fueEliminado;
	}

	public void setFueEliminado(Boolean fueEliminado) {
		this.fueEliminado = fueEliminado;
	}
	
	public Integer getIntentosDeLogeo() {
		return this.intentosDeLogeo;
	}

	public void sumarUnIntentoDeLogeo() {
		intentosDeLogeo++;
	}

	@Override
	public Boolean validarContrasenia() {
        return this.getContrasenia().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+$");

	}
}
