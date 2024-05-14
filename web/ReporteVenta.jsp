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
                    <li class="breadcrumb-item active">Reporte Venta Diario</li>
                </ol>
            </c:if>

            <div class="card">
                <div class="card-header"><i class="bi bi-wallet-fill"></i> Reporte Venta </div>
                <div class="card-body">
                    <form action="Reporte">
                        <div class="row align-items-end">
                            <div class="col-sm-3">
                                <div class="d-grid mb-2">
                                    <button  type="button"class="btn btn-primary" onclick="abrirModalDia();"> Reporte Diario</button>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="d-grid mb-2">
                                    <button type="button" class="btn btn-primary" onclick="abrirModalRango();">Reporte Rango Fecha</button>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="d-grid mb-2">
                                    <button type="button" class="btn btn-primary" onclick="abrirModalMensual();">Reporte Mensual</button>
                                </div>
                            </div>
                            <div class="col-sm-3">
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
                                <th>Fecha</th>
                                <th>Total S/.</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" items="${lista}">
                                <tr>
                                    <td>${list.getFecha()}</td>
                                    <td>${list.getPagoTotal()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Modal dia -->
            <div class="modal fade" id="FormModalDia" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header bg-dark text-white">
                            <h1 class="modal-title fs-5" id="exampleModalLabel"> Reporte Diario</h1>
                            <button type="button" class="btn btn-dark" data-bs-dismiss="modal" aria-label="Close">X</button>
                        </div>
                        <form method="POST" action="Reporte">
                            <div class="modal-body">
                                <div class="row g-2">
                                    <div class="col-sm-6 requerido">
                                        <label class="form-label">Fecha</label>
                                        <input type="text" class="form-control" name="Fecha" id="txtFecha" onclick="mostrarFechas();">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                    <button type="submit" name="accion" value="buscarRepVentDia" class="btn btn-primary">Buscar Reporte</button>
                                </div> 
                            </div> 
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal Rango -->
        <div class="modal fade" id="FormModalRango" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-dark text-white">
                        <h1 class="modal-title fs-5" id="exampleModalLabel"> Reporte Rango</h1>
                        <button type="button" class="btn btn-dark" data-bs-dismiss="modal" aria-label="Close">X</button>
                    </div>
                    <form method="POST" action="Reporte">
                        <div class="modal-body">
                            <div class="row g-2">
                                <div class="col-sm-6 requerido">
                                    <label class="form-label">Fecha Inicio</label>
                                    <input type="text" class="form-control" name="FechaIni" id="txtinicioFecha" onclick="mostrarFechas();">
                                </div>
                                <div class="col-sm-6 requerido">
                                    <label class="form-label">Fecha Fin</label>
                                    <input type="text" class="form-control" name="FechaFin" id="txtFinFecha" onclick="mostrarFechas();">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" name="accion" value="buscarRepVentRango" class="btn btn-primary">Buscar Reporte</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Mensual -->
    <div class="modal fade" id="FormModalMensual" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-dark text-white">
                    <h1 class="modal-title fs-5" id="exampleModalLabel"> Reporte Mensual</h1>
                    <button type="button" class="btn btn-dark" data-bs-dismiss="modal" aria-label="Close">X</button>
                </div>
                <form method="POST" action="Reporte">
                    <div class="modal-body">
                        <div class="row g-2">
                            <div class="col-sm-6 requerido">
                                <label class="form-label">Mes</label>
                                <input type="text" class="form-control" name="mes" required id="txtMes" onclick="ocultarAnio();">
                            </div>
                            <div class="col-sm-6 requerido">
                                <label class="form-label">Año</label>
                                <input type="text" class="form-control" name="anio" required id="txtAnio" onclick="ocultarMes();">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" name="accion" value="buscarRepVentMensual" class="btn btn-primary">Buscar Reporte</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<script>
    $("#txtFecha").datepicker({dateFormat: 'yy-mm-dd'}).datepicker('setDate', new Date());
    $("#txtinicioFecha").datepicker({dateFormat: 'yy-mm-dd'}).datepicker('setDate', new Date());
    $("#txtFinFecha").datepicker({dateFormat: 'yy-mm-dd'}).datepicker('setDate', new Date());

    const $btnExportar = document.querySelector("#btnExportar"),
            $tabla = document.querySelector("#tabla");

    $btnExportar.addEventListener("click", function () {
        let tableExport = new TableExport($tabla, {
            exportButtons: false, // No queremos botones
            filename: "Reporte de venta diario", //Nombre del archivo de Excel
            sheetname: "Reporte de venta diario" //Título de la hoja
        });
        let datos = tableExport.getExportData();
        let preferenciasDocumento = datos.tabla.xlsx;
        tableExport.export2file(preferenciasDocumento.data, preferenciasDocumento.mimeType, preferenciasDocumento.filename, preferenciasDocumento.fileExtension, preferenciasDocumento.merges, preferenciasDocumento.RTL, preferenciasDocumento.sheetname);
    });
    function abrirModalDia() {
        $('#FormModalDia').modal("show");
    }
    function abrirModalRango() {
        $('#FormModalRango').modal("show");
    }
    function abrirModalMensual() {
        $('#FormModalMensual').modal("show");
    }

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
                {"searchable": false},
                {"searchable": false}
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

    function mostrarFechas() {
        document.querySelector('.ui-datepicker-calendar').style.display = 'block';
    }
</script>
</html>
