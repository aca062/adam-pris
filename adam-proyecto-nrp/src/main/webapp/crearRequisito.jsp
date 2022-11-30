<!-- IMPORTANTE PARA UTF-8 -->
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<!-- saved from url=(0050)https://getbootstrap.com/docs/4.0/examples/album/# -->
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon"
	href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">

<title>Crear requisito</title>

<!-- Bootstrap core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">

<!-- Custom styles for this template -->
<link href="./css/album.css" rel="stylesheet">
<style>
undefined
</style>
<style type="text/css">
@font-face {
	font-family: Roboto;
	src:
		url("chrome-extension://mcgbeeipkmelnpldkobichboakdfaeon/css/Roboto-Regular.ttf");
}
</style>
<script
	src="chrome-extension://mooikfkahbdckldjjndioackbalphokd/assets/prompt.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com/"
	crossorigin="true">
<link rel="preconnect" href="https://fonts.gstatic.com/">
<link rel="stylesheet" href="./css/css2">
</head>

<body>

	<header>
		<div class="navbar navbar-dark bg-dark box-shadow">
			<div class="container d-flex justify-content-between"
				style="text-align: center">
				<div class="navbar-brand d-flex align-items-center"
					style="margin: 0px auto">
					<strong>PROYECTO: Problema de la siguiente versión</strong>
				</div>
				<button class="navbar-toggler collapsed" type="button"
					data-toggle="collapse" data-target="#navbarHeader"
					aria-controls="navbarHeader" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
		</div>
	</header>

	<main role="main">

		<section class="text-center" style="margin-top: 30px">
			<div class=row
				style="text-align: left; margin-left: 8%; margin-bottom: 2%">
				<h2>Crear un requisito</h2>
			</div>
			<%
			String error = (String) request.getAttribute("error");
			if (error != null) {
			%>
			<li style="color: red; margin-bottom: 25px;"><%=error%></li>
			<%
			}
			%>
			<form id="crearRequisito-form"
				action="ServletRequisito?action=crear_requisito" method="post"
				role="form" style="margin-bottom: 5%;">
				<div class=row style="text-align: center">
					<div class=col-sm-4 style="margin: 0px auto; text-align: left">
						<strong>Nombre</strong>
					</div>
					<div class=col-sm-4 style="margin: 0px auto; text-align: left">
						<strong>Esfuerzo</strong>
					</div>
				</div>
				<div class=row style="text-align: center">
					<div class=col-sm-4 style="margin: 0px auto">
						<input type="text" name="nombre" id="nombre" tabindex="1"
							class="form-control" placeholder="Nombre" value=""
							style="border-radius: 2rem" required>
					</div>
					<div class=col-sm-4 style="margin: 0px auto">
						<input type="text" name="esfuerzo" id="esfuerzo" tabindex="2"
							class="form-control" placeholder="Esfuerzo" value=""
							style="border-radius: 2rem" required>
					</div>
				</div>
				<div class="container-xl">
					<div class="table"
						style="max-width: 65%; margin-left: 17.5%; margin-top: 5%; justify-content: center">
						<div class="table-wrapper">
							<div class="table-title">
								<div class="row" style="justify-content: center">
									<div class="col-sm-6">
										<h3>Valor de los clientes</h3>
									</div>
									<%
									String errorCliente = (String) request.getAttribute("errorCliente");
									if (errorCliente != null) {
									%>
									<li style="color: red; margin-bottom: 25px;"><%=errorCliente%></li>
									<%
									}
									%>
								</div>
							</div>
							<table class="table table-striped table-hover table-bordered">
								<thead>
									<tr>

										<th>Nombre del cliente</th>
										<th>Prioridad del cliente</th>
										<th>Valor estimado por el cliente</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="cliente" items="${listaClientes}">
										<tr>
											<td style="vertical-align: middle"><c:out
													value="${cliente.nombre}" /></td>
											<td style="vertical-align: middle"><c:out
													value="${cliente.prioridad}" /></td>
											<td><input type="number" min="0" step="1" pattern="\d*"
												name="valor${cliente.id}" id="valor${cliente.id}"
												tabindex="2" class="form-control" placeholder="Valor"
												value="0" style="border-radius: 2rem" required></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<input type="hidden" name="proyecto_id" id="proyecto_id" value="${proyecto_id}">
							<table class="table table-striped table-hover table-bordered">
								<thead>
									<tr>

										<th>Nombre del requisito</th>
										<th>Tipo de dependencia con el requisito</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="requisito" items="${listaRequisitos}">
										<tr>
											<td style="vertical-align: middle"><c:out
													value="${requisito.nombre}" /></td>
											<td>
												<select id="tipoRelacion${requisito.id}" name="tipoRelacion${requisito.id}" required>
												  <option value="no-relacion">Sin relación</option>
												  <option value="exclusion">Exclusión</option>
												  <option value="implicacion">Implicación</option>
												  <option value="combinacion">Combinación</option>
												</select>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class=row style="text-align: center; margin-top: 2%">
					<div class=col-sm-4 style="margin: 0px auto">
						<input type="submit" name="register-requirement"
							id="register-requirement" tabindex="4" class="btn btn-dark"
							value="Crear requisito" style="border-radius: 2rem">
					</div>
				</div>
			</form>
		</section>

	</main>

	<footer class="text-muted"
		style="background: black; position: relative; bottom: 0; width: 100%">
		<div class="container">
			<p style="text-align: center; color: white">Adrián Camacho, David
				Silvente, Antonio Jesús Cano, Mariano Fernández</p>
			<p style="text-align: center; color: white"">Universidad de
				Almería</p>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./js/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')
	</script>
	<script src="./js/popper.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/holder.min.js"></script>


	<svg xmlns="http://www.w3.org/2000/svg" width="348" height="225"
		viewBox="0 0 348 225" preserveAspectRatio="none"
		style="display: none; visibility: hidden; position: absolute; top: -100%; left: -100%;">
		<defs>
		<style type="text/css"></style></defs>
		<text x="0" y="17"
			style="font-weight:bold;font-size:17pt;font-family:Arial, Helvetica, Open Sans, sans-serif">Thumbnail</text></svg>
</body>
</html>