<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setup Campus</title>
</head>
<%@include file="/include/Header.jsp"%>
<body>
	<!-- Setup Campus -->
	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">Setup Campus</h3>
					<hr>
				</div>
			</div>
			<form id="campus-form" action="#" method="post"
				class="form-horizontal" name="campusfrm">
				<div class="form-group">
					<input type="hidden" id="campus_Id" value="" />
				</div>
				<div class="form-group">
					<label for="campus_Name" class="control-label col-md-6"><strong>Campus
							Name</strong></label>
					<div class="col-md-4">
						<input type="text" id="campus_Name" name="campus_Name"
							class="form-control" placeholder=" Enter Campus">
					</div>
				</div>
				<div class="form-group" id="radioBtn">
					<label for="section_status" class="control-label col-md-2"><strong>Status</strong></label>
					<div class="col-md-4">
						<label class="radio-inline"><input type="radio"
							id="active" name="rbtn" value="1">Active</label> <label
							class="radio-inline"><input type="radio" id="inactive"
							name="rbtn" value="2">Inactive</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-md-10">
						<input type="button" id="btnSave" class="btn btn-primary"
							value="save" onclick="javascript:saveCampusDetails();">
						<input type="reset" class="btn btn-primary" value="Cancel">
					</div>
				</div>
				<h2 align="center">Campus Details</h2>
				<table class="table table-bordered table-hover" id="campus_table">
					<thead>
						<tr>
							<th>NAME</th>
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
	</div>
</body>
</html>