package unlam.edu.ar;

import java.util.*;


public class Sistema {

	
	private String nombreDelSistema;
	private Set<Usuario> usuarios;
	
	public Sistema(String nombreDelSistema) {
		this.setNombreDelSistema(nombreDelSistema);
		this.usuarios = new HashSet<Usuario>();
	}
	
	

	public String getNombreDelSistema() {
		return nombreDelSistema;
	}

	public void setNombreDelSistema(String nombreDelSistema) {
		this.nombreDelSistema = nombreDelSistema;
	}
	
	public void agregarUnUsuarioAlSistema(Usuario usuarioNuevo) throws UsuarioConElMismoNombreException, InvalidPassword {
	    for (Usuario usuario : usuarios) {
	        if (usuario.getNombreDeUsuario().equals(usuarioNuevo.getNombreDeUsuario())) {
	            throw new UsuarioConElMismoNombreException("No se puede agregar un usuario con el mismo nombre");
	        }
	    }
	    
	    if (usuarioNuevo instanceof UsuarioBasico) {
	        UsuarioBasico usuarioBasico = (UsuarioBasico) usuarioNuevo;
	        if (!usuarioBasico.validarContrasenia()) {
	            throw new InvalidPassword("La contraseña no cumple con los requisitos");
	        }
	    } else if (usuarioNuevo instanceof UsuarioAdministrador) {
	        UsuarioAdministrador usuarioAdmin = (UsuarioAdministrador) usuarioNuevo;
	        if (!usuarioAdmin.validarContrasenia()) {
	            throw new InvalidPassword("La contraseña no cumple con los requisitos");
	        }
	    }

	    usuarios.add(usuarioNuevo);
	}
	
	
	
	public Integer getCantidadDeUsuarios() {
		return usuarios.size();
	}
	
	public void loggearUsuario(Usuario usuarioALoguear, String contrasenia) throws UserNotFoundException {
		Usuario usuarioALoggear = buscarUsuarioPorNombre(usuarioALoguear.getNombreDeUsuario());
		if(usuarioALoggear == null) {
			throw new UserNotFoundException("No se encontro al usuario");
		}
		
		if(usuarioALoguear instanceof UsuarioBasico) {
			if(usuarioALoguear.getContrasenia().equals(contrasenia)) {
				usuarioALoguear.setEstaLogueado(true);
			} else {
				((UsuarioBasico) usuarioALoguear).sumarUnIntentoDeLogeo();
				if(((UsuarioBasico) usuarioALoguear).getIntentosDeLogeo() == 3) {
					((UsuarioBasico) usuarioALoguear).setEstaBloqueado(true);
				}
			}
		} else {
			if(usuarioALoguear.getContrasenia().equals(contrasenia)) {
				usuarioALoguear.setEstaLogueado(true);
			}
		}
	}
	
	public Usuario buscarUsuarioPorNombre(String nombreDelUsuario) throws UserNotFoundException {
		for(Usuario usuario: usuarios) {
			if(usuario.getNombreDeUsuario().equals(nombreDelUsuario)) {
				return usuario;
			}
		}
		throw new UserNotFoundException("No se encontro al usuario");
	}
	
	public TreeSet<Usuario> obetenerUsuariosOrdenadosPorNombre(OrdenarPorNombre ordenPorNombre ){
		
		
		 TreeSet<Usuario> usuariosOrdenados = new TreeSet<>(ordenPorNombre);


		    return usuariosOrdenados;		
		
	}
	
	public void eliminarUsuario(Usuario usuarioABorrar) throws ClassCastException, UserNotFoundException {
		Usuario usuarioAEliminar = buscarUsuarioPorNombre(usuarioABorrar.getNombreDeUsuario());
		
		if(usuarioAEliminar instanceof UsuarioBasico) {
			((UsuarioBasico) usuarioAEliminar).setFueEliminado(true);
		} else {
			throw new ClassCastException("No se puede eliminar un administrador");
		}
		
	}

}
