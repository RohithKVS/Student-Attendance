<?php
session_start();

require 'database.php';


/* User login process, checks if user exists and password is correct */

//To prevent SQL Injections

$rollno =mysql_escape_string($_POST['rollno']);

$result =mysql_query("SELECT * FROM student_login WHERE rollno='$rollno'");

if (mysql_num_rows($result) == 0)
{
exit();
}
else 
{	// User exists
    $user =mysql_fetch_assoc($result);
    $pass=$user['password'];

    if ($pass==$_POST['pass'])
	{
        
        $_SESSION['rollno'] = $user['rollno'];
        $_SESSION['name'] = $user['name'];
        
        // This is how we'll know the user is logged in
        $_SESSION['logged_in'] = true;

        header("location: afterstudentlogin.php");        
    }
	
    else 
	{
        header("Location: index.php?error=invalidpasshosp");
        exit();
    }
}
?>