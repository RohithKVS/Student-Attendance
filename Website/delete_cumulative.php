<?php
$subj="BI";
foreach (glob($subj."*.*") as $filename) {
    unlink($filename);
}
?>