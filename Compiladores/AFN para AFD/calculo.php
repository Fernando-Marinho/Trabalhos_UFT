<?php

session_start();

require_once("conversor.php");
require_once("automato.php");

$conversor = new Conversor($_POST['input']);
$saida = $conversor->converteExpressao();
$automato = new Automato();

$temp_saida = str_split($saida);
$pilha = [];

for ($i = 0; $i < count($temp_saida); $i++) {
    if ($conversor->checkOperando($temp_saida[$i])) {
        $a = $automato->base($temp_saida[$i]);
        array_push($pilha, $a);
    } else if ($temp_saida[$i] == '*') {
        $a = array_pop($pilha);
        array_push($pilha, $automato->fechoDeKleene($a));
    } else if ($temp_saida[$i] == '.') {
        $b = array_pop($pilha);
        $a = array_pop($pilha);
        array_push($pilha, $automato->concatenacao($a, $b));
    } else if ($temp_saida[$i] == '+') {
        $b = array_pop($pilha);
        $a = array_pop($pilha);
        array_push($pilha, $automato->uniao($a, $b));
    }
}

$automato = array_pop($pilha);

$automato->imprimir();

$automato->imprimirFechoE();

$automato->converteParaAFD();







echo ("<p>Resultado: " . $saida . "</p>");

$_SESSION['expressao'] = $_POST['input'];
$_SESSION['saida'] = $saida;
/*unset($_SESSION['erro']);
header("Location: index.php");*/
exit;
