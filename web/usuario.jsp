<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>LICORERÍA MONARCA</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/styles.css" rel="stylesheet" />
        <script src="js/scripts.js"></script>

        <!-- Boostrap y iconos -->
        <script src="js/fontawesome.js"></script>
        <script src="js/bootstrap@5.2.3.js"></script>
        <link rel="stylesheet" href="css/bootstrap-icons-1.10.5/font/bootstrap-icons.css"/>
        <link rel="icon" href="img/icono.ico" sizes="32x32">
        <script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" 
        crossorigin="anonymous"></script>

        <style>
            .requerido label:after {
                content: " *";
                color: red;
            }
        </style>
    </head>
    <body class="sb-nav-fixed" onload="noAtras()">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <c:if test="${error != null}">
                        <div class="alert alert-danger mt-4" id="mensajeEmergente">
                            <strong>Danger!</strong> ${error}
                        </div>
                    </c:if>
                    <c:if test="${success != null}">
                        <div class="alert alert-success mt-4" id="mensajeEmergente">
                            <strong>Success!</strong> ${success}
                        </div>
                    </c:if>
                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                        <div class="card">
                            <div class="card-header"><i class="fas fa-user"></i> Usuario</div>
                            <div class="card-body">
                                <form method="POST" action="Empleado">
                                    <div class="modal-body">
                                        <div class="row g-2">
                                            <div class="col-sm-6 requerido">
                                                <label class="form-label">Nombre</label>
                                                <input id="nombre" type="text" class="form-control" onkeypress="return soloLetras(event)" name="nombreEmp" required autocomplete="off" value="${objEmp.getNombre()}">
                                            </div>
                                            <div class="col-sm-6 requerido">
                                                <label class="form-label">Apellido</label>
                                                <input id="apellido" type="text" class="form-control" onkeypress="return soloLetras(event)" name="apellidoEmp" required autocomplete="off" value="${objEmp.getApellido()}">
                                            </div>
                                            <div class="col-sm-6 requerido">
                                                <label class="form-label">Teléfono</label>
                                                <input id="telefono" type="text" class="form-control" minlength="9" required maxlength="9" onkeypress="return soloNumero(event)" name="telefonoEmp" autocomplete="off" value="${objEmp.getTelefono()}">
                                            </div>
                                            <div class="col-sm-6 requerido">
                                                <label class="form-label">Dirección</label>
                                                <input type="text" class="form-control" required name="direccionEmp" id="direccion" autocomplete="off" value="${objEmp.getDireccion()}">
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="form-label">DNI</label>
                                                <input type="text" disabled class="form-control" minlength="8" required name="dniEmp" maxlength="8" id="dni" onkeypress="return soloNumero(event)" autocomplete="off" value="${objEmp.getDni()}">
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="form-label">Correo</label>
                                                <input type="email" disabled class="form-control" required name="correoEmp" id="correo" autocomplete="off" value="${objEmp.getCorreo()}">
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="form-label">Cargo</label>
                                                <input type="text" class="form-control" disabled name="cargoEmp" required id="cargo" autocomplete="off" value="${objEmp.getIdCargo().getNombre()}">
                                            </div>
                                            <div class="col-sm-6 requerido">
                                                <label class="form-label">Usuario</label>
                                                <input type="text" class="form-control" name="userEmp" required id="user" autocomplete="off" value="${objEmp.getUser()}">
                                            </div>
                                            <div class="col-sm-6" id="contraseña">
                                                <div class="row">
                                                    <div class="col-12 mt-2">
                                                        <a type="button" class="btn btn-success" href="Empleado?accion=editarPassword">Cambiar Password</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${errorMsj != null}">
                                            <div class="row mt-2">
                                                <div class="col-12">
                                                    <div class="alert alert-danger" role="alert">
                                                        ${errorMsj}
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="modal-footer mt-4">
                                        <a class="btn btn-secondary m-2" href="Empleado?accion=cancelar">Cancelar</a>
                                        <button type="submit" name="accion" value="guardarUser" class="btn btn-primary">Guardar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

    <script>
        $(document).ready(function () {
            if (document.getElementById('mensajeEmergente')) {
                var timeLeft = 5;
                var timerId = setInterval(countdown, 500);
                function countdown() {
                    if (timeLeft === -1) {
                        clearTimeout(timerId);
                    } else {
                        if (timeLeft === 1) {
                            document.getElementById('mensajeEmergente').style.display = 'none';
                        }
                        timeLeft--;
                    }
                }
            }
        });
        function noAtras() {
            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button"; //chrome necesita esto
            window.onhashchange = function () {
                window.location.hash = "no-back-button";
            };
        }
        function soloNumero(evt) {
            key = evt.keyCode || evt.which;
            tecla = String.fromCharCode(key).toString();
            letras = "0123456789";
            especiales = [8, 13, 32];
            tecla_especial = false;
            for (var i in especiales) {
                if (key === especiales[i]) {
                    tecla_especial = true;
                    break;
                }
            }
            if (letras.indexOf(tecla) === -1 && !tecla_especial) {
                alert("Ingrese solo Números");
                return false;
            }
        }
        function soloLetras(e) {
            key = e.keyCode || e.which;
            tecla = String.fromCharCode(key).toString();
            letras = "QWERTYUIOPASDFGHJKLÑZXCVBNMÉÚÍÓÁqwertyuiopasdfghjklñzxcvbnmáéíóú";
            especiales = [8, 13, 32];
            tecla_especial = false;
            for (var i in especiales) {
                if (key === especiales[i]) {
                    tecla_especial = true;
                    break;
                }
            }
            if (letras.indexOf(tecla) === -1 && !tecla_especial) {
                alert("Ingrese solo Letras");
                return false;
            }
        }
    </script>
</html>
