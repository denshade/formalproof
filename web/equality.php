<?php

//the index must indicate the location of a 1 and will replace that one.
//indexstring is the location of the formula that needs replacing. Should be in the format of [2][1][3] to append after the currentformula array.

function equality_reflexive($cur, $indexstring, $f, $debug = false)
{
	$name = "\$cur$indexstring";
	$a = array();	
	$a[] = '=';
	$a[] = $f;
        $a[] = $f;
	if ($debug)
	{
		echo "cur:";
		var_dump($cur);
		echo "a:";
		var_dump($a);
		echo "$name = ".'$a;'."\n";
	}
 	eval("$name = ".'$a;');
 	if ($debug)
        {
		var_dump($cur);

	}	
	return $cur;
}
equality_reflexive(array("f", array("a","1")), "[1][1]", "f");
var_dump(equality_reflexive(array("1"), "[0]","f"));
?>
