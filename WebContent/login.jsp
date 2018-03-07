<%@page import="org.Nyit.Utilis.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NYIT ADMIN LOGIN</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="./js/css/bootstrap.min.css">
<link rel="stylesheet" href="./js/css/font-awesome.min.css">
<link rel="stylesheet" href="./js/css/sb-admin.min.css">
</head>
<script type="text/javascript">
function loginUser() {
		var btnValue = document.getElementById("loginSubmit").value;
		var requestUrl ="base?module=user&action=login";
		var xmlhttp = new XMLHttpRequest();
		var email_Id = document.getElementById("email_Id").value;
		var password = document.getElementById("password").value;
		xmlhttp.onreadystatechange = function() {
					if(xmlhttp.readyState==4 && xmlhttp.status==200){
						var response = JSON.parse(xmlhttp.responseText);
						if(response.isSuccess){
							var response = response.message;
								alert(response);
								document.forms["loginfrm"].reset();
								document.getElementById("loginSubmit").value = "Sign In";
								document.getElementById("user_Id").value = "";
							    window.location.replace("<%=StoreConstant.SERVLET_BASE %>?<%=StoreConstant.MODULE%>=<%=StoreConstant.MOD_HOME%>&<%=StoreConstant.ACTION %>=<%=StoreConstant.ACTION_VIEW%>");

						}else{
								var response = response.message;
								alert(response);
							    window.location.replace("login.jsp");
						 	}						
					}
};
	
		xmlhttp.open("post", requestUrl, true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send( "email_Id=" + email_Id +"&password=" + password);

}
</script>
<body>
	<body class="bg-dark">
	<div class="container">

      <div class="card card-login mx-auto mt-5">
        <div class="card-header" align="center">
          <img alt="NYIT" src="./Images/nyit_Logo.png"
			style="height: 80px; width: 80px;"><br/>
			<h5>SIGN IN</h5> 
        </div>
        <div class="card-body">
          <form id="loginfrm" action="" method="post">
           <input type="hidden" id="user_Id" value=""/>
            <div class="form-group">
              <label for="Email Id" >Email address</label>
              <input type="email" class="form-control" id="email_Id" name="email_Id" placeholder="Enter email">
            </div>
            <div class="form-group">
              <label for="Password">Password</label>
              <input type="password" class="form-control" id="password" name="password" placeholder="password">
            </div>
            <div class="form-group">
              <div class="form-check">
                <label class="form-check-label">
                  <input type="checkbox" class="form-check-input">
                  Remember Password
                </label>
              </div>
            </div>
            <input type="button" class="btn btn-warning btn-block" id="loginSubmit" value="Sign In" onclick="javascript:loginUser();">
           </form>
          <div class="text-center">
            <a class="d-block small mt-3" href="registration.jsp">Register an Account</a>
            <a class="d-block small" href="forgetPassword.jsp">Forgot Password?</a>
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