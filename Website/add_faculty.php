<?php

require 'database.php';


/* User login process, checks if user exists and password is correct */

//To prevent SQL Injections
$facultyname =mysql_escape_string($_POST['facultyname']);
$password=mysql_escape_string($_POST['password']);

$result1 =mysql_query("SELECT * FROM login WHERE username='$facultyname'");

if (mysql_num_rows($result1)>0)
{
    echo "Faculty name already exists";
}
else
{
    $result2 =mysql_query("insert into login (username,password) values('$facultyname','$password')");
    echo "Faculty added successfully";
}
?>