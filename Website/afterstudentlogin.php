<?php
/* Displays user information and some useful messages */
session_start();
require 'database.php';
// Check if user is logged in using the session variable
if (!isset($_SESSION['logged_in']))
{
header("location:index.php");
exit();
//create an error handler You must log in before viewing your profile page!
}
else
{
// Makes it easier to read
$rollno = $_SESSION['rollno'];
$result =mysql_query("SELECT * FROM student_login WHERE rollno='$rollno'");
$user=mysql_fetch_assoc($result);
}
$dbname='cse4b';
$db=mysql_select_db($dbname,$conn) or die("Error".mysql_error());
$begin = new DateTime( "2018-03-31" );
$curr_date=date("Y-m-d");
$end   = new DateTime($curr_date);

echo '<center><br><br><br><br><table border="1"><tr><th>Date</th><th>Day</th><th>Period 1</th><th>Period 2</th><th>Period 3</th><th>Period 4</th><th>Period 5</th><th>Period 6</th><th>Period 7</th></tr>';

for($i = $begin; $i <= $end; $i->modify('+1 day'))
{
	$subj=array();
	$str=strtolower($i->format("dMY"));
	$day= date("l", strtotime($i->format("Y-m-d")));
	$result1=mysql_query('describe '.$str) or die('Error'.mysql_error());
	while($row = mysql_fetch_assoc($result1))
	{
	    if($row['Field']=='id'||$row['Field']=='name'||$row['Field']=='rollno')
	    unset($row['Field']);
	    else
	    {
	        array_push($subj,$row['Field']);
	    }
	}

	$query1='select * from '.$str.' where rollno="'.$rollno.'"';
	$result3=mysql_query($query1) or die('Error'.mysql_error());
	$user=mysql_fetch_assoc($result3);
	echo '<tr><td>'.$i->format("d-m-Y").'</td><td>'.$day.'</td><td>'.$user[$subj[0]].'</td><td>'.$user[$subj[1]].'</td><td>'.$user[$subj[2]].'</td><td>'.$user[$subj[3]].'</td><td>'.$user[$subj[4]].'</td><td>'.$user[$subj[5]].'</td><td>'.$user[$subj[6]].'</td></tr>';


}
?>
<!DOCTYPE html>
<html lang="en">
	<head>
	<style type="text/css">
	th
	{
		padding: 20px 15px;
		text-align: center;
		font-weight: bold;		
		font-size: 17px;
		text-transform: uppercase;
	}
	td
	{
		padding:15px;
		text-align:center;
		vertical-align:middle;
		font-weight: 300;
	}

	</style>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="dist/images/attendance.ico">
		<title>Welcome <?= $user['name'] ?></title>
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
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">STUDENT ONLINE ATTENDANCE</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="logout.php"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<h3 class="page-header" align="center">Welcome <?= $user['name'] ?><br><br>This is your daily attendance.</h3>

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