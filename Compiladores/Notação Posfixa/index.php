<?php 
    session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notação Posfixa</title>
</head>
<body>
    <form method="POST" action="calculo.php">
        <label>Entrada: </label>
        <input type="search" name="input" value="<?php echo array_key_exists('expressao', $_SESSION) ? $_SESSION['expressao']: ''?>">
    </form>
    <?php 
        if(array_key_exists('saida', $_SESSION)){
            echo("<p> Resultado: ".$_SESSION['saida']."</p>");
        }
        if(array_key_exists('erro', $_SESSION)){
            echo("<p> Erro: ".$_SESSION['erro']."</p>");
        }
    ?>
</body>
</html>