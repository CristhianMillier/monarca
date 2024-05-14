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
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                        <div class="card">
                            <div class="card-header"><i class="fas fa-user"></i> Cambiar Contrasña</div>
                            <div class="card-body">
                                <form method="POST" action="Empleado">
                                    <div class="modal-body">
                                        <div class="modal-body">
                                            <div class="row g-2">
                                                <div class="col-sm-6 requerido">
                                                    <label class="form-label">New Password</label> <br>
                                                    <input type="text" class="form-control" minlength="8" required name="passwordEmp" id="password" autocomplete="off" value="${passwordUser}">
                                                </div>
                                                <div class="col-sm-6 requerido">
                                                    <label class="form-label">Clave de Seguridad</label> <br>
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" minlength="5" maxlength="5" required name="claveSer" id="claveSer" autocomplete="off" value="${claveUser}">
                                                        <span class="input-group-btn px-4">
                                                            <a class="btn btn btn-info" onclick="generar()">Generar</a>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="form-label">La contraseña debe ser mayor o igual a 8 caracteres</label> <br>
                                                </div>
                                                <c:if test="${errorMsjUser != null}">
                                                    <div class="row mt-2">
                                                        <div class="col-12">
                                                            <div class="alert alert-danger" role="alert">
                                                                ${errorMsjUser}
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <div class="modal-footer mt-4">
                                                <a class="btn btn-secondary m-2" href="Empleado?accion=atrasUser">Cancelar</a>
                                                <button type="submit" name="accion" value="guardarPasswordUser" class="btn btn-primary">Guardar</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
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
    </body>

    <script>
        function noAtras() {
            window.location.hash = "no-back-button";
            window.location.hash = "Again-No-back-button"; //chrome necesita esto
            window.onhashchange = function () {
                window.location.hash = "no-back-button";
            };
        }
        function generar() {
            $('#cargar').modal("show");
            var password = $("#password").val();
            var clave = $("#claveSer").val();
            let url = "Empleado?accion=claveSeguridad&passwordEmp=" + password + "&claveSer=" + clave;
            document.location.href = url;
        }
    </script>
</html>
