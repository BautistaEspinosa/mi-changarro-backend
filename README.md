# 🧠 Mi Changarro - Backend

Backend del sistema Mi Changarro, una aplicación educativa diseñada para ayudar a jóvenes emprendedores a entender y administrar su dinero de forma simple y visual.

---

## 🎯 Objetivo del sistema

Mi Changarro ayuda a los usuarios a:

- Entender cuánto dinero ganan realmente
- Saber cuánto pueden gastar
- Controlar su inventario de productos
- Registrar compras y ventas
- Manejar metas de ahorro
- Visualizar de dónde viene su dinero

El sistema está diseñado para ser simple, visual y educativo (no contable).

---

## 🧱 Stack tecnológico

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- Bean Validation

---

## 🏗️ Arquitectura

Arquitectura en capas:

Controller → Service → Repository → DTO → Mapper → Entity

Reglas importantes:
- La lógica de negocio vive en Service
- Los Controllers solo manejan HTTP
- Nunca se exponen Entities al frontend
- El inventario se controla automáticamente por compras y ventas

---

## 📦 Módulos del sistema

- Producto / Inventario
- Compras
- Ventas (incluye Betterware)
- Me Deben (deudas)
- Metas + Ahorro automático
- Dashboard (solo lectura)

---

## 💡 Conceptos clave

### Producto
Representa el estado actual del negocio:
- stock disponible
- costo actual
- precio de venta

---

### Compras
Registra la entrada de mercancía al negocio.
- Aumenta el stock automáticamente
- Calcula el costo unitario real
- Genera historial de compras

---

### Ventas
Registra la salida de productos.
- Reduce stock automáticamente
- Puede ser CONTADO o FIADO
- FIADO genera deuda automáticamente

---

### Inventario
No es una tabla.
Es una vista del estado actual de productos.

---

### Metas
Permite ahorro automático basado en ingresos reales.

---

## 🔁 Flujo general del sistema

Compra → incrementa stock  
Venta → reduce stock  
Producto → refleja estado actual  
Inventario → consulta Producto

---

## 🚫 Reglas del proyecto

- No se permite modificar stock manualmente
- No se usan conceptos financieros complejos (ROI, margen, etc.)
- No se expone lógica de negocio en Controllers
- Las ventas son inmutables una vez creadas
- Solo una meta activa a la vez

---

## 🗄️ Base de datos

- producto
- compra
- detalle_compra
- venta
- detalle_venta
- venta_betterware
- deuda
- meta
- movimiento_ahorro

---

## 🚀 Estado del proyecto

- Arquitectura definida
- Modelo de dominio definido
- Base de datos definida
- Implementación backend (en progreso)
- Frontend (pendiente)
- Integración (pendiente)

---

## 👨‍💻 Autor

Proyecto de aprendizaje enfocado en:
- Java + Spring Boot
- React
- Arquitectura de software
- Sistemas de negocio simplificados

---

## 📌 Nota

Este proyecto prioriza:
- Simplicidad
- Claridad
- Educación financiera básica
- Experiencia visual

No es un ERP ni sistema contable.