# Gestor de Reservas de Salón de Eventos

Aplicación desarrollada en **Java** con arquitectura **MVC** para gestionar reservas de un salón de eventos. Permite ingresar ofertas de horarios con montos y equipamiento y luego, seleccionar las reservas que maximizan la ganancia sin superposición de horarios.

---

## Tecnologías utilizadas
- **Lenguaje:** Java  
- **Arquitectura:** MVC (Model-View-Controller)  
- **Testing:** JUnit  
- **Persistencia:** Serialización de ofertas registradas

---

## Funcionalidades principales
- Registro de ofertas (nombre, horario, monto, equipamiento).  
- Selección automática de reservas óptimas mediante:
  - **Heurística golosa** (encuentra una ganancia cercana a la máxima).  
  - **Algoritmo polinomial óptimo** (garantiza la máxima ganancia).  
- Visualización de ofertas ingresadas y adjudicadas.  
- Persistencia de las ofertas ingresadas mediante serialización en formato JSON.

---

## Estructura del repositorio
- /src/controller    -> Contiene a la clase encargada de realizar las interacciones desde la vista del usuario con la lógica del programa (para no romper el principio de "Separated presentation").
- /src/model         -> Contiene todas las clases encargadas de la lógica de negocio.
- /src/view          -> Contiene todas las clases responsables de la vista del usuario.

---

## Autores 
- Alegre Maximiliano
- Baez Tomas
- Berini Bruno
- Ibarra Milagros
