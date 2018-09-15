<?php 
define('DB_USER','rohith');
define('DB_PSWD','rohith');
define('DB_HOST','localhost');

$dbname=mysql_escape_string($_POST['dbname']);
$sub=mysql_escape_string($_POST['sub']);
$date=mysql_escape_string($_POST['date']);
$per1=(int)mysql_escape_string($_POST['p1']);
$per2=(int)mysql_escape_string($_POST['p2']);

$flag=0;
$conn = mysql_connect(DB_HOST,DB_USER, DB_PSWD) or die("Error".mysql_error());
$db=mysql_select_db($dbname,$conn) or die("Error".mysql_error());

for($i=$per1,$j=1;$i<=$per2;$i++,$j++)
{
	if($per1==$per2)
		$query1='alter table '.$date.' change per'.$i.' '.$sub.' varchar(5);';
	else
		$query1='alter table '.$date.' change per'.$i.' '.$sub.$j.' varchar(5);';
	$result1=mysql_query($query1);
	if(!$result1)
	{
		$flag=1;
		break;
	}
}
if($flag==0)
{
	$query="select id,rollno,name from ".$date.";";
	$result=mysql_query($query);

	if($result)
	{
		while ($row=mysql_fetch_array($result))
		{
			$res[]=$row;
		}
		print(json_encode($res));
	}
}
else
{
	echo "Hi";
}

?>