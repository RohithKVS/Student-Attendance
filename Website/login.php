<?php

require 'database.php';


/* User login process, checks if user exists and password is correct */

//To prevent SQL Injections
$username =mysql_escape_string($_POST['username']);

$result =mysql_query("SELECT * FROM login WHERE username='$username'");

if (mysql_num_rows($result) == 0)
{
    echo "User does not exists. Contact the administrator";
}
else 
{	// User exists
    $user =mysql_fetch_assoc($result);
    $user=strtolower($user);
	$pass=$user['password'];
    if($pass==$_POST['password'])
    {
        if(strcmp($username,'rohith')==0)
        	echo "Admin";
        else
        	echo "Faculty";
    }
    else
    {
        echo "Username and password do not match";
    }
}
?>