# PB2TNRecuperatorio

Recuperatorio del parcial 2!


Sistema Seguro
Nos contratan para desarrollar un software que permita administrar los usuarios de un sistema de manera independiente al resto del sistema.
	•	Los usuarios pueden ser de tipo Básico o Administradores.
	•	Los usuarios básicos son Bloqueables y Eliminables.
	•	Que un usuario sea Bloqueable quiere decir que se puede bloquear. Esto sucede al hacer el login más de 3 veces con una contraseña incorrecta.
	•	Que un usuario sea Eliminable quiere decir que se puede eliminar. Sin embargo la eliminación será lógica a través de una marca en la base de datos.
	•	Los objetos usuarios no se pueden duplicar, siendo su nombre de usuario la clave.
	•	Los usuarios se encuentran ordenados a partir de su nombre de usuario.
	•	Si se intenta loguear un usuario que no existe en la base de datos se debe producir la Excepción unlam.pb2.UserNotFound.
	•	Si se intenta eliminar un usuario que no sea Eliminable se debe arrojar la excepción java.lang.ClassCastException.
	•	Los requisitos de las contraseñas varían entre los usuarios Básicos y los usuarios Administradores. Los usuarios básicos deben tener contraseñas que contengan al menos un número y una minúscula y una mayúscula. Los usuarios administradores además de los requisitos de la contraseña de los usuarios básicos deben tener un carácter especial y no puede tener secuencia de más de 3 caracteres seguidos (123 y abc es válido, pero 1234 o abcd no). 
	•	Intentar generar un usuario con una contraseña inválida debe arrojar la excepción unlam.pb2.InvalidPassword
Condiciones
	•	Se solicita diseñar las clases que dan solución a lo planteado. Se debe aplicar el uso de Herencia, Polimorfismo, Interfaces y Excepciones. 
	•	El desarrollo debe ser con la técnica TDD 
	•	La entrega es a través de Miel, pero sólo se aceptará URL del repositorio público de GitHub (No se aceptan archivos zip).
