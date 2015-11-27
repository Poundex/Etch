<%--
  Created by IntelliJ IDEA.
  User: poundex
  Date: 20/11/15
  Time: 20:12
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="base"/>
	<title>Dashboard</title>
	<style type="text/css">
	body {
		padding-top: 30px;
	}
	</style>
</head>

<body>
<div class="container">

	<g:each in="${dashboard.rows}" var="row">
		<div class="row">
			<g:each in="${row.blocks}" var="block">
				<etch:renderBlock block="${block}" />
			</g:each>
		</div>
	</g:each>

</div>
</body>
</html>