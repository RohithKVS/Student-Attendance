<?php
define('DB_USER','rohith');
define('DB_PSWD','rohith');
define('DB_HOST','localhost');
$dbname='cse4b';

$sub=mysql_escape_string($_POST['sub']);
$date=mysql_escape_string($_POST['date']);
$per1=(int)mysql_escape_string($_POST['p1']);
$per2=(int)mysql_escape_string($_POST['p2']);
$presentees=mysql_escape_string($_POST['presentees']);

$myArray = explode(',', $presentees);

$username = "sahithreddy1133@gmail.com";
$hash = "1c88c3e02c4e0c902528043a07353b296b588453b1d27a7c1d5cc6481ead60a7";
// Config variables. Consult http://api.textlocal.in/docs for more info.
$test = "0";
$sender = "TXTLCL"; // This is who the message appears to be from.
$end='';

$conn = mysql_connect(DB_HOST,DB_USER, DB_PSWD) or die("Error".mysql_error());
$db=mysql_select_db($dbname,$conn) or die("Error".mysql_error());

//This for loop stores attendance in the database
for($i=$per1,$j=1;$i<=$per2;$i++,$j++)
{
	if($per1==$per2)
	{
	    $query1='update '.$date.' set '.$sub.'="A"';
	    $result1=mysql_query($query1);
	    foreach ($myArray as $roll) 
	    {
	    	$query2='update '.$date.' set '.$sub.'="P" where rollno="'.$roll.'"';
	   		$result2=mysql_query($query2);
	    }
	}
		
	else
	{
	    $query1='update '.$date.' set '.$sub.$j.'="A"';
	    $result1=mysql_query($query1);
	    foreach ($myArray as $roll) 
	    {
	    	$query2='update '.$date.' set '.$sub.$j.'="P" where rollno="'.$roll.'"';
	   		$result2=mysql_query($query2);
	    }
	}
}
echo 'Data sent successfully';

//This for loop retrieves absentees from the database
for($i=$per1,$j=1;$i<=$per2;$i++,$j++)
{
	if($per1==$per2)
	{
	    $query1='select s.mobileno from '.$date.' r, students_details s where r.rollno=s.rollno and r.'.$sub.'="A"';
	    $result1=mysql_query($query1);
	    $end='for period '.$per1;
	}
		
	else
	{
	    $query1='select s.mobileno from '.$date.' r, students_details s where r.rollno=s.rollno and r.'.$sub.$j.'="A"';
	    $result1=mysql_query($query1);
	    $end='from periods '.$per1.' to '.$per2;
	}
}
		
//This loop fetches mobile nos of absentees into array
while ($row = mysql_fetch_assoc($result1))
{		
	$array[] = $row; 
}
	
	// Data for text message. This is the text message data.
	$message = 'Your ward  is absent '.$end;
		// 612 chars or less
		// A single number or a comma-seperated list of numbers
		$message = urlencode($message);
	$numbers=implode(', ', array_column($array, 'mobileno'));
	
	//This function sends email
	mail("rohithkvs@gmail.com","(noreply)",$message,"From:mp16cc7@gmail.com\r\n");
	
	//This is the code for sending text message
	$data = "username=".$username."&hash=".$hash."&message=".$message."&sender=".$sender."&numbers=".$numbers."&test=".$test;
		$ch = curl_init('http://api.textlocal.in/send/?');
		curl_setopt($ch, CURLOPT_POST, true);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		$result = curl_exec($ch); // This is the result from the API
		if (curl_errno ( $ch )) {
            echo curl_error ( $ch );
            curl_close ( $ch );
            exit ();
        }
        curl_close ( $ch );
        
        // dump output of api if you want during test
        echo $result;
?>