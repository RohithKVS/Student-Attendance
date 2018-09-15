<?php
define('DB_NAME','test');
define('DB_USER','rohith');
define('DB_PSWD','rohith');
define('DB_HOST','localhost');

$conn = mysql_connect(DB_HOST,DB_USER, DB_PSWD);
if(!$conn)
{
die("Error".mysql_error());
}
$db=mysql_select_db(DB_NAME,$conn) or die("Error".mysql_error());
?>