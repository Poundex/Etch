<%--
  Created by IntelliJ IDEA.
  User: poundex
  Date: 20/11/15
  Time: 20:12
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html><head>
	<meta charset="utf-8">
	<title><g:layoutTitle default="Etch" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="Carlos Alvarez - Alvarez.is">

	<!-- Le styles -->
	<asset:stylesheet src="lib/bootstrap.css" />
	<asset:stylesheet src="lib/blocks.css" />
	<asset:stylesheet src="lib/font-style.css" />
	<asset:stylesheet src="lib/flexslider.css" />
	<asset:stylesheet src="lib/font-awesome.css" />

	<asset:javascript src="lib/jquery-2.1.3.js" />
	<asset:javascript src="lib/opendolphin.js" />

	<script type="text/javascript">
		var dolphin = opendolphin.dolphin("http://local-google-dev.com:8080/dolphin", false);
	</script>

	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

	<!-- Le fav and touch icons -->
	<asset:link rel="shortcut icon" href="img/facicon.ico" />

	<!-- Google Fonts call. Font Used Open Sans & Raleway -->
	<link href="http://fonts.googleapis.com/css?family=Raleway:400,300" rel="stylesheet" type="text/css">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">

	<script type="text/javascript">
		$(window).load(function(){
			$('.flexslider').flexslider({
				animation: "slide",
				slideshow: true,
				start: function(slider){
					$('body').removeClass('loading');
				}
			});
		});
	</script>
	<g:layoutHead/>
</head>
<body>

<g:layoutBody />

<div id="footerwrap">
	<footer class="clearfix"></footer>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-lg-12">
				<p>Blah</p>
				<p>Blocks Dashboard Theme - Crafted With Love - Copyright 2013</p>
			</div>

		</div><!-- /row -->
	</div><!-- /container -->
</div><!-- /footerwrap -->


<!-- Le javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
%{--<script type="text/javascript" src="assets/js/bootstrap.js"></script>--}%
<asset:javascript src="lib/bootstrap.js" />
%{--<script type="text/javascript" src="assets/js/lineandbars.js"></script>--}%

%{--<script type="text/javascript" src="assets/js/dash-charts.js"></script>--}%
%{--<script type="text/javascript" src="assets/js/gauge.js"></script>--}%


<!-- You can add more layouts if you want -->

<!-- <script type="text/javascript" src="assets/js/dash-noty.js"></script> This is a Noty bubble when you init the theme-->
<script type="text/javascript" src="http://code.highcharts.com/highcharts.js"></script>
<asset:javascript src="lib/jquery.flexslider.js" />
<asset:javascript src="lib/blocks.js" />
</body></html>