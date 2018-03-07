<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Section Subject Evaluation</title>
</head>
<script type="text/javascript">
//*** Script For Section ***
function saveSectionDetails(){
	var btnValue=document.getElementById("btnSaveSection").value;
	var requestUrl="base?module=section&action=new";
	if(btnValue=="Update"){
		requestUrl="base?module=section&action=update";
		document.getElementById('Section_radioBtn').style.visibility = "hidden";
	}
	var xmlhttp = new XMLHttpRequest();
	var section_Id=document.getElementById("section_Id").value;
	var section_Name=document.getElementById("section_Name").value;
	var rbtn = document.getElementsByName("rbtn_section");
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
				showSectionDetails(1);
				document.forms["sectionfrm"].reset();
				document.getElementById("btnSaveSection").value="Save";
				document.getElementById("section_Id").value="";

			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("post",requestUrl,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("section_Id="+ section_Id + "&section_Name=" + section_Name +"&rbtn="+ rbtn);
	
}
// Display Section Details Function
function showSectionDetails(pageNo){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var tableHTML="";
			for(var it=0;it<response.pageData.length;it++){
				tableHTML+="<tr>";
				tableHTML+="<td>"+response.pageData[it].section_Name+"</td>";
				tableHTML+="<td>"+response.pageData[it].created_Date+"</td>";
				tableHTML+="<td>"+response.pageData[it].created_By+"</td>";
				if(response.pageData[it].status=="ACTIVE"){		
					tableHTML+="<td align=center class=text-success>"+response.pageData[it].status+"</td>";
					}
				else{
					tableHTML+="<td align=center class=text-danger>"+response.pageData[it].status+"</td>";					
					}
				tableHTML+="<td><a href='javascript:editSection(" + response.pageData[it].section_Id + ");'><i class='fa fa-pencil-square-o fa-lg' style='color:blue'></i></a>&nbsp;&nbsp;<a href='javascript:deleteSection(" + response.pageData[it].section_Id + ");'><i class='fa fa-trash-o fa-lg' style='color:red'></i></a></td>";
				tableHTML+="</tr>";
			}
			document.getElementById("section_Data").innerHTML=tableHTML;
			showPages(response);
		}
	};
	xmlhttp.open("get","base?module=section&action=pageData&pageNo="+ pageNo +"&pageSize=5",true);
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
		footerHTML += "<td><a href='javascript:showSectionDetails("+ it +");'>" + it + "</a></td>"; 
	}
	document.getElementById("section_pages").innerHTML = footerHTML;
}

//Edit Section
function editSection(section_Id){
	document.getElementById('Section_radioBtn').style.visibility = "visible";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			document.getElementById("section_Name").value=response.section_Name;
			document.getElementById("section_Id").value=response.section_Id;
			if (response.status=="ACTIVE") {
				 document.getElementById('section_Active').checked=response.status;
				}else{
					document.getElementById("section_Inactive").checked=response.status;	
				}
			document.getElementById("btnSaveSection").value="Update";
		}
	};
	xmlhttp.open("get","base?module=section&action=getRowData&section_Id="+ section_Id,true);
	xmlhttp.send(null);
}
// Delete Section
function deleteSection(section_Id){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				showSectionDetails(1);
			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("get","base?module=section&action=delete&section_Id="+section_Id,true);
	xmlhttp.send(null);
}
// Load Section
function loadSection(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var sectionSelect=document.getElementById("checkBoxSection_Id");
			for(it=0;it<response.length;it++){
 				var checkbox = document.createElement('input');
 				var label = document.createElement('label');
 			 	var myBr = document.createElement('br');
 				 checkbox.type ='checkbox';
				 checkbox.name ='section';
				 checkbox.id = 'section'; 
 				 checkbox.value = response[it].section_Id;
     			 label.innerHTML=response[it].section_Name + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
     			sectionSelect.appendChild(checkbox);
     			sectionSelect.appendChild(label);
			}
		} 
	};
	xmlhttp.open("get","base?module=section&action=combolist");
	xmlhttp.send(null);
}

// *** Section Script Ends ***

//*** Combo box for Campus Subject 
function loadCampusSubject(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var campusSubject = document.getElementById("campusSubject");
			for(var it=0;it<response.length;it++){
				var option = document.createElement("option");
				option.value=response[it].campusSubject_Id;
				option.innerHTML=response[it].campus_Name +" - " + response[it].subject_Code + " - " + response[it].major_Code +" - "+ response[it].subject_Name;
				campusSubject.appendChild(option);
			}
		}
	}; 
	xmlhttp.open("get","base?module=campussubjectmapping&action=combolist");
	xmlhttp.send(null);
}

// *** Section Subject Scripts 
function saveSectionSubjectDetails(){
	var btnValue=document.getElementById("btnSectionSubject").value;
	var requestUrl="base?module=sectionsubjectmapping&action=new";
	if(btnValue=="Update"){
		requestUrl="base?module=sectionsubjectmapping&action=update";
		document.getElementById('Sectionsubject_radioBtn').style.visibility = "hidden";
	}
	var xmlhttp = new XMLHttpRequest();
	var vals = "";
	var sectionSubjectSchedule_Id=document.getElementById("sectionSubjectSchedule_Id").value;
	var campusSubjectSelect=document.getElementById("campusSubject").value;
	var checkBoxValue=document.getElementsByName("section");
	var sectionsubject_rbtn = document.getElementsByName("sectionsubject_rbtn");
	for(var i=0;i<sectionsubject_rbtn.length;i++){
		if(sectionsubject_rbtn[i].checked){
			 value=sectionsubject_rbtn[i].value;
			 sectionsubject_rbtn=value;
		}
	}
	for(var j=0;j<checkBoxValue.length;j++){
		if(checkBoxValue[j].checked){
			vals += checkBoxValue[j].value + ",";	 
	    }
	}
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				showSectionSubjectMappingDetails(1);
				document.forms["sectionSubjectfrm"].reset();
				document.getElementById("btnSectionSubject").value="Save";
				document.getElementById("sectionSubjectSchedule_Id").value="";

			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("post",requestUrl,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("sectionSubjectSchedule_Id="+ sectionSubjectSchedule_Id + "&campusSubjectSelect=" + campusSubjectSelect + "&checkBoxValue="+ vals + "&sectionsubject_rbtn=" + sectionsubject_rbtn);
}
function showSectionSubjectMappingDetails(pageNo){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var tableHTML="";
			for(var it=0;it<response.pageData.length;it++){
				tableHTML+="<tr>";
				tableHTML+="<td align=center>"+response.pageData[it].section_Name+"</td>";
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
				tableHTML+="<td><a href='javascript:editSectionSubjectMapping(" + response.pageData[it].sectionSubjectSchedule_Id + ");'><i class='fa fa-pencil-square-o fa-lg' style='color:blue'></i></a>&nbsp;&nbsp;<a href='javascript:deleteSectionSubjectMapping(" + response.pageData[it].sectionSubjectSchedule_Id + ");'><i class='fa fa-trash-o fa-lg' style='color:red'></i></a></td>";
				tableHTML+="</tr>";
			}
			document.getElementById("subject_section_Data").innerHTML=tableHTML;
			showSectionSubjectPages(response);
		}
	};
	xmlhttp.open("get","base?module=sectionsubjectmapping&action=pageData&pageNo="+ pageNo +"&pageSize=5",true);
	xmlhttp.send(null);
} 
// Display Page Footer Details 

function showSectionSubjectPages(response){
	var pageSize=response.pageSize;
	var pageNo=response.pageNo;
	var totalRecords=response.Total;
	var noOfpages=Math.ceil(totalRecords/pageSize);
	var footerHTML="";
	for(var it=1;it<=noOfpages;it++){
		footerHTML += "<td><a href='javascript:showSectionSubjectMappingDetails("+ it +");'>" + it + "</a></td>"; 
	}
	document.getElementById("SectionSubject_pages").innerHTML=footerHTML;
}
// Edit Section Subject Function

function editSectionSubjectMapping(sectionSubjectSchedule_Id){
	document.getElementById('Sectionsubject_radioBtn').style.visibility = "visible";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			document.getElementById("sectionSubjectSchedule_Id").value=response.sectionSubjectSchedule_Id;
			document.getElementById("campusSubject").value=response.campusSubject_Id;
			if(response.section_Name!=null){
             document.querySelectorAll("input[type='checkbox'][value='"+ response.section_Name + "']")[0].checked=true;
			}
			if (response.status=="ACTIVE") {
				 document.getElementById('sectionsubject_Active').checked=response.status;
				}else{
					document.getElementById("sectionsubject_Inactive").checked=response.status;	
				}
			document.getElementById("btnSectionSubject").value="Update";
		}
	};
	xmlhttp.open("get","base?module=sectionsubjectmapping&action=getRowData&sectionSubjectSchedule_Id="+ sectionSubjectSchedule_Id,true);
	xmlhttp.send(null);
}
// Delete Section Subject Mapping Function 

function deleteSectionSubjectMapping(sectionSubjectSchedule_Id){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				showSectionSubjectMappingDetails(1);
			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("get","base?module=sectionsubjectmapping&action=delete&sectionSubjectSchedule_Id="+ sectionSubjectSchedule_Id,true);
	xmlhttp.send(null);
}

// *** Combo box Subject Section Script

function loadSectionSubject(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var sectionsubjectSelect = document.getElementById("sectionsubjectSelect");
			for(var it=0;it<response.length;it++){
				var option = document.createElement("option");
				option.value=response[it].sectionSubjectSchedule_Id;
				option.innerHTML=response[it].section_Name +" - " + response[it].subject_Code + " - " + response[it].major_Code +" - "+ response[it].subject_Name;
				sectionsubjectSelect.appendChild(option);
			}
		}
	}; 
	xmlhttp.open("get","base?module=sectionsubjectmapping&action=combolist");
	xmlhttp.send(null);
}
 // *** Script for Seat Evaluation
 
 function saveSectionCampusSeatDetails(){
	 var btnValue=document.getElementById("btnSaveSectionSeat").value;
		var requestUrl="base?module=campussectionseatevaluation&action=new";
		if(btnValue=="Update"){
			requestUrl="base?module=campussectionseatevaluation&action=update";
			document.getElementById('Sectionseat_radioBtn').style.visibility = "hidden";
		}
		var xmlhttp = new XMLHttpRequest();
		var sectionSubjectSeatEvaluation_Id=document.getElementById("sectionSubjectSeatEvaluation_Id").value;
		var sectionsubjectSelect=document.getElementById("sectionsubjectSelect").value;
		var total_seats=document.getElementById("total_seats").value;
		var start_date=document.getElementById("start_date").value;
		var end_date=document.getElementById("end_date").value;
		var rbtn = document.getElementsByName("sectionseat_rbtn");
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
					showSectionSeatDetails(1);
					document.forms["seatfrm"].reset();
					document.getElementById("btnSaveSectionSeat").value="Save";
					document.getElementById("sectionSubjectSeatEvaluation_Id").value="";

				}else{
					var response = response.message;
					alert(response);
				}
			}
		};
		xmlhttp.open("post",requestUrl,true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.send("sectionSubjectSeatEvaluation_Id="+ sectionSubjectSeatEvaluation_Id + "&sectionsubjectSelect=" + sectionsubjectSelect + "&total_seats=" + total_seats + "&start_date=" + start_date + "&end_date=" + end_date + "&sectionseat_rbtn="+ rbtn);
		
 }
 function showSectionSeatDetails(pageNo){
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.status==200 && xmlhttp.readyState==4){
				var response = JSON.parse(xmlhttp.responseText);
				var tableHTML="";
				for(var it=0;it<response.pageData.length;it++){
					tableHTML+="<tr>";
					tableHTML+="<td align=center>"+response.pageData[it].section_Name+"</td>";
					tableHTML+="<td align=center>"+response.pageData[it].subject_Name+"</td>";
					tableHTML+="<td align=center>"+response.pageData[it].subject_Code+"</td>";
					tableHTML+="<td align=center>"+response.pageData[it].major_Code+"</td>";
					tableHTML+="<td align=center>"+response.pageData[it].total_seats+"</td>";
					tableHTML+="<td align=center>"+response.pageData[it].seats_available+"</td>";
					tableHTML+="<td align=center>"+response.pageData[it].start_Date+"</td>";
					tableHTML+="<td align=center>"+response.pageData[it].end_Date+"</td>";
					
					if(response.pageData[it].status=="ACTIVE"){		
						tableHTML+="<td align=center class=text-success>"+response.pageData[it].status+"</td>";
						}
					else{
						tableHTML+="<td align=center class=text-danger>"+response.pageData[it].status+"</td>";					
						}
					tableHTML+="<td><a href='javascript:editSectionSeatEvaluation(" + response.pageData[it].campus_subject_seat_evaluation_Id + ");'><i class='fa fa-pencil-square-o fa-lg' style='color:blue'></i></a>&nbsp;&nbsp;<a href='javascript:deleteSectionSeatEvaluation(" + response.pageData[it].campus_subject_seat_evaluation_Id + ");'><i class='fa fa-trash-o fa-lg' style='color:red'></i></a></td>";
					tableHTML+="</tr>";
				}
				document.getElementById("seat_Evaluation_Data").innerHTML=tableHTML;
				showCampusSubjectPages(response);
			}
		};
		xmlhttp.open("get","base?module=campussectionseatevaluation&action=pageData&pageNo="+ pageNo +"&pageSize=5",true);
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
			footerHTML += "<td><a href='javascript:showSectionSeatDetails("+ it +");'>" + it + "</a></td>"; 
		}
		document.getElementById("subject_seat_pages").innerHTML=footerHTML;
	}
	// Edit Section Seat Function

	function editSectionSeatEvaluation(sectionSubjectSeatEvaluation_Id){
		document.getElementById('Sectionseat_radioBtn').style.visibility = "visible";
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.status==200 && xmlhttp.readyState==4){
				var response = JSON.parse(xmlhttp.responseText);
				document.getElementById("sectionsubjectSelect").value=response.section_subject_schedule_Id;
				document.getElementById("total_seats").value=response.total_seats;
				document.getElementById("start_date").value=response.start_Date;
				document.getElementById("end_date").value=response.end_Date;
				document.getElementById("sectionSubjectSeatEvaluation_Id").value=response.campus_subject_seat_evaluation_Id;
				if (response.status=="ACTIVE") {
					 document.getElementById('sectionseat_Active').checked=response.status;
					}else{
						document.getElementById("sectionseat_Inactive").checked=response.status;	
					}
				document.getElementById("btnSaveSectionSeat").value="Update";
			}
		};
		xmlhttp.open("get","base?module=campussectionseatevaluation&action=getRowData&sectionSubjectSeatEvaluation_Id="+ sectionSubjectSeatEvaluation_Id,true);
		xmlhttp.send(null);
	}
	// Delete Section Seat Function 

	function deleteSectionSeatEvaluation(sectionSubjectSeatEvaluation_Id){
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange=function(){
			if(xmlhttp.status==200 && xmlhttp.readyState==4){
				var response = JSON.parse(xmlhttp.responseText);
				if(response.isSuccess){
					var response = response.message;
					alert(response);
					showSectionSeatDetails(1);
				}else{
					var response = response.message;
					alert(response);
				}
			}
		};
		xmlhttp.open("get","base?module=campussectionseatevaluation&action=delete&sectionSubjectSeatEvaluation_Id="+ sectionSubjectSeatEvaluation_Id,true);
		xmlhttp.send(null);
	}
</script>
<%@include file="/include/Header.jsp"%>
<body>
	<!-- Setup Section -->
	<div class="content-wrapper">
		<div class="container-fluid">
			
			
				<!-- Up division form -->
				<div style="width:100%; float:top; border: 1px solid gray;">
				
				<form id="section-form" action="#" method="post"
				class="form-horizontal" name="sectionSubjectfrm">
			    <div class="row">
                 	<div class="col-lg-12">
                    	<h3 class="page-header">Section Subject Mapping</h3><hr>
                	 </div>
                </div>  
				<div class="form-group">
					<input type="hidden" id="sectionSubjectSchedule_Id" value="" />
				</div>
				<div class="form-group">
					<label for="campus_subject_Id" class="control-label col-md-6"><strong>Campus Subject
							Name</strong></label>
					<div class="col-md-5">
						<div class="input-group">
							<select class="form-control" id="campusSubject" name="campusSubject">
							</select>&nbsp;&nbsp;&nbsp;
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="section_Id" class="control-label col-md-6"><strong>Section
							Name</strong>
						&nbsp;&nbsp;&nbsp;
					 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#btnSection">Add Section</button>
					</label>			
					<div class="col-md-3">
						<div class="inpt-group">
						<div class="checkbox-inline" id="checkBoxSection_Id" name="checkBoxSection_Id">
						</div>	
						</div>
					</div>
				</div>
				<div class="form-group" id="Sectionsubject_radioBtn" style="visibility:hidden;">
					<label for="sectionSubject_status" class="control-label col-md-2"><strong>Status</strong></label>
					<div class="col-md-4">
						<label class="radio-inline"><input type="radio"
							id="sectionsubject_Active" name="sectionsubject_rbtn" value="1">Active</label> <label
							class="radio-inline"><input type="radio" id="sectionsubject_Inactive"
							name="sectionsubject_rbtn" value="2">Inactive</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-md-10">
						<input type="button" id="btnSectionSubject" class="btn btn-primary"
							value="Save" onclick="javascript:saveSectionSubjectDetails();">
						<input type="reset" class="btn btn-primary" value="Cancel">
					</div>
				</div>
				
				<h2 align="center">Section Subject Mapping</h2>
				<table class="table table-bordered table-hover table-responsive" id="sectionSubject_table" style="width:95%" align="center">
					<thead>
						<tr>
							<th>SECTION_NAME</th>
							<th>SUBJECT_NAME</th>
							<th>SUBJECT_CODE</th>
							<th>MAJOR_CODE</th>
							<th>CREATED_DATE</th>
							<th>CREATED_BY</th>
							<th>STATUS</th>
							<th>ACTION</th>
						</tr>
					</thead>
					<tbody id="subject_section_Data">

					</tbody>
					<tfoot id="pagination">
						<tr id="SectionSubject_pages">
						</tr>
					</tfoot>
				</table>
				</form>
			</div>
			<br>
			
			<!-- Down division -->
				<div style="width:100%; float:bottom; border: 1px solid gray;">
				<div class="row">
                <div class="col-lg-12">
                    <h3 class="page-header">Subject Seat Evaluation</h3><hr>
                </div>
            </div>  
            <form id="seat-form" action="#" method="post"
				class="form-horizontal" name="seatfrm">
				<div class="form-group">
					<input type="hidden" id="sectionSubjectSeatEvaluation_Id" value="" />
				</div>
				<div class="form-group">
					<label for="campus_subject_Id" class="control-label col-md-6"><strong>Campus Subject
							Name</strong></label>
					<div class="col-md-5">
						<div class="input-group">
							<select class="form-control" id="sectionsubjectSelect" name="sectionsubjectSelect">
							</select>&nbsp;&nbsp;&nbsp;
							<div>
								
					    	</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="total_seats" class="control-label col-md-6"><strong>Total
							Seats</strong></label>
					<div class="col-md-2">
						<div class="input-group">
							<input type="text" name="total_seats" id="total_seats" class="form-control" placeholder="Enter Total seat"/>
							
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="start_date" class="control-label col-md-6"><strong>Start
							Date</strong></label>
					<div class="col-md-3">
						<div class="input-group">
							<input type="date" name="start_date" id="start_date" class="form-control"/>
							
						</div>
					</div>
			
				</div>
				<div class="form-group">
					<label for="end_date" class="control-label col-md-6"><strong>End
							Date</strong></label>
					<div class="col-md-3">
						<div class="input-group">
						 <input type="date" name="end_date" id="end_date" class="form-control"/>
						</div>
					</div>
				</div>
				<div class="form-group" id="Sectionseat_radioBtn" style="visibility:hidden;">
					<label for="sectionSeat_status" class="control-label col-md-2"><strong>Status</strong></label>
					<div class="col-md-4">
						<label class="radio-inline"><input type="radio"
							id="sectionseat_Active" name="sectionseat_rbtn" value="1">Active</label> <label
							class="radio-inline"><input type="radio" id="sectionseat_Inactive"
							name="sectionseat_rbtn" value="2">Inactive</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-md-10">
						<input type="button" id="btnSaveSectionSeat" class="btn btn-primary"
							value="Save" onclick="javascript:saveSectionCampusSeatDetails();">
						<input type="reset" class="btn btn-primary" value="Cancel">
					</div>
				</div>
				<h2 align="center">Seat Evaluation</h2>
				<table class="table table-bordered table-hover table-responsive" id="seat_evaluation" style="width:99%" align="center">
					<thead>
						<tr>
							<th>SECTION</th>
							<th>NAME</th>
							<th>CODE</th>
							<th>MAJOR</th>
							<th>TOTAL_SEATS</th>
							<th>AVAILABLE_SEATS</th>
							<th>START_DATE</th>
							<th>END_DATE</th>
							<th>STATUS</th>
							<th>ACTION</th>
						</tr>
					</thead>
					<tbody id="seat_Evaluation_Data">

					</tbody>
					<tfoot id="pagination">
						<tr id="subject_seat_pages">
						</tr>
					</tfoot>
				</table>
				</form>
				</div>
				
				
				
				<!-- **** Section Modal **** -->
			
			<div class="modal fade" id="btnSection" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Setup Section</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<form id="section-form" action="#" method="post"
								class="form-horizontal" name="sectionfrm">
								<div class="form-group">
									<input type="hidden" id="section_Id" value="" />
								</div>
								<div class="form-group">
									<label for="section_Name" class="control-label col-md-6"><strong>Section
											Name</strong></label>
									<div class="col-md-4">
										<input type="text" id="section_Name" name="section_Name"
											class="form-control" placeholder=" Enter Section">
									</div>
								</div>
								
								<div class="form-group" id="Section_radioBtn" style="visibility:hidden;">
									<label for="section_status" class="control-label col-md-2"><strong>Status</strong></label>
									<div class="col-md-4">
										<label class="radio-inline"><input type="radio"
											id="section_Active" name="rbtn_section" value="1" >Active</label>
										<label class="radio-inline"><input type="radio"
											id="section_Inactive" name="rbtn_section" value="2">Inactive</label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-offset-2 col-md-10">
										<input type="button" id="btnSaveSection" class="btn btn-primary"
											value="save" onclick="javascript:saveSectionDetails();">
										<input type="reset" class="btn btn-primary" value="Cancel" onclick="windows.location.reload();">
									</div>
								</div>
								<div class="container">
								<h2 align="center">Section Details</h2>
								<table class="table table-bordered table-hover table-responsive" id="section_table" >
									<thead>
										<tr align="center">
											<th>SECTION_NAME</th>
											<th>CREATED_DATE</th>
											<th>CREATED_BY</th>
											<th>STATUS</th>
											<th>ACTION</th>
										</tr>
									</thead>
									<tbody id="section_Data">

									</tbody>
									<tfoot id="pagination">
										<tr id="section_pages">
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
			
</div>
</div>
<!-- *** Major Modal ends **** -->
<script type="text/javascript">
	
	$(document).ready(function() {
		$('#sectionfrm').on('hidden.bs.modal', function(e) {
			window.location.reload();
		});
	});
	$(document).ready(function() {
		$('.modal').on('hidden.bs.modal', function() {
			$(this).find('form')[0].reset();
		});
	});
	$(document).ready(function() {	
		$('#btnSection').on('hidden.bs.modal', function (e) {
		    window.location.reload();
			});
		});
</script>
<script type="text/javascript">
showSectionDetails(1);
loadCampusSubject();
loadSection();
showSectionSubjectMappingDetails(1);
loadSectionSubject();
showSectionSeatDetails(1);
</script>
</body>
</html>