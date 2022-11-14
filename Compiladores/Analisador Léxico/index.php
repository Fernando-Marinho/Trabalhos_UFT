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
</head>
<style>
    td {
        text-align: center;
    }
</style>

<body bgcolor="#F0F8FF">
    <p></br></p>
    <center>
        <table border="1" cellspacing="0" cellpadding="20">
            <tbody>
                <tr>
                    <td bgcolor="#FFFFFF" align="center">
                        <img src="./brasao_uft.jpg" width="100" />
                        <p></p>
                        <form method="POST" action="calculo.php">
                            <textarea rows="10" cols="50" name="input" value="<?php echo array_key_exists('expressao', $_SESSION) ? $_SESSION['expressao'] : '' ?>"></textarea>
                            <!-- <input type="search" name="input" value="<?php echo array_key_exists('expressao', $_SESSION) ? $_SESSION['expressao']: ''?>"> -->
                            <p><button type="submit">Enviar</button></p>
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>
    </center>

    <?php
    if (array_key_exists('saida', $_SESSION)) {
        echo ("<p> Resultado: " . $_SESSION['saida'] . "</p>");
    }
    if (array_key_exists('erro', $_SESSION)) {
        echo ("<p> Erro: " . $_SESSION['erro'] . "</p>");
    }
    ?>
</body>

</html>