<?php
define('DB_USER','rohith');
define('DB_PSWD','rohith');
define('DB_HOST','localhost');
$dbname='cse4b';
$conn = mysql_connect(DB_HOST,DB_USER, DB_PSWD) or die("Error".mysql_error());
$db=mysql_select_db($dbname,$conn) or die("Error".mysql_error());

$curr_date=date("dMY");
$curr_date=strtolower($curr_date);
$prev_date = date('dMY', strtotime($curr_date .' -1 day'));
$prev_date=strtolower($prev_date);


$result1=mysql_query('describe '.$prev_date);
$result2=mysql_query('describe '.$curr_date);
$prev_subj=array();
$curr_subj=array();
$prev_abst=array();
$curr_abst=array();
$str='';
//Get subject names from previous date
while($row = mysql_fetch_assoc($result1))
{
    if($row['Field']=='id'||$row['Field']=='name'||$row['Field']=='rollno')
    unset($row['Field']);
    else
    {
        array_push($prev_subj,$row['Field']);
    }
}
//Get subject names from current date
while($row = mysql_fetch_assoc($result2))
{
    if($row['Field']=='id'||$row['Field']=='name'||$row['Field']=='rollno')
    unset($row['Field']);
    else
    {
        array_push($curr_subj,$row['Field']);
    }
}
//Get absentees from previous date
$query1='select rollno from '.$prev_date.' where '.$prev_subj[0].'="A" AND '.$prev_subj[1].'="A" AND '.$prev_subj[2].'="A" AND '.$prev_subj[3].'="A" AND '.$prev_subj[4].'="A" AND '.$prev_subj[5].'="A" AND '.$prev_subj[6].'="A"';
$result3=mysql_query($query1);
//Get absentees from current date
$query2='select rollno from '.$curr_date.' where '.$curr_subj[0].'="A" AND '.$curr_subj[1].'="A" AND '.$curr_subj[2].'="A" AND '.$curr_subj[3].'="A" AND '.$curr_subj[4].'="A" AND '.$curr_subj[5].'="A" AND '.$curr_subj[6].'="A"';
$result4=mysql_query($query2);

if((mysql_num_rows($result3)>0)&&(mysql_num_rows($result4)>0))
{
    while( $user1=mysql_fetch_assoc($result3))
    {
        array_push($prev_abst,$user1['rollno']);
    }
    while( $user2=mysql_fetch_assoc($result4))
    {
        array_push($curr_abst,$user2['rollno']);
    }
    //Get roll nos of absentees who are absent for 2days
    $abst_twodays=array_intersect($prev_abst,$curr_abst);
    foreach ($abst_twodays as $key => $value) 
    {
        $str=$str.'
        '.$value;
    }
    $message="

    Below are the roll numbers of the students who are absent for all periods for two consecutive days.

    $str

    Contact their parents regarding the reason.";
    mail("rohithkvs@gmail.com","(noreply)",$message,"From:mp16cc7@gmail.com\r\n");
}

?>