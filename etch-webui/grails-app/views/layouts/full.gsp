<%--
  Created by IntelliJ IDEA.
  User: poundex
  Date: 20/11/15
  Time: 20:23
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<g:applyLayout name="base">
<html>
<head>
	<title><g:layoutTitle /></title>

	<style type="text/css">
	body {
		padding-top: 60px;
	}
	</style>

	<g:layoutHead/>
</head>

<body>

<!-- NAVIGATION MENU -->

<div class="navbar-nav navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html"><img src="assets/img/logo30.png" alt=""> BLOCKS Dashboard</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.html"><i class="icon-home icon-white"></i> Home</a></li>
				<li><a href="manager.html"><i class="icon-folder-open icon-white"></i> File Manager</a></li>
				<li><a href="calendar.html"><i class="icon-calendar icon-white"></i> Calendar</a></li>
				<li><a href="tables.html"><i class="icon-th icon-white"></i> Tables</a></li>
				<li><a href="login.html"><i class="icon-lock icon-white"></i> Login</a></li>
				<li><a href="user.html"><i class="icon-user icon-white"></i> User</a></li>

			</ul>
		</div><!--/.nav-collapse -->
	</div>
</div>

<g:layoutBody/>

</body>
</html>
</g:applyLayout>