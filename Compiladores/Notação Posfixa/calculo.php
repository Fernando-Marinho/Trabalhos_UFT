<?php

session_start();
$input = $_POST['input'];
$input = str_split($input);
$pilha = [];
$saida = "";
$topo = -1;
$contrabarra = [];

function prioridade($x)
{
    if ($x == '(')
        return 0;
    if ($x == '+')
        return 1;
    if ($x == '.')
        return 2;
    if ($x == '*')
        return 3;
    return 0;
}

function empilha($x)
{
    global $pilha, $topo;
    $topo++;
    $pilha[$topo] = $x;
}

function desempilha()
{
    global $pilha, $topo;
    if ($topo == -1) {
        return $topo;
    }
    $topo--;
    return array_pop($pilha);
}

function checkOperando($i)
{
    global $input;
    if (ctype_alnum($input[$i])  || $input[$i] == '&' || $input[$i] == '#')
        return true;
}

function addPonto($i)
{
    global $input;
    $start_str = substr(implode("", $input), 0, $i + 1);
    $end_str = substr(implode("", $input), $i + 1);
    $input = $start_str . "." . $end_str;
    $input = str_split($input);
}

//Substitui operando com contrabarra por um '#'
for ($i = 0; $i < count($input); $i++) {
    if (array_key_exists($i + 1, $input)) {
        if ($input[$i] == '\\') {
            array_push($contrabarra, $input[$i] . $input[$i + 1]);
            $start_str = substr(implode("", $input), 0, $i);
            $end_str = substr(implode("", $input), $i + 2);
            $input = $start_str . "#" . $end_str;
            $input = str_split($input);
        }
    }
}

//adiciona '.' em concatenacoes
for ($i = 0; $i < count($input); $i++) {
    if (array_key_exists($i + 1, $input)) {
        if (checkOperando($i) && checkOperando($i + 1)) {
            addPonto($i);
        } else if ($input[$i] == '*' && checkOperando($i + 1)) {
            addPonto($i);
        } else if ($input[$i] == '*' && $input[$i + 1] == '(') {
            addPonto($i);
        } else if (checkOperando($i) && $input[$i + 1] == '(') {
            addPonto($i);
        } else if ($input[$i] == ')' && checkOperando($i)) {
            addPonto($i);
        } else if ($input[$i] == ')' && $input[$i + 1] == '(') {
            addPonto($i);
        }
    }
}

//Converter para notacao posfixa
for ($i = 0; $i < count($input); $i++) {
    if (checkOperando($i)) {
        $saida .= $input[$i];
    } else if ($input[$i] == '(') {
        empilha($input[$i]);
    } else if ($input[$i] == ')') {
        while (true) {
            $x = desempilha();
            if ($x == '(') break;
            else if ($topo == -1) {
                unset($_SESSION['saida']);
                $_SESSION['erro'] = "parenteses nao aberto/fechado";
                header("Location: index.php");
                exit;
            }
            $saida .= $x;
        }
    } else if (($input[$i] == '*')) {
        $saida .= $input[$i];
    } else if ($topo >= 0) {
        while (prioridade($pilha[$topo]) >= prioridade($input[$i])) {
            $saida .= desempilha();
            if ($topo == -1) break;
        }
        empilha($input[$i]);
    } else {
        empilha($input[$i]);
    }
}

// Checa se ainda existe operadores na pilha
while ($topo > -1) {

    $saida .= desempilha();

    if (str_contains($saida, "(") || str_contains($saida, "(")) {
        unset($_SESSION['saida']);
        $_SESSION['erro'] = "parenteses nao aberto/fechado";
        header("Location: index.php");
        exit;
    }
}

// Devolve a string os contrabarras
$saida = str_split($saida);
for ($i = 0; $i < count($saida); $i++) {
    if ($saida[$i] == '#') {
        $x = array_shift($contrabarra);
        $start_str = substr(implode("", $saida), 0, $i);
        $end_str = substr(implode("", $saida), $i + 1);
        $saida = $start_str . $x . $end_str;
        $saida = str_split($saida);
    }
}

$_SESSION['expressao'] = $_POST['input'];
$_SESSION['saida'] = implode("", $saida);
unset($_SESSION['erro']);
header("Location: index.php");
exit;
