<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="wx" value="${pageContext.request.contextPath}" />	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-us" id="extr-page">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SmartAdmin</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- #CSS Links -->
<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${wx}/smart/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${wx}/smart/css/font-awesome.min.css">

<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${wx}/smart/css/smartadmin-production-plugins.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${wx}/smart/css/smartadmin-production.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="${wx}/smart/css/smartadmin-skins.min.css">

<!-- SmartAdmin RTL Support -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${wx}/smart/css/smartadmin-rtl.min.css">

<!-- We recommend you use "your_style.css" to override SmartAdmin
		     specific styles this will also ensure you retrain your customization with each SmartAdmin update.
		<link rel="stylesheet" type="text/css" media="screen" href="${wx}/smart/css/your_style.css"> -->

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
<link rel="stylesheet" type="text/css" media="screen"
	href="${wx}/smart/css/demo.min.css">

<!-- #FAVICONS -->
<link rel="shortcut icon" href="${wx}/smart/img/favicon/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="${wx}/smart/img/favicon/favicon.ico" type="image/x-icon">

<!-- #GOOGLE FONT -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

<!-- #APP SCREEN / ICONS -->
<!-- Specifying a Webpage Icon for Web Clip 
			 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
<link rel="apple-touch-icon" href="${wx}/smart/img/splash/sptouch-icon-iphone.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="${wx}/smart/img/splash/touch-icon-ipad.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="${wx}/smart/img/splash/touch-icon-iphone-retina.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="${wx}/smart/img/splash/touch-icon-ipad-retina.png">

<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- Startup image for web apps -->
<link rel="apple-touch-startup-image"
	href="${wx}/smart/img/splash/ipad-landscape.png"
	media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
<link rel="apple-touch-startup-image"
	href="${wx}/smart/img/splash/ipad-portrait.png"
	media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
<link rel="apple-touch-startup-image" href="${wx}/smart/img/splash/iphone.png"
	media="screen and (max-device-width: 320px)">

</head>

<body class="animated fadeInDown">

	<header id="header">

	<div id="logo-group">
		<span id="logo"> <img src="${wx}/smart/img/logo.png" alt="SmartAdmin">
		</span>
	</div>

	<span id="extr-page-header-space"> <span
		class="hidden-mobile hidden-xs">Need an account?</span> <a
		href="register.html" class="btn btn-danger">Create account</a>
	</span> </header>

	<div id="main" role="main">

		<!-- MAIN CONTENT -->
		<div id="content" class="container">

			<div class="row">
				<div
					class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
					<h1 class="txt-color-red login-header-big">SmartAdmin</h1>
					<div class="hero">

						<div class="pull-left login-desc-box-l">
							<h4 class="paragraph-header">It's Okay to be Smart.
								Experience the simplicity of SmartAdmin, everywhere you go!</h4>
							<div class="login-app-icons">
								<a href="javascript:void(0);" class="btn btn-danger btn-sm">Frontend
									Template</a> <a href="javascript:void(0);"
									class="btn btn-danger btn-sm">Find out more</a>
							</div>
						</div>

						<img src="${wx}/smart/img/demo/iphoneview.png"
							class="pull-right display-image" alt="" style="width: 210px">

					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<h5 class="about-heading">About SmartAdmin - Are you up to
								date?</h5>
							<p>Sed ut perspiciatis unde omnis iste natus error sit
								voluptatem accusantium doloremque laudantium, totam rem aperiam,
								eaque ipsa.</p>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
							<h5 class="about-heading">Not just your average template!</h5>
							<p>Et harum quidem rerum facilis est et expedita distinctio.
								Nam libero tempore, cum soluta nobis est eligendi voluptatem
								accusantium!</p>
						</div>
					</div>

				</div>
				<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
					<div class="well no-padding">
						<form action="${wx}/auth/login_browser" id="login-form"
							class="smart-form client-form">
							<header> Sign In </header>

							<fieldset>
								<section> <label class="label">E-mail</label> <label
									class="input"> <i class="icon-append fa fa-user"></i> <input
									type="email" name="username"> <b
									class="tooltip tooltip-top-right"><i
										class="fa fa-user txt-color-teal"></i> Please enter email
										address/username</b></label> </section>

								<section> <label class="label">Password</label> <label
									class="input"> <i class="icon-append fa fa-lock"></i> <input
									type="password" name="password"> <b
									class="tooltip tooltip-top-right"><i
										class="fa fa-lock txt-color-teal"></i> Enter your password</b>
								</label>
								<div class="note">
									<a href="forgotpassword.html">Forgot password?</a>
								</div>
								</section>

								<section> <label class="checkbox"> <input
									type="checkbox" name="remember" checked=""> <i></i>Stay
									signed in
								</label> </section>
							</fieldset>
							<footer>
							<button type="submit" class="btn btn-primary">Sign in</button>
							</footer>
						</form>

					</div>

					<h5 class="text-center">- Or sign in using -</h5>

					<ul class="list-inline text-center">
						<li><a href="javascript:void(0);"
							class="btn btn-primary btn-circle"><i class="fa fa-facebook"></i></a>
						</li>
						<li><a href="javascript:void(0);"
							class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>
						</li>
						<li><a href="javascript:void(0);"
							class="btn btn-warning btn-circle"><i class="fa fa-linkedin"></i></a>
						</li>
					</ul>

				</div>
			</div>
		</div>

	</div>

	<!--================================================== -->

	<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
	<script src="${wx}/smart/js/plugin/pace/pace.min.js"></script>

	<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script> if (!window.jQuery) { document.write('<script src="${wx}/smart/js/libs/jquery-2.1.1.min.js"><\/script>');} </script>

	<script
		src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	<script> if (!window.jQuery.ui) { document.write('<script src="${wx}/smart/js/libs/jquery-ui-1.10.3.min.js"><\/script>');} </script>

	<!-- IMPORTANT: APP CONFIG -->
	<script src="${wx}/smart/js/app.config.js"></script>

	<!-- JS TOUCH : include this plugin for mobile drag / drop touch events 		
		<script src="${wx}/smart/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

	<!-- BOOTSTRAP JS -->
	<script src="${wx}/smart/js/bootstrap/bootstrap.min.js"></script>

	<!-- JQUERY VALIDATE -->
	<script src="${wx}/smart/js/plugin/jquery-validate/jquery.validate.min.js"></script>

	<!-- JQUERY MASKED INPUT -->
	<script src="${wx}/smart/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
	<!--[if IE 8]>
			
			<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
			
		<![endif]-->
	<!-- MAIN APP JS FILE -->
	<script src="${wx}/smart/js/app.min.js"></script>
	<script type="text/javascript">
			runAllForms();

			$(function() {
				// Validation
				$("#login-form").validate({
					// Rules for form validation
					rules : {
						username : {
							required : true,
							email : true
						},
						password : {
							required : true,
							minlength : 3,
							maxlength : 20
						}
					},

					// Messages for form validation
					messages : {
						username : {
							required : 'Please enter your email address',
							email : 'Please enter a VALID email address'
						},
						password : {
							required : 'Please enter your password'
						}
					},

					// Do not change code below
					errorPlacement : function(error, element) {
						error.insertAfter(element.parent());
					}
				});
			});
		</script>

</body>
</html>