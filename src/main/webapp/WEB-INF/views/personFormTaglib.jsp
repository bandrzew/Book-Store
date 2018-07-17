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

	<form:form method="post" modelAttribute="person">
		<div>
			Login<form:input path="login" />
		</div>
		<div>
			Password<form:password path="password" />
		</div>
		<div>
			Email<form:input type="email" path="email" />
		</div>
		<div>
			<input type="submit" />
		</div>
	</form:form>

</body>
</html>