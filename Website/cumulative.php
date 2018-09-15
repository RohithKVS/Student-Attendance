<?php
/* Displays user information and some useful messages */
session_start();
if (!isset($_SESSION['logged_in']))
{
header("location:index.php");
exit();
//create an error handler You must log in before viewing your profile page!
}
define('DB_USER','rohith');
define('DB_PSWD','rohith');
define('DB_HOST','localhost');
$dbname='cse4b';
$conn = mysql_connect(DB_HOST,DB_USER, DB_PSWD) or die("Error".mysql_error());
$db=mysql_select_db($dbname,$conn) or die("Error".mysql_error());
$begin = new DateTime( "2018-03-31" );
$curr_date=date("Y-m-d");
$curr_date1=date("d-F-Y");
$end   = new DateTime($curr_date);
$subjname =mysql_escape_string($_POST['subj']);
if($subjname==''||$subjname=='Select Subject')
{
header("Location: afterlogin.php?error=novalue");
exit();
}
$username = $_SESSION['username'];
$subj=end(explode("-",$subjname));
$count=0;
$roll=array();
$result=mysql_query("select rollno from students") or die("Error".mysql_error());
while($row=mysql_fetch_assoc($result))
{
$roll[$row['rollno']]=0;
}
for($i = $begin; $i <= $end; $i->modify('+1 day'))
{
$str=strtolower($i->format("dMY"));
$query1="SELECT column_name FROM information_schema.columns WHERE table_schema = 'cse4b' AND table_name ='" .$str. "' AND column_name = '" .$subj. "'";
$result1=mysql_query($query1);
if(mysql_num_rows($result1)>0)
{
$count++;
$query1="SELECT rollno FROM ".$str." where ".$subj."='P'";
$result1=mysql_query($query1);
if(mysql_num_rows($result1)>0)
{
while($row=mysql_fetch_assoc($result1))
{
$roll[$row['rollno']]=$roll[$row['rollno']]+1;
}
}
}
else
{
$query2="SELECT column_name FROM information_schema.columns WHERE table_schema = 'cse4b' AND table_name ='" .$str. "' AND column_name = '" .$subj. "1'";
$result2=mysql_query($query2);
if(mysql_num_rows($result2)>0)
{
$count=$count+2;
$query1="SELECT rollno FROM ".$str." where ".$subj."1='P'";
$result1=mysql_query($query1);
if(mysql_num_rows($result1)>0)
{
while($row=mysql_fetch_assoc($result1))
{
$roll[$row['rollno']]=$roll[$row['rollno']]+2;
}
}
}
}
}
$file_open=fopen($subj.'_'.$curr_date1.'.csv','w');
if($count!=0)
{
echo "<br><br><br><br><br>";
$cumu=array("Subject:".$subj,"space1"=>'',"Cumulative Attendance upto:".$curr_date1);
fputcsv($file_open,$cumu);
$space=array();
fputcsv($file_open,$space);
$list = array("Roll Number",'',"Number of classes present",'','',"Cumulative Attendance");
fputcsv($file_open,$list);
fputcsv($file_open,$space);

foreach ($roll as $key => $value)
{
$form_data=array("roll"=> $key,
"space1"=>'',
"Number of classes present"=>$value,
"space2"=>'',"space3"=>'',
"Cumulative"=>round(($value/$count)*100).'%');
fputcsv($file_open,$form_data);
}
}
else
echo "<br><br><br><br><br><center><h4 class='headi'>You cannot view the details of students as you have not yet taken attendance for this class. Contact the administrator if you have already taken attendance</center>";
?>
<!DOCTYPE html>
<html lang="en">
	<head>
		<style>
		.headi{
		color: #fff;
		font-weight: 300;
		text-align: center;
		margin-bottom: 15px;
		}
		table{
		width:100%;
		table-layout: fixed;
		}
		.tbl-header{
		background-color: rgba(255,255,255,0.3);
		}
		.tbl-content{
		height:300px;
		overflow-x:auto;
		margin-top: 0px;
		border: 1px solid rgba(255,255,255,0.3);
		}
		.headin{
		padding: 20px 15px;
		text-align: center;
		font-weight: 500;
		font-size: 12px;
		color: #fff;
		text-transform: uppercase;
		}
		.rolln{
		padding: 15px;
		text-align: center;
		vertical-align:middle;
		font-weight: 300;
		font-size: 12px;
		color: #fff;
		border-bottom: solid 1px rgba(255,255,255,0.1);
		}
		a.download
		{
		font-size: 20px;
		font-family: Corbel, Arial;
		color: white;
		background-color: rgb(180, 24, 27);
		padding: 8px;
		text-decoration: none;
		border-radius: 8px;
		text-align: center;
		cursor: pointer;
		text-transform: uppercase;
		}
		a.download:hover
		{
		background-color: red;
		text-decoration: none;
		color: white;
		}
		
		/* demo styles */
		@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
		body{
		background: linear-gradient(to right, #25c481, #25b7c4);
		font-family: 'Roboto', sans-serif;
		}
		section{
		margin: 50px;
		}
		/* for custom scrollbar for webkit browser*/
		::-webkit-scrollbar {
		width: 6px;
		}
		::-webkit-scrollbar-track {
		-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
		}
		::-webkit-scrollbar-thumb {
		-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
		}
		</style>
		<script>
		// '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
		$(window).on("load resize ", function() {
		var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
		$('.tbl-header').css({'padding-right':scrollWidth});
		}).resize();
		</script>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="dist/images/attendance.ico">
		<title>Welcome <?= $username ?></title>
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
		<section>
			<!--for demo wrap-->
			<h3 class="headi">Subject Name: <?= $subj ?></h3>
			<h4 class="headi"><br>Total number of classes as of <?= $curr_date1?> : <?=$count?></h4>
			<div class="tbl-header">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th class="headin">Roll no</th>
							<th class="headin">No of classes present</th>
							<th class="headin">Cumulative Attendance</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="tbl-content">
				<table cellpadding="0" cellspacing="0" border="0">
					<tbody>
						
						<?php
						
						if($count!=0)
						{
						foreach ($roll as $key => $value)
						echo '<tr><td class="rolln">'.$key.'</td><td class="rolln">'.$value.'</td><td class="rolln">'.round(($value/$count)*100).'%</td></tr>';
						}
						?>
				</tbody>
			</table>
		</div>
		<br>
		<center>
		<a href="<?php echo $subj.'_'.$curr_date1.'.csv'?>" class="download">Download</a>
		</center>
	</section>
	
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