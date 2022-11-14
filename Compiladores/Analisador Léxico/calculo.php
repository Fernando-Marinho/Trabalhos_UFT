<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trabalho 4</title>
    <style>
        body {
            display: table;
            margin: auto;
            margin-top: 1rem;
            margin-bottom: 1rem;
            border: 3px solid black;
            padding: 1rem;
        }

        td {
            padding: 0.3rem;
        }
    </style>
</head>

<body>
    <div>
        <img src="brasao_uft.jpg" width="100">
        <p>
            <center>
                <form method="POST" action="calculo.php">
                    <input type="hidden" name="codigo" value="<?php echo array_key_exists('codigo', $_SESSION) ? $_SESSION['codigo'] : '' ?>" />
                    <textarea rows="10" cols="50" name="input"><?php echo array_key_exists('inputs', $_SESSION) ? $_SESSION['inputs'] : '' ?></textarea>
                    <p><button type="submit">Enviar</button></p>
                </form>
            </center>
        <p>
            <?php
            require_once("conversor.php");
            require_once("automato.php");
            require_once("analisador.php");
            $inputs = $_POST['input'];
            $_SESSION['inputs'] = $inputs;
            $input = explode("\r\n", trim($inputs));
            $expressoes = [];
            $tokens = [];

            ?>
            <center>
                <table cellspacing="0" cellpadding="5" border="1">
                    <tbody>
                        <tr>
                            <td>&nbsp;</td>
                            <td align="center">Token</td>
                            <td>&nbsp;</td>
                            <td align="center">Expressão Regular</td>
                        </tr>
                        <?php

                        for ($i = 0; $i < count($input); $i++) {
                            echo ("<tr><td>${i}</td>");
                            $tokens[$i] = explode("=", $input[$i])[0];
                            echo ("<td align='center'>" . $tokens[$i] . "</td>");
                            echo ("<td>=</td>");
                            $expressoes[$i] = explode("=", $input[$i])[1];
                            echo ("<td align='center'>" . $expressoes[$i] . "</td>");
                            echo ("</tr>");
                        }
                        ?>

                    </tbody>
                </table>
            </center>
            <?php
            $conversor = [];
            $automato = [];
            for ($j = 0; $j < count($expressoes); $j++) {
                $conversor[$j] = new Conversor($expressoes[$j]);
                $expressoes[$j] = $conversor[$j]->converteExpressao();
                $automato[$j] = new Automato();
               

                $temp_saida = str_split($expressoes[$j]);
                $pilha = [];
                for ($i = 0; $i < count($temp_saida); $i++) {
                    if ($temp_saida[$i] == '\\') {
                        $temp_saida[$i] = $temp_saida[$i] . $temp_saida[$i + 1];
                        array_splice($temp_saida, $i + 1, 1);
                    }

                    if ($conversor[$j]->checkOperando($temp_saida[$i])) {
                        $a = $automato[$j]->base($temp_saida[$i]);
                        array_push($pilha, $a);
                    } else if ($temp_saida[$i] == '*') {
                        $a = array_pop($pilha);
                        array_push($pilha, $automato[$j]->fechoDeKleene($a));
                    } else if ($temp_saida[$i] == '.') {
                        $b = array_pop($pilha);
                        $a = array_pop($pilha);
                        array_push($pilha, $automato[$j]->concatenacao($a, $b));
                    } else if ($temp_saida[$i] == '+') {
                        $b = array_pop($pilha);
                        $a = array_pop($pilha);
                        array_push($pilha, $automato[$j]->uniao($a, $b));
                    }
                }

                $automato[$j] = array_pop($pilha);

                $automato[$j] = $automato[$j]->converteParaAFD();
            }

            $unido = new Automato();

            $tabTokens = [];

            if (count($automato) > 1) {

                $unido->setEstado(0);
                $unido->setEstadoInicial(0);

                for ($i = 0; $i < count($automato) - 1; $i++) {
                    $a = $automato[$i];
                    $b = $automato[$i + 1];
                    if (!array_key_exists($tokens[$i], $tabTokens)) {
                        $tabTokens[$tokens[$i]] = [];
                    }
                    if (!array_key_exists($tokens[$i + 1], $tabTokens)) {
                        $tabTokens[$tokens[$i + 1]] = [];
                    }

                    $unido->setAlfabeto($unido->uneAlfabetos($a->getAlfabeto(), $b->getAlfabeto(), $unido->getAlfabeto()));


                    $startA = count($unido->getEstados());
                    $unido->renomeiaEstados($a, $startA);
                    $unido->setTransicao(0, '&', $startA);

                    $finais = $a->getEstadosFinais();
                    for ($j = 0; $j < count($finais); $j++) {
                        $unido->setEstadoFinal($finais[$j] + $startA);
                        array_push($tabTokens[$tokens[$i]], $finais[$j] + $startA);
                    }


                    $startB = count($a->getEstados()) + $startA;
                    $unido->renomeiaEstados($b, $startB);
                    $unido->setTransicao(0, '&', $startB);

                    $finais = $a->getEstadosFinais();
                    for ($j = 0; $j < count($finais); $j++) {
                        $unido->setEstadoFinal($finais[$j] + $startB);
                        array_push($tabTokens[$tokens[$i + 1]], $finais[$j] + $startB);
                    }
                }
            } else {

                $unido->setEstado(0);
                $unido->setEstadoInicial(0);
                $unido->setAlfabeto($automato[0]->getAlfabeto());
                $unido->renomeiaEstados($automato[0], 1);
                $unido->setTransicao(0, '&', 1);

                if (!array_key_exists($tokens[0], $tabTokens)) {
                    $tabTokens[$tokens[0]] = [];
                }

                $finais = $automato[0]->getEstadosFinais();
                for ($j = 0; $j < count($finais); $j++) {
                    $unido->setEstadoFinal($finais[$j] + 1);
                    array_push($tabTokens[$tokens[0]], $finais[$j] + 1);
      
                }
            }
            echo ("<center>");
            $aux = $unido->converteParaAFD($tabTokens);
            $unido = $aux[0];
            $newTokens = $aux[1];
            echo ("</center>");


            ?>
            <center>
                <p>
                <form method="POST" action="calculo.php">
                    <input type="hidden" name="input" value="<?php echo array_key_exists('inputs', $_SESSION) ? $_SESSION['inputs'] : '' ?>" />
                    <textarea rows="10" cols="50" name="codigo"><?php echo array_key_exists('codigo', $_SESSION) ? $_SESSION['codigo'] : '' ?></textarea>
                    <p><button type="submit">Enviar</button></p>
                </form>

        </p>
        <?php
        $codigo = $_POST['codigo'];
        $_SESSION['codigo'] = $codigo;

        $codigos = explode(" ", $codigo);

        for ($i = 0; $i < count($codigos); $i++) {

            $analisador = new Analisador($unido, $codigos[$i]);
            $reconhecido = $analisador->reconhecer();
            if ($reconhecido) {
                for ($j = 0; $j < count($newTokens); $j++) {
                    $key = array_keys($newTokens)[$j];
                    for ($k = 0; $k < count($newTokens[$key]); $k++) {
                        if ($newTokens[$key][$k] == $reconhecido) {
                            echo ($codigos[$i] . ": " . $key . "<br>");
                        }
                    }
                }
            } else {
                echo $codigos[$i] . ": não reconhecido<br>";
            }
        }
        // header("Location: calculo.php");
        exit;
        ?>
        </center>
    </div>
</body>