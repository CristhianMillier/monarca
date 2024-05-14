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

        <!-- Para dar diseño a tabla -->
        <script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" 
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/jquery.dataTables.css" />
        <link rel="stylesheet" href="css/responsive.dataTables.css" />
        <script src="js/jquery.dataTables.js"></script>
        <script src="js/dataTables.responsive.js"></script>

        <script src="js/sweetalet.js"></script>
        <link rel="stylesheet" href="css/sweetalert.css" />

        <script src="https://unpkg.com/xlsx@0.16.9/dist/xlsx.full.min.js"></script>
        <script src="https://unpkg.com/file-saverjs@latest/FileSaver.min.js"></script>
        <script src="https://unpkg.com/tableexport@latest/dist/js/tableexport.min.js"></script>

        <style>
            .sa-button-container {
                display: none;
            }
        </style>
    </head>
    <body class="sb-nav-fixed" onload="noAtras()" style="overflow: hidden">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <div class="navbar-brand ps-3">Licorería Monarca</div>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto me-0 me-md-3 my-2 my-md-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="Empleado?accion=user">${objEmp.getNombre()} ${objEmp.getApellido()}</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="index.jsp">Cerrar Sección</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <c:if test="${objEmp.getIdCargo().getNombre() == 'Administrador'}">
                                <div class="sb-sidenav-menu-heading">Resumen</div>
                                <a target="myFrame" class="nav-link" href="Dashboard?accion=listar" id="cargarDefecto">
                                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                    Dashboard
                                </a>
                                <div class="sb-sidenav-menu-heading">Mantenimiento</div>
                                <a target="myFrame" class="nav-link" href="Categoria?accion=listar">
                                    <div class="sb-nav-link-icon"><i class="bi bi-tags-fill"></i></div>
                                    Categoría
                                </a>
                                <a target="myFrame" class="nav-link" href="Empleado?accion=listar">
                                    <div class="sb-nav-link-icon"><i class="bi bi-person-lines-fill"></i></div>
                                    Empleado
                                </a>
                                <a target="myFrame" class="nav-link" href="Marca?accion=listar">
                                    <div class="sb-nav-link-icon"><i class="bi bi-bookmark-star-fill"></i></div>
                                    Marca
                                </a>
                                <a target="myFrame" href="Producto?accion=listar" class="nav-link">
                                    <div class="sb-nav-link-icon"><i class="fa-solid fa-boxes-stacked"></i></div>
                                    Productos
                                </a>
                                <a target="myFrame" class="nav-link" href="Proveedor?accion=listar">
                                    <div class="sb-nav-link-icon"><i class="fas fa-users"></i></div>
                                    Proveedores
                                </a>
                                <div class="sb-sidenav-menu-heading">Operaciones</div>
                                <a target="myFrame" class="nav-link" href="Compra?accion=listar&idEmpleado=${objEmp.getId()}">
                                    <div class="sb-nav-link-icon"><i class="bi bi-bag-fill"></i></div>
                                    Compra
                                </a>
                                <a target="myFrame" class="nav-link" href="Venta?accion=listar&idEmpleado=${objEmp.getId()}&cargo=${objEmp.getIdCargo().getNombre()}">
                                    <div class="sb-nav-link-icon"><i class="bi bi-receipt"></i></div>
                                    Venta
                                </a>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                    <div class="sb-nav-link-icon"><i class="bi bi-file-earmark-spreadsheet"></i></i></div>
                                    Reportes
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a target="myFrame" class="nav-link" href="Reporte?accion=compra&cargo=${objEmp.getIdCargo().getNombre()}">Compras</a>
                                        <a target="myFrame" class="nav-link" href="Reporte?accion=producto&cargo=${objEmp.getIdCargo().getNombre()}">Productos</a>
                                        <a target="myFrame" class="nav-link" href="Reporte?accion=Venta&cargo=${objEmp.getIdCargo().getNombre()}">Ventas</a>
                                    </nav>
                                </div>
                                <div class="sb-sidenav-menu-heading">CONFIGURACIÓN</div>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                    <div class="sb-nav-link-icon"><i class="bi bi-info-square-fill"></i></i></div>
                                    Ayuda
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="#" onclick="manualBD();">Diccionario de Datos</a>
                                        <a class="nav-link" href="#" onclick="manualUser();">Manual de Usuario</a>
                                        <a class="nav-link" href="#" onclick="manualConf();">Manual de Configuración</a>
                                    </nav>
                                </div>
                            </c:if>
                            <c:if test="${objEmp.getIdCargo().getNombre() == 'Vendedor'}">
                                <div class="sb-sidenav-menu-heading">Operaciones</div>
                                <a target="myFrame" class="nav-link" href="Venta?accion=listar&idEmpleado=${objEmp.getId()}&cargo=${objEmp.getIdCargo().getNombre()}" id="cargarDefecto">
                                    <div class="sb-nav-link-icon"><i class="bi bi-receipt"></i></div>
                                    Venta
                                </a>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                    <div class="sb-nav-link-icon"><i class="bi bi-file-earmark-spreadsheet"></i></i></div>
                                    Reportes
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="Reporte?accion=compra&cargo=${objEmp.getIdCargo().getNombre()}">Compras</a>
                                        <a class="nav-link" href="Reporte?accion=producto&cargo=${objEmp.getIdCargo().getNombre()}">Productos</a>
                                        <a class="nav-link" href="Reporte?accion=Venta&cargo=${objEmp.getIdCargo().getNombre()}">Ventas</a>
                                    </nav>
                                </div>
                                <div class="sb-sidenav-menu-heading">CONFIGURACIÓN</div>
                                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                    <div class="sb-nav-link-icon"><i class="bi bi-info-square-fill"></i></i></div>
                                    Ayuda
                                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                </a>
                                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="#" onclick="manualBD();">Diccionario de Datos</a>
                                        <a class="nav-link" href="#" onclick="manualUser();">Manual de Usuario</a>
                                        <a class="nav-link" href="#" onclick="manualConf();">Manual de Configuración</a>
                                    </nav>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="sb-sidenav-footer">
                        <div class="small">Conectado como:</div>
                        ${objEmp.getIdCargo().getNombre()}
                    </div>
                </nav>
            </div>

            <div id="layoutSidenav_content">
                <div class="container-fluid px-4" style="height: 100%; width:100%">
                    <iframe  name="myFrame" style="height: 100%; width:100%; border: none "></iframe> 
                </div>
            </div>
        </div>

        <c:if test="${existeCaduc != null || existeAgot != null}">
            <div class="modal fade modal-xl" id="alertModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header bg-danger text-white">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">MENSAJE DE ALERTA</h1>
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal" aria-label="Close">X</button>
                        </div>
                        <div class="modal-body">
                            <c:if test="${existeAgot != null}">
                                <c:if test="${objEmp.getIdCargo().getNombre() == 'Administrador'}">
                                    <div class="card mb-4">
                                        <div class="card-header"><h1 class="modal-title fs-5 text-center text-danger" id="exampleModalLabel">Los Siguientes productos estan por agotarse</h1></div>
                                        <div class="row g-2 card-body">
                                            <table class="display cell-border" id="tabla" style="width: 100%" >
                                                <thead>
                                                    <tr>
                                                        <th>Descripción</th>
                                                        <th style="max-width: 80px">Categoria</th>
                                                        <th style="max-width: 30px">Stock</th>
                                                        <th style="max-width: 150px">WhatsApp</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="list" items="${listaProdAgotarse}">
                                                        <tr>
                                                            <td id="describe-${list.getId()}">${list.comboProd()}</td>
                                                            <td>${list.getIdCat().getNombre()}</td>
                                                            <td>${list.getStock()}</td>
                                                            <td>
                                                                <div class="form-group row">
                                                                    <div class="col-sm-4 d-flex">
                                                                        <input type="number" placeholder="Cant." class="form-control" required id="${list.getId()}" autocomplete="off" step="1">
                                                                    </div>
                                                                    <div class="col-sm-4">
                                                                        <select id="tipo-${list.getId()}" class="form-select">
                                                                            <option selected value="seleccion">Selección</option>
                                                                            <option value="cajas">Cajas</option>
                                                                            <option value="paquetes">Paquetes</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-sm-3">
                                                                        <button class="btn btn-success" type="button" id="envioWhatsApp" onclick="envioWhatsApp(${list.getNroProv()}, ${list.getId()});"><i class="bi bi-whatsapp"></i></button>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                                            <button class="btn btn-success" type="button" id="btnExportar" onclick="exportAgotarse();"><i class="fa-solid fa-file-excel"></i> Exportar</button>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${objEmp.getIdCargo().getNombre() == 'Vendedor'}">
                                    <div class="card mb-4">
                                        <div class="card-header"><h1 class="modal-title fs-5 text-center text-danger" id="exampleModalLabel">Los Siguientes productos estan por agotarse</h1></div>
                                        <div class="row g-2 card-body">
                                            <table class="display cell-border" id="tabla3" style="width: 100%" >
                                                <thead>
                                                    <tr>
                                                        <th>Descripción</th>
                                                        <th style="width: 100px">Marca</th>
                                                        <th style="width: 100px">Categoria</th>
                                                        <th style="width: 100px">Caducidad</th>
                                                        <th style="width: 90px">Stock</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="list" items="${listaProdAgotarse}">
                                                        <tr>
                                                            <td>${list.getDescripcion()}</td>
                                                            <td>${list.getIdMarca().getNombre()}</td>
                                                            <td>${list.getIdCat().getNombre()}</td>
                                                            <td>${list.getFechaCaducidad()}</td>
                                                            <td>${list.getStock()}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                                            <button class="btn btn-success" type="button" id="btnExportar3" onclick="exportAgotarse2();"><i class="fa-solid fa-file-excel"></i> Exportar</button>
                                        </div>
                                    </div>
                                </c:if>
                            </c:if>

                            <c:if test="${existeCaduc != null}">
                                <div class="card">
                                    <div class="card-header"><h1 class="modal-title fs-5 text-center text-danger" id="exampleModalLabel">Los Siguientes productos estan por caducar</h1></div>
                                    <div class="row g-2 card-body">
                                        <table class="display cell-border" id="tabla2" style="width: 100%" >
                                            <thead>
                                                <tr>
                                                    <th>Descripción</th>
                                                    <th style="width: 100px">Marca</th>
                                                    <th style="width: 100px">Categoria</th>
                                                    <th style="width: 100px">Caducidad</th>
                                                    <th style="width: 90px">Stock</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="list" items="${listaProdCaduc}">
                                                    <tr>
                                                        <td>${list.getDescripcion()}</td>
                                                        <td>${list.getIdMarca().getNombre()}</td>
                                                        <td>${list.getIdCat().getNombre()}</td>
                                                        <td>${list.getFechaCaducidad()}</td>
                                                        <td>${list.getStock()}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                                        <button class="btn btn-success" type="button" id="btnExportar2" onclick="exportCaduca();"><i class="fa-solid fa-file-excel"></i> Exportar</button>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </body>

    <script>
        window.onload = function () {
            document.querySelector('#cargarDefecto').click();
        }

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
                    {"searchable": false}
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
                    {},
                    {},
                    {"searchable": false},
                    {"searchable": false}
                ]
            });

            $('#tabla3').DataTable({
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
                    {},
                    {"searchable": false},
                    {"searchable": false}
                ]
            });

            if (document.getElementById('alertModal')) {
                $('#alertModal').modal("show");
            }
        });

        function noAtras() {
            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button"; //chrome necesita esto
            window.onhashchange = function () {
                window.location.hash = "no-back-button";
            };
        }

        function desargarAlerta() {
            var url = "Empleado?accion=descargar";
            document.location.href = url;
        }

        function exportAgotarse() {
            $tabla = document.querySelector("#tabla");
            let tableExport = new TableExport($tabla, {
                exportButtons: false, // No queremos botones
                filename: "Prodcutos por Agotarse", //Nombre del archivo de Excel
                sheetname: "Prodcutos por Agotarse", //Título de la hoja
            });
            let datos = tableExport.getExportData();
            let preferenciasDocumento = datos.tabla.xlsx;
            tableExport.export2file(preferenciasDocumento.data, preferenciasDocumento.mimeType, preferenciasDocumento.filename, preferenciasDocumento.fileExtension, preferenciasDocumento.merges, preferenciasDocumento.RTL, preferenciasDocumento.sheetname);
        }

        function exportAgotarse2() {
            $tabla3 = document.querySelector("#tabla3");
            let tableExport = new TableExport($tabla3, {
                exportButtons: false, // No queremos botones
                filename: "Prodcutos por Agotarse", //Nombre del archivo de Excel
                sheetname: "Prodcutos por Agotarse", //Título de la hoja
            });
            let datos = tableExport.getExportData();
            let preferenciasDocumento = datos.tabla3.xlsx;
            tableExport.export2file(preferenciasDocumento.data, preferenciasDocumento.mimeType, preferenciasDocumento.filename, preferenciasDocumento.fileExtension, preferenciasDocumento.merges, preferenciasDocumento.RTL, preferenciasDocumento.sheetname);
        }

        function exportCaduca() {
            $tabla2 = document.querySelector("#tabla2");
            let tableExport = new TableExport($tabla2, {
                exportButtons: false, // No queremos botones
                filename: "Productos proximos a vencer", //Nombre del archivo de Excel
                sheetname: "Productos proximos a vencer", //Título de la hoja
            });
            let datos = tableExport.getExportData();
            let preferenciasDocumento = datos.tabla2.xlsx;
            tableExport.export2file(preferenciasDocumento.data, preferenciasDocumento.mimeType, preferenciasDocumento.filename, preferenciasDocumento.fileExtension, preferenciasDocumento.merges, preferenciasDocumento.RTL, preferenciasDocumento.sheetname);
        }

        function envioWhatsApp(nroProv, idProd) {
            let id = "#" + idProd;
            let describe = "#describe-" + idProd;
            let combo = "#tipo-" + idProd;

            var cant = $(id).val();
            var texto = $(describe).text();
            var tipo = $(combo).val();

            if (cant === '') {
                $('#alertModal').hide();
                swal("Ingrese cantidad a comprar del producto!");
                var timeLeft = 5;
                var timerId = setInterval(countdown, 500);
                function countdown() {
                    if (timeLeft === -1) {
                        clearTimeout(timerId);
                    } else {
                        if (timeLeft === 1) {
                            document.querySelector(".sweet-overlay").style.display = 'none';
                            document.querySelector(".sweet-alert").style.display = 'none';
                            $('#alertModal').show();
                        }
                        timeLeft--;
                    }
                }
            }

            if (tipo === 'seleccion') {
                $('#alertModal').hide();
                swal("No ha seleccionado la medida de compra del producto (caja o paquete)!");
                var timeLeft = 5;
                var timerId = setInterval(countdown, 500);
                function countdown() {
                    if (timeLeft === -1) {
                        clearTimeout(timerId);
                    } else {
                        if (timeLeft === 1) {
                            document.querySelector(".sweet-overlay").style.display = 'none';
                            document.querySelector(".sweet-alert").style.display = 'none';
                            $('#alertModal').show();
                        }
                        timeLeft--;
                    }
                }
            }

            if (cant !== '' && tipo !== 'seleccion') {
                let url = "https://api.whatsapp.com/send?phone=" + nroProv + "&text=Deseo comprar el siguiente producto: " + texto + ", cantidad " + cant + " " + tipo;
                window.open(url, 'WhatsApp');
            }
        }

        function manualUser() {
            let url = "https://drive.google.com/file/d/1LT1uCowTnETjVVDH7x0Xz3tXdv06srTq/view";
            window.open(url, 'Manual de Usuario');
        }

        function manualConf() {
            let url = "https://drive.google.com/file/d/1P6RplHvqhcTOd_X8lYZXDkOduL-7xTpM/view";
            window.open(url, 'Manual de Configuración');
        }

        function manualBD() {
            let url = "https://drive.google.com/file/d/1Au9exoVwwipa3F0vtbNX6D8BSApH6OuR/view";
            window.open(url, 'Diccionario de Datos');
        }
    </script>
</html>
