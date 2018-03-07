<!Doctype HTML>
<html>
<head>
<meta charset="utf-8">
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="stylesheet" href="./js/css/bootstrap.min.css"> -->
<!--   <script src="./js/jquery.min.js"></script> -->
<!--   <script src="./js/bootstrap.min.js"></script> -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">

$(document).ready(function() {
 	$("#add_row").on("click",function(e) {
 			var counter = document.getElementById("registrationInfo_table").rows.length;
			var newRow = $("<tr>");
					var cols = "";
					cols += '<td><input type="text" id="term'+ counter +'" name="term" class="form-control" placeholder=""/></td>';
					cols += '<td><input type="text" class="form-control" id="campusSubjectId' + counter + '" name="campusSubjectId"/></td>'
					cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td></tr>';
					newRow.append(cols);
					$("table.order-list").append(newRow);
				
					});

	$("table.order-list").on("click", ".ibtnDel",
		function(event) {
							$(this).closest("tr").remove();
							counter -= 1
						});
			});
function saveStudentInfo(){
	var btnValue=document.getElementById("studentInfo").value;
	var requestUrl="base?module=student&action=studentInfoNew";
	if(btnValue=="Update"){
		requestUrl="base?module=student&action=update";
	}
	var xmlhttp = new XMLHttpRequest();
	var  vals = "";
	var campusSubjectId = "";
	var term = "";
	var student_Id=document.getElementById("student_Id").value;
	var nyit_Id=document.getElementById("nyit_Id").value;
	var student_Email=document.getElementById("student_Email").value;
	var student_Lname=document.getElementById("student_Lname").value;
	var student_Fname=document.getElementById("student_Fname").value;
	var address=document.getElementById("address").value;
	var city=document.getElementById("city").value;
	var state=document.getElementById("state").value;
	var zipcode=document.getElementById("zipcode").value;
	var homePhone=document.getElementById("homePhone").value;
	var workPhone=document.getElementById("workPhone").value;
	var cellPhone=document.getElementById("cellPhone").value;
	var academic=document.getElementById("academic").value;
	var campus = document.getElementsByName("campus");
	var campus = document.getElementsByName("campus");
	for(var j=0;j<campus.length;j++){
		if(campus[j].checked){
			vals += campus[j].value + ",";	 
	    }
	}
xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				sessionStorage.SessionName = nyit_Id;
				sessionStorage.setItem("SessionName",nyit_Id);
				FindStudentById();
				jQuery(function ($){
					$('#studentSubjectForm :input').prop('disabled', false);
					$('#studentfrm :input').prop('disabled', true); 
				})
				document.forms["studentfrm"].reset();
				document.getElementById("studentInfo").value="Save";
				document.getElementById("student_Id").value="";

			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("post",requestUrl,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("student_Id="+ student_Id + "&nyit_Id=" + nyit_Id + "&student_Email=" + student_Email + "&student_Lname=" + student_Lname +"&student_Fname="+ student_Fname + "&address=" + address +  "&city=" + city + "&state=" + state + "&zipcode=" + zipcode + "&homePhone=" + homePhone + "&workPhone=" + workPhone + "&cellPhone=" + cellPhone + "&academic=" + academic + "&campus=" + vals);
}
function saveStudentsDetails(){
	var btnValue=document.getElementById("submit_A").value;
	var requestUrl="base?module=student&action=new";
	if(btnValue=="Update"){
		requestUrl="base?module=student&action=update";
	}
	var xmlhttp = new XMLHttpRequest();
	var  vals = "";
	var campusSubjectId = "";
	var term = "";
	var subjectInfo_Id=document.getElementById("subjectInfo_Id").value;
	var student_Id=document.getElementById("student_Id").value;
	var counter = document.getElementById("registrationInfo_table").rows.length;
	for(var i=1;i<counter;i++){
		campusSubjectId += document.getElementById("campusSubjectId"+i).value;
		 term += document.getElementById("term"+i).value;
		 campusSubjectId += ",";
		 term += ",";
		}
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				document.forms["studentSubjectForm"].reset();
				document.getElementById("submit_A").value="Save";
				document.getElementById("subjectInfo_Id").value="";
				location.replace("/registrationForm_B1.jsp");
			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("post",requestUrl,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("subjectInfo_Id="+ subjectInfo_Id +"&student_Id="+ student_Id + "&campusSubjectId=" + campusSubjectId + "&term=" + term);
}
function FindStudentById(){
	var nyit_Id = sessionStorage.getItem("SessionName");
	document.getElementById("search")["value"] = nyit_Id;
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			document.getElementById("student_Id").value=response.student_Id;
			document.getElementById("nyit_Id").value=response.student_nyit_Id;
			document.getElementById("student_Email").value=response.email_Id;
			document.getElementById("student_Lname").value=response.last_Name;
			document.getElementById("student_Fname").value=response.first_Name;
			document.getElementById("address").value=response.address;
			document.getElementById("city").value=response.city;
			document.getElementById("state").value=response.state;
			document.getElementById("zipcode").value=response.zipcode;
			document.getElementById("homePhone").value=response.home_phoneNo;
			document.getElementById("workPhone").value=response.work_phoneNo;
			document.getElementById("cellPhone").value=response.cell_phoneNo;
			document.getElementById("academic").value=response.academic;
			if(response.campusName!=null){
	             document.querySelectorAll("input[type='checkbox'][value='"+ response.campusName + "']")[0].checked=true;
			}
		}
	};
	xmlhttp.open("get","base?module=student&action=getRowData&search="+ nyit_Id,true);
	xmlhttp.send(null);
}
jQuery(function ($){
       var $inputs = $('#studentSubjectForm :input').prop('disabled', true);
 	   var $studentInfo = $('#studentfrm :input').prop('disabled', false); 
//     $('#studentInfo').click(function () {
//         $inputs.prop('disabled', false);
//         $studentInfo.prop('disabled', true);
//     });
//     $('#submit_A').click(function () {
//         $inputs.prop('disabled', true);
//     });
})

function validateForm() {
	var nyit_Id = document.studentfrm.nyit_Id.value;
	if (nyit_Id =="" || nyit_Id == null){
		alert("Please enter 7 digit NYIT Student ID");
		document.getElementById("nyit_Id").focus();
		return false;
	}
	
	 else if (nyit_Id > 7) {
 		if (!/^\d{7}$/.test(nyit_Id)) {
 	    alert("The NYIT Student ID must be 7 Digits");
	        document.getElementById("nyit_Id").focus();
 	    return false;
 	 }
    }
	
	 else if (nyit_Id != "" || nyit_Id != null) {
	 		if (!/^\d+$/.test(nyit_Id)) {
	 	    alert("Please enter only numbers");
		        document.getElementById("nyit_Id").focus();
	 	    return false;
	 	 }
	    }
	
	var student_Email = document.studentfrm.student_Email.value;
	if (student_Email =="" || student_Id == null){
		alert("Please Enter NYIT email");
		document.getElementById("student_Email").focus();
		return false;
	}
	
	else if (student_Email != "") {
 		if (!/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/.test(student_Email)) {
 	    alert("Please enter NYIT email only");
	        document.getElementById("student_Email").focus();
 	    return false;
 		}
 	 }
	
	var student_Lname = document.studentfrm.student_Lname.value;
    if (student_Lname == "" || student_Lname == null) {
    	alert("Please enter your Last Name");
        document.getElementById("student_Lname").focus();
        return false;  
    }
    
	 else if (student_Lname != "" || student_Lname != null) {
 		if (!/^[A-Za-z ]+$/.test(student_Lname)) {
 	    alert("Please enter only alphabets for your Last Name");
	        document.getElementById("student_Lname").focus();
 	    return false;
 	 }
    }
    
    
    var student_Fname = document.forms["studentfrm"]["student_Fname"].value;
     if (student_Fname == "" || student_Fname == null){
    	window.alert("Please enter your First Name");
        document.getElementById("student_Fname").focus();
        return false;  
    }
     
	 else if (student_Fname != "" || student_Fname != null) {
	 		if (!/^[A-Za-z ]+$/.test(student_Fname)) {
	 	    alert("Please enter only alphabets for your First Name");
		        document.getElementById("student_Fname").focus();
	 	    return false;
	 	 }
	    }
	    
     
    var address = document.forms["studentfrm"]["address"].value;
     	if(address =="" || address == null){
     		window.alert("Please enter your valid Address");
 	        document.getElementById("address").focus();
 	        return false;
     	}
    var city = document.forms["studentfrm"]["city"].value;
     	if(city  =="" || city  == null){
     		window.alert("Please enter your City");
 	        document.getElementById("city").focus();
 	        return false;
     	}
     	
    
		 else if (city != "" || city != null) {
	    		if (!/^[A-Za-z ]+$/.test(city)) {
	    	    alert("Please enter only alphabets for City");
		        document.getElementById("city").focus();
	    	    return false;
	    	 }
	       }
		
    var state = document.forms["studentfrm"]["state"].value;
    	if(state =="" || state == null){
    		window.alert("Please enter your State");
	        document.getElementById("state").focus();
	        return false;
    	}
    	
        
		 else if (state != "" || state != null) {
	    		if (!/^[A-Za-z ]+$/.test(state)) {
	    	    alert("Please enter only alphabets for State");
		        document.getElementById("state").focus();
	    	    return false;
	    	 }
	       }
    	
	var zipcode = document.forms["studentfrm"]["zipcode"].value;
		if(zipcode=="" || zipcode==null){
			window.alert("Please enter your ZipCode");
	        document.getElementById("zipcode").focus();
	        return false;
		}
		
		 else if (zipcode != "" || zipcode != null) {
	    		if (!/^\d+$/.test(zipcode)) {
	    	    alert("Please enter only digits for Zipcode");
		        document.getElementById("zipcode").focus();
	    	    return false;
	    	 }
	       }
		
	
	    var cellPhone = document.forms["studentfrm"]["cellPhone"].value;
	    if(cellPhone == "" || cellPhone == null){
	    	 	window.alert("Please enter your Cell Phone number");
		        document.getElementById("cellPhone").focus();
		        return false;
	    }
	   
	    else if (cellPhone != "" || cellPhone != null) {
	    		if (!/^\d{10}$/.test(cellPhone)) {
	    	    alert("Please enter your valid 10 digit Cell Phone number");
		        document.getElementById("cellPhone").focus();
	    	    return false;
	    	 }
	       }
		
	var academic = document.forms["studentfrm"]["academic"].value;
		if(academic=="" || academic==null){
			window.alert("Please enter your Academic Program");
	        document.getElementById("academic").focus();
	        return false;
		}
		
		
		 else if (academic != "" || academic != null) {
	    		if (!/^[A-Za-z]+$/.test(academic)) {
	    	    alert("Please enter only alphabets for Academic Program");
		        document.getElementById("academic").focus();
	    	    return false;
	    	 }
	       } 
    saveStudentInfo(true);
}


</script>
<style>

header{margin-top:2%;
border-bottom: 3px solid black;
padding-bottom:10px;
}
section div p{
border-bottom: 1px solid black;
padding-bottom:10px;
font-weight:bold;
}
header span{
Font-weight:600px;
Font-size:32px;
padding-top:15%;
}
input {
    border: none;
    background: transparent;
}
</style>
</head>
<body>
<div class="container">
<header>
<div class="row">
<div class="col-xs-12 col-md-12 col-sm-12">
<img src="./Images/logo-nyit-black.png" width="80px" height="80px"/> &nbsp; &nbsp; <span><b>Student Information Form</b></span>
</div>
</div>
</header>
<section>
<form id="studentform" name="studentfrm" action="#" method="post">
<div class="row">
<div class="col-xs-12 col-md-12 col-sm-12">
<p style="padding-top:1%;">STUDENT INFORMATION</p></div>
<input type="hidden" name="student_Id" id="student_Id">

<div class="col-xs-6 col-md-6 col-sm-6">
<P>Student ID: &nbsp;&nbsp;&nbsp; <input type="text" name="nyit_Id" id="nyit_Id" placeholder="your student ID"></p></div>

<div class="col-xs-6 col-md-6 col-sm-6">
<p>NYIT Email Id:&nbsp;&nbsp;&nbsp; <input type="text" id="student_Email" name="student_Email" placeholder="your student email ID"></p></div>

<div class="col-xs-6 col-md-6 col-sm-6">
<p>Last name: &nbsp;&nbsp;&nbsp; <input type="text" id="student_Lname" name="student_Lname" placeholder="your last name"></p></div>
<div class="col-xs-6 col-md-6 col-sm-6">
<p>First name: &nbsp;&nbsp;&nbsp; <input type="text" id="student_Fname" name="student_Fname" placeholder="your first name"></p></div>

<div class="col-xs-4 col-md-4 col-sm-4">
<p>Address: &nbsp;&nbsp;&nbsp; <input type="text" id="address" name="address" placeholder="your address" maxlength="25" size="25" ></p></div>
<div class="col-xs-8 col-md-8 col-sm-8">

<div class="col-xs-4 col-md-4 col-sm-4"><p>City: <input type="text" id="city" name="city" placeholder="your city" maxlength="15" size="15"></p></div>

<div class="col-xs-4 col-md-4 col-sm-4 "><p>State: <input type="text" id="state" name="state" placeholder="your state" maxlength="10" size="10"></p></div>

<div class="col-xs-4 col-md-4 col-sm-4"><p>Zip code: <input type="text" id="zipcode" name="zipcode" placeholder="your zipcode" maxlength="11" size="11"></p></div>

</div>

<div class="col-xs-4 col-md-4 col-sm-4"><p>Cell phone: <input type="text" id="cellPhone" name="cellPhone" maxlength="10" size="10"></p></div>


<div class="col-xs-4 col-md-4 col-sm-4"><p>Home phone: <input type="text" id="homePhone" name="homePhone" maxlength="10" size="10"></p></div>

<div class="col-xs-4 col-md-4 col-sm-4"><p>Work phone: <input type="text" id="workPhone" name="workPhone" maxlength="10" size="10"></p></div>


<div class="col-xs-12 col-md-12 col-sm-12"><div class="form-group"><div class="form-inline"><p>Academic program: &nbsp <select class="form-control" id="academic" name="academic" placeholder="your academic program" ></p></div></div></div>
<option value="academicProgram">Select Academic Program</option><option value = "CS">CS</option><option value = "INCS">INCS</option><option value = "ECE">ECE</option></select>

<div class="col-xs-12 col-md-12 col-sm-12"><p>I am registering on this campus: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="ow"
							name="campus" value="OW"> Old Westbury &nbsp;&nbsp;&nbsp;
<input type="checkbox" id="manhattan" name="campus" value="MA"> Manhattan
</p><br></div>
</div>
<center><input type="button" name="studentInfo" id="studentInfo" value="SAVE" class="btn-primary" onclick="validateForm();"/> <br><br><br></center>
</form>
	<div class="form-group">
				<div class="form-inline">
					<div class="col-md-4">
						<input type="text" id="search" name="search" class="form-control"
							placeholder="Search By Id" disabled>&nbsp;&nbsp;
						<input type="button" id="search" name="search" class="btn-primary"
							value="Search" onclick="javascript:FindStudentById();">	
					</div>
				</div>
	</div>
<form id="studentSubjectForm" action="#" method="post" class="form-horizontal" name="studentSubjectfrm">
<input type="hidden" name="subjectInfo_Id" id="subjectInfo_Id">
<div><p>Please enter your courses that you have already completed.</p></div>
	<div class="form-group" id="subjectDetails">
				<table class="table table-bordered table-hover order-list" id="registrationInfo_table">
					<thead>
						<tr>
							<th>TERM</th>
							<th>SUBJECT AND COURSE#</th>
							<th><input type="button" class="btn-primary" value="ADD SUBJECT" id="add_row" required></th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td><input type="text" id="term1" name="term1"
							class="form-control" input type="text"></td>
							
							<td><input type="text" class="form-control" id="campusSubjectId1" name="campusSubjectId" input type="text" required>
							</td>
							
						</tr>
						</tbody>
				</table>
			</div>
<br>
<div class="col-xs-6 col-md-6 col-sm-6">
<p>Advisor's Signature: MA, MS, CS &nbsp;&nbsp;&nbsp; <input type="text" id="advisor_signature" name="advisor_signature" placeholder=""></p></div>
<div class="col-xs-6 col-md-6 col-sm-6">
<p>Date: &nbsp;&nbsp;&nbsp; <input type="text" id="date" name="date" placeholder="mm/dd/yy"></p></div>

<b>nyit.edu</b>

<center><input type="button" name="submit_A" id="submit_A" value="SAVE & NEXT" class="btn-primary" onclick="javascript:saveStudentsDetails();"/> <br><br><br></center>
</form>
</section>
</div>
</body>
</html>