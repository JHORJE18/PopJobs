# PopJobs [Proyecto DAM]
![alt text](https://i0.wp.com/jhorje18.com/wp-content/uploads/2018/02/Banner.png?w=1024&ssl=1)

## Descripción
La idea de esta aplicación es ayudar tanto a los particulares que necesitan generar unos ingresos extra ofreciendo sus servicios, ya que nosotros ponemos en contacto a los que ofrecen servicios contra los que buscan determinado servicio.

Esta app usa tu ubicación para mostrar a los demás usuarios donde te encuentras (Sólo cuando publicas un servicio).
Esta app ha sido desarrollada en el Proyecto Integrado del Ciclo Formativo DAM (Desarrollo de Aplicaciones Multiplataforma) en Florida Universitaria.
> **🚧** Información completa del proyecto en la web https://jhorje18.com/portfolio/popjobs

## Base de datos
Hemos utilizado Firebase para nuestra base de datos ya que, al ser una base de datos no relacional, es más sencillo crearla y que no haya conflictos entre ninguna de las tablas.

Nuestra base de datos consta de dos tablas, una de usuarios, donde irán todas aquellas personas que se registren. Se registrarán mediante Google, y luego la aplicación le pedirá datos personales que necesitamos nosotros para el Firebase. Y la otra tabla es la de Servicios, donde se almacenan todos los servicios que sean añadidos en la aplicación, relacionándolos con su dueño mediante la clave del usuario que lo haya añadido.
Después también utilizamos una funcionalidad que tiene Firebase, que es el Storage, donde almacenamos las imágenes, ya sea de servicios, o del perfil de los usuarios. Las guardamos en la carpeta IMÁGENES/SERVICIOS e IMÁGENES/USUARIOS.

## Desarrollo
Para el desarrollo de la aplicación hemos utilizado las siguientes herramientas

- Android Studio: Para el desarrollo de la aplicación Android
- GitHub: Para contar con un control de versiones compartidos
- Firebase Realtime Database: Base de datos en tiempo real en la nube, proporcionada por Google Firebase para el almacenamiento de datos
- Firebase Auth: Gestor de Sesiones proporcionado por Google para el Inicio de Sesión y creación de cuentas de forma segura
- Firebase Storage: Servidor de Firebase para el almacenamiento de archivos como imágenes…
