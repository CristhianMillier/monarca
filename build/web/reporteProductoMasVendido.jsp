<%-- 
    Document   : reporteProductoMasVendido
    Created on : 25/06/2023, 11:04:49 PM
    Author     : USUARIO
--%>

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

        <script src="https://unpkg.com/xlsx@0.16.9/dist/xlsx.full.min.js"></script>
        <script src="https://unpkg.com/file-saverjs@latest/FileSaver.min.js"></script>
        <script src="https://unpkg.com/tableexport@latest/dist/js/tableexport.min.js"></script>

        <style>
            .requerido label:after {
                content: " *";
                color: red;
            }

            .ui-datepicker-calendar {
                display: none;
            }
        </style>
    </head>
    <body class="sb-nav-fixed" onload="noAtras()">
        <div class="container-fluid px-4 mt-4">
            <c:if test="${cargo == 'Administrador'}">
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="dashboard.jsp">Dashboard</a></li>
                    <li class="breadcrumb-item active">Reporte Producto</li>
                </ol>
            </c:if>

            <div class="card">
                <div class="card-header"><i class="bi bi-wallet-fill"></i> Reporte Producto mas vendido  Mensual</div>
                <div class="card-body">
                    <form action="Reporte">
                        <div class="row align-items-end">
                            <div class="col-sm-2">
                                <div class="mb-2 requerido">
                                    <label class="form-label">Mes</label>
                                    <input type="text" class="form-control" name="mes" required id="txtMes" onclick="ocultarAnio();">
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <div class="mb-2 requerido">
                                    <label class="form-label">Año</label>
                                    <input type="text" class="form-control" name="anio" required id="txtAnio" onclick="ocultarMes();">
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <div class="d-grid mb-2">
                                    <button class="btn btn-primary" type="submit" name="accion" value="buscarRepProducto"><i class="fa-solid fa-magnifying-glass"></i> Buscar</button>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <div class="d-grid mb-2">
                                    <button class="btn btn-success" type="button" id="btnExportar"><i class="fa-solid fa-file-excel"></i> Exportar</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <hr />
                    <table class="display cell-border" id="tabla" style="width: 100%">
                        <thead>
                            <tr>
                                <th>Descripcion</th>
                                <th>Marca</th>
                                <th style="width: 150px">PrecioVenta S/. </th>
                                <th style="width: 150px">Cantidad Vendida</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" items="${lista}">
                                <tr>
                                    <td>${list.getDescripcion()}</td>
                                    <td>${list.getMarca()}</td>
                                    <td>${list.getPrecioVenta()}</td>
                                    <td>${list.getCantidadVenta()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>

    <script>
        const $btnExportar = document.querySelector("#btnExportar"),
                $tabla = document.querySelector("#tabla");

        $btnExportar.addEventListener("click", function () {
            let tableExport = new TableExport($tabla, {
                exportButtons: false, // No queremos botones
                filename: "Reporte Prod Mas Vendido", //Nombre del archivo de Excel
                sheetname: "Reporte Prod Mas Vendido", //Título de la hoja
            });
            let datos = tableExport.getExportData();
            let preferenciasDocumento = datos.tabla.xlsx;
            tableExport.export2file(preferenciasDocumento.data, preferenciasDocumento.mimeType, preferenciasDocumento.filename, preferenciasDocumento.fileExtension, preferenciasDocumento.merges, preferenciasDocumento.RTL, preferenciasDocumento.sheetname);
        });

        $("#txtMes").datepicker({
            changeMonth: true,
            changeYear: false,
            showButtonPanel: false,
            dateFormat: 'mm',
            onClose: function (dateText, inst) {
                $(this).datepicker('setDate', new Date(1, inst.selectedMonth, 1));
            }
        });

        $("#txtAnio").datepicker({
            changeMonth: false,
            changeYear: true,
            showButtonPanel: false,
            dateFormat: 'yy',
            onClose: function (dateText, inst) {
                $(this).datepicker('setDate', new Date(inst.selectedYear, 1, 1));
            }
        });

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

        function ocultarMes() {
            document.querySelector('.ui-datepicker-month').style.display = 'none';
        }
        function ocultarAnio() {
            document.querySelector('.ui-datepicker-year').style.display = 'none';
        }
    </script>
</html>
