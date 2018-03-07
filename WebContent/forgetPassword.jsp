<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="./js/css/bootstrap.min.css">
<link rel="stylesheet" href="./js/css/font-awesome.min.css">
<link rel="stylesheet" href="./js/css/sb-admin.min.css">

</head>
<body class="bg-dark">

	<div class="container">

		<div class="card card-login mx-auto mt-5">
			<div class="card-header">Reset Password</div>
			<div class="card-body">
				<div class="text-center mt-4 mb-5">
					<h4>Forgot your password?</h4>
					<p>Enter your email address and we will send you instructions
						on how to reset your password.</p>
				</div>
				<form>
					<div class="form-group">
						<input type="email" class="form-control" id="emailId"
							name="emailId" placeholder="Enter email address">
					</div>
					<a class="btn btn-primary btn-block" href="login.jsp">Reset Password</a>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="registration.jsp">Register an Account</a> 
					<a class="d-block small" href="login.jsp">Login Page</a>
				</div>
			</div>
		</div>
	</div>
<!-- Bootstrap JAVASCRIPT -->
	<script src="./js/jquery.min.js"></script>
	<script src="./js/sb-admin.min.js"></script>
	<script src="./js/popper.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>