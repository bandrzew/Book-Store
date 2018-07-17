<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>

	<form:form method="post" modelAttribute="book">
		<div>
			title<form:input path="title" />
			<form:errors path="title" />
		</div>
		<div>
			authors<form:select path="authors" multiple="true" items="${authors}" itemValue="id" itemLabel="fullName" />
			<form:errors path="authors" />
		</div>
		<div>
			rating<form:input type="number" path="rating" />
			<form:errors path="rating" />
		</div>
		<div>
			publisher<form:select path="publisher" items="${publishers}" itemValue="id" itemLabel="name" />
			<form:errors path="publisher" />
		</div>
		<div>
			description<form:textarea path="description" />
			<form:errors path="description" />
		</div>
		<div>
			pages<form:input path="pages" />
			<form:errors path="pages" />
		</div>
		<div>
			<input type="submit" />
		</div>
	</form:form>

</body>
</html>