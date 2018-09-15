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
$username = $_SESSION['username'];
$result =mysql_query("SELECT * FROM login WHERE username='$username'");
$user=mysql_fetch_assoc($result);
}
?>
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
        <div class="container-fluid">
            <div class="row">
                <center>
                <br><br>
                <h3 class="page-header" align="center">Welcome <?= $username ?><br><br>Select subject and click on submit to get the cumulative attendance.</h3>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    
                    
                    <div class="row placeholders">
                        <form name="d" method="post" autocomplete="off" action="cumulative.php">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-sm-3 col-sm-offset-3">
                                        <select name="subj" required>
                                            <option value="Select Subject" selected>Select Subject</option>
                                            <option value="<?=$user['sub1'];?>"><?=$user['sub1'];?></option>
                                            <option value="<?=$user['sub2'];?>" ><?=$user['sub2'];?></option>
                                            <option value="<?=$user['sub3'];?>" ><?=$user['sub3'];?></option>
                                            <option value="<?=$user['sub4'];?>" ><?=$user['sub4'];?></option>
                                            <option value="<?=$user['sub5'];?>" ><?=$user['sub5'];?></option>
                                        </select>
                                        <?php
                                                                                        $loc="http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
                                                                                        if(strpos($loc,'error=novalue') !==false)
                                                                                        {
                                                                                            echo "<br><div class='required'>Enter subject name</div>";
                                                                                        }
                                        ?>
                                        <br><br><br><br><br><br>
                                        <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Submit">
                                    </div>
                                </div>
                            </div>
                        </form>
                        
                        </center>
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