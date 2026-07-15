# ProyectoFormas — Guía de instalación y ejecución

## 1. Estructura del proyecto

```
ProyectoFormas/
├── src/
│   ├── modelo/          <- lógica de negocio (Figura, Cuerpo, Coleccion, etc.)
│   ├── datos/           <- capa DAO (persistencia MySQL)
│   ├── controlador/     <- controlador MVC
│   └── presentacion/    <- vista: Menu.java, Main.java
├── sql/
│   └── schema.sql       <- script para crear la base de datos
└── README.md
```

## 2. Instalar MySQL en Windows

1. Descarga el **MySQL Installer** desde:
   https://dev.mysql.com/downloads/installer/
   (elige "mysql-installer-community-x.x.x.msi")
2. Ejecútalo y elige el tipo de instalación **"Developer Default"** (instala
   MySQL Server + MySQL Workbench).
3. En el paso de configuración de red, deja el puerto por defecto (**3306**).
4. En "Authentication Method" elige **"Use Strong Password Encryption"**.
5. Define una contraseña para el usuario `root` — **anótala**, la necesitarás
   en `ConexionBD.java`.
6. Termina la instalación y deja que el servicio de MySQL arranque
   automáticamente (queda corriendo como servicio de Windows).

## 3. Crear la base de datos

1. Abre **MySQL Workbench** (se instaló junto con el paso anterior).
2. Conéctate al servidor local con el usuario `root` y la contraseña
   que definiste.
3. Abre el archivo `sql/schema.sql` de este proyecto (File → Open SQL Script)
   y ejecútalo completo (ícono del rayo ⚡ o Ctrl+Shift+Enter).
4. Deberías ver la base `proyecto_formas` con las tablas `figuras` y
   `cuerpos` en el panel de la izquierda (Schemas).

## 4. Descargar el conector JDBC de MySQL

Como no usamos Maven/Gradle, hay que agregar el driver manualmente:

1. Ve a: https://dev.mysql.com/downloads/connector/j/
2. En "Select Operating System" elige **"Platform Independent"**.
3. Descarga el archivo `.zip` (o `.tar.gz`), y descomprímelo. Dentro vas a
   encontrar un archivo como `mysql-connector-j-9.x.x.jar`.
4. Copia ese `.jar` a la carpeta `lib/` de este proyecto (ya viene creada).

## 5. Configurar el proyecto en IntelliJ

1. Abre IntelliJ → **Open** → selecciona la carpeta `ProyectoFormas`.
2. Si IntelliJ no detecta automáticamente `src` como carpeta fuente:
   clic derecho sobre `src` → **Mark Directory as** → **Sources Root**.
3. Agrega el `.jar` del conector como librería:
   - **File → Project Structure → Libraries → botón "+" → Java**
   - Selecciona el archivo `mysql-connector-j-9.x.x.jar` que copiaste en `lib/`.
   - Aplica y acepta.
4. Abre `src/datos/ConexionBD.java` y actualiza la contraseña:
   ```java
   private static final String CONTRASENA = "TU_PASSWORD_AQUI";
   ```
   reemplázala por la contraseña de tu usuario `root` de MySQL.
5. Ejecuta `src/presentacion/Main.java` (botón ▶ verde, o clic derecho →
   Run 'Main.main()').

## 6. Verificar que todo funciona

- Al ejecutar, deberías ver el menú de consola.
- Agrega una figura (opción 1) — debería imprimirse su información y,
  si vas a MySQL Workbench y haces `SELECT * FROM figuras;`, debería
  aparecer la fila insertada.
- Lo mismo para cuerpos con `SELECT * FROM cuerpos;`.

## Notas sobre el diseño

- **MVC**: `presentacion` (vista) → `controlador` (ColeccionControlador)
  → `modelo` (Coleccion, Figura, Cuerpo). El controlador es el único que
  conoce tanto el modelo como el DAO.
- **DAO**: `datos.FiguraDAO` / `datos.CuerpoDAO` son las interfaces;
  `FiguraDAOImpl` / `CuerpoDAOImpl` son las implementaciones concretas
  sobre MySQL. Si algún día cambias de base de datos, solo tocas estas
  dos clases.
- **Ligado dinámico en el menú**: en vez de un switch gigante, cada opción
  del menú es una clase que implementa `OpcionMenu` (p. ej.
  `OpcionAgregarFigura`, `OpcionListarCuerpos`). El `Menu` guarda una lista
  de `OpcionMenu` y llama `opcion.ejecutar(...)` — el método que realmente
  corre depende del tipo concreto en tiempo de ejecución.
- **Fábrica de triángulos**: `Triangulo.crearTriangulo(l1, l2, l3)` decide,
  según las medidas, si crea un `Equilatero`, `Isosceles` o `Escaleno`.
- **Esfera**: es la única figura de la jerarquía `Cuerpo` sin atributo de
  asociación a una figura plana; sus cálculos se derivan indirectamente de
  un círculo interno que no se guarda como atributo del objeto.
