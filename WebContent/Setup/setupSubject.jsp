<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setup Subject</title>
<script type="text/javascript">

//*** Functions of Subject ***//
//Save and Update Function

function saveSubjectDetails(){
	
	var btnValue=document.getElementById("btnSubjectSave").value;
	var requestUrl="base?module=subject&action=new";
	if(btnValue=="Update"){
		requestUrl="base?module=subject&action=update";
		document.getElementById('subject_radioBtn').style.visibility = "hidden";
	}
	var xmlhttp = new XMLHttpRequest();
	var  vals = "";
	var subject_Id=document.getElementById("subject_Id").value;
	var subject_Name=document.getElementById("subject_Name").value;
	var subject_Code=document.getElementById("subject_Code").value;
	var rbtn = document.getElementsByName("subject_rbtn");
	for(var i=0;i<rbtn.length;i++){
		if(rbtn[i].checked){
			 value=rbtn[i].value;
			 rbtn=value;
		}
	}
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				showSubjectDetails(1);
				document.forms["subjectfrm"].reset();
				document.getElementById("btnSubjectSave").value="Save";
				document.getElementById("subject_Id").value="";

			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("post",requestUrl,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("subject_Id="+ subject_Id + "&subject_Name=" + subject_Name + "&subject_Code=" + subject_Code +"&rbtn="+ rbtn);
	
}
// Display Subject Details Function
function showSubjectDetails(pageNo){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var tableHTML="";
			for(var it=0;it<response.pageData.length;it++){
				tableHTML+="<tr>";
				tableHTML+="<td>"+response.pageData[it].subject_Name+"</td>";
				tableHTML+="<td>"+response.pageData[it].subject_Code+"</td>";
				tableHTML+="<td>"+response.pageData[it].created_Date+"</td>";
				tableHTML+="<td>"+response.pageData[it].created_By+"</td>";
				if(response.pageData[it].status=="ACTIVE"){		
					tableHTML+="<td align=center class=text-success>"+response.pageData[it].status+"</td>";
					}
				else{
					tableHTML+="<td align=center class=text-danger>"+response.pageData[it].status+"</td>";					
					}
				tableHTML+="<td><a href='javascript:editSubject(" + response.pageData[it].subject_Id + ");'><i class='fa fa-pencil-square-o fa-lg' style='color:blue'></i></a>&nbsp;&nbsp;<a href='javascript:deleteSubject(" + response.pageData[it].subject_Id + ");'><i class='fa fa-trash-o fa-lg' style='color:red'></i></a></td>";
				tableHTML+="</tr>";
			}
			document.getElementById("subject_Data").innerHTML=tableHTML;
			showPages(response);
		}
	};
	xmlhttp.open("get","base?module=subject&action=pageData&pageNo="+ pageNo +"&pageSize=5",true);
	xmlhttp.send(null);
}
// Function for Displaying Page Footer
function showPages(response){
	var pageSize=response.pageSize;
	var pageNo=response.pageNo;
	var totalRecords=response.Total;
	var noOfpages=Math.ceil(totalRecords/pageSize);
	var footerHTML="";
	for(var it=1;it<=noOfpages;it++){
		footerHTML += "<td><a href='javascript:showSubjectDetails("+ it +");'>" + it + "</a></td>"; 
	}
	document.getElementById("subject_pages").innerHTML = footerHTML;
}

//Edit Subject
function editSubject(subject_Id){
	document.getElementById('subject_radioBtn').style.visibility = "visible";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			document.getElementById("subject_Name").value=response.subject_Name;
			document.getElementById("subject_Code").value=response.subject_Code;
			document.getElementById("subject_Id").value=response.subject_Id;
			if (response.status=="ACTIVE") {
				 document.getElementById('subject_Active').checked=response.status;
				}else{
					document.getElementById("subject_Inactive").checked=response.status;	
				}
			document.getElementById("btnSubjectSave").value="Update";
		}
	};
	xmlhttp.open("get","base?module=subject&action=getRowData&subject_Id="+ subject_Id,true);
	xmlhttp.send(null);
}
// Delete Subject
function deleteSubject(subject_Id){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				showSubjectDetails(1);
			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("get","base?module=subject&action=delete&subject_Id="+subject_Id,true);
	xmlhttp.send(null);
}	

</script>
</head>
<%@include file="/include/Header.jsp"%>
<body>
	<!-- Setup Subject -->
	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">Setup Subject</h3>
					<hr>
				</div>
			</div>
			<form id="subject-form" action="#" method="post"
				class="form-horizontal" name="subjectfrm">
				<div class="form-group">
					<input type="hidden" id="subject_Id" value="" />
				</div>
				<div class="form-group">
					<label for="subject_Name" class="control-label col-md-6"><strong>Subject
							Name</strong></label>
					<div class="col-md-4">
						<input type="text" id="subject_Name" name="subject_Name"
							class="form-control" placeholder=" Enter Subject Name">
					</div>
				</div>
				<div class="form-group">
					<label for="subject_Code" class="control-label col-md-6"><strong>Subject
							Code</strong></label>
					<div class="col-md-4">
						<input type="text" id="subject_Code" name="subject_Code"
							class="form-control" placeholder=" Enter Subject Code">
					</div>
				</div>
				<div class="form-group" id="subject_radioBtn" style="visibility:hidden;">
					<label for="section_status" class="control-label col-md-2"><strong>Status</strong></label>
					<div class="col-md-4">
						<label class="radio-inline"><input type="radio"
							id="subject_Active" name="subject_rbtn" value="1">Active</label> <label
							class="radio-inline"><input type="radio" id="subject_Inactive"
							name="subject_rbtn" value="2">Inactive</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-md-10">
						<input type="button" id="btnSubjectSave" class="btn btn-primary"
							value="Save" onclick="javascript:saveSubjectDetails();">
						<input type="reset" class="btn btn-primary" value="Cancel">
					</div>
				</div>

				<h2 align="center">Subject Details</h2>
				<table class="table table-bordered table-hover" id="subject_table">
					<thead>
						<tr>
							<th>SUBJECT NAME</th>
							<th>SUBJECT CODE</th>
							<th>CREATED DATE</th>
							<th>CREATED BY</th>
							<th>STATUS</th>
							<th>ACTION</th>
						</tr>
					</thead>
					<tbody id="subject_Data">

					</tbody>
					<tfoot id="subject_pagination">
						<tr id="subject_pages">
						</tr>
					</tfoot>
				</table>
			</form>
			
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.modal').on('hidden.bs.modal', function() {
		$(this).find('subjectfrm')[0].reset();
	});
});

</script>
<script type="text/javascript">
showSubjectDetails(1);
</script>
</body>
</html>