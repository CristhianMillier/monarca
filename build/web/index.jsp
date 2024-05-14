<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>LICORERÍA MONARCA</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/login.css" rel="stylesheet" />
        <script src="js/scripts.js"></script>
        <link rel="icon" href="img/icono.ico" sizes="32x32">
        <link rel="stylesheet" href="css/bootstrap-icons-1.10.5/font/bootstrap-icons.css"/>
    </head>
    <body onload="noAtras()">
        <div class="container">            
            <form method="POST" action="Empleado">
                <div class="logeo">
                    <img src="img/Logo.png" alt="70" width="170"/>
                    <h1>BIENVENIDO AL SISTEMA</h1>
                </div>
                <div class="cont">
                    <label>USUARIO:</label>  
                    <input type="text" name="txtuser" required class="text" autocomplete="off" autofocus>
                    <i class="bi bi-eye-slash-fill" style="color: #0e181e"></i>
                </div>
                <div class="cont separator">
                    <label>PASSWORD:</label>
                    <input type="password" name="txtpass" required class="text" autocomplete="off" id="password">
                    <i class="bi bi-eye-slash-fill" style="cursor: pointer" id="verPassword" onclick="mostrarPassword()"></i>
                    <label class="warinig">${mensaje}</label>
                </div>
                <button type="submit" name="accion" value="ingresar" class="boton">INGRESAR</button>
            </form>
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
        function mostrarPassword(){
            var password = document.getElementById('password');
            var icono = document.getElementById('verPassword');
            if ( password.type === "text" ) {
                password.type = "password";
                icono.classList = 'bi bi-eye-slash-fill';
            } else { 
                password.type = "text";
                icono.classList = 'bi bi-eye-fill';
            }
        }
    </script>
</html>
