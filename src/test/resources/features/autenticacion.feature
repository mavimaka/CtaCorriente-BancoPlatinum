# language: es
Característica: Autenticación y Gestión de Clientes en Banco Platinum

  Escenario: 4.1 Ingreso exitoso con credenciales válidas registradas en BD
    Dado que el usuario navega a la página de inicio de sesión "http://localhost:8080/CtaCorriente/login.jsp"
    Cuando ingresa el nombre de usuario "admin_platinum" y la contraseña "Platinum2026!"
    Y hace clic en el botón "Iniciar Sesión"
    Entonces el sistema muestra el panel principal con el mensaje "Bienvenido al sistema"

  Escenario: 4.2 Ingreso fallido con credenciales erróneas
    Dado que el usuario navega a la página de inicio de sesión "http://localhost:8080/CtaCorriente/login.jsp"
    Cuando ingresa el nombre de usuario "usuario_invalido" y la contraseña "ClaveIncorrecta"
    Y hace clic en el botón "Iniciar Sesión"
    Entonces el sistema muestra un mensaje de error "Usuario o contraseña incorrectos"

  Escenario: 4.3 Registro de una nueva cuenta cliente
    Dado que el ejecutivo accede al formulario de registro de clientes "http://localhost:8080/CtaCorriente/registro.jsp"
    Cuando completa los datos del cliente con RUT "98765432-1", Nombre "María", Apellido "Gómez", Dirección "Av. Las Condes 500" y Teléfono "+56987654321"
    Y asigna el monto inicial 500000 y el ejecutivo "11111111-1"
    Y hace clic en el botón "Registrar Cuenta"
    Entonces la base de datos confirma la creación de la cuenta corriente