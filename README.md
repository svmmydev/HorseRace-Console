<div align="center">

# 🏇 Carrera de caballos 🏇

¡Hola! 👋
Bienvenido a nuestro humilde proyecto. Una pequeña aplicación que te permite pasar un ratillo disfrutando de una carrera de caballos, como lo oyes, ¡y encima puedes hacer apuestas! Pero tranquilo.. no tienes que meter dinero, ni siquiera son monedas virtuales, son ¡CHIPS! 🍪



Lo que nació siendo un trabajo de clase, se ha convertido en todo un desafió grupal del que estamos super orgullosos, esperamos que disfrutéis de este pequeño trabajo.

</br>

## Integrantes del equipo
## The Java Scrolls: Code Assassins 🗡️📜<br>
<br>
<div>
  <img src="resources/Readme-Images/TheJavaScrolls_CodeAssasins.png" alt="Logo Grupo The Java Scrolls: Code Assasins" width="300">
</div>
<br>

<table>
  <tr>
    <td align="center">
        <img src= "resources/Readme-Images/Roger.png" alt="Mini Roger" width="80"></td>
      <td align="center">
        <img src= "resources/Readme-Images/Sammy.png" alt="Mini Sammy" width="80"></td>
      <td align="center">
        <img src= "resources/Readme-Images/Leandro.png" alt="Mini Leandro" width="80"></td>
  </tr>
  <tr>
    <td>
      <img src="https://img.shields.io/badge/Roger-Git?style=flat&logo=github&logoColor=white&labelColor=black&color=50e520&label=GitHub" alt="Badge">
    </td>
    <td>
      <img src="https://img.shields.io/badge/Sammy-Git?style=flat&logo=github&logoColor=white&labelColor=black&color=50e520&label=GitHub" alt="Badge">
    </td>
    <td>
      <img src="https://img.shields.io/badge/Leandro-Git?style=flat&logo=github&logoColor=white&labelColor=black&color=50e520&label=GitHub&link=https%3A%2F%2Fgithub.com%2FLeanEmanuel" alt="Badge">
    </td>
  </tr>
</table>

---

## Descripción Funcional del Juego 🎮
***Objetivo del Juego:***<br>  
En este juego de carreras de caballo, los jugadores apuestan por los caballos que creen que ganará la carrera.  
La carrera esta representado por las cartas de la baraja española, donde los caballos se van moviendo por el tablero según
las cartas que se lanzan en cada turno.

***Instrucciones Básicas:***

🚀 Cómo comenzar una partida<br>
  <p align="left">
  • Inicio del juego: Ingresa la cantidad de jugadores totales, se ingresa el nombre de los jugadores humanos.<br>
  • Apuestas: Se selecciona el caballo a apostar y el número de la apuesta en fichas.<br>
  • Inicio de la carrera: Comienza el primer turno sacando la primera carta del mazo.<br>
  </p>
  
📝 Reglas principales del juego.<br>
<p align="left">
  • Selección de jugadores humanos, mínimo un jugador humano y mínimo dos jugadores en total.
    El máximo de jugadores permitidos sumando humanos y bots es de 6.
  • Carrera por turnos: Cada ronda representa un turno en la carrera, donde se determina el caballo que se movera según
  el palo de la última carta tirada.<br>
  • Movimiento de los caballos: El caballo que se corresponde con el palo de la última carta tirada avanza una casilla en el tablero.<br>
  Si el turno es múltiplo de 5 se retrocede una casilla.<br>
  • Ganador de la carrera: El primer caballo en llegar a la última casilla, se reparten las apuesta entre los jugadores que han ganado.<br>
  
🎮 Explicación de los controles o comandos importantes.<br>
  <p align="left">
    • Iniciar partida: Se indica el número de jugadores y sus nombres por consola.<br>
    • Seleccionar caballo y apuesta: Cada jugador selecciona un caballo y una apuesta en fichas.<br>
    • Continuar carrera: Despúes de cada turno se ejecuta una pausa, presionar Enter para continuar.<br>
  </p>
  
***Características Principales:***

<p align="left">
  • Multijugador: Permite entre 2 y 6 jugadores.<br>
  • Apuestas por carrera: Los jugadores eligen sus apuestas antes de iniciar la carrera.<br>
  • Clasificación de jugadores: Los jugadores se clasifican según el bankroll acumulado tras cada carrera.<br>
  • Narración de la carrera: La consola muestra mensajes narrativos, detallando movimientos y posiciones de los caballos en el tablero.<br>
  • Dinámica de eliminación: Los jugadores que pierden todas sus fichas quedan eliminados del juego.<br>
</p>
<br>

<div align="center">
  <img src="resources/Readme-Images/game_horse_race.gif" alt="Gif Game Horse Race" width="800">
</div>

---

## Descripción Técnica ⚙️
***Arquitectura General del Proyecto***  
Aquí puedes describir cómo se organiza el proyecto, el flujo de datos y las interacciones entre los componentes principales.

### Diagramas de Clases
![Diagrama de Clases](ruta/a/diagrama_de_clases.png)
- **Descripción:** Explica brevemente cómo se relacionan las clases principales, destacando sus roles en el juego y cualquier patrón de diseño relevante.

### Estructura de Directorios

<table align="center" border="6px"><td><pre>
📦NombreDelProyecto
 ┣ 📂src
 ┃ ┣ 📜main.php
 ┃ ┗ 📜clases
 ┃   ┣ 📜jugador.php
 ┃   ┗ 📜juego.php
 ┗ 📜README.md
</pre></td>
</table>

### Tecnologías y Herramientas
• **Lenguaje de programación:** PHP
- **Entorno de desarrollo:** Visual Studio Code, XAMPP, etc.

---

## Instalación y Ejecución 🚀
1. Clona el repositorio: `git clone https://github.com/tu_usuario/tu_proyecto.git`
2. Abre el proyecto en tu entorno de desarrollo.
3. Configura las dependencias (si aplica).
4. Inicia el servidor y ejecuta el juego siguiendo las instrucciones.

---

## Créditos y Reconocimientos
Agradecimientos especiales a los profesores o compañeros que han contribuido o inspirado el proyecto.

</div>
