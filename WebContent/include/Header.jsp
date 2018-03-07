<%@page import="org.Nyit.Utilis.StoreConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>NYIT ADMIN</title>
<link rel="stylesheet" href="./js/css/bootstrap.min.css">
<link rel="stylesheet" href="./js/css/font-awesome.min.css">
<link rel="stylesheet" href="./js/css/sb-admin.min.css">
<link rel="stylesheet" href="./js/css/dataTables.bootstrap4.css">

</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<img alt="Nyit" src="./Images/nyit_Logo.png"
			style="height: 30px; width: 30px;">&nbsp; <a
			class="navbar-brand" href="#">NYIT</a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="APPROVE/DECLINE REQUEST"><a class="nav-link" 
					href="<%=StoreConstant.SERVLET_BASE%>?<%=StoreConstant.MODULE%>=<%=StoreConstant.MOD_STUDENT_REQUEST%>&<%=StoreConstant.ACTION%>=<%=StoreConstant.ACTION_VIEW%>">
					<span class="nav-link-text">APPROVE/DECLINE REQUEST</span>
				</a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="ADD STUDENT"><a class="nav-link" 
					href="<%=StoreConstant.SERVLET_BASE%>?<%=StoreConstant.MODULE%>=<%=StoreConstant.MOD_STUDENTS%>&<%=StoreConstant.ACTION%>=<%=StoreConstant.ACTION_VIEW%>"> 
					<span class="nav-link-text">ADD STUDENT</span>
				</a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="ADD SCHEDULE"><a class="nav-link" 
					href="<%=StoreConstant.SERVLET_BASE%>?<%=StoreConstant.MODULE%>=<%=StoreConstant.MOD_CAMPUS_SCHEDULE%>&<%=StoreConstant.ACTION%>=<%=StoreConstant.ACTION_VIEW%>"> 
					<span class="nav-link-text">ADD SCHEDULE</span>
				</a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="ADD SUBJECT"><a class="nav-link" 
					href="<%=StoreConstant.SERVLET_BASE%>?<%=StoreConstant.MODULE%>=<%=StoreConstant.MOD_SUBJECT%>&<%=StoreConstant.ACTION%>=<%=StoreConstant.ACTION_VIEW%>"> 
					<span class="nav-link-text">ADD SUBJECT</span>
				</a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="ADD SECTION"><a class="nav-link" 
					href="<%=StoreConstant.SERVLET_BASE%>?<%=StoreConstant.MODULE%>=<%=StoreConstant.MOD_SECTION_SUBJECT_MAPPING%>&<%=StoreConstant.ACTION%>=<%=StoreConstant.ACTION_VIEW%>">
					<span class="nav-link-text">ADD SECTION</span>
				</a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="CAMPUS SUBJECT MAPPING"><a class="nav-link"
					href="<%=StoreConstant.SERVLET_BASE%>?<%=StoreConstant.MODULE%>=<%=StoreConstant.MOD_CAMPUS_SUBJECT_MAPPING%>&<%=StoreConstant.ACTION%>=<%=StoreConstant.ACTION_VIEW%>">
						<span class="nav-link-text">CAMPUS SUBJECT MAPPING</span>
				</a></li>

			</ul>
			<a href="#"> <span class="glyphicon glyphicon-user"></span> 
			<%
 				if (session.getAttribute("currentUser") != null) {
 				out.println(session.getAttribute("currentUser"));
 				}
			 %>
			</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a id="loginName" href="#"></a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="modal"
					data-target="#signout"> <i class="fa fa-fw fa-sign-out"></i>
						Logout
				</a></li>
			</ul>
		</div>
	</nav>

	<footer class="sticky-footer">
		<div class="container">
			<div class="text-center">
				<small>Copyright &copy; NYIT 2017</small>
			</div>
		</div>
	</footer>

	<!-- Scroll to Top Button -->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fa fa-angle-up"></i>
	</a>

	<!-- Logout Modal -->
	<div class="modal fade" id="signout" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary"
						href="<%=StoreConstant.SERVLET_BASE%>?<%=StoreConstant.MODULE%>=<%=StoreConstant.MOD_USER%>&<%=StoreConstant.ACTION%>=<%=StoreConstant.ACTION_LOGOUT%>">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<script src="./js/jquery.min.js"></script>
	<script src="./js/sb-admin.min.js"></script>
	<script src="./js/popper.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/jquery.easing.min.js"></script>
	<script src="./js/jquery.dataTables.js"></script>
	<script src="./js/dataTables.bootstrap4.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>