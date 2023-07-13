package unlam.edu.ar;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSistema {



	@Test
	public void queSePuedaAgregarUnUsuarioBasicoAlSistema() throws UsuarioConElMismoNombreException, InvalidPassword {

	
		final Integer CANTIDAD_DE_USUARIOS_ESPERADOS = 1;
		
		Usuario nuevoUsuario = new UsuarioBasico("Matias", "Password1");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		
		assertEquals(CANTIDAD_DE_USUARIOS_ESPERADOS, nuevoSistema.getCantidadDeUsuarios());
	
	}
	
	@Test(expected = UsuarioConElMismoNombreException.class)
	public void queNoSePuedanAgregarDosUsuariosConElMismoNombre() throws UsuarioConElMismoNombreException, InvalidPassword {
		
		Usuario nuevoUsuario = new UsuarioBasico("Matias", "Password1");
		Usuario nuevoUsuario2 = new UsuarioAdministrador("Matias", "Password1"); 
		Sistema nuevoSistema = new Sistema("BBVA");
		final Integer CANTIDAD_DE_USUARIOS_ESPERADOS = 1;

		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario2);
		
		assertEquals(CANTIDAD_DE_USUARIOS_ESPERADOS, nuevoSistema.getCantidadDeUsuarios());

	}
	

	@Test
	public void queSePuedaBuscarUnUsuarioPorSuNombre() throws UsuarioConElMismoNombreException, UserNotFoundException, InvalidPassword {

		final String NOMBRE_DE_USUARIO_ESPERADO = "Matias";
		final String CONTRASENIA_DE_USUARIO_ESPERADA = "Password1";
		
		Usuario nuevoUsuario = new UsuarioBasico("Matias", "Password1");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		

		Usuario usuarioEncontrado = nuevoSistema.buscarUsuarioPorNombre("Matias");
		
		assertEquals(NOMBRE_DE_USUARIO_ESPERADO, usuarioEncontrado.getNombreDeUsuario());
		assertEquals(CONTRASENIA_DE_USUARIO_ESPERADA, usuarioEncontrado.getContrasenia());
		
	}


	
	@Test(expected = UserNotFoundException.class)
	public void queSiNoSeEncuentraUsuarioLanzeExcepcion() throws UsuarioConElMismoNombreException, UserNotFoundException, InvalidPassword {

		
		Usuario nuevoUsuario = new UsuarioBasico("Matias", "Password1");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		

		Usuario usuarioEncontrado = nuevoSistema.buscarUsuarioPorNombre("Matia");
		
		
	}
	
	@Test
	public void queSePuedaEliminarUnUsuarioBasicoDelSistema() throws UsuarioConElMismoNombreException, ClassCastException, UserNotFoundException, InvalidPassword {
		UsuarioBasico nuevoUsuario = new UsuarioBasico("Matias", "Password1");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		
		nuevoSistema.eliminarUsuario(nuevoUsuario);
		
		assertTrue(nuevoUsuario.getFueEliminado());

	}
	
	@Test(expected = ClassCastException.class)
	public void queSiIntentamosEliminarUnUsuarioAdministradorLanzeClassCastException() throws UsuarioConElMismoNombreException, UserNotFoundException, ClassCastException, InvalidPassword {

		
		UsuarioAdministrador nuevoUsuario = new UsuarioAdministrador("Matias", "Password1*");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		

		nuevoSistema.eliminarUsuario(nuevoUsuario);
		
	}
	
	@Test
	public void queSePuedaLoggearUnUsuario() throws UserNotFoundException, UsuarioConElMismoNombreException, InvalidPassword {

		
		UsuarioBasico  nuevoUsuario = new UsuarioBasico("Matias", "Password1");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		nuevoSistema.loggearUsuario(nuevoUsuario, "Password1");
		
		assertTrue(nuevoUsuario.getEstaLogueado());
	}
	
	@Test
	public void queSeSumeUnIntentoDeLogeoCadaVesQueErraLaContrasenia() throws UserNotFoundException, UsuarioConElMismoNombreException, InvalidPassword {

		final Integer CANTIDAD_DE_INTENTOS_FALLIDOS = 2;
		UsuarioBasico  nuevoUsuario = new UsuarioBasico("Matias", "Password1");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		nuevoSistema.loggearUsuario(nuevoUsuario, "passworderronea");
		nuevoSistema.loggearUsuario(nuevoUsuario, "passworderronea");

		assertFalse(nuevoUsuario.getEstaLogueado());
		assertEquals(CANTIDAD_DE_INTENTOS_FALLIDOS, nuevoUsuario.getIntentosDeLogeo());
	}
	
	@Test
	public void queSeBloqueeUnUsuarioAlErrarTresVecesLaContrasenia() throws UserNotFoundException, UsuarioConElMismoNombreException, InvalidPassword {

		UsuarioBasico  nuevoUsuario = new UsuarioBasico("Matias", "Password1");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		nuevoSistema.loggearUsuario(nuevoUsuario, "passworderronea");
		nuevoSistema.loggearUsuario(nuevoUsuario, "passworderronea");
		nuevoSistema.loggearUsuario(nuevoUsuario, "passworderronea");

		assertFalse(nuevoUsuario.getEstaLogueado());
		assertTrue(nuevoUsuario.getEstaBloqueado());
	}
	
	@Test
	public void queSePuedaLoggearCorrectamenteUnUsuarioAdministrador() throws UsuarioConElMismoNombreException, UserNotFoundException, InvalidPassword {
		
		UsuarioAdministrador  nuevoUsuario = new UsuarioAdministrador("Matias", "Password1!");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		nuevoSistema.loggearUsuario(nuevoUsuario, "Password1!");
	

		assertTrue(nuevoUsuario.getEstaLogueado());
	}
	
	@Test(expected = InvalidPassword.class)
	public void queSiIntentamosAgregarUnUsuarioBasicoSinUnaMayusculaLanzeUnaExcepcion() throws UsuarioConElMismoNombreException, InvalidPassword {
		UsuarioBasico  nuevoUsuario = new UsuarioBasico("Matias", "password");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
	}
	
	@Test(expected = InvalidPassword.class)
	public void queSiIntentamosAgregarUnUsuarioBasicoSinUnaMinusculaEnLaContraseniaLanzeUnaExcepcion() throws UsuarioConElMismoNombreException, InvalidPassword {
		UsuarioBasico  nuevoUsuario = new UsuarioBasico("Matias", "PASSWORD");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
	}
	
	@Test(expected = InvalidPassword.class)
	public void queSiIntentamosAgregarUnUsuarioBasicoSinUnNumeroEnLaContraseniaLanzeUnaExcepcion() throws UsuarioConElMismoNombreException, InvalidPassword {
		UsuarioBasico  nuevoUsuario = new UsuarioBasico("Matias", "Password");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
	}
	
	@Test(expected = InvalidPassword.class)
	public void queSiIntentamosAgregarUnAdministradorSinContraseniaConCaracterEspecialLanzeExcepciopn() throws UsuarioConElMismoNombreException, InvalidPassword {
		UsuarioAdministrador  nuevoUsuario = new UsuarioAdministrador("Matias", "Password");
		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
	}
	
	
	@Test(expected = UserNotFoundException.class)
	public void queSiSeIntentaLoggearUnUsuarioQueNoExisteEnLaBaseDeDatosLanzeError() throws UsuarioConElMismoNombreException, InvalidPassword, UserNotFoundException {
		UsuarioAdministrador  nuevoUsuario = new UsuarioAdministrador("Usuario11", "Password1!");
		UsuarioBasico  usuarioInfiltrado = new UsuarioBasico("UsuarioInfiltrado", "Password1");

		Sistema nuevoSistema = new Sistema("BBVA");
		
		
		nuevoSistema.agregarUnUsuarioAlSistema(nuevoUsuario);
		
		nuevoSistema.loggearUsuario(usuarioInfiltrado, "Password1");
	}
	
	
	

	

}
