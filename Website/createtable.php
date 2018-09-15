<?php
define('DB_USER','rohith');
define('DB_PSWD','rohith');
define('DB_HOST','localhost');

$db=array('cse4b');
$date=date("dMY");
$date=strtolower($date);
$conn = mysql_connect(DB_HOST,DB_USER, DB_PSWD) or die("Error".mysql_error());

$result1 = mysql_query("SHOW DATABASES",$conn);
while($row = mysql_fetch_row($result1))
{
    if (in_array($row[0],$db))
    {
        mysql_select_db($row[0],$conn) or die("Error".mysql_error());
        $result2 = mysql_query("SHOW TABLES LIKE '".$date."'");
        if(mysql_num_rows($result2) == 1)
        {
            echo "Attendance sheet exists";
        }
        else 
        {
            $query="create table IF NOT EXISTS ".$date." select * from students;";
            if (mysql_query($query,$conn))
            {
                echo "Attendance sheet created successfully";
            } 
            else
            {
                echo "Error creating sheet: " . mysql_error($conn);
            }
        }
    }
}
?>