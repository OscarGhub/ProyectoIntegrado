# ProyectoIntegrado

Trabajo de: Samuel Leyton Rojas, Pedro Moreno Gullón y Oscar García Delgado.

-----------------------------------------------------------------------------------------------------------------------------------

IMPORTANTE: EL USUARIO DE LA BASE DE DATOS ES C##PROYECTOINTEGRADO Y LA CONTRASEÑA 123456.

MANUAL DE USO

1. Introducción
Dogpuccino es una aplicación de escritorio desarrollada en JavaFX que combina el amor por los perros y la
experiencia de una cafetería temática. Ofrece un entorno amigable para:

- Reservar citas para visitar la cafetería.
- Registrar clientes interesados en adoptar.
- Registrar protectoras de animales.
- Gestionar solicitudes de adopción.
- Promover el cuidado y bienestar animal.

2. Requisitos del sistema

- Sistema operativo: Windows, macOS o Linux.
- Java JDK 11 o superior.
- Scene Builder (opcional para editar archivos FXML).
- IDE compatible como IntelliJ IDEA, NetBeans o Eclipse.
  
3. Instalación y ejecución
   
1. Clonar o descargar el repositorio del proyecto.
2. Abrir el proyecto en un IDE.
3. Asegurarse de tener JavaFX configurado correctamente en el entorno.
4. Ejecutar la clase Main.java para iniciar la aplicación.

4. Pantalla principal

La pantalla principal sirve como punto de entrada a las diferentes funcionalidades: registro y login de clientes y
registro y login de protectoras, una vez dentro de ellas nos encontramos distintas funcionalidades como formularios de adopción y ajustes,
se puede navegar a las distintas ventanas mediante botones o menús disponibles.

5. Registro de clientes y protectoras
   
- En el registro de clientes se solicita información como nombre, correo electrónico, y preferencias.
- En el registro de protectoras se incluyen datos de contacto y detalles sobre la organización.
Ambos formularios están validados para asegurar la entrada correcta de datos antes de ser enviados.

6. Gestión de citas

La primera función a la que tiene que acceder el cliente si quiere adoptar un perro.
La función de citas permite a los usuarios reservar visitas a la
cafetería para interactuar con perros disponibles para adopción. Incluye:
- Selección de fecha y hora (1h x cita).
- Confirmación y posible modificación o cancelación de la cita.
  
- Condiciones: 1 perro solo puede tener 3 citas x día, 1 cita en la misma hora y la cita tiene que tomarse con un día de antelación.

Sin cita previa no se puede adoptar.

7. Formulario de adopción
   
Este formulario permite a los usuarios completar una solicitud para adoptar un perro. Se deben ingresar:
- Datos personales del adoptante (autocompletados al iniciar sesión con una cuenta).
- Información sobre el lugar donde vivirá el animal (dirección que se conoce por el registro de su cuenta).
La solicitud será enviada a la protectora del perro para su evaluación.

- Condiciones: Una adopción sólo puede ser solicitada por un cliente el cual haya tenido una cita previamente con ese mismo perro.
  
8. Ajustes y configuración
   
La sección de ajustes permite modificar opciones de usuario como:
- Preferencias visuales.
- Modificar nombre, correo y contraseña de la cuenta.

9. Contacto y soporte
    
Para contacto visitar el repositorio en GitHub.

-----------------------------------------------------------------------------------------------------------------------------------

ERRORES O PROBLEMAS ENCONTRADOS DURANTE EL DESARROLLO DE LA APLICACIÓN EN JAVA FX:

- Ajustar el tamaño de los contenedores, imágenes, textos y encabezados de las tablas al hacer la interfaz responsive.
- Algunos botones que abren o cierran ventanas no han podido ser modificados, y no se ha logrado implementar los métodos abrirVentana y cerrarVentana debido a que su estructura es diferente.
- Modificar el script SQL para adaptarlo a las necesidades específicas de la aplicación.
- Si en IntelliJ se inicia un proyecto desde una carpeta que contiene otra carpeta con el proyecto real, este último no tendrá el JDK inicializado correctamente.
- Si se realizan merges desde la terminal (cmd), puede haber errores invisibles: aunque no se reporten conflictos, al abrir el proyecto puede aparecer parcialmente fusionado o con errores no detectados.

-----------------------------------------------------------------------------------------------------------------------------------

