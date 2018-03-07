<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setup Major</title>
</head>
<%@include file="/include/Header.jsp"%>
<body>
	<!-- Setup Major -->
	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h3 class="page-header">Setup Major</h3>
					<hr>
				</div>
			</div>
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
							value="save" onclick="javascript:saveMajorDetails();"> <input
							type="reset" class="btn btn-primary" value="Cancel">
					</div>
				</div>
				<h2 align="center">Major Details</h2>
				<table class="table table-bordered table-hover" id="major_table">
					<thead>
						<tr>
							<th>NAME</th>
							<th>CREATED DATE</th>
							<th>CREATED BY</th>
							<th>STATUS</th>
							<th>ACTION</th>
						</tr>
					</thead>
					<tbody id="major_Data">

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