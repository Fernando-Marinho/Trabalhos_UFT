<?php
require_once("automato.php");

class Analisador
{
    private $automato;
    private $fita;
    private $estadoAtual;
    private $ultimoEstadoFinal;
    private $simboloAtual;
    private $contador;

    function __construct(Automato $automato, String $fita)
    {
        $this->automato = $automato;
        $this->fita = $fita;
    }

    private function ehEstadoFinal($estadoAtual)
    {
        for ($i = 0; $i < count($this->automato->getEstadosFinais()); $i++) {
            if ($estadoAtual == $this->automato->getEstadosFinais()[$i]) return $estadoAtual;
        }

        return false;
    }

    public function reconhecer()
    {
        $this->estadoAtual = $this->automato->getEstadoInicial();
        $this->ultimoEstadoFinal = null;
        $this->contador = 0;
        $this->fita = str_split($this->fita);
        while (count($this->fita) > 0) {
            $this->simboloAtual = array_shift($this->fita);
            if($this->simboloAtual == '\\') $this->simboloAtual = $this->simboloAtual.array_shift($this->fita);
            $existeTransicao = $this->automato->getTransicao()->getfuncaoTransicao($this->estadoAtual, $this->simboloAtual);
            if (count($existeTransicao) > 0) {
                $this->estadoAtual = $existeTransicao[0];
            } else {
                return false;
            }
        }
  
        return  $this->ehEstadoFinal($this->estadoAtual);
    }
}
