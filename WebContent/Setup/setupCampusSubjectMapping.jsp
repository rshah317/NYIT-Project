<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Campus Subject Mapping</title>
</head>
<script type="text/javascript">
// Script For Campus Subject Mapping
function saveCampusSubjectDetails(){
	var btnValue=document.getElementById("btnCampusSubjectSave").value;
	var requestUrl="base?module=campussubjectmapping&action=new";
	if(btnValue=="Update"){
		requestUrl="base?module=campussubjectmapping&action=update";
		document.getElementById('Campussubject_radioBtn').style.visibility = "hidden";
	}
	var xmlhttp = new XMLHttpRequest();
	var vals = "";
	var campusSubject_Id=document.getElementById("campusSubject_Id").value;
	var campusSelect=document.getElementById("campusSelect").value;
	var subjectSelect=document.getElementById("subjectSelect").value;
	var majorSelect=document.getElementById("majorSelect").value;
	var campussubject_rbtn = document.getElementsByName("campussubject_rbtn");
	for(var i=0;i<campussubject_rbtn.length;i++){
		if(campussubject_rbtn[i].checked){
			 value=campussubject_rbtn[i].value;
			 campussubject_rbtn=value;
		}
	}
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				showCampusSubjectMappingDetails(1);
				document.forms["campusSubjectFrm"].reset();
				document.getElementById("btnCampusSubjectSave").value="Save";
				document.getElementById("campusSubject_Id").value="";

			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("post",requestUrl,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("campusSubject_Id="+ campusSubject_Id + "&campusSelect=" + campusSelect +"&subjectSelect="+ subjectSelect + "&majorSelect="+ majorSelect +"&campussubject_rbtn="+ campussubject_rbtn);
}
function showCampusSubjectMappingDetails(pageNo){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var tableHTML="";
			for(var it=0;it<response.pageData.length;it++){
				tableHTML+="<tr>";
				tableHTML+="<td align=center>"+response.pageData[it].campus_Name+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].subject_Name+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].subject_Code+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].major_Code+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].created_Date+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].created_By+"</td>";
				
				if(response.pageData[it].status=="ACTIVE"){		
					tableHTML+="<td align=center class=text-success>"+response.pageData[it].status+"</td>";
					}
				else{
					tableHTML+="<td align=center class=text-danger>"+response.pageData[it].status+"</td>";					
					}
				tableHTML+="<td><a href='javascript:editCampusSubjectMapping(" + response.pageData[it].campusSubject_Id + ");'><i class='fa fa-pencil-square-o fa-lg' style='color:blue'></i></a>&nbsp;&nbsp;<a href='javascript:deleteCampusSubjectMapping(" + response.pageData[it].campusSubject_Id + ");'><i class='fa fa-trash-o fa-lg' style='color:red'></i></a></td>";
				tableHTML+="</tr>";
			}
			document.getElementById("campus_subject_Data").innerHTML=tableHTML;
			showCampusSubjectPages(response);
		}
	};
	xmlhttp.open("get","base?module=campussubjectmapping&action=pageData&pageNo="+ pageNo +"&pageSize=5",true);
	xmlhttp.send(null);
} 
// Display Page Footer Details 

function showCampusSubjectPages(response){
	var pageSize=response.pageSize;
	var pageNo=response.pageNo;
	var totalRecords=response.Total;
	var noOfpages=Math.ceil(totalRecords/pageSize);
	var footerHTML="";
	for(var it=1;it<=noOfpages;it++){
		footerHTML += "<td><a href='javascript:showCampusSubjectMappingDetails("+ it +");'>" + it + "</a></td>"; 
	}
	document.getElementById("campus_subject_pages").innerHTML=footerHTML;
}
// Edit Campus Subject Function

function editCampusSubjectMapping(campusSubject_Id){
	document.getElementById('Campussubject_radioBtn').style.visibility = "visible";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			document.getElementById("campusSelect").value=response.campus_Id;
			document.getElementById("subjectSelect").value=response.subject_Id;
			document.getElementById("campusSubject_Id").value=response.campusSubject_Id;
			document.getElementById("majorSelect").value=response.major_Id;
			if (response.status=="ACTIVE") {
				 document.getElementById('campussubject_Active').checked=response.status;
				}else{
					document.getElementById("campussubject_Inactive").checked=response.status;	
				}
			document.getElementById("btnCampusSubjectSave").value="Update";
		}
	};
	xmlhttp.open("get","base?module=campussubjectmapping&action=getRowData&campusSubject_Id="+ campusSubject_Id,true);
	xmlhttp.send(null);
}
// Delete Campus Subject Mapping Function 

function deleteCampusSubjectMapping(campusSubject_Id){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				showCampusSubjectMappingDetails(1);
			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("get","base?module=campussubjectmapping&action=delete&campusSubject_Id="+ campusSubject_Id,true);
	xmlhttp.send(null);
}


//*** Campus Script ****

function saveCampusDetails(){
	var btnValue=document.getElementById("btnSave").value;
	var requestUrl="base?module=campus&action=new";
	if(btnValue=="Update"){
		requestUrl="base?module=campus&action=update";
		document.getElementById('radioBtn').style.visibility = "hidden";
	}
	var xmlhttp = new XMLHttpRequest();
	var campus_Id=document.getElementById("campus_Id").value;
	var campus_Name=document.getElementById("campus_Name").value;
	var rbtn = document.getElementsByName("rbtn");
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
				showCampusDetails(1);
				document.forms["campusfrm"].reset();
				document.getElementById("btnSave").value="Save";
				document.getElementById("campus_Id").value="";

			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("post",requestUrl,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("campus_Id="+ campus_Id + "&campus_Name=" + campus_Name + "&rbtn="+ rbtn);
}
function showCampusDetails(pageNo){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var tableHTML="";
			for(var it=0;it<response.pageData.length;it++){
				tableHTML+="<tr>";
				tableHTML+="<td align=center>"+response.pageData[it].campus_Name+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].created_Date+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].created_By+"</td>";
				if(response.pageData[it].status=="ACTIVE"){		
					tableHTML+="<td align=center class=text-success>"+response.pageData[it].status+"</td>";
					}
				else{
					tableHTML+="<td align=center class=text-danger>"+response.pageData[it].status+"</td>";					
					}
				tableHTML+="<td><a href='javascript:editCampus(" + response.pageData[it].campus_Id + ");'><i class='fa fa-pencil-square-o fa-lg' style='color:blue'></i></a>&nbsp;&nbsp;<a href='javascript:deleteCampus(" + response.pageData[it].campus_Id + ");'><i class='fa fa-trash-o fa-lg' style='color:red'></i></a></td>";
				tableHTML+="</tr>";
			}
			document.getElementById("campus_Data").innerHTML=tableHTML;
			showPages(response);
		}
	};
	xmlhttp.open("get","base?module=campus&action=pageData&pageNo="+ pageNo +"&pageSize=5",true);
	xmlhttp.send(null);
}
function showPages(response){
	var pageSize=response.pageSize;
	var pageNo=response.pageNo;
	var totalRecords=response.Total;
	var noOfpages=Math.ceil(totalRecords/pageSize);
	var footerHTML="";
	for(var it=1;it<=noOfpages;it++){
		footerHTML += "<td><a href='javascript:showCampusDetails("+ it +");'>" + it + "</a></td>"; 
	}
	document.getElementById("pages").innerHTML= footerHTML;
}
function editCampus(campus_Id){
	document.getElementById('radioBtn').style.visibility = "visible";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			document.getElementById("campus_Name").value=response.campus_Name;
			document.getElementById("campus_Id").value=response.campus_Id;
			if (response.status=="ACTIVE") {
				 document.getElementById('active').checked=response.status;
				}else{
					document.getElementById("inactive").checked=response.status;	
				}
			document.getElementById("btnSave").value="Update";
		}
	};
	xmlhttp.open("get","base?module=campus&action=getRowData&campus_Id="+ campus_Id,true);
	xmlhttp.send(null);
}
function deleteCampus(campus_Id){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				showCampusDetails(1);
			}else{
				var response = response.message;
				alert(JSON.stringify(response));
			}
		}
	};
	xmlhttp.open("get","base?module=campus&action=delete&campus_Id="+campus_Id,true);
	xmlhttp.send(null);
}
function loadCampus(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var campusSelect = document.getElementById("campusSelect");
			for(var it=0;it<response.length;it++){
				var option = document.createElement("option");
				option.value=response[it].campus_Id;
				option.innerHTML=response[it].campus_Name;
				campusSelect.appendChild(option);
			}
		}
	}; 
	xmlhttp.open("get","base?module=campus&action=combolist");
	xmlhttp.send(null);
}
// **** Subject Script ****

function loadSubject(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var subjectSelect = document.getElementById("subjectSelect");
			for(var it=0;it<response.length;it++){
				var option = document.createElement("option");
				option.value=response[it].subject_Id;
				option.innerHTML=response[it].subject_Name + " " + response[it].subject_Code;
				subjectSelect.appendChild(option);
			}
		}
	}; 
	xmlhttp.open("get","base?module=subject&action=combolist");
	xmlhttp.send(null);
}


// Major Script

//**** Functions for Major ****//

//Save and Update Function
function saveMajorDetails(){
	var btnValue=document.getElementById("btnSave").value;
	var requestUrl="base?module=major&action=new";
	if(btnValue=="Update"){
		requestUrl="base?module=major&action=update";
		document.getElementById('radioBtn').style.visibility = "hidden";
	}
	var xmlhttp = new XMLHttpRequest();
	var major_Id=document.getElementById("major_Id").value;
	var major_Name=document.getElementById("major_Name").value;
	var major_Code=document.getElementById("major_Code").value;
	var rbtn = document.getElementsByName("rbtn");
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
				showMajorDetails(1);
				document.forms["majorfrm"].reset();
				document.getElementById("btnSave").value="Save";
				document.getElementById("major_Id").value="";

			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("post",requestUrl,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("major_Id="+ major_Id + "&major_Name=" + major_Name + "&major_Code=" + major_Code + "&rbtn="+ rbtn);
}

// Display Major Details 
function showMajorDetails(pageNo){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var tableHTML="";
			for(var it=0;it<response.pageData.length;it++){
				tableHTML+="<tr>";
				tableHTML+="<td align=center>"+response.pageData[it].major_Name+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].major_Code+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].created_Date+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].created_By+"</td>";
				if(response.pageData[it].status=="ACTIVE"){		
					tableHTML+="<td align=center class=text-success>"+response.pageData[it].status+"</td>";
					}
				else{
					tableHTML+="<td align=center class=text-danger>"+response.pageData[it].status+"</td>";					
					}
				tableHTML+="<td><a href='javascript:editMajor(" + response.pageData[it].major_Id + ");'><i class='fa fa-pencil-square-o fa-lg' style='color:blue'></i></a>&nbsp;&nbsp;<a href='javascript:deleteMajor(" + response.pageData[it].major_Id + ");'><i class='fa fa-trash-o fa-lg' style='color:red'></i></a></td>";
				tableHTML+="</tr>";
			}
			document.getElementById("major_Data").innerHTML=tableHTML;
			showMajorPages(response);
		}
	};
	xmlhttp.open("get","base?module=major&action=pageData&pageNo="+ pageNo +"&pageSize=5",true);
	xmlhttp.send(null);
} 
// Display Page Footer Details 

function showMajorPages(response){
	var pageSize=response.pageSize;
	var pageNo=response.pageNo;
	var totalRecords=response.Total;
	var noOfpages=Math.ceil(totalRecords/pageSize);
	var footerHTML="";
	for(var it=1;it<=noOfpages;it++){
		footerHTML += "<td><a href='javascript:showMajorDetails("+ it +");'>" + it + "</a></td>"; 
	}
	document.getElementById("major_pages").innerHTML=footerHTML;
}
// Edit Major Function

function editMajor(major_Id){
	document.getElementById('MajorradioBtn').style.visibility = "visible";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			document.getElementById("major_Name").value=response.major_Name;
			document.getElementById("major_Code").value=response.major_Code;
			document.getElementById("major_Id").value=response.major_Id;
			if (response.status=="ACTIVE") {
				 document.getElementById('major_Active').checked=response.status;
				}else{
					document.getElementById("major_Inactive").checked=response.status;	
				}
			document.getElementById("btnSave").value="Update";
		}
	};
	xmlhttp.open("get","base?module=major&action=getRowData&major_Id="+ major_Id,true);
	xmlhttp.send(null);
}
// Delete Major Function 

function deleteMajor(major_Id){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				showMajorDetails(1);
			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("get","base?module=major&action=delete&major_Id="+major_Id,true);
	xmlhttp.send(null);
}	

// *** Function for Loading Major in Combo box *** //
function loadMajor(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var majorSelect=document.getElementById("majorSelect");
			for(it=0;it<response.length;it++){
			    var option = document.createElement("option");
			     option.value = response[it].major_Id;
			     option.innerHTML=response[it].major_Code;
    			 majorSelect.appendChild(option);
			}
		} 
	};
	xmlhttp.open("get","base?module=major&action=combolist");
	xmlhttp.send(null);
}

</script>
<%@include file="/include/Header.jsp"%>
<body>
	<!-- Campus Dropdown  -->
	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">Setup Campus Subject Mapping</h3>
					<hr>
				</div>
			</div>
			<form id="campus-form" action="#" method="post"
				class="form-horizontal" name="campusSubjectFrm">
				<div class="form-group">
					<input type="hidden" id="campusSubject_Id" value="" />
				</div>
				<div class="form-group">
					<label for="campus_Id" class="control-label col-md-6"><strong>Campus
							Name</strong></label>
					<div class="col-md-5">
						<div class="input-group">
							<select class="form-control" id="campusSelect" name="campusSelect">
							</select>&nbsp;&nbsp;&nbsp;
							<div>
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#btnCampus">Add
									Campus</button>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="subject_Id" class="control-label col-md-6"><strong>Subject
							Name</strong></label>
					<div class="col-md-5">
						<div class="input-group">
							<select class="form-control" id="subjectSelect" name="subjectSelect">
							</select>&nbsp;&nbsp;&nbsp;
							<div>
								<a href="base?module=subject&action=view" class="btn btn-primary" >Add Subject</a>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="major_Id" class="control-label col-md-6"><strong>Major
							Code</strong></label>
					
					<div class="col-md-4">
						<div class="input-group">
						<select class="form-control" id="majorSelect" name="majorSelect">
						</select>&nbsp;&nbsp;&nbsp;
							<div>
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#btnMajor">Add Major</button>
							</div>
						</div>	

							
						</div>
					</div>
				<div class="form-group" id="Campussubject_radioBtn" style="visibility:hidden;">
					<label for="section_status" class="control-label col-md-2"><strong>Status</strong></label>
					<div class="col-md-4">
						<label class="radio-inline"><input type="radio"
							id="campussubject_Active" name="campussubject_rbtn" value="1">Active</label> <label
							class="radio-inline"><input type="radio" id="campussubject_Inactive"
							name="campussubject_rbtn" value="2">Inactive</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-md-10">
						<input type="button" id="btnCampusSubjectSave" class="btn btn-primary"
							value="Save" onclick="javascript:saveCampusSubjectDetails();">
						<input type="reset" class="btn btn-primary" value="Cancel" onclick="window.location.reload();">
					</div>
				</div>

				<h2 align="center">Campus Subject Mapping Details</h2>
				<table class="table table-bordered table-hover table-responsive" id="campus_subject_table">
					<thead>
						<tr>
							<th>CAMPUS_NAME</th>
							<th>SUBJECT_NAME</th>
							<th>SUBJECT_CODE</th>
							<th>MAJOR_CODE</th>
							<th>CREATED_DATE</th>
							<th>CREATED_BY</th>
							<th>STATUS</th>
							<th>ACTION</th>
						</tr>
					</thead>
					<tbody id="campus_subject_Data">

					</tbody>
					<tfoot id="campus_subject_pagination">
						<tr id="campus_subject_pages">
						</tr>
					</tfoot>
				</table>
			</form>
			<!-- Campus Modal  -->
			<div class="modal fade" id="btnCampus" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Setup Campus</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<form id="campusfrm" action="#" method="post"
								class="form-horizontal" name="campusfrm">
								<div class="form-group">
									<input type="hidden" id="campus_Id" name="campus_Id" value="" />
								</div>
								<div class="form-group">
									<label for="campus_Name" class="control-label col-md-6"><strong>Campus
											Name</strong></label>
									<div class="col-md-4">
										<input type="text" id="campus_Name" name="campus_Name"
											class="form-control" placeholder="Enter Campus">
									</div>
								</div>
								<div class="form-group" id="radioBtn" style="visibility:hidden;">
									<label for="section_status" class="control-label col-md-2"><strong>Status</strong></label>
									<div class="col-md-4">
										<label class="radio-inline"><input type="radio"
											id="active" name="rbtn" value="1">Active</label> <label
											class="radio-inline"><input type="radio"
											id="inactive" name="rbtn" value="2">Inactive</label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-offset-2 col-md-10">
										<input type="button" id="btnSave" class="btn btn-primary"
											value="save" onclick="javascript:saveCampusDetails();">
										<input type="reset" class="btn btn-primary" value="Cancel" onclick="window.location.reload();">
									</div>
								</div>
								<h2 align="center">Campus Details</h2>
								<table class="table table-bordered table-hover table-responsive" id="campus_Table">
									<thead>
										<tr>
											<th>CAMPUS NAME</th>
											<th>CREATED DATE</th>
											<th>CREATED BY</th>
											<th>STATUS</th>
											<th>ACTION</th>
										</tr>
									</thead>
									<tbody id="campus_Data">

									</tbody>
									<tfoot id="pagination">
										<tr id="pages">
										</tr>
									</tfoot>
								</table>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
				<!-- **** Major Modal **** -->
			
			<div class="modal fade" id="btnMajor" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Setup Major</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<form id="major-form" action="#" method="post"
								class="form-horizontal" name="majorfrm">
								<div class="form-group">
									<input type="hidden" id="major_Id" value="" />
								</div>
								<div class="form-group">
									<label for="major_Name" class="control-label col-md-6"><strong>Major
											Name</strong></label>
									<div class="col-md-4">
										<input type="text" id="major_Name" name="major_Name"
											class="form-control" placeholder=" Enter Major">
									</div>
								</div>
								<div class="form-group">
									<label for="major_Code" class="control-label col-md-6"><strong>Major
											Code</strong></label>
									<div class="col-md-4">
										<input type="text" id="major_Code" name="major_Code"
											class="form-control" placeholder=" Enter Major Code">
									</div>
								</div>
								<div class="form-group" id="MajorradioBtn" style="visibility:hidden;">
									<label for="major_status" class="control-label col-md-2"><strong>Status</strong></label>
									<div class="col-md-4">
										<label class="radio-inline"><input type="radio"
											id="major_Active" name="rbtn" value="1" >Active</label>
										<label class="radio-inline"><input type="radio"
											id="major_Inactive" name="rbtn" value="2">Inactive</label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-offset-2 col-md-10">
										<input type="button" id="btnSave" class="btn btn-primary"
											value="save" onclick="javascript:saveMajorDetails();">
										<input type="reset" class="btn btn-primary" value="Cancel" onclick="window.location.reload();">
									</div>
								</div>
								<div class="container">
								<h2 align="center">Major Details</h2>
								<table class="table table-bordered table-hover table-responsive" id="major_table">
									<thead>
										<tr align="center">
											<th>MAJOR NAME</th>
											<th>MAJOR_CODE</th>
											<th>CREATED_DATE</th>
											<th>CREATED BY</th>
											<th>STATUS</th>
											<th>ACTION</th>
										</tr>
									</thead>
									<tbody id="major_Data">

									</tbody>
									<tfoot id="pagination">
										<tr id="major_pages">
										</tr>
									</tfoot>
								</table>
							  </div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			
			<!-- *** Major Modal ends **** -->
		</div>
	</div>
<script type="text/javascript">
		$(document).ready(function() {
			$('.modal').on('hidden.bs.modal', function() {
				$(this).find('form')[0].reset();
			});
		});
		$(document).ready(function() {	
			$('#btnCampus').on('hidden.bs.modal', function (e) {
			    window.location.reload();
				});
			});	

		$(document).ready(function() {	
			$('#btnMajor').on('hidden.bs.modal', function (e) {
			    window.location.reload();
				});
			});
			
</script>
<script type="text/javascript">
showCampusSubjectMappingDetails(1);
showCampusDetails(1);
showMajorDetails(1);
loadMajor();
loadCampus();
loadSubject();
</script>
</body>
</html>