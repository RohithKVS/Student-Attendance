<?php
session_start();

require 'database.php';


/* User login process, checks if user exists and password is correct */

//To prevent SQL Injections

$username =mysql_escape_string($_POST['username']);

$result =mysql_query("SELECT * FROM login WHERE username='$username'");

if (mysql_num_rows($result) == 0)
{
// User doesn't exist
header("Location: index.php?error=doesnotdonor");
exit();
}
else 
{	// User exists
    $user =mysql_fetch_assoc($result);
	$username=strtolower($user['username']);
    $pass=$user['password'];

    if ($pass==$_POST['pass'])
	{
        if(strcmp($username,'rohith')==0)
        {
            header("Location: index.php?error=admin");
            exit();
        }
        else
        {
        $_SESSION['username'] = $user['username'];
        
        // This is how we'll know the user is logged in
        $_SESSION['logged_in'] = true;

        header("location: afterlogin.php");
        }
        
    }
	
    else 
	{
        header("Location: index.php?error=invalidpassdonor");
        exit();
    }
}
?>