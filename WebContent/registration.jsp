<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="./js/css/bootstrap.min.css">
<link rel="stylesheet" href="./js/css/font-awesome.min.css">
<link rel="stylesheet" href="./js/css/sb-admin.min.css">
</head>
<script type="text/javascript">
function saveUser() {
		var btnValue = document.getElementById("btnsave").value;
		var requestUrl ="base?module=user&action=register";
		var xmlhttp = new XMLHttpRequest();
		var firstName = document.getElementById("firstName").value;
		var lastName= document.getElementById("lastName").value;
		var email_Id = document.getElementById("email_Id").value;
		var password = document.getElementById("password").value;
		xmlhttp.onreadystatechange = function() {
					if(xmlhttp.readyState==4 && xmlhttp.status==200){
						var response = JSON.parse(xmlhttp.responseText);
						if(response.isSuccess){
							var response = response.message;
								alert(response);
								document.forms["regfrm"].reset();
								document.getElementById("btnsave").value = "Save";
								document.getElementById("user_Id").value = "";
							    window.location.replace("\login.jsp");

						}else{
								var response = response.message;
								alert(response);
						 	}						
						
					}
};
	
		xmlhttp.open("post", requestUrl, true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send( "firstName=" + firstName +"&lastName="+lastName+ "&email_Id="+ email_Id + "&password=" + password);

}
</script>
<body class="bg-dark">
	<div class="container">
		<div class="card card-register mx-auto mt-5">
			<div class="card-header" align="center">
				<img alt="NYIT" src="./Images/nyit_Logo.png"
					style="height: 80px; width: 80px;"><br />
				<h5>SIGN UP</h5>
			</div>
			<div class="card-body">
				<form action="" method="post" name="regfrm">
				  <input type="hidden" id="user_Id" value=""/>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="First Name">First name</label> <input type="text"
									class="form-control" id="firstName" name="firstName"
									placeholder="Enter first name">
							</div>
							<div class="col-md-6">
								<label for="Last Name">Last name</label> <input type="text"
									class="form-control" id="lastName" name="lastName"
									placeholder="Enter last name">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="Email Id">Email address</label> <input type="email"
							class="form-control" id="email_Id" name="email_Id"
							placeholder="Enter email">
					</div>
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="password">Password</label> <input type="password"
									class="form-control" id="password" name="password"
									placeholder="Password">
							</div>
							<div class="col-md-6">
								<label for="confirmPassword">Confirm password</label> <input
									type="password" class="form-control" id="confirmPassword"
									name="confirmPassword" placeholder="Confirm password">
							</div>
						</div>
					</div>
					<input type="button" class="btn btn-warning btn-block" id="btnsave"
						value="Save" onclick="javascript:saveUser();">
					<!-- <a class="btn btn-warning btn-block" href="login.jsp">Register</a> -->
				</form>
				<div class="text-center">

					<a class="d-block small mt-3" href="login.jsp">Login Page</a> <a
						class="d-block small" href="forgetPassword.jsp">Forgot
						Password?</a>
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