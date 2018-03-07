<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Request Details</title>
<script type="text/javascript">
function showStudentRequestDetails(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var tableHTML="";
			for(var it=0;it<response.pageData.length;it++){
				tableHTML+="<tr>";
				tableHTML+="<td align=center>"+response.pageData[it].student_nyit_Id+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].last_Name+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].first_Name+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].email_Id+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].academic+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].campus+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].section+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].Subject+"</td>";
				if(response.pageData[it].status=="pending"){
					tableHTML+="<td align=center class=text-primary>"+response.pageData[it].status+"</td>";					
					tableHTML+="<td><a href='javascript:approveRequest(" + response.pageData[it].student_Id + ");'<i class='fa fa-thumbs-o-up fa-2x' style='color:green'></i></a>&nbsp;<a href='javascript:deleteRequest(" + response.pageData[it].student_Id + ");'><i class='fa fa-ban fa-2x' style='color:red'></i>&nbsp;<a href='javascript:sendEmail("+ response.pageData[it].student_Id +");' id='#mymodal'><i class='fa fa-envelope-o fa-2x'></i></a></div></td>";
					}
				else if(response.pageData[it].status=="approved"){		
					tableHTML+="<td align=center class=text-success>"+response.pageData[it].status+"</td>";
					tableHTML+="<td><a href='javascript:deleteRequest(" + response.pageData[it].student_Id + ");'><i class='fa fa-ban fa-2x' style='color:red'></i>&nbsp;<a data-toggle='modal' href='#myModal'><i class='fa fa-envelope-o fa-2x'></i></a></div></td>";

					}
				else{
					tableHTML+="<td align=center class=text-danger>"+response.pageData[it].status+"</td>";	
					tableHTML+="<td><a data-toggle='modal' href='#myModal'><i class='fa fa-envelope-o fa-2x'></i></a></div></td>";

				}
				tableHTML+="</tr>";
			}
			document.getElementById("studentRequest_Data").innerHTML=tableHTML;
		}
	};
	xmlhttp.open("get","base?module=studentrequest&action=pageData",true);
	xmlhttp.send(null);
} 
//Approve Student Request
function approveRequest(student_Id){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = "Email sent Successfully";
				alert(response);
			}else{
				var response = "Oops Did not Complete your Request";
				alert(response);
			}
		}
	};
	xmlhttp.open("get","base?module=studentrequest&action=saveApprovedStudents&student_Id="+student_Id,true);
	xmlhttp.send(null);
}
function deleteRequest(student_Id){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = "Decline Successfully";
				alert(response);
			}else{
				var response = "Oops Did not Complete your Request";
				alert(response);
			}
		}
	};
	xmlhttp.open("get","base?module=studentrequest&action=updateStatus&student_Id="+student_Id,true);
	xmlhttp.send(null);
}
function sendEmail(student_Id){
	$(document).ready(function(){
	    $("#myModal").modal("show");
	    $("#myBtn").click(function(){
	        $("#myModal").modal("hide");
	    });
	});
var xmlhttp = new XMLHttpRequest();
var btnValue = document.getElementById("buttonSave").value;
var requestUrl = "base?module=studentrequest&action=declineStudent&student_Id="+student_Id;
var to = document.getElementById("to").value;
var message = document.getElementById("message").value;
document.getElementById("from").value = "soecsgrad@gmail.com";
xmlhttp.onreadystatechange = function() {
	if (xmlhttp.status == 200 && xmlhttp.readyState == 4) {
		var response = JSON.parse(xmlhttp.responseText);
		if (response.isSuccess) {
			var response = response.message;
			alert(response);
			document.getElementById("buttonSave").value = "Save";
		} else {
			var response = response.message;
			alert(response);
		}
	}
};
xmlhttp.open("post", requestUrl, true);
xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xmlhttp.send("student_Id=" + student_Id + "&from=" + from + "&to=" + to + "&message=" + message);

}
</script>
<style type="text/css">
table {
        display: block;
        overflow-x: auto;
        white-space: nowrap;
    }
</style>
</head>
<%@include file="/include/Header.jsp"%>
<body>
<div class="content-wrapper">
		<div class="container-fluid">
		<form>
		<h2 align="center">Student Request Information</h2>
		<table class="table table-bordered table-hover table-responsive" id="studentRequestTable">
					<thead>
						<tr>
							<th>NYIT_ID</th>
							<th>LAST_NAME</th>
							<th>FIRST_NAME</th>
							<th>EMAIL_ID</th>
							<th>ACADEMIC_PROGRAM</th>
							<th>CAMPUS</th>
							<th>SECTION</th>
							<th>SUBJECT</th>
							<th>STATUS</th>
							<th>ACTION</th>
						</tr>
					</thead>
					<tbody id="studentRequest_Data">

					</tbody>
					<tfoot id="studentRequest_pagination">
						<tr id="studentRequest_pages">
						
						</tr>
					</tfoot>
				</table>
		
		</form>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Decline Email</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">To</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="to" name = "to" placeholder="To Email">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">From</label>
								<div class="col-sm-10">
									<input type="email" class="form-control" id="from" name="from" placeholder="From Email">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">Message</label>
								<div class="col-sm-10">
								<textarea rows="4" cols="50" class="form-control" id="message" name="message"
										placeholder="Enter your message"></textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="button" class="btn btn-primary" id="buttonSave" value="Send" onclick="javascript:sendEmail();">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>
	</div>
<script type="text/javascript">
showStudentRequestDetails();
</script>
</body>
</html>