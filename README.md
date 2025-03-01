<div align="center">

# 🏇 Carrera de caballos 🏇

¡Hola! 👋
Bienvenido a nuestro humilde proyecto. Una pequeña aplicación que te permite pasar un ratillo disfrutando de una carrera de caballos, como lo oyes, ¡y encima puedes hacer apuestas! Pero tranquilo.. no tienes que meter dinero, ni siquiera son monedas virtuales, son ¡CHIPS! 🍪



Lo que nació siendo un trabajo de clase, se ha convertido en todo un desafió grupal del que estamos super orgullosos, esperamos que disfrutéis de este pequeño trabajo.

</br>

### The Java Scrolls: Code Assassins 🗡️📜<br>
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
      <a href="https://github.com/Roger486">
        <img src="https://img.shields.io/badge/Roger-Git?style=flat&logo=github&logoColor=white&labelColor=black&color=50e520&label=GitHub" alt="Badge">
      </a>
    </td>
    <td>
       <a href="https://github.com/svmmydev">
      <img src="https://img.shields.io/badge/Sammy-Git?style=flat&logo=github&logoColor=white&labelColor=black&color=50e520&label=GitHub" alt="Badge">
         </a>
    </td>
    <td>
      <a href="https://github.com/LeanEmanuel">
      <img src="https://img.shields.io/badge/Leandro-Git?style=flat&logo=github&logoColor=white&labelColor=black&color=50e520&label=GitHub" alt="Badge">
      </a>
    </td>
  </tr>
</table>

</br>

---

</br>

### **Descripción funcional del juego** 

</br>

### ***🏅 Objetivo:***<br>  
En este juego de carreras de caballo, los jugadores apuestan por los caballos que creen que ganará la carrera.  
La carrera esta representado por las cartas de la baraja española, donde los caballos se van moviendo por el tablero según las cartas que se lanzan en cada turno.

</br>

### **🚀 Instrucciones Básicas:**

<p align="center">
    
• ***[Inicio del juego]*** Ingresa la cantidad de jugadores totales, se ingresa el nombre de los jugadores humanos.<br><br>
• ***[Apuestas]*** Se selecciona el caballo a apostar y el número de la apuesta en fichas.<br><br>
• ***[Inicio de la carrera]*** Comienza el primer turno sacando la primera carta del mazo.<br><br>
• ***[Trascurso]*** Se sacan cartas hasta encontrar un caballo ganador.<br><br>
• ***[Final de la carrera]*** Se redistribuyen las ganancias y finaliza la partida.<br><br>
• ***[Jugar de nuevo (Opcional)]*** Se inicia una nueva carrera.<br><br>
</p>
  
### **📝 Reglas del juego:**<br>
<p align="center">
  
• ***Selección de jugadores humanos:*** mínimo un jugador humano y mínimo dos jugadores en total. El máximo de jugadores permitidos sumando humanos y bots es de 6.<br><br>
• ***Carrera por turnos:*** Cada ronda representa un turno en la carrera, donde se determina el caballo que se movera según el palo de la última carta tirada.<br><br>
• ***Movimiento de los caballos:*** El caballo que se corresponde con el palo de la última carta tirada avanza una casilla en el tablero. Si el turno es múltiplo de 5 se retrocede una casilla.<br><br>
• ***Ganador de la carrera:*** El primer caballo en llegar a la última casilla, se reparten las apuesta entre los jugadores que han ganado.<br><br>
  
### **✨ Características Principales:**<br>

<p align="center">
  
• ***Multijugador:*** Permite entre 2 y 6 jugadores.<br><br>
• ***Apuestas por carrera:*** Los jugadores eligen sus apuestas antes de iniciar la carrera.<br><br>
• ***Clasificación de jugadores:*** Los jugadores se clasifican según el bankroll acumulado tras cada carrera.<br><br>
• ***Narración de la carrera:*** La consola muestra mensajes narrativos, detallando movimientos y posiciones de los caballos en el tablero.<br><br>
 • ***Dinámica de eliminación:*** Los jugadores que pierden todas sus fichas quedan eliminados del juego.<br><br>
</p>
<br>

#### 🏁 Muestra de carrera 🏁

<div align="center">
  <img src="resources/Readme-Images/game_horse_race.gif" alt="Gif Game Horse Race" width="800">
</div>

</br>

---

</br>

### **📊 Diagramas de Clases**

</br>

#### Main + MVC
![MVC](resources/class-diagrams/MVC.png)

</br>

#### CardsDeck
![CardsDeck](resources/class-diagrams/CardsDeck.png)

</br>

#### PlayerManager
![PlauerManager](resources/class-diagrams/PlayerManager.png)

</br>

---

</br>

### **⚙️ Descripción Técnica:**

<p align="center">
  
El proyecto está organizado en capas, donde cada módulo tiene una función clara para mantener el código ordenado y fácil de manejar. A continuación se detalla el papel de cada módulo y cómo interactúan entre sí:
</p><br>

<p align="center">
  
**🕹 Módulo controller**<br><br>
Este módulo alberga el controlador principal (*GameController.java*), que se encarga de gestionar el flujo del juego y de coordinar la comunicación entre los componentes principales. GameController actúa como el puente entre la interfaz de usuario y la lógica del juego, manejando la información y las decisiones durante cada turno para aplicar las reglas de la partida.
</p><br>

<p align="center">
  
**🗃 Módulo model**<br><br>
• ***[Board]*** Lleva el control del tablero y de la posición de los caballos en la carrera.<br><br>
• ***[GameHorsesRace]*** Contiene la lógica central del juego de carreras, gestionando el estado general, el avance por turnos y las reglas de movimiento.<br><br>
• ***[model/deck]*** Este submódulo contiene las clases de las cartas:<br><br>
• ***[Card, CardFace, CardSuit, y FacedCard]*** Definen los aspectos de las cartas del juego, como su valor, tipo y la relación con los caballos.<br><br>
• ***[model/player]***  Incluye las clases relacionadas con los jugadores y sus apuestas, ya sean humanos (*Human.java*) o bots (*Bot.java*), que interactúan durante la carrera.
</p><br>

<p align="center">
  
**🧰 Módulo utils:**<br><br>
Este módulo agrupa utilidades que apoyan el funcionamiento general, como *ConsoleInOut* para gestionar la interacción en consola, *Colors* para aplicar colores, y Pause para controlar pausas y ritmos del juego.
</p></br>
    
<p align="center">
  
**👀 Módulo view:**<br><br>
ConsoleView proporciona una interfaz de usuario en consola para la visualización de la carrera, la información de los turnos y la dirección de movimiento de los caballos.<br><br>
La vista interactúa principalmente con el GameController y permite visualizar el estado del juego, las apuestas y los resultados.<br>
</p></br>

### 📁 Estructura de Directorios 📁

<table align="center" border="6px">
  <tr>
    <td>
      <pre>
📦 Práctica UF4
 ┣ 📂 src
   ┣ 📜 Main.java
   ┣ 📂 controller
   ┃ ┣ 📜 GameController.java
   ┣ 📂 model
   ┃ ┣ 📜 Board.java
   ┃ ┣ 📜 GameHorsesRace.java
   ┃ ┣ 📜 PlayerManager.java
   ┃ ┣ 📂 deck
   ┃ ┃ ┣ 📜 Card.java
   ┃ ┃ ┣ 📜 CardFace.java
   ┃ ┃ ┣ 📜 CardsDeck.java
   ┃ ┃ ┣ 📜 CardSuit.java
   ┃ ┃ ┣ 📜 FacedCard.java
   ┃ ┃ ┗ 📜 NumeredCard.java
   ┃ ┣ 📂 player
   ┃ ┃ ┣ 📜 Bet.java
   ┃ ┃ ┣ 📜 Bot.java
   ┃ ┃ ┣ 📜 Human.java
   ┃ ┃ ┗ 📜 Player.java
   ┣ 📂 utils
   ┃ ┣ 📜 Colors.java
   ┃ ┣ 📜 ConsoleInOut.java
   ┃ ┗ 📜 Pause.java
   ┗ 📂 view
     ┗ 📜 ConsoleView.java
      </pre>
    </td>
  </tr>
</table>

</br>

### 🛠️ Tecnologías y Herramientas 🛠️

</br>

<img alt="java" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" width="80"/>  
<img alt="java" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" width="80"/><br><br><br>

<p>
  Integrantes:
  Roger Navarro, 
  Samuel Mateos, 
  Leandro Struni</br>
</p>
</div>
