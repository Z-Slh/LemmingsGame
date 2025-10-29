#  Chicks : Java Project inspired by Lemmings

University project developed as part of the **Object-Oriented Programming and Design** course (Bachelor's degree, Université Paris-Est Créteil).

---

##  Description

**Chicks** is a Java game inspired by the classic *Lemmings* (1991).  
The goal is to guide a squad of chicks from an **entry point** to an **exit**, losing as few as possible.  
Chicks walk straight ahead, fall if there’s no ground beneath them, and can die from long falls or deadly obstacles.

Players can assign various **tasks** to the chicks to help them survive and reach the exit:
- **Blocker**: stops and makes others turn around.  
- **Builder**: constructs stairs upwards.  
- **Digger / Driller / Bomber**: digs or destroys destructible blocks.  
- **Climber / Parachutist**: climbs walls or slows down during falls.

---

##  Project Structure

The project follows a **Model-View-Controller (MVC)** architecture:

```
src/
 ├── modele/         // game logic (terrain, cells, chicks, states…)
 ├── vue/            // graphical rendering and window management
 └── controleur/     // user interactions and event control
```

---

##  Design Patterns Implemented

- **MVC (Model-View-Controller)** : separates logic, UI, and control flow.  
- **State Pattern** : manages dynamic chick behaviors (`BloqueurState`, `BomberState`, `CharpentierState`, `ForreurState`, etc.).  
- **Observer Pattern** : handles real-time updates and event listening (`Observable`, `Observer`, `CustomMouseListener`, etc.).  
- **Factory Pattern** : used in `FactoryCellType` for object creation and abstraction.  
- **Strategy Pattern** : defines behaviors and interactions for cell types (`Cell`, `DestructibleCell`, `NotDestructibleCell`, etc.).  
- **Polymorphism and Composition** : implemented in `GameObject`, `RectGameObject`, `CircleGameObject`, etc.

---

##  How to Run

### Compile
```bash
javac -d bin src/**/*.java
```

### Run
```bash
java -cp bin controleur.App
```


---

##  Implementation Highlights

- Step-by-step simulation of chick movement and environment updates.  
- Real-time user interaction through mouse and keyboard listeners.  
- Modular state transitions for different chick roles.  
- Strong decoupling between model logic and GUI rendering.  
- No external resources: only Java’s standard graphics (Swing/AWT).

---

##  Future Improvements
- Add a graphical level editor
- Implement multiple levels and difficulty settings
- Improve pathfinding for complex terrains
- Add saving/loading functionality

---

##  Author

- **Salah Zili**, Université Paris-Est Créteil (Computer Science, L3)

