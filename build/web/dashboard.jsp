<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>LICORERÍA MONARCA</title>
        <meta charset="UTF-8">
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

        <script src="js/sweetalet.js"></script>
        <link rel="stylesheet" href="css/sweetalert.css" />
        <script src="https://unpkg.com/xlsx@0.16.9/dist/xlsx.full.min.js"></script>
        <script src="https://unpkg.com/file-saverjs@latest/FileSaver.min.js"></script>
        <script src="https://unpkg.com/tableexport@latest/dist/js/tableexport.min.js"></script>
        
        <style>
            .requerido label:after {
                content: " *";
                color: red;
            }
        </style>
    </head>
    <body class="sb-nav-fixed" onload="noAtras()">
        <div class="container-fluid px-4 mt-4">
            <c:if test="${error != null}">
                <div class="alert alert-danger mt-4" id="mensajeEmergente">
                    <strong>Danger!</strong> ${error}
                </div>
            </c:if>

            <h1 class="mt-4">Dashboard</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">Dashboard</li>
            </ol>
            <div class="row">
                <div class="col-xl-3 col-md-6">
                    <div class="card bg-success text-white mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-9">
                                    <h6>Cantidad Proveedores</h6>
                                    <h6>${proveedor}</h6>
                                </div>
                                <div class="col-sm-3">
                                    <i class="fas fa-users fa-2x"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6">
                    <div class="card bg-warning text-white mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-9">
                                    <h6>Cantidad Ventas</h6>
                                    <h6>${venta}</h6>
                                </div>
                                <div class="col-sm-3">
                                    <i class="fa-solid fa-bag-shopping fa-2x"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6">
                    <div class="card bg-secondary text-white mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-9">
                                    <h6>Cantidad Productos</h6>
                                    <h6>${producto}</h6>
                                </div>
                                <div class="col-sm-3">
                                    <i class="fa-solid fa-boxes-stacked fa-2x"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fa-solid fa-tags"></i> Historial de Ventas
                </div>
                <div class="card-body">
                    <form action="Dashboard">
                        <div class="row align-items-end">
                            <div class="col-sm-2">
                                <div class="mb-2 requerido">
                                    <label class="form-label">Fecha de Inicio</label>
                                    <input type="text" class="form-control" required name="fechaInicio" id="txtInicioFecha">
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <div class="mb-2 requerido">
                                    <label class="form-label">Fecha Fin</label>
                                    <input type="text" class="form-control" required name="fechaFin" id="txtFinFecha">
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <div class="mb-2 requerido">
                                    <label class="form-label">Empleado</label>
                                    <select class="form-select" name="empleado" id="txtEmpleado">
                                        <c:forEach var="list" items="${listaEmpleado}">
                                            <option value="${list.getId()}">${list.getNombre()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <div class="d-grid mb-2">
                                    <button class="btn btn-primary" type="submit" name="accion" value="buscar"><i class="fa-solid fa-magnifying-glass"></i> Buscar</button>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <div class="d-grid mb-2">
                                    <button class="btn btn-success" type="button"><i class="fa-solid fa-file-excel" onclick="exportExcel();"></i> Exportar</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <hr />
                    <div class="row">
                        <div class="col-sm-12">
                            <table class="display cell-border" id="tabla" style="width: 100%">
                                <thead>
                                    <tr>
                                        <th>Ticket</th>
                                        <th>Fecha</th>
                                        <th>Pago</th>
                                        <th>Descuento</th>
                                        <th>Empleado</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="list" items="${lista}">
                                        <tr>
                                            <td>${list.getNroTicket()}</td>
                                            <td>${list.getFecha()}</td>
                                            <td>${list.getPagoTotal()}</td>
                                            <td>${list.getDescuento()}</td>
                                            <td>${list.getIdEmp().getNombre()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        $("#txtFinFecha").datepicker({dateFormat: 'yy-mm-dd'}).datepicker('setDate', new Date());
        $("#txtInicioFecha").datepicker({dateFormat: 'yy-mm-dd'}).datepicker('setDate', new Date());

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
                    {}
                ]
            });
        });

        function noAtras() {
            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button"; //chrome necesita esto
            window.onhashchange = function () {
                window.location.hash = "no-back-button";
            };
        }

        function exportExcel() {
            $tabla = document.querySelector("#tabla");
            let tableExport = new TableExport($tabla, {
                exportButtons: false, // No queremos botones
                filename: "Reporte Ventas", //Nombre del archivo de Excel
                sheetname: "Reporte Ventas", //Título de la hoja
            });
            let datos = tableExport.getExportData();
            let preferenciasDocumento = datos.tabla.xlsx;
            tableExport.export2file(preferenciasDocumento.data, preferenciasDocumento.mimeType, preferenciasDocumento.filename, preferenciasDocumento.fileExtension, preferenciasDocumento.merges, preferenciasDocumento.RTL, preferenciasDocumento.sheetname);
        }

    </script>
</html>
