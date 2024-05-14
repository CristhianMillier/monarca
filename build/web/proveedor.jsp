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
        </style>

    </head>
    <body class="sb-nav-fixed" onload="noAtras();">
        <div class="container-fluid px-4 mt-4">
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="dashboard.jsp">Dashboard</a></li>
                <li class="breadcrumb-item active">Proveedor</li>
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
                <div class="card-header"><i class="fas fa-building"></i> Proveedor</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <button type="button" class="btn btn-success" onclick="abrirModal();">Nuevo Proveedor</button>
                        </div>
                    </div>
                    <hr />
                    <table class="display cell-border" id="tabla" style="width: 100%">
                        <thead>
                            <tr>
                                <th style="width: 200px">Razon Social</th>
                                <th style="width: 100px">Teléfono</th>
                                <th style="width: 100px">Ruc</th>
                                <th style="width: 90px">Activo</th>
                                <th style="width: 90px">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" items="${listaProveedor}">
                                <tr>
                                    <td>${list.getRazoSocial()}</td>
                                    <td>${list.getTelefono()}</td>
                                    <td>${list.getRuc()}</td>

                                    <c:if test="${list.getEstado() == 'Si'}">
                                        <td><span class="badge bg-success" id="Tablaestado-${list.getId()}">${list.getEstado()}</span></td>
                                        </c:if>
                                        <c:if test="${list.getEstado() == 'No'}">
                                        <td><span class="badge bg-danger" id="Tablaestado-${list.getId()}">${list.getEstado()}</span></td>
                                        </c:if>
                                    <td>
                                        <a class="btn btn-primary btn-sm" href="Proveedor?accion=subEditar&idP=${list.getId()}"><i class="fas fa-pen"></i></a>
                                        <a class="btn btn-danger btn-sm ms-2" id="eliminarVer" onclick="verficarEliminar(${list.getId()});"><i class="fas fa-trash"></i></a>
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
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Proveedor</h1>
                            <button onclick="limpiar();" type="button" class="btn btn-dark" data-bs-dismiss="modal" aria-label="Close">X</button>
                        </div>
                        <form method="POST" action="Proveedor">
                            <div class="modal-body">
                                <div class="row g-2">
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Razon Social</label>
                                        <input id="razonSocial" type="text" class="form-control" onkeypress="return soloLetras(event)" name="RazonProve" required autocomplete="off" value="${objPro.getRazoSocial()}">
                                    </div>

                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Teléfono</label>
                                        <input id="telefono" type="text" class="form-control" required minlength="9" maxlength="9" onkeypress="return soloNumero(event)" name="telefonoPro" autocomplete="off" value="${objPro.getTelefono()}">
                                    </div>

                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Ruc</label>
                                        <input type="text" class="form-control" required name="rucPro" maxlength="11" minlength="11" id="ruc" onkeypress="return soloNumero(event)" autocomplete="off" value="${objPro.getRuc()}">
                                    </div>

                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Activo</label>
                                        <select id="activo" class="form-select" name="estado">
                                            <c:if test="${objPro == null}">
                                                <option selected value="0">--Seleccionar--</option>
                                                <option value="Si">Activo</option>
                                                <option value="No">Inactivo</option>
                                            </c:if>
                                            <c:if test="${objPro != null}">
                                                <option value="0">--Seleccionar--</option>
                                                <c:if test="${objPro.getEstado() == 'Si'}">
                                                    <option selected value="Si">Activo</option>
                                                    <option value="No">Inactivo</option>
                                                </c:if>
                                                <c:if test="${objPro.getEstado() == 'No'}">
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
                                <button type="submit" name="accion" value="guardar" class="btn btn-primary">Guardar</button>
                            </div>
                        </form>
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
                    {"searchable": false},
                    {"searchable": false},
                    {"searchable": false},
                    {"searchable": false}
                ]
            });

            var razonSocial = $('#razonSocial').val();
            if (razonSocial.length > 0) {
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

        function abrirModal() {
            $('#FormModal').modal("show");
            limpiar();
        }

        function limpiar() {
            $('#razonSocial').val("");
            $('#telefono').val("");
            $('#ruc').val("");
            $('#activo').val(0);
        }

        function verficarEliminar(idProv) {
            let describe = "#Tablaestado-" + idProv;
            var valores = $(describe).text();

            if (valores === "Si") {
                swal({
                    title: "¿Estas seguro de Eliminar?",
                    text: "¿Desea eliminar la marca selecionada?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonClass: "btn-primary",
                    confirmButtonText: "Si",
                    cancelButtonText: "No",
                    closeOnConfirm: false
                },
                        function () {
                            let url = "Proveedor?accion=eliminar&idProveedor=" + idProv;
                            document.location.href = url;
                        });
            } else {
                swal("Proveedor ya elimado!!");
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
