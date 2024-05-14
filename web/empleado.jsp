<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>LICORERÍA MONARCA</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/scripts.js"></script>
        <link href="css/styles.css" rel="stylesheet" />

        <!-- Boostrap y iconos -->
        <script src="js/fontawesome.js"></script>
        <script src="js/bootstrap@5.2.3.js"></script>
        <link rel="stylesheet" href="css/bootstrap-icons-1.10.5/font/bootstrap-icons.css"/>
        <link rel="icon" href="img/icono.ico" sizes="32x32">

        <!-- Para dar diseño a tabla -->
        <script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" 
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/jquery.dataTables.css" />
        <link rel="stylesheet" href="css/responsive.dataTables.css" />
        <script src="js/jquery.dataTables.js"></script>
        <script src="js/dataTables.responsive.js"></script>

        <!-- Para mensaje Eliminar -->
        <script src="js/sweetalet.js"></script>
        <link rel="stylesheet" href="css/sweetalert.css" />

        <style>
            .requerido label:after {
                content: " *";
                color: red;
            }
            .centrado{
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .lds-spinner {
                color: official;
                display: inline-block;
                position: relative;
                width: 80px;
                height: 80px;
            }
            .lds-spinner div {
                transform-origin: 40px 40px;
                animation: lds-spinner 1.2s linear infinite;
            }
            .lds-spinner div:after {
                content: " ";
                display: block;
                position: absolute;
                top: 3px;
                left: 37px;
                width: 6px;
                height: 18px;
                border-radius: 20%;
                background: #000;
            }
            .lds-spinner div:nth-child(1) {
                transform: rotate(0deg);
                animation-delay: -1.1s;
            }
            .lds-spinner div:nth-child(2) {
                transform: rotate(30deg);
                animation-delay: -1s;
            }
            .lds-spinner div:nth-child(3) {
                transform: rotate(60deg);
                animation-delay: -0.9s;
            }
            .lds-spinner div:nth-child(4) {
                transform: rotate(90deg);
                animation-delay: -0.8s;
            }
            .lds-spinner div:nth-child(5) {
                transform: rotate(120deg);
                animation-delay: -0.7s;
            }
            .lds-spinner div:nth-child(6) {
                transform: rotate(150deg);
                animation-delay: -0.6s;
            }
            .lds-spinner div:nth-child(7) {
                transform: rotate(180deg);
                animation-delay: -0.5s;
            }
            .lds-spinner div:nth-child(8) {
                transform: rotate(210deg);
                animation-delay: -0.4s;
            }
            .lds-spinner div:nth-child(9) {
                transform: rotate(240deg);
                animation-delay: -0.3s;
            }
            .lds-spinner div:nth-child(10) {
                transform: rotate(270deg);
                animation-delay: -0.2s;
            }
            .lds-spinner div:nth-child(11) {
                transform: rotate(300deg);
                animation-delay: -0.1s;
            }
            .lds-spinner div:nth-child(12) {
                transform: rotate(330deg);
                animation-delay: 0s;
            }
            @keyframes lds-spinner {
                0% {
                    opacity: 1;
                }
                100% {
                    opacity: 0;
                }
            }
        </style>
    </head>
    <body class="sb-nav-fixed" onload="noAtras()">
        <div class="container-fluid px-4 mt-4">
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="dashboard.jsp">Dashboard</a></li>
                <li class="breadcrumb-item active">Empleado</li>
            </ol>

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

            <div class="card">
                <div class="card-header"><i class="fas fa-building"></i> Empleado</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <button type="button" class="btn btn-success" onclick="abrirModal();">Nuevo Empleado</button>
                        </div>
                    </div>
                    <hr />
                    <table class="display cell-border" id="tabla" style="width: 100%">
                        <thead>
                            <tr>
                                <th style="width: 200px">Nombre</th>
                                <th style="width: 200px">Apellido</th>
                                <th style="width: 100px">Teléfono</th>
                                <th style="width: 100px">Dni</th>
                                <th style="width: 90px">Cargo</th>
                                <th style="width: 90px">Activo</th>
                                <th style="width: 90px">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" items="${listaEmpleado}">
                                <tr>
                                    <td>${list.getNombre()}</td>
                                    <td>${list.getApellido()}</td>
                                    <td>${list.getTelefono()}</td>
                                    <td>${list.getDni()}</td>
                                    <td>${list.getIdCargo().getNombre()}</td>
                                    <c:if test="${list.getEstado() == 'Si'}">
                                        <td><span class="badge bg-success" id="Tablaestado-${list.getId()}">${list.getEstado()}</span></td>
                                        </c:if>
                                        <c:if test="${list.getEstado() == 'No'}">
                                        <td><span class="badge bg-danger" id="Tablaestado-${list.getId()}">${list.getEstado()}</span></td>
                                        </c:if>
                                    <td>
                                        <a class="btn btn-primary btn-sm" href="Empleado?accion=subEditar&idE=${list.getId()}"><i class="fas fa-pen"></i></a>
                                        <a class="btn btn-danger btn-sm ms-2" id="eliminarVer" onclick="verficarEliminar(${list.getId()}, ${empSeccion});"><i class="fas fa-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="FormModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header bg-dark text-white">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Empleado</h1>
                            <button onclick="limpiar();" type="button" class="btn btn-dark" data-bs-dismiss="modal" aria-label="Close">X</button>
                        </div>
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
                                        <input id="telefono" type="text" class="form-control" required minlength="9" maxlength="9" onkeypress="return soloNumero(event)" name="telefonoEmp" autocomplete="off" value="${objEmp.getTelefono()}">
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Dirección</label>
                                        <input type="text" class="form-control" required name="direccionEmp" id="direccion" autocomplete="off" value="${objEmp.getDireccion()}">
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">DNI</label>
                                        <input type="text" class="form-control" required name="dniEmp" maxlength="8" minlength="8" id="dni" onkeypress="return soloNumero(event)" autocomplete="off" value="${objEmp.getDni()}">
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Correo</label>
                                        <input type="email" class="form-control" required name="correoEmp" id="correo" autocomplete="off" value="${objEmp.getCorreo()}">
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Cargo</label>
                                        <select id="cargo" class="form-select" name="cargo">
                                            <c:if test="${objEmp == null}">
                                                <option selected value="0">--Seleccionar--</option>
                                            </c:if>
                                            <c:if test="${objEmp != null}">
                                                <option value="0">--Seleccionar--</option>
                                            </c:if>
                                            <c:forEach var="list" items="${listaCargo}">
                                                <c:if test="${list.getNombre() == objEmp.getIdCargo().getNombre()}">
                                                    <option selected value="${list.getId()}">${list.getNombre()}</option>
                                                </c:if>
                                                <c:if test="${list.getNombre() != objEmp.getIdCargo().getNombre()}">
                                                    <option value="${list.getId()}">${list.getNombre()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Usuario</label>
                                        <input type="text" class="form-control" required name="userEmp" id="user" autocomplete="off" value="${objEmp.getUser()}">
                                    </div>
                                    <c:if test="${objEmp != null}">
                                        <div class="col-sm-6" id="contraseña">
                                            <label class="form-label">Password</label> <br>
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" value="CambiarPassword" name="cambiarEmp">
                                                <label class="form-check-label" for="flexCheckDefault">
                                                    Cambiar Contraseña
                                                </label>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Activo</label>
                                        <select id="activo" class="form-select" name="estado">
                                            <c:if test="${objEmp == null}">
                                                <option selected value="0">--Seleccionar--</option>
                                                <option value="Si">Activo</option>
                                                <option value="No">Inactivo</option>
                                            </c:if>
                                            <c:if test="${objEmp != null}">
                                                <option value="0">--Seleccionar--</option>
                                                <c:if test="${objEmp.getEstado() == 'Si'}">
                                                    <option selected value="Si">Activo</option>
                                                    <option value="No">Inactivo</option>
                                                </c:if>
                                                <c:if test="${objEmp.getEstado() == 'No'}">
                                                    <option value="Si">Activo</option>
                                                    <option selected value="No">Inactivo</option>
                                                </c:if>
                                            </c:if>
                                        </select>
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
                            <div class="modal-footer">
                                <button onclick="limpiar();" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" name="accion" value="guardar" class="btn btn-primary" onclick="cargarModal()">Guardar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="cargar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">ENVIANDO CORREO...</h1>
                        </div>
                        <div class="modal-body">
                            <div class="centrado"><div class="lds-spinner"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

    <script>
        $(document).ready(function () {
            $('#tabla').DataTable({
                //para cambiar el lenguaje a español
                responsive: true,
                ordering: false,
                "language": {
                    "lengthMenu": "Mostrar _MENU_ registros",
                    "zeroRecords": "No se encontraron resultados",
                    "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                    "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                    "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                    "sSearch": "Buscar:",
                    "oPaginate": {
                        "sFirst": "Primero",
                        "sLast": "Último",
                        "sNext": "Siguiente",
                        "sPrevious": "Anterior"
                    },
                    "sProcessing": "Procesando...",
                },
                "columns": [
                    {},
                    {},
                    {"searchable": false},
                    {},
                    {},
                    {"searchable": false},
                    {
                        "orderable": false,
                        "searchable": false
                    }
                ]
            });

            var nombre = $('#nombre').val();
            if (nombre.length > 0) {
                $('#FormModal').modal("show");
            }

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

        function cargarModal() {
            $('#cargar').modal("show");
            $('#FormModal').hide();
        }
        function abrirModal() {
            $('#FormModal').modal("show");
            limpiar();
        }

        function limpiar() {
            $('#nombre').val("");
            $('#apellido').val("");
            $('#telefono').val("");
            $('#direccion').val("");
            $('#dni').val("");
            $('#user').val("");
            $('#correo').val("");
            $('#cargo').val("0");
            $('#activo').val("0");
            if (document.getElementById('contraseña')) {
                document.getElementById('contraseña').style.display = 'none';
            }
        }

        function verficarEliminar(idEmp, idEmpSecc) {
            let describe = "#Tablaestado-" + idEmp;
            var valores = $(describe).text();

            if (idEmpSecc === idEmp) {
                swal("Usted no puede eliminarse así mismo!!");
            } else {
                if (valores === "Si") {
                    swal({
                        title: "¿Estas seguro de Eliminar?",
                        text: "¿Desea eliminar el empleado selecionado?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonClass: "btn-primary",
                        confirmButtonText: "Si",
                        cancelButtonText: "No",
                        closeOnConfirm: false
                    },
                            function () {
                                let url = "Empleado?accion=eliminar&idEmpleado=" + idEmp;
                                document.location.href = url;
                            });
                } else {
                    swal("Empleado ya elimado!!");
                }
            }
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

        function noAtras() {
            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button"; //chrome necesita esto
            window.onhashchange = function () {
                window.location.hash = "no-back-button";
            };
        }
    </script>
</html>
