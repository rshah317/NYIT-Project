<!Doctype HTML>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript">
function showStudentDetails(){
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
				tableHTML+="<td align=center>"+response.pageData[it].cell_phoneNo+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].campusName+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].Term+"</td>";
				tableHTML+="<td align=center>"+response.pageData[it].Subject+"</td>";
				tableHTML+="<td><a href='javascript:editStudentInfo(" + response.pageData[it].studentInfo_Id + ");'><i class='fa fa-pencil-square-o fa-lg' style='color:blue'></i></a>&nbsp;&nbsp;<a href='javascript:deleteStudentInfo(" + response.pageData[it].student_Id + ");'><i class='fa fa-trash-o fa-lg' style='color:red'></i></a></td>";
				tableHTML+="</tr>";
			}
			document.getElementById("student_Data").innerHTML=tableHTML;
			//showStudentPages(response);
		}
	};
	xmlhttp.open("get","base?module=student&action=pageData",true);
	xmlhttp.send(null);
} 
// Display Page Footer Details 

function showStudentPages(response){
	var pageSize=response.pageSize;
	var pageNo=response.pageNo;
	var totalRecords=response.Total;
	var noOfpages=Math.ceil(totalRecords/pageSize);
	var footerHTML="";
	for(var it=1;it<=noOfpages;it++){
		footerHTML += "<td><a href='javascript:showStudentDetails("+ it +");'>" + it + "</a></td>"; 
	}
	document.getElementById("student_pages").innerHTML=footerHTML;
}
</script>
<style>
table {
        display: block;
        overflow-x: auto;
        white-space: nowrap;
    }
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
<%@include file="/include/Header.jsp"%>
<body>
	<div class="content-wrapper">
		<div class="container-fluid">
		<form>
		<h2 align="center">Student Information</h2>
		<table class="table table-bordered table-hover table-responsive" id="studentInfoTable">
					<thead>
						<tr>
							<th>NYIT_ID</th>
							<th>LAST_NAME</th>
							<th>FIRST_NAME</th>
							<th>EMAIL_ID</th>
							<th>ACADEMIC_PROGRAM</th>
							<th>CELL_PHONE</th>
							<th>CAMPUS</th>
							<th>TERM</th>
							<th>SUBJECT</th>
							<th>ACTION</th>
						</tr>
					</thead>
					<tbody id="student_Data">

					</tbody>
					<tfoot id="student_pagination">
						<tr id="student_pages">
						
						</tr>
					</tfoot>
				</table>
		
		</form>
		</div>
	</div>
<script type="text/javascript">
showStudentDetails(1);
</script>
</body>
</html>