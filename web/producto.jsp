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

        <!-- Para dar diseño a text Fecha -->
        <script src="js/jquery-ui-1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="js/jquery-ui-1.12.1/jquery-ui.css" />

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
    <body class="sb-nav-fixed" onload="noAtras()">
        <div class="container-fluid px-4 mt-4">
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="dashboard.jsp">Dashboard</a></li>
                <li class="breadcrumb-item active">Productos</li>
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
                <div class="card-header"><i class="fas fa-building"></i> Productos</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <button type="button" class="btn btn-success" onclick="abrirModal();">Nuevo Producto</button>
                        </div>
                    </div>
                    <hr />
                    <table class="display cell-border" id="tabla" style="width: 100%">
                        <thead>
                            <tr>
                                <th>Descripción</th>
                                <th style="width: 100px">Marca</th>
                                <th style="width: 100px">Precio S/.</th>
                                <th style="width: 100px">Categoria</th>
                                <th style="width: 100px">Caducidad</th>
                                <th style="width: 90px">Stock</th>
                                <th style="width: 90px">Activo</th>
                                <th style="width: 90px">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" items="${listaProducto}">
                                <tr>
                                    <td>${list.getDescripcion()}</td>
                                    <td>${list.getIdMarca().getNombre()}</td>
                                    <td>${list.getPrecio()}</td>
                                    <td>${list.getIdCat().getNombre()}</td>
                                    <td>${list.getFechaCaducidad()}</td>
                                    <td>${list.getStock()}</td>
                                    <c:if test="${list.getEstado() == 'Si'}">
                                        <td><span class="badge bg-success" id="Tablaestado-${list.getId()}">${list.getEstado()}</span></td>
                                        </c:if>
                                        <c:if test="${list.getEstado() == 'No'}">
                                        <td><span class="badge bg-danger" id="Tablaestado-${list.getId()}">${list.getEstado()}</span></td>
                                        </c:if>
                                    <td>
                                        <a class="btn btn-primary btn-sm" href="Producto?accion=subEditar&idP=${list.getId()}"><i class="fas fa-pen"></i></a>
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
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Producto</h1>
                            <button onclick="limpiar();" type="button" class="btn btn-dark" data-bs-dismiss="modal" aria-label="Close">X</button>
                        </div>
                        <form method="POST" action="Producto">
                            <div class="modal-body">
                                <div class="row g-2">
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Descripción</label>
                                        <input id="descripcion" type="text" class="form-control" name="descripcionProd" required autocomplete="off" value="${objProd.getDescripcion()}">
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Precio</label>
                                        <input id="precio" type="number" class="form-control" name="precioProd" required disabled autocomplete="off" value="${objProd.getPrecio()}">
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Stock</label>
                                        <input id="stock" type="number" class="form-control" name="stockProd" required disabled step="1" autocomplete="off" value="${objProd.getStock()}">
                                    </div>
                                    <div class="col-sm-6 ">
                                        <label class="form-label">Caducidad</label>
                                        <input type="text" class="form-control" name="caducaProd" id="txtCaduca" autocomplete="off" value="${objProd.getFechaCaducidad()}">
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Categoría</label>
                                        <select id="categoria" class="form-select" name="categoria">
                                            <c:if test="${objProd == null}">
                                                <option selected value="0">--Seleccionar--</option>
                                            </c:if>
                                            <c:if test="${objProd != null}">
                                                <option value="0">--Seleccionar--</option>
                                            </c:if>
                                            <c:forEach var="list" items="${listaCategoria}">
                                                <c:if test="${list.getNombre() == objProd.getIdCat().getNombre()}">
                                                    <option selected value="${list.getId()}">${list.getNombre()}</option>
                                                </c:if>
                                                <c:if test="${list.getNombre() != objProd.getIdCat().getNombre()}">
                                                    <option value="${list.getId()}">${list.getNombre()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Marca</label>
                                        <select id="marca" class="form-select" name="marca">
                                            <c:if test="${objProd == null}">
                                                <option selected value="0">--Seleccionar--</option>
                                            </c:if>
                                            <c:if test="${objProd != null}">
                                                <option value="0">--Seleccionar--</option>
                                            </c:if>
                                            <c:forEach var="list" items="${listaMarca}">
                                                <c:if test="${list.getNombre() == objProd.getIdMarca().getNombre()}">
                                                    <option selected value="${list.getId()}">${list.getNombre()}</option>
                                                </c:if>
                                                <c:if test="${list.getNombre() != objProd.getIdMarca().getNombre()}">
                                                    <option value="${list.getId()}">${list.getNombre()}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Activo</label>
                                        <select id="activo" class="form-select" name="estado">
                                            <c:if test="${objProd == null}">
                                                <option selected value="0">--Seleccionar--</option>
                                                <option value="Si">Activo</option>
                                                <option value="No">Inactivo</option>
                                            </c:if>
                                            <c:if test="${objProd != null}">
                                                <option value="0">--Seleccionar--</option>
                                                <c:if test="${objProd.getEstado() == 'Si'}">
                                                    <option selected value="Si">Activo</option>
                                                    <option value="No">Inactivo</option>
                                                </c:if>
                                                <c:if test="${objProd.getEstado() == 'No'}">
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
        $("#txtCaduca").datepicker({dateFormat: 'yy-mm-dd'});

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
                    {},
                    {
                        "orderable": false,
                        "searchable": false
                    }
                ]
            });

            var descripcion = $('#descripcion').val();
            if (descripcion.length > 0) {
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
            $('#descripcion').val("");
            $('#precio').val("");
            $('#stock').val("");
            $('#txtCaduca').val("");
            $('#categoria').val("0");
            $('#marca').val("0");
            $('#activo').val("0");
        }

        function verficarEliminar(idProd) {
            let describe = "#Tablaestado-" + idProd;
            var valores = $(describe).text();

            if (valores === "Si") {
                swal({
                    title: "¿Estas seguro de Eliminar?",
                    text: "¿Desea eliminar el producto selecionado?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonClass: "btn-primary",
                    confirmButtonText: "Si",
                    cancelButtonText: "No",
                    closeOnConfirm: false
                },
                        function () {
                            let url = "Producto?accion=eliminar&idProducto=" + idProd;
                            document.location.href = url;
                        });
            } else {
                swal("Producto ya elimado!!");
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
