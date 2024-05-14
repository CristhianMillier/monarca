<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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
            <c:if test="${cargo == 'Administrador'}">
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="dashboard.jsp">Dashboard</a></li>
                    <li class="breadcrumb-item active">Venta</li>
                </ol>
            </c:if>

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
            <c:if test="${verModal != null}">
                <div id="trueModal">
                </div>
            </c:if>

            <div class="card">
                <div class="card-header"><i class="bi bi-receipt"></i> Venta</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <button type="button" class="btn btn-success" onclick="abrirModal();">Nueva Venta</button>
                        </div>
                    </div>
                    <hr />
                    <table class="display cell-border" id="tabla" style="width: 100%">
                        <thead>
                            <tr>
                                <th>Nro. Ticket</th>
                                <th>Fecha</th>
                                <th>Pago S/.</th>
                                <th>Descuento</th>
                                <th>Empleado</th>
                                <th style="width: 90px">Activo</th>
                                <th style="width: 150px">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" items="${listaVenta}">
                                <tr>
                                    <td>${list.getNroTicket()}</td>
                                    <td>${list.getFecha()}</td>
                                    <td>${list.getPagoTotal()}</td>
                                    <td>${list.getDescuento()}</td>
                                    <td>${list.getIdEmp().getApellido()}</td>
                                    <c:if test="${list.getEstado() == 'Si'}">
                                        <td><span class="badge bg-success">${list.getEstado()}</span></td>
                                        </c:if>
                                        <c:if test="${list.getEstado() == 'No'}">
                                        <td><span class="badge bg-danger">${list.getEstado()}</span></td>
                                        </c:if>
                                    <td>
                                        <a class="btn btn-primary btn-sm" href="Venta?accion=subEditar&idV=${list.getId()}"><i class="fas fa-pen"></i></a>
                                        <a class="btn btn-danger btn-sm ms-2" id="eliminarVer" onclick="verficarEliminar(${list.getId()});"><i class="fas fa-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade modal-xl" id="FormModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header bg-dark text-white">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Venta</h1>
                            <button onclick="limpiar();" type="button" class="btn btn-dark" data-bs-dismiss="modal" aria-label="Close">X</button>
                        </div>
                        <form method="POST" action="Venta">
                            <div class="modal-body">
                                <div class="row g-2">
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Nro. Ticket</label>
                                        <input id="nroTicket" type="text" disabled class="form-control" name="nroTicket" required autocomplete="off" value="${ticketNro}">
                                    </div>
                                    <div class="col-sm-6 ">
                                        <label class="form-label">Descuento (S/.)</label>
                                        <input type="number" class="form-control" name="descuento" id="descuento" autocomplete="off" value="${objVent.getDescuento()}">
                                    </div>
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Fecha</label>
                                        <input type="text" class="form-control" required name="fecha" id="fecha" autocomplete="off" value="${objVent.getFecha()}">
                                    </div>
                                </div>
                                <div class="card mt-3">
                                    <div class="card-header">Datos Producto a Vender</div>
                                    <div class="card-body">
                                        <div class="row g-2">
                                            <div class="col-sm-6">
                                                <label class="form-label">Producto</label>
                                                <select id="producto" class="form-select" name="producto" onchange="mostrarArray();">
                                                    <c:if test="${ProductoV == null}">
                                                        <option selected value="0">--Seleccionar--</option>
                                                    </c:if>
                                                    <c:if test="${ProductoV != null}">
                                                        <option value="0">--Seleccionar--</option>
                                                    </c:if>
                                                    <c:forEach var="list" items="${listaProducto}">
                                                        <c:if test="${list.comboProd() == ProductoV.comboProd()}">
                                                            <option selected value="${list.getId()}">${list.comboProd()}</option>
                                                        </c:if>
                                                        <c:if test="${list.comboProd() != ProductoV.comboProd()}">
                                                            <option value="${list.getId()}">${list.comboProd()}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="form-label">Precio (S/.)</label>
                                                <input type="text" name="precio" id="precio" value="${ProductoV.getPrecio()}" class="form-control" placeholder="S/. 0.00" readonly/>
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="form-label">Cantidad a Vender</label>
                                                <input type="number" name="cantidad" value="1" class="form-control" id="cantidad" placeholder="0"/>
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="form-label">Stock</label>
                                                <div class="form-group d-flex">
                                                    <div class="col-sm-6 d-flex">
                                                        <input type="text" name="stock" id="stock" value="${ProductoV.getStock()}" class="form-control" readonly/>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <button type="submit" name="accion" value="agregar" class="btn btn-success" style="margin-left: 15px">Agregar</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${errorMsj != null}">
                                    <div class="row mt-2" id="mensajeEror">
                                        <div class="col-12">
                                            <div class="alert alert-danger" role="alert">
                                                ${errorMsj}
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="card mt-3">
                                    <div class="card-header">Listado de Productos</div>
                                    <div class="card-body">
                                        <table class="display cell-border" id="tabla2" style="width: 100%">
                                            <thead>
                                                <tr>
                                                    <th>Descripción</th>
                                                    <th>Precio S/.</th>
                                                    <th>Cantidad</th>
                                                    <th>SubTotal</th>
                                                    <th style="width: 90px">Acciones</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="list" items="${listaDetVenta}">
                                                    <tr>
                                                        <td>${list.getIdProd().comboProd()}</td>
                                                        <td>${list.getIdProd().getPrecio()}</td>
                                                        <td>${list.getCantidad()}</td>
                                                        <td>${list.getSubTotal()}</td>
                                                        <td>
                                                            <a class="btn btn-primary btn-sm" href="Venta?accion=editarCarrito&idProdE=${list.getId()}"><i class="fas fa-pen"></i></a>
                                                            <a class="btn btn-danger btn-sm ms-2" href="Venta?accion=elimCarrito&idProdE=${list.getId()}"><i class="fas fa-trash"></i></a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="card-fotter d-flex" style="padding: 15px">
                                        <div class="col-sm-6">
                                            <b>Total a Pagar (S/.):</b>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="text" name="txtPago" id="pago" value="${pago}" class="form-control" readonly/>
                                        </div>
                                    </div>
                                </div>                
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
        $("#fecha").datepicker({dateFormat: 'yy-mm-dd'}).datepicker('setDate', new Date());

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
                    {"searchable": false},
                    {"searchable": false},
                    {},
                    {
                        "orderable": false,
                        "searchable": false
                    }
                ]
            });

            $('#tabla2').DataTable({
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
                    {
                        "orderable": false,
                        "searchable": false
                    }
                ]
            });

            if (document.getElementById('trueModal')) {
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
        }

        function limpiar() {
            $('#descuento').val("");
            $('#producto').val(0);
            $('#cantidad').val(1);
            $('#stock').val("");
            $('#precio').val("");
            $("#tabla2  tbody").remove();
            if (document.getElementById('mensajeEror')) {
                document.getElementById('mensajeEror').style.display = "none";
            }
            let url = "Venta?accion=limpiar";
            document.location.href = url;
        }

        function noAtras() {
            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button"; //chrome necesita esto
            window.onhashchange = function () {
                window.location.hash = "no-back-button";
            };
        }

        function mostrarArray() {
            var idProd = document.getElementById("producto");
            var descuento = document.getElementById("descuento");
            var fecha = document.getElementById("fecha");
            let url = "Venta?accion=cargarDatos&idProducto=" + idProd.options[idProd.selectedIndex].value
                    + "&descuento=" + descuento.value + "&fecha=" + fecha.value;
            document.location.href = url;
        }

        function verficarEliminar(idVenta) {
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
                        let url = "Venta?accion=eliminar&idV=" + idVenta;
                        document.location.href = url;
                    });
        }
    </script>
</html>
