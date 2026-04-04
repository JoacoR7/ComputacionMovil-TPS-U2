## Ejercicio 7A
Para la realización del ejercicio, se siguió el siguiente fragmento de tutorial https://www.youtube.com/watch?v=lDgv43ZR3VM. El mismo está trabajado en Kotlin, pero se adaptó a Java. Adicionalmente, se implementó la funcionalidad de hacer peticiones con el token.  

Cabe aclarar que la aplicación fue probada utilizando el servicio JWT del proyecto realizado en 2024 en Ingeniería del Software II.
[Repositorio del proyecto](https://github.com/TomasRandoM/ProyectoSeguridad/tree/main/appseguridad)

El funcionamiento es el siguiente:
Al apretar el botón enviar, se envía una petición POST con Retrofit a la url /auth/login del servidor. Este POST lleva consigo el usuario y contraseña del usuario. El servidor valida los datos y devuelve un authorizationToken si el usuario es correcto. Caso contrario, el servidor responde con un 403. Si se recibe el token, se crea un SharedPreferences (Una manera de guardar datos persistentes en forma de clave-valor) con el token. Además, se crea nuevamente el ApiService para incluir el token recibido como cabecera en las siguientes peticiones.  

Al hacer las peticiones (En este ejemplo, se hace una petición a /api/v1/usuario/buscar/nombreUsuario para buscar el nombre y rol del usuario) se envía el token en la cabecera. Si el token es correcto, esta petición tendrá exito y se mostrará un TOAST con la información del usuario. Caso contrario, se mostrará un TOAST informando que el usuario no se encuentra autenticado.  

El presente ejemplo es un ejemplo sencillo que busca probar el funcionamiento de JWT en android, por ello la interfaz es simple y todos los mensajes de respuesta se hacen a través de TOAST. 