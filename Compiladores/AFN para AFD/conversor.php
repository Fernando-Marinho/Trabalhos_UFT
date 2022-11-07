<?php 

class Conversor
{

    private $input;
    private $pilha = [];
    private $saida = "";
    private $topo = -1;
    private $contrabarra = [];

    function __construct($input)
    {
        $this->input = str_split($input);
    }

    private function prioridade($x)
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

    private function empilha($x)
    {
        $this->topo++;
        $this->pilha[$this->topo] = $x;
    }

    private function desempilha()
    {
        if ($this->topo == -1) {
            return $this->topo;
        }
        $this->topo--;
        return array_pop($this->pilha);
    }

    public function checkOperando($i)
    {
        if (ctype_alnum($i)  || $i == '&' || $i == '#'){
            return true;
        }

        return false;
    }

    private function addPonto($i)
    {
        $start_str = substr(implode("", $this->input), 0, $i + 1);
        $end_str = substr(implode("", $this->input), $i + 1);
        $this->input = $start_str . "." . $end_str;
        $this->input = str_split($this->input);
    }

    private function trataContrabarra()
    {
        for ($i = 0; $i < count($this->input); $i++) {
            if (array_key_exists($i + 1, $this->input)) {
                if ($this->input[$i] == '\\') {
                    array_push($this->contrabarra, $this->input[$i] . $this->input[$i + 1]);
                    $start_str = substr(implode("", $this->input), 0, $i);
                    $end_str = substr(implode("", $this->input), $i + 2);
                    $this->input = $start_str . "#" . $end_str;
                    $this->input = str_split($this->input);
                }
            }
        }
    }

    public function trataConcatenacoes()
    {
        
        for ($i = 0; $i < count($this->input); $i++) {
            if (array_key_exists($i + 1, $this->input)) {
                if ($this->checkOperando($this->input[$i]) && $this->checkOperando($this->input[$i+1])) {
                    $this->addPonto($i);
                } else if ($this->input[$i] == '*' && $this->checkOperando($this->input[$i+1])) {
                    $this->addPonto($i);
                } else if ($this->input[$i] == '*' && $this->input[$i + 1] == '(') {
                    $this->addPonto($i);
                } else if ($this->checkOperando($this->input[$i]) && $this->input[$i + 1] == '(') {
                    $this->addPonto($i);
                } else if ($this->input[$i] == ')' && $this->checkOperando($this->input[$i+1])) {
                    $this->addPonto($i);
                } else if ($this->input[$i] == ')' && $this->input[$i + 1] == '(') {
                    $this->addPonto($i);
                }
            }
        }
    }

    private function converte()
    {
        for ($i = 0; $i < count($this->input); $i++) {
            if ($this->checkOperando($this->input[$i])) {
                $this->saida .= $this->input[$i];
            } else if ($this->input[$i] == '(') {
                $this->empilha($this->input[$i]);
            } else if ($this->input[$i] == ')') {
                while (true) {
                    $x = $this->desempilha();
                    if ($x == '(') break;
                    else if ($this->topo == -1) {
                        unset($_SESSION['saida']);
                        $_SESSION['erro'] = "parenteses nao aberto/fechado";
                        header("Location: index.php");
                        exit;
                    }
                    $this->saida .= $x;
                }
            } else if (($this->input[$i] == '*')) {
                $this->saida .= $this->input[$i];
            } else if ($this->topo >= 0) {
                while ($this->prioridade($this->pilha[$this->topo]) >= $this->prioridade($this->input[$i])) {
                    $this->saida .= $this->desempilha();
                    if ($this->topo == -1) break;
                }
                $this->empilha($this->input[$i]);
            } else {
                $this->empilha($this->input[$i]);
            }
        }
    }

    private function checkPilha()
    {
        while ($this->topo > -1) {

            $this->saida .= $this->desempilha();

            if (str_contains($this->saida, "(") || str_contains($this->saida, "(")) {
                unset($_SESSION['saida']);
                $_SESSION['erro'] = "parenteses nao aberto/fechado";
                header("Location: index.php");
                exit;
            }
        }
    }

    private function restauraContrabarra()
    {
        $this->saida = str_split($this->saida);
        for ($i = 0; $i < count($this->saida); $i++) {
            if ($this->saida[$i] == '#') {
                $x = array_shift($this->contrabarra);
                $start_str = substr(implode("", $this->saida), 0, $i);
                $end_str = substr(implode("", $this->saida), $i + 1);
                $this->saida = $start_str . $x . $end_str;
                $this->saida = str_split($this->saida);
            }
        }
        
    }

    public function converteExpressao(): string {
        $this->trataContrabarra();
        $this->trataConcatenacoes();
        $this->converte();
        $this->checkPilha();
        $this->restauraContrabarra();

        return implode($this->saida);
    }
}

?>