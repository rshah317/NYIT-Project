<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<meta charset="utf-8">
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="stylesheet" href="./js/css/bootstrap.min.css"> -->
<!--   <script src="./js/jquery.min.js"></script> -->
<!--   <script src="./js/bootstrap.min.js"></script> -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script><title>Registration Form B1</title>



<style>
header{margin-top:2%;
border-bottom: 3px solid black;
padding-bottom:10px;
}
section div p{
border-bottom: 1px solid black;
padding-bottom:10px;
font-weight:bold;
text-align: justify;
	text-justify: inter-word;
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

<!-- <link rel="stylesheet" href="./js/css/bootstrap.min.css"> -->
<!-- <script type="text/javascript"></script> -->
<!-- <script src="./js/jquery.min.js"></script> -->
<!-- <script src="./js/bootstrap.min.js"></script> -->

<script type="text/javascript">

	$(document).ready(function() {
 	$("#add_row").on("click",function(e) {
 			var counter = document.getElementById("registration_table").rows.length;
			var newRow = $("<tr>");
					var cols = "";
					cols += '<td><input type="text" id="term'+ counter +'" name="term" class="form-control" placeholder=""/></td>';
					cols += '<td><input type="text" id="class'+ counter +'" name="class" class="form-control" placeholder=""/></td>';
					cols += '<td><select class="form-control" id="majorCode'+ counter +'" name="majorCode" onchange="loadSubject(this.value);"></select></td>';
					cols += '<td><select class="form-control" id="campusSubjectId' + counter + '" name="campusSubjectId" onchange="loadSubjectDayTime(this.value);"></select></td>'
					cols += '<td><input class="form-control" id="sectionId'+ counter +'" name="sectionId" disabled/></td>';
					cols += '<td><select type="text" id="dayTime'+ counter +'" name="dayTime" class="form-control" placeholder=""></select></td>';
					cols += '<td><input type="text" id="credit'+ counter +'" name="credit" class="form-control" placeholder=""></td>';
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
	
	function saveStudentDetails() {
		var btnValue = document.getElementById("submit_B1").value;
		var requestUrl = "base?module=sstudent=new";
		var xmlhttp = new XMLHttpRequest();
		var vals = "";
		var student_Id = document.getElementById("student_Id").value;
		var student_Email = document.getElementById("student_Email").value;
		var subject_Name = document.getElementById("subject_Name").value;
		var subject_Code = document.getElementById("subject_Code").value;
		var rbtn = document.getElementsByName("subject_rbtn");
		for (var i = 0; i < rbtn.length; i++) {
			if (rbtn[i].checked) {
				value = rbtn[i].value;
				rbtn = value;
			}
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.status == 200 && xmlhttp.readyState == 4) {
				var response = JSON.parse(xmlhttp.responseText);
				if (response.isSuccess) {
					var response = response.message;
					alert(response);
					showSubjectDetails(1);
					sessionStorage.removeItem("SessionName");
					document.forms["subjectfrm"].reset();
					document.getElementById("btnSubjectSave").value = "Save";
					document.getElementById("subject_Id").value = "";

				} else {
					var response = response.message;
					alert(response);
				}
			}
		};
		xmlhttp.open("post", requestUrl, true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send("subject_Id=" + subject_Id + "&subject_Name="
				+ subject_Name + "&subject_Code=" + subject_Code + "&rbtn="
				+ rbtn);

	}
function loadMajor(){
    $.ajax({
        type: "GET",
        url: "base?module=major&action=combolist",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
     success: function (response) {
    	 var lastRowIndex = document.getElementById("registration_table").rows.length - 1;
    	 if(lastRowIndex!=0){
        	 var lastRow = document.getElementById("registration_table").rows[lastRowIndex];
        	 $('#majorCode'+ lastRowIndex).append($('<option />', { value: -1 , text: 'Select Major' }));
             $(response).each(function (index , response) {
            	 $('#majorCode'+ lastRowIndex).append($('<option />', { value: response.major_Id,  text: response.major_Code}));
             });
    	 }
       },
     error: function (data) {
         alert("Failed to get Major.");
     }
 });
}
function loadSubject(majorId){
  	 var lastRowIndex = document.getElementById("registration_table").rows.length - 1;
	 if(lastRowIndex!=0){
		  $('#campusSubjectId'+ lastRowIndex).children().remove()
		    $.ajax({
		        type: "GET",
		        url: "base?module=campusschedule&action=subjectmajorDetails&major_Id="+majorId,
		        contentType: "application/json; charset=utf-8",
		        dataType: "json",		
		        success: function (response) {
		        	 var lastRow = document.getElementById("registration_table").rows[lastRowIndex];
			       	 $('#campusSubjectId'+ lastRowIndex).append($('<option />', { value: -1 , text: 'Select Subject' }));
			         $(response).each(function (index , response) {
			         $('#campusSubjectId'+ lastRowIndex).append($('<option />', { value: response.campus_subject_seat_evaluation_Id, text: response.subject_Name +" "+ response.subject_Code +" "+response.section_Name }));
						});
					},
					error : function(data) {
						alert("Failed to get Subject.");
					}
				});
	 }
  
	}
function loadSubjectDayTime(campusSubjectId){
	var lastRowIndex = document.getElementById("registration_table").rows.length - 1;
	 if(lastRowIndex!=0){
	$('#dayTime'+ lastRowIndex).children().remove()
    $.ajax({
        type: "GET",
        url: "base?module=campusschedule&action=subjectScheduleDetails&campusSubjectId="+campusSubjectId,
        contentType: "application/json; charset=utf-8",
        dataType: "json",		
        success: function (response) {
       	 $('#dayTime'+ lastRowIndex).append($('<option />', { value: -1 , text: 'Select Day & Time' }));
            $(response).each(function (index , response) {
           	 $('#dayTime'+ lastRowIndex).append($('<option />', { value: response.schedule_Id, text: response.day +" "+ response.time }));
    		  $('#sectionId'+ lastRowIndex).val(response.section_Name) 	
			});
            
			},
			error : function(data) {
				alert("Failed to get Day and time.");
			}
		});
	 }
}
function findStudentById(){
	var nyit_Id = sessionStorage.getItem("SessionName");
	document.getElementById("search")["value"] = nyit_Id;
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var str = "N/A";
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
			if(response.home_phoneNo==0 && response.home_phoneNo==""){
			document.getElementById("homePhone").value=str;
			}else{
				document.getElementById("homePhone").value=response.home_phoneNo;
			}
			if(response.work_phoneNo==0 && response.work_phoneNo==""){
			document.getElementById("workPhone").value=str;
			}
			else{
				document.getElementById("workPhone").value=response.work_phoneNo;
			}
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
function saveStudentRequest(){
	var btnValue=document.getElementById("submit_B1").value;
	var requestUrl="base?module=studentrequest&action=new";
	if(btnValue=="Update"){
		requestUrl="base?module=studentrequest&action=update";
	}
	var xmlhttp = new XMLHttpRequest();
	var  dayTime = "";
	var student_Id=document.getElementById("student_Id").value;
	var nyit_Id=document.getElementById("nyit_Id").value;
	var counter = document.getElementById("registration_table").rows.length;
	for(var i=1;i<counter;i++){
		dayTime += document.getElementById("dayTime"+i).value;
		dayTime += ",";
		}
xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				sessionStorage.removeItem('SessionName');
				document.forms["studentfrm"].reset();
				document.forms["studentRequestform"].reset();
				document.getElementById("submit_B1").value="Save";
				document.getElementById("studentRequest_Id").value="";
			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("post",requestUrl,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("student_Id="+ student_Id + "&nyit_Id=" + nyit_Id + "&dayTime=" + dayTime);

}
jQuery(function ($){
   	var $inputs = $('#student-form :input').prop('disabled', true);
});  	
</script>
<body>

<div class="container">
<header>
<div class="row">
<div class="col-xs-12 col-md-12 col-sm-12">
<img src="./Images/logo-nyit-black.png" width="80px" height="80px"/> &nbsp; &nbsp; <span><b>Registration Form</b></span>
</div>
</div>
</header>
<section>
<form id="student-form" name="studentfrm" action="#" method="post">
<div class="row">
<div class="col-xs-6 col-md-6 col-sm-6">
<p style="padding-top:1%;">STUDENT INFORMATION</p></div>
<input type="hidden" name="student_Id" id="student_Id">

<div class="col-xs-5 col-md-5 col-sm-5"><div class="form-group"><div class="form-inline"><div class="col-md-0"> 
						<input type="text" id="search" name="search" class="form-control"
							placeholder="Search By Id" disabled>&nbsp;&nbsp;
						<input type="button" id="search" name="search" class="btn-primary"
							value="Search" onclick="javascript:FindStudentById();"></div></div></div></div>



<div class="col-xs-6 col-md-6 col-sm-6">
<P>Student ID &nbsp;&nbsp;&nbsp; <input type="text" name="nyit_Id" id="nyit_Id" placeholder="your student ID"></p></div>

<div class="col-xs-6 col-md-6 col-sm-6">
<p>NYIT Email Id&nbsp;&nbsp;&nbsp; <input type="text" id="student_Email" name="student_Email" placeholder="your student email ID"></p></div>

<div class="col-xs-6 col-md-6 col-sm-6">
<p>Last name &nbsp;&nbsp;&nbsp; <input type="text" id="student_Lname" name="student_Lname" placeholder="your last name"></p></div>
<div class="col-xs-6 col-md-6 col-sm-6">
<p>First name &nbsp;&nbsp;&nbsp; <input type="text" id="student_Fname" name="student_Fname" placeholder="your first name"></p></div>

<div class="col-xs-4 col-md-4 col-sm-4">
<p>Address &nbsp;&nbsp;&nbsp; <input type="text" id="address" name="address" placeholder="your address" maxlength="25" size="25"></p></div>
<div class="col-xs-8 col-md-8 col-sm-8">

<div class="col-xs-4 col-md-4 col-sm-4"><p>City <input type="text" id="city" name="city" placeholder="your city" maxlength="15" size="15"></p></div>

<div class="col-xs-4 col-md-4 col-sm-4 "><p>State <input type="text" id="state" name="state" placeholder="your state" maxlength="10" size="10"></p></div>

<div class="col-xs-4 col-md-4 col-sm-4"><p>Zip code <input type="text" id="zipcode" name="zipcode" placeholder="your zipcode" maxlength="11" size="11"></p></div>

</div>

<div class="col-xs-4 col-md-4 col-sm-4"><p>Cell phone <input type="text" id="cellPhone" name="cellPhone" maxlength="10" size="10"></p></div>


<div class="col-xs-4 col-md-4 col-sm-4"><p>Home phone <input type="text" id="homePhone" name="homePhone" maxlength="10" size="10"></p></div>

<div class="col-xs-4 col-md-4 col-sm-4"><p>Work phone <input type="text" id="workPhone" name="workPhone" maxlength="10" size="10"></p></div>


<div class="col-xs-12 col-md-12 col-sm-12"><p>Academic program <input type="text" id="academic" name="academic" placeholder="your academic program"></p></div>

<div class="col-xs-12 col-md-12 col-sm-12"><p>I am registering on this campus: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="ow"
							name="campus" value="OW"> Old Westbury &nbsp;&nbsp;&nbsp;
<input type="checkbox" id="manhattan" name="campus" value="MA"> Manhattan
</p><br></div>
</div>
<!-- <center><input type="button" name="studentInfo" id="studentInfo" value="SAVE" class="btn-info" onclick="validateForm();"/> <br><br><br></center> -->
</form>
<form id="studentRequestform" action="#" method="post"
			class="form-horizontal" name="studentRequestform">
			<div class="form-group">
				<table class="table table-bordered table-hover order-list" id="registration_table" style="border-color: black;">
					<thead>
						<tr>
							<th>TERM</th>
							<th>CLASS</th>
							<th style="width:150px;">MAJOR</th>
							<th style="width:250px;">SUBJECT AND COURSE#</th>
							<th>SECTION</th>
							<th style="width:150px;">DAY & TIME</th>
							<th>CREDIT</th>
							<th><input type="button"
								class="btn-primary" value="Add Subject" id="add_row" onclick="loadMajor()"></th>
						</tr>
					</thead>
					<tbody>
 						
					</tbody>
				</table>
			</div>

			<br>

			<div>
				<p>By authorizing a registration or by dropping and/or adding or
					withdrawing or being dismissed from the courses I registered for
					this semester, I agree to be charged in accordance with the
					schedule set forth in NYIT's online catalogs and nyit.edu with
					respect to payment of tuition and fees, refunds, dropping and
					adding courses, and, withdrawal and dismissal policies and
					procedures. I agree to be bound by this registration form and abide
					by NYIT's rules and regulations set forth in NYIT's online catalogs
					and nyit.edu. I agree to pay my debt to NYIT for any amounts due
					for tuition and fees and other charges. If my charges are not paid
					when due, I agree to pay NYIT all fees and costs associated with
					the collection of my delinquent account. In addition to payment of
					the principal amount due, the additional fees and costs may include
					collection agency fees constituting 33 to 50 percent of the
					principal amount due if NYIT engages a collection agency to collect
					payment; legal fees of 33.3 percent of the principal amount due if
					NYIT engages legal counsel to collect payment; any and all interest
					on the outstanding balance at the maximum legal rate allowed by law
					and; any and all other costs associated with collection of the
					amount due NYIT. I understand my obligation to pay these additional
					fees and costs associated with collection of my delinquent account.
				</p>
			</div>

			<br>

<div class="col-xs-6 col-md-6 col-sm-6">
<p>Student's Signature &nbsp;&nbsp;&nbsp; <input type="text" id="student_signature" name="student_signature" placeholder=""></p></div>
<div class="col-xs-6 col-md-6 col-sm-6">
<p>Date &nbsp;&nbsp;&nbsp; <input type="text" id="date" name="date" placeholder="mm/dd/yy"></p></div>

			<hr>
			<hr style="border: 3px solid black">
			<div>
				<hd>
				<b>IMPORTANT INFORMATION FOR FACULTY ADVISORS</b></hd>
				<p>To add a student to a closed class, waive a prerequisite,
					override class consent, or approve registration in a graduate
					course for an undergraduate student, the Authorization for
					Registration on the reverse side of this form must be completed and
					approved by the course chairperson. The chairperson will add the
					approval in NYIT Class Permissions.</p>
			</div>
			<br>
<div class="col-xs-12 col-md-12 col-sm-12"><p>Advisor's Printed name <input type="text" id="advisor_name" name="advisor_name" placeholder=""></p></div>



<div class="col-xs-6 col-md-6 col-sm-6">
<p>Advisor's Signature: MA, MS, CS &nbsp;&nbsp;&nbsp; <input type="text" id="advisor_signature" name="advisor_signature" placeholder=""></p></div>
<div class="col-xs-6 col-md-6 col-sm-6">
<p>Date &nbsp;&nbsp;&nbsp; <input type="text" id="date" name="date" placeholder="mm/dd/yy"></p></div>
			<br>
			<div>
				<hr>
				<p>Advisors must release student's advising hold on NYITConnect.</p>
				
					<b>nyit.edu</b> <br>
			</div>
			<div>
				<center>
					<input type="button" name="submit_B1" id="submit_B1"
						value="Register" class="btn-info" onclick="javascript:saveStudentRequest();" />
				</center>
				<br>
				<br>
				<br>
			</div>
		</form>
	</div>

<script type="text/javascript">
findStudentById();
</script>
</body>
</html>