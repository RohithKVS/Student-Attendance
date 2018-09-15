<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="dist/images/attendance.ico">
		<title>Welcome to Student Online Attendance</title>
		<!-- Bootstrap core CSS -->
		<link href="dist/css/bootstrap.css" rel="stylesheet">
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
		<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
		<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
		<script src="assets/js/ie-emulation-modes-warning.js"></script>
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		<!-- Custom styles for this template -->
		<link href="dist/css/login.css" rel="stylesheet">
	</head>
	<!-- NAVBAR
	================================================== -->
	<body>
		<div class="container marketing">
			<!-- START THE FEATURETTES -->
			<div class="row featurette">
				<center>
				<h3>About this website</h3>
				<p>This website is designed for faculty and students to know the cumulative attendance and daily attendance respectively.<br><br>For Teachers:- Login with your teacher id and password assigned to you by the administrator.<br><br>For Students:- Login with your roll no.</p>
				</center>
			</div>
			<div class="col-md-7 col-md-push-5">
				<div class="panel panel-login">
				<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">FACULTY LOGIN</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<form id="login-form" action="verify_faculty.php" method="post" role="form"  autocomplete="off">
								<div class="form-group">
									<input type="text" name="username" class="form-control" tabindex="1" placeholder="Teacher ID" required autofocus>
									<br>
									<?php
									$loc="http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
									if(strpos($loc,'error=doesnotdonor') !==false)
									{
										echo "<div class='required'>User does not exists. Contact the administrator</div>";
									}
									if(strpos($loc,'error=admin') !==false)
									{
										echo "<div class='required'>You are not authorized to access administrator's account</div>";
									}
									?>
								</div>
								<div class="form-group">
									<input type="password" name="pass" class="form-control" tabindex="2" placeholder="Password" required>
									<br>
									<?php
									$loc="http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
									if(strpos($loc,'error=invalidpassdonor') !==false)
									{
										echo "<div class='required'>Invalid Password</div>";
									}
									?>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-6 col-sm-offset-3">
											<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
											<br>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-5 col-md-pull-7">
				<div class="panel panel-login">
				<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">

								<a href="#" class="active" id="login-form-link">STUDENT LOGIN</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<form id="login-form" action="verify_student.php" method="post" role="form" >
								<div class="form-group">
									<input type="text" name="rollno" class="form-control" tabindex="1" placeholder="Roll No" required autofocus>
									<br>
								</div>
								<div class="form-group">
									<input type="password" name="pass" class="form-control" tabindex="2" placeholder="Password" required>
									<br>
									<?php
									$loc="http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
									if(strpos($loc,'error=invalidpasshosp') !==false)
									{
										echo "<div class='required'>Invalid Password</div>";
									}
									?>
								</div>
								<div class="form-group">
									<div class="row">
										<div class="col-sm-6 col-sm-offset-3">
											<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
											<br>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="dist/js/bootstrap.min.js"></script>
		<script src="dist/js/login.js"></script>
		<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
		<script src="assets/js/vendor/holder.min.js"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
	</body>
</html>