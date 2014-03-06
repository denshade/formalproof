<?php

function getFormulaArray($str)
{
        if (strpos($str,"(")===FALSE) return $str;
        $ret = array();
        $ar = explode("(", $str);
        $ret[] = $ar[0];
        $last = strpos($str, "(") + 1;
        $level = 0;
        for ($i = strpos($str, "(") + 1; $i < strlen($str); $i++)
        {
                if ($str[$i] == "(")$level++;
                if ($str[$i] == ")")$level--;
                if ($str[$i] == "," && $level == 0)
                {
			if (trim(substr($str, $last, $i - $last )) != "")
	                        $ret[] = getFormulaArray(substr($str, $last, $i - $last ));
                        $last = $i+1;
                }
        }
 	if (trim(substr($str, $last, strlen($str) - 1 - $last)  ) != "")
        	$ret[] = getFormulaArray(substr($str, $last, strlen($str) - 1 - $last));
        return $ret;
}

function parseProveLine($line, $currentFormula)
{
	echo "formula goes in" ;
	var_dump($line);
	$step = getFormulaArray($line);
	var_dump($step);
	$functionname = $step[0];
	$args = array();
	for ($a= 1; $a < count($step); $a++)
	{	
		$args[] = $step[$a];		
	}
	var_dump($currentFormula);
	$args=implode(",", $args);
	if (trim($functionname == ""))
		stop("empty axiom!");
	echo '$currentFormula ='." $functionname(".'$currentFormula'.", $args);\n";
	eval('$currentFormula ='." $functionname(".'$currentFormula'.", $args);");
	return $currentFormula;
}

function loadAxioms($line)
{
	$arr = explode(",", $line);
	if (count($arr)==0)
		stop("You have no axioms defined!");
	foreach($arr as $line)
	{
		if (!file_exists($line.".php"))
			stop("I don't know axiomset $line");
		else
			require_once($line.".php");
	}
		
}

function loadProof($proof, $given, $goal)
{
	$currentFormula =getFormulaArray($given); 
	$proof = explode("\n", $proof);	
	foreach($proof as $line)
		$currentFormula = parseProveLine($line, $currentFormula);

	if ($goal != formulaToStr($currentFormula))
		stop("Proof failed, your goal was $goal but you got ".formulaToStr($currentFormula));
	echo "Proof accepted!";
}

function formulaToStr($formula)
{
	if (is_array($formula))
		{
			if (count($formula) == 1)
				return formulaToStr($formula[0]);
			else
				$functionname = $formula[0];
			for($a = 1; $a < count($formula);$a++)
				$str[] = formulaToStr($formula[$a]);
			$ar = implode(",", $str);
			return "$functionname($ar)";
		}else return $formula;
		
}

function stop($line)
{
	echo ("$line");
	exit(0);
}
/*
var_dump(getFormulaArray("f"));
var_dump(getFormulaArray("f(a)"));
var_dump(getFormulaArray("f(a,b)"));
var_dump(getFormulaArray("f(a,g(b))"));
var_dump(getFormulaArray("f(a,b,k(c,p))"));
var_dump(getFormulaArray("test()"));
*/
loadAxioms("equality");
$a = file("kwadraat.proof");
foreach ($a as $line)
{
 $lines[] = str_replace("\n","",$line);
}
$lines = implode("\n", $lines);
loadProof($lines,array("1"),"=(f,f)" );
//loadProof("equality_reflexive(f)","f","=(f,f)" );
?>

