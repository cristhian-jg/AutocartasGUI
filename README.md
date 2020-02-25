# AUTOCARTAS: Manual de usuario

## INDEX

## 1. REGLAS Y FUNCIONAMIENTO

## 2. USO DE LA APLICACIÓN

![Autocartas](https://www.nostalgia80.com/wp-content/uploads/2016/03/baraja-de-cartas-de-coches-de-carreras.jpg)

### 1. REGLAS Y FUNCIONAMIENTO

En este documento se explicará de forma detallada cómo utilizar e instalar la aplicación Autocartas, un juego de cartas en el que se compite contra la CPU haciendo uso de especificaciones de coches.

Al comienzo de cada partida a cada jugador se le repartirán 6 cartas de las 12 del mazo, cada una de estas cartas consta de unas especificaciones:

* Motor: CC (+)
* Potencia:KW (+)
* Velocidad máxima: km/h (+)
* Nº Cilindros: unidades (+)
* Rev. por minuto (rpm):unidades (-)
* Consumo: Litros (-)

Como se puede ver cada especificación tiene un simbolo (-) o (+), esto indica que ganará la caracteristica que menos o más tenga. Despues de haberse repartido las 6 cartas, aleatoriamente el juego elegirá quien empieza seleccionando la caracteristica, esto es algo que pasa a elegirlo el otro jugador en cada turno, es decir, si el jugador gana una de las manos de todas maneras en la siguiente ronda le tocará elegir a la CPU y en caso contrario ocurrirá lo mismo. 

### 2. USO DE LA APLICACIÓN


#### LOGIN Y REGISTRO

Al arrancar la aplicación nos encontraremos con un menú de inicio de sesión, aquí podremos loguearnos con un usuario existente ya en la base de datos o bien registrarnos para poder acceder al programa.

La opción de registro nos llevará a otra ventana donde se nos pedirá un nombre de usuario, este no podrá repetirse, es decir que si ya existe uno en la base de datos no podrá crearse, por lo que tiene que ser un nombre de usuario unico. Tambien se nos pedirá un nombre cualquiera y una constraseña para poder acceder a la aplicación en futuras ocaciones. Al introducir todos los datos correctamente podremos utilizar el botón de registro lo que accionará el crear un nuevo usuario en la base de datos.

Si ya estamos registrados, podremos loguearnos introduciendo nuestro nombre de usuario y contraseña, estas tiene que coincidir sí o sí, tanto mayusculas como minusculas, una vez introducidos estos datos podremos accinar el botón de iniciar sesión lo que nos llevará a la ventana principal del juego.

#### MENU DE INICIO

Como en la gran mayoria de los videojuegos, no podía falta un menú de inicio, donde empezar la partida o bien elegir los ajustes de la partida.

Lo que nos encontraremos en esta ventana en una tabla donde se prensentan las estadisticas de todas las partidas jugadas hasta ahora, es scrollable y es la representación de una de las tablas de la base de datos. 

Por otro lado nos encontraremos con el botón para comenzar la partida que nos llevará a la ventana de juego donde podremos jugar una partida, pero antes de ir a esa ventana tendremos otro botón que nos llevará a la ventana de preferencias, algo que se podría describir como los ajustes de la aplicación, donde dispondremos de varias opciones.

#### PREFERENCIAS 

En las preferencias podremos elegir nuestras configuraciones, se encuentra divida en varias secciones por lo que será más facil identificar lo que buscamos.

###### DATOS DE CONEXION

Si lo que queremos es cambiar de usuario nos dirigiremos al apartado de datos de conexión donde podremos indicar el usuario y la contraseña con el que nos queremos conectar.

###### NOTIFICACIONES

Recibiremos notificaciones diaras de los retos que puedan presentarse en la aplicación. (Proximamanete... )

###### SONIDO

Para activiar la musica de fondo necesitarán habilitar esta opción, por defecto viene desactivada. (Proximamente... )

##### JUEGO

Aquí podrás elegir el nivel de dificultad del juego, podiendo seleccionar desde una dificultad facil, donde la CPU hace operaciones aleatorias hasta la dificultad dificil donde la CPU trata de hacer la mejor jugada posible. (Proximamente... )

Además puedes habilitar un bot automatico que juegue por ti en todo momento, permitiendote ganar monedas sin jugar . (Proximamente... )

#### VENTANA DE JUEGO

Al pulsar en la opción de nueva partida en el menú de inicio se creará una nueva partida y se te llevará a la ventana de juego donde se repartirán las cartas y te dirán quien empieza primero. 

En caso de ser tu momento de elegir una caracteristica podrás hacerlo mediante los 6 botones que tienes a tu derecha:

- POTENCIA
- CILINDROS
- RPM
- CONSUMO
- MOTOR 
- VELOCIDAD

Al seleccionar la caracteristica deberás elegir la carta que quieres jugar, esto lo podrás hacer con las 6 cartas que se te dan repartidas en la parte inferior, presiona sobre una de ellas para seleccionarla, lo que se verá reflejado en la pantalla de juego. 

Cuando hayas seleccionado tanto la carta como la caracteristica deberías asegurar tu jugada dandole al botón LANZAR, situado debajo de las caracteristicas, lo que accionará a la CPU a elegir su carta y desvelar las dos cartas así repartiendo los puntos correspondientes, lo que dará comienzo a una nueva mano.

Si ahora es el turno de la CPU esta deberá seleccionar la caracteristica, por lo que no tendrás acceso a los botones más que al de lanzar. Ahora solo tendrás que eleigr bien tu carta según la caracteristica que haya elegido la CPU, esto se verá representado entre los botones de caracteristicas y el botón lanzar.

Cada vez que juegues una carta se te retirará de tu baraja hasta llegar a las 6 manos donde ya no te quedará ninguna carta, podrás ver el resultado que llevas durante cada mano en la pantalla de juego pero una vez hayas terminado de jugar se te llevará nuevamente al menú de inicio donde verás las caracteristicas de la partida que has jugado. 






