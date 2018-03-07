<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setup Schedule</title>
</head>
<%@include file="/include/Header.jsp"%>
<script type="text/javascript">
//*** Script for Adding Schedule 
function addCampusSchedule(){
	var btnValue=document.getElementById("SaveSubjectSchedule").value;
	var requestUrl="base?module=campusschedule&action=new";
	if(btnValue=="Update"){
		requestUrl="base?module=campusschedule&action=update";
		document.getElementById('subjectSchedule_radioBtn').style.visibility = "hidden";
	}
	var xmlhttp = new XMLHttpRequest();
	var schedule_Id=document.getElementById("schedule_Id").value;
	var subjectscheduleSelect=document.getElementById("subject_schedule_Select").value;
	var day=document.getElementById("day").value;
	var time=document.getElementById("time").value;
	var rbtn = document.getElementsByName("subjectSchedule_rbtn");
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
				showSubjectSchedule(1);
				document.forms["schedulefrm"].reset();
				document.getElementById("SaveSubjectSchedule").value="Save";
				document.getElementById("schedule_Id").value="";

			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("post",requestUrl,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("schedule_Id="+ schedule_Id + "&subjectscheduleSelect=" + subjectscheduleSelect + "&day=" + day + "&time=" + time +"&rbtn="+ rbtn);
}
function showSubjectSchedule(pageNo){
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
				tableHTML+="<td align=center>"+response.pageData[it].day+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].time+"</td>";				
				if(response.pageData[it].status=="ACTIVE"){		
					tableHTML+="<td align=center class=text-success>"+response.pageData[it].status+"</td>";
					}
				else{
					tableHTML+="<td align=center class=text-danger>"+response.pageData[it].status+"</td>";					
					}
				tableHTML+="<td><a href='javascript:editSubjectSchedule(" + response.pageData[it].schedule_Id + ");'><i class='fa fa-pencil-square-o fa-lg' style='color:blue'></i></a>&nbsp;&nbsp;<a href='javascript:deleteSubjectSchedule(" + response.pageData[it].schedule_Id + ");'><i class='fa fa-trash-o fa-lg' style='color:red'></i></a></td>";
				tableHTML+="</tr>";
			}
			document.getElementById("schedule_Data").innerHTML=tableHTML;
			showSubjectSchedulePages(response);
		}
	};
	xmlhttp.open("get","base?module=campusschedule&action=pageData&pageNo="+ pageNo +"&pageSize=5",true);
	xmlhttp.send(null);
} 
// Display Page Footer Details 

function showSubjectSchedulePages(response){
	var pageSize=response.pageSize;
	var pageNo=response.pageNo;
	var totalRecords=response.Total;
	var noOfpages=Math.ceil(totalRecords/pageSize);
	var footerHTML="";
	for(var it=1;it<=noOfpages;it++){
		footerHTML += "<td><a href='javascript:showSubjectSchedule("+ it +");'>" + it + "</a></td>"; 
	}
	document.getElementById("pages").innerHTML=footerHTML;
}
// Edit Section Seat Function

function editSubjectSchedule(schedule_Id){
	document.getElementById('subjectSchedule_radioBtn').style.visibility = "visible";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			document.getElementById("subject_schedule_Select").value=response.campus_subject_seat_evaluation_Id;
			document.getElementById("day").value=response.day;
			document.getElementById("time").value=response.time;
			document.getElementById("schedule_Id").value=response.schedule_Id;
			if (response.status=="ACTIVE") {
				 document.getElementById('subjectSchedule_Active').checked=response.status;
				}else{
					document.getElementById("subjectSchedule_Inactive").checked=response.status;	
				}
			document.getElementById("SaveSubjectSchedule").value="Update";
		}
	};
	xmlhttp.open("get","base?module=campusschedule&action=getRowData&schedule_Id="+ schedule_Id,true);
	xmlhttp.send(null);
}
// Delete Section Seat Function 

function deleteSubjectSchedule(schedule_Id){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			if(response.isSuccess){
				var response = response.message;
				alert(response);
				showSubjectSchedule(1);
			}else{
				var response = response.message;
				alert(response);
			}
		}
	};
	xmlhttp.open("get","base?module=campusschedule&action=delete&schedule_Id="+ schedule_Id,true);
	xmlhttp.send(null);
}
//*** Combo box for Section Subject 
function loadSectionSubject(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.status==200 && xmlhttp.readyState==4){
			var response = JSON.parse(xmlhttp.responseText);
			var subjectscheduleSelect = document.getElementById("subject_schedule_Select");
			for(var it=0;it<response.length;it++){
				var option = document.createElement("option");
				option.value=response[it].campus_subject_seat_evaluation_Id;
				option.innerHTML=response[it].section_Name +" - " + response[it].subject_Code + " - " + response[it].major_Code +" - "+ response[it].subject_Name;
				subjectscheduleSelect.appendChild(option);
			}
		}
	}; 
	xmlhttp.open("get","base?module=campussectionseatevaluation&action=combolist");
	xmlhttp.send(null);
}
</script>
<body>
	<!-- Setup Schedule -->
	<div class="content-wrapper" >
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">Setup Schedule</h3>
					<hr>
				</div>
			</div>
			<form id="schedule-form" action="#" method="post"
				class="form-horizontal" name="schedulefrm">
				<div class="form-group">
					<input type="hidden" id="schedule_Id" value="" />
				</div>
				<div class="form-group">
					<label for="section_subject_schedule_Id" class="control-label col-md-6"><strong>Campus Subject Section
							Name</strong></label>
					<div class="col-md-5">
						<div class="input-group">
							<select class="form-control" id="subject_schedule_Select" name="subject_schedule_Select">
							</select>
							<div>
								
					    	</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="day" class="control-label col-md-6"><strong>Day
							</strong></label>
					<div class="col-md-3">
						<select class="form-control" id="day" name="day">
							<option id="monday" value="Monday">Monday</option>
							<option id="tuesday" value="Tuesday">Tuesday</option>
							<option id="wednesday" value="Wednesday">Wednesday</option>
							<option id="thursday" value="Thursday">Thursday</option>
							<option id="friday" value="Friday">Friday</option>
							<option id="saturday" value="Saturday">Saturday</option>
							<option id="sunday" value="Sunday">Sunday</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="time" class="control-label col-md-3"><strong>Time
							</strong></label>
					<div class="col-md-4">
						<input type="text" id="time" name="time"
							class="form-control" placeholder=" Enter Time">
					</div>
				</div>
				<div class="form-group" id="subjectSchedule_radioBtn" style="visibility:hidden;">
					<label for="subjectSchedule_status" class="control-label col-md-2"><strong>Status</strong></label>
					<div class="col-md-4">
						<label class="radio-inline"><input type="radio"
							id="subjectSchedule_Active" name="subjectSchedule_rbtn" value="1">Active</label> <label
							class="radio-inline"><input type="radio" id="subjectSchedule_Inactive"
							name="subjectSchedule_rbtn" value="2">Inactive</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-md-10">
						<input type="button" id="SaveSubjectSchedule" class="btn btn-primary"
							value="save" onclick="javascript:addCampusSchedule();">
						<input type="reset" class="btn btn-primary" value="Cancel" onclick="window.location.reload()">
					</div>
				</div>
				
				<h2 align="center">Schedule Details</h2>
				<table class="table table-bordered table-hover" id="schedule_table">
					<thead>
						<tr>
							<th>SECTION_NAME</th>
							<th>SUBJECT_NAME</th>
							<th>SUBJECT_CODE</th>
							<th>MAJOR_CODE</th>
							<th>DAY</th>
							<th>TIME</th>
							<th>STATUS</th>
							<th>ACTION</th>
						</tr>
					</thead>
					<tbody id="schedule_Data">

					</tbody>
					<tfoot id="pagination">
						<tr id="pages">
						</tr>
					</tfoot>
				</table>
			</form>
		</div>
	</div>
<script type="text/javascript">
loadSectionSubject();
showSubjectSchedule(1);
</script>
</body>
</html>