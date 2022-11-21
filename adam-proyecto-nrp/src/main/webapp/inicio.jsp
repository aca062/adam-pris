<!-- IMPORTANTE PARA UTF-8 -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<!-- saved from url=(0050)https://getbootstrap.com/docs/4.0/examples/album/# -->
<html lang="es">
<head>
    <script>
      // The function below will start the confirmation dialog
      function confirmAction() {
        let confirmAction = confirm("Are you sure to execute this action?");
        if (confirmAction) {
          alert("Action successfully executed");
        } else {
          alert("Action canceled");
        }
      }
    </script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Inicio</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">

    <!-- Custom styles for this template -->
    <link href="./css/album.css" rel="stylesheet">
  <style>undefined</style><style type="text/css">@font-face { font-family: Roboto; src: url("chrome-extension://mcgbeeipkmelnpldkobichboakdfaeon/css/Roboto-Regular.ttf"); }</style><script src="chrome-extension://mooikfkahbdckldjjndioackbalphokd/assets/prompt.js"></script><link rel="preconnect" href="https://fonts.googleapis.com/" crossorigin="true"><link rel="preconnect" href="https://fonts.gstatic.com/"><link rel="stylesheet" href="./css/css2">
  </head>

  <body>

    <header>
      <div class="navbar navbar-dark bg-dark box-shadow">
        <div class="container d-flex justify-content-between" style = "text-align: center">
          <div class="navbar-brand d-flex align-items-center" style = "margin: 0px auto" >
            <strong>PROYECTO: Problema de la siguiente versión</strong>
          </div>
          <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        </div>
      </div>
    </header>

    <main role="main">
    
      <section class="text-center" style="margin-top:30px">
      <div class="row">
      <div style= "margin-bottom:20px" class="col-sm-12 col-xs-12"><h4>Ejecute para obtener una solución</h4></div>
        <div class="col-sm-12 col-xs-12" style="margin-bottom:120px">
			<a class= "btn btn-default btn-dark" href="solucionManual.jsp" role="button" style = "border-radius: 2rem; margin-right:2%"">Idear solución manual</a>
			<a class= "btn btn-default btn-dark" href="solucionAutomatica.jsp" role="button" style = "border-radius: 2rem; margin-right:2%"">Idear solución automática</a>
		</div>
    </div>
	<div class="container-xl">
    <div class="table" style="max-width:65%; margin-left:5%">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-4"><h2>Requisitos del problema</h2></div>
                    <div class="col-sm-2" style = "align-items:center; display: flex; justify-content: center">
                        <a href="./crearRequisito.jsp" class="bi bi-plus-circle" data-toggle="modal"></a>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        <th>Ri <i class="fa fa-sort"></i></th>
                        <th>Nombre</th>
                        <th>Esfuerzo <i class="fa fa-sort"></i></th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="requisito" items="${listaRequisito}">
					<tr>
						<td><c:out value="${requisito.id}"/></td>
						<td><c:out value="${requisito.nombre}"/></td>
						<td><c:out value="${requisito.esfuerzo}"/></td>
						<td>
	                        <a href="ServletRequisito?action=eliminarRequisito&id=<c:out value="${requisito.id}" />" class="bi bi-x-circle" title="Delete" data-toggle="tooltip"></a>
	                        <a href="ServletRequisito?action=editarRequisito&id=<c:out value="${requisito.id}" />" class="bi bi-pencil-square" title="Edit" data-toggle="tooltip"></a>
	                    </td>			
					</tr>
				</c:forEach>        
                </tbody>
            </table>
        </div>
    </div>  
</div>
<div class="container-xl">
    <div class="table" style="max-width:65%; margin-left:5%">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-4 "><h2>Clientes del problema</h2></div>
                    <div class="col-sm-2" style = "align-items:center; display: flex; justify-content: center">
                        <a href="crearCliente.jsp" class="bi bi-plus-circle" data-toggle="modal"></a>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover table-bordered">
                <thead>
                    <tr>
                        
                        <th>Ci <i class="fa fa-sort"></i></th>
                        <th>Nombre</th>
                        <th>Wi <i class="fa fa-sort"></i></th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
	            <c:forEach var="cliente" items="${listaClientes}">
					<tr>
						<td><c:out value="${cliente.id}"/></td>
						<td><c:out value="${cliente.nombre}"/></td>
						<td><c:out value="${cliente.prioridad}"/></td>
						<td>
	                        <a href="ServletCliente?action=eliminar_cliente&id=<c:out value="${cliente.id}" />" onclick="return confirm('¿Estás seguro de que quieres eliminar el cliente <c:out value="${cliente.nombre}" />?')" class="bi bi-x-circle" title="Delete" data-toggle="tooltip"></a>
	                        <a href="ServletCliente?action=mostrar_editar_cliente&id=<c:out value="${cliente.id}" />&nombre=<c:out value="${cliente.nombre}"/>&prioridad=<c:out value="${cliente.prioridad}"/>" class="bi bi-pencil-square" title="Edit" data-toggle="tooltip"></a>
	                    </td>			
					</tr>
				</c:forEach>
                </tbody>
            </table>
        </div>
    </div>  
</div>


      </section>

    </main>
    
    <footer class="text-muted" style= "background:black; position:relative; bottom:0; width: 100%">
      <div class="container">
        <p style= "text-align: center; color: white">Adrián Camacho, David Silvente, Antonio Jesús Cano, Mariano Fernández</p>
        <p style= "text-align: center; color: white"">Universidad de Almería</p>
      </div>
    </footer>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="./js/popper.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/holder.min.js"></script>
  

<svg xmlns="http://www.w3.org/2000/svg" width="348" height="225" viewBox="0 0 348 225" preserveAspectRatio="none" style="display: none; visibility: hidden; position: absolute; top: -100%; left: -100%;"><defs><style type="text/css"></style></defs><text x="0" y="17" style="font-weight:bold;font-size:17pt;font-family:Arial, Helvetica, Open Sans, sans-serif">Thumbnail</text></svg></body></html>