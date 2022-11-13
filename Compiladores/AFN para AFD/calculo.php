<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trabalho 3</title>
    <style>
        body {
            display: table;
            margin: auto;
            margin-top: 1rem;
            margin-bottom: 1rem;
        }

        td {
            padding: 0.3rem;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            border: 3px solid black;
            padding: 1rem;
        }
    </style>
</head>

<body>
    <div class="container">
        <img src="brasao_uft.jpg" width="100">
        <p>
        <form method="POST" action="calculo.php">
            <label>Entrada: </label>
            <input type="search" name="input">
        </form>
        <p>
            <?php
            session_start();

            require_once("conversor.php");
            require_once("automato.php");

            $conversor = new Conversor($_POST['input']);
            $saida = $conversor->converteExpressao();
            echo ("Infixa: " . $_POST['input'] . "</br>");
            echo ("Posfixa: " . $saida . "</br>");
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

            echo ("<p><table><tbody><tr><td>");

            $automato->imprimirFechoE();

            echo ("</td><td>&nbsp;</td><td>");

            $automato = $automato->converteParaAFD();

            echo ("</td></tr></tbody></table></p>");

            $_SESSION['expressao'] = $_POST['input'];
            $_SESSION['saida'] = $saida;
            /*unset($_SESSION['erro']);
            header("Location: index.php");*/
            exit;
            ?>
    </div>
</body>