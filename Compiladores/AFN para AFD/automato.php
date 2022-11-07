<?php

class Transicao
{
    private $q = [];
    private $a = [];
    private $p = [];

    public function setFuncaoTransicao(int $q1, string $a1, int $p1)
    {
        array_push($this->q, $q1);
        array_push($this->a, $a1);
        array_push($this->p, $p1);
    }

    public function getFuncaoTransicao(int $q1, string $a1)
    {
        $retorno = [];
        $qt = 0;

        for ($i = 0; $i < count($this->q); $i++) {
            if ($this->q[$i] == $q1 && $this->a[$i] == $a1) {
                $retorno[$qt] = $this->p[$i];
                $qt++;
            }
        }

        return $retorno;
    }

    public function atualizaFuncaoTransicao(int $q1, int $start)
    {
        for ($i = 0; $i < count($this->q); $i++) {
            if ($this->q[$i] == $q1) {
                $this->q[$i] = $start;
            }
        }
    }
}

class Automato
{
    private $alfabeto = [];
    private $estado = [];
    private $transicao = [];
    private $inicial;
    private $finais = [];
    private $fecho = [];

    function __construct()
    {
        $this->transicao = new Transicao();
    }

    public function setSimbolo(string $a)
    {
        array_push($this->alfabeto, $a);
    }

    public function setEstado(int|string $a)
    {
        array_push($this->estado, $a);
    }

    public function ehEstado(int $q1): bool
    {
        for ($i = 0; $i < count($this->estado); $i++) {
            if ($q1 == $this->estado[$i]) {
                return true;
            }
        }
        return false;
    }

    public function ehSimbolo(string $a1): bool
    {
        for ($i = 0; $i < count($this->alfabeto); $i++) {
            if ($a1 == $this->alfabeto[$i]) {
                return true;
            }
        }
        return false;
    }


    public function setTransicao(int $q1, string $a1, int $p1)
    {
        // if ($this->EhEstado($q1) && $this->EhSimbolo($a1) && $this->EhEstado($p1)) {
        $this->transicao->setFuncaoTransicao($q1, $a1, $p1);
        // }
    }

    public function setEstadoInicial(int $q1)
    {
        for ($i = 0; $i < count($this->estado); $i++) {
            if ($this->estado[$i] == $q1) {
                $this->inicial = $i;
            }
        }
    }

    public function setEstadoFinal(int $q1)
    {
        for ($i = 0; $i < count($this->estado); $i++) {
            if ($this->estado[$i] == $q1) {
                array_push($this->finais, $i);
            }
        }
    }

    public function getEstadoInicial()
    {
        return $this->inicial;
    }

    public function getEstadosFinais()
    {
        return $this->finais;
    }

    public function getAlfabeto()
    {
        return $this->alfabeto;
    }

    public function imprimir()
    {
        echo ("<p>Alfabeto: " . implode($this->alfabeto) . "</p>");
        echo ("<p>Estados: ");
        for ($i = 0; $i < count($this->estado); $i++) {
            if ($this->inicial == $i) {
                echo ("->");
            }
            for ($j = 0; $j < count($this->finais); $j++) {
                if ($this->finais[$j] == $i) {
                    echo ("*");
                    break;
                }
            }
            echo ("q" . $this->estado[$i] . " ");
        }
        echo ("</p>");
        echo ("<p>Transições: </p>");
        for ($i = 0; $i < count($this->estado); $i++) {
            for ($j = 0; $j < count($this->alfabeto); $j++) {
                $retorno = $this->transicao->getFuncaoTransicao($this->estado[$i], $this->alfabeto[$j]);
                if (count($retorno) == 0) continue;
                echo ("T(q" . $this->estado[$i] . "," . $this->alfabeto[$j] . ") = {");

                for ($k = 0; $k < count($retorno); $k++) {
                    if ($k > 0 && $k < count($retorno)) {
                        echo (",q" . $retorno[$k]);
                    } else {
                        echo ("q" . $retorno[$k]);
                    }
                }
                echo ("}<br>");
            }
        }
    }

    public function uneAlfabetos(array $a, array $b)
    {
        $alfabeto = array_unique(array_merge($a, $b));

        if (!in_array('&', $alfabeto)) {
            array_push($alfabeto, '&');
        }

        return $alfabeto;
    }

    public function base(string $char)
    {
        $a = new Automato();
        $a->setSimbolo($char);
        $a->setEstado(0);
        $a->setEstadoInicial(0);

        $a->setEstado(1);
        $a->setEstadoFinal(1);

        $a->setTransicao(0, $char, 1);

        return $a;
    }

    public function fechoDeKleene(Automato $a)
    {
        $a->setTransicao($a->finais[0], '&', $a->inicial);
        $novo = new Automato();
        $novo->estado = $a->estado;
        $novo->alfabeto = $this->uneAlfabetos($a->alfabeto, []);
        $novo->transicao = $a->transicao;

        $novo->setEstado(0);
        $novo->setEstado(count($novo->estado));

        $qtdEstados = count($novo->estado);

        $novo->inicial = $qtdEstados - 2;
        $novo->finais[0] = $qtdEstados - 1;

        $novo->setTransicao($novo->estado[$qtdEstados - 2], '&', $novo->estado[$qtdEstados - 1]);
        $novo->setTransicao($novo->estado[$qtdEstados - 2], '&', $a->inicial);
        $novo->setTransicao($a->finais[0], '&', $novo->estado[$qtdEstados - 1]);

        return $novo;
    }

    public function concatenacao(Automato $a, Automato $b)
    {
        $novo = new Automato();
        $novo->alfabeto = $this->uneAlfabetos($a->alfabeto, $b->alfabeto);

        $startA = 0;
        $startB = count($a->estado);

        $novo->renomeiaEstados($a, $startA);
        $novo->renomeiaEstados($b, $startB);

        $novo->setEstadoInicial(0);
        $novo->finais[0] = count($novo->estado) - 1;

        $novo->setTransicao($a->finais[0] + $startA, '&', $b->inicial + $startB);

        return $novo;
    }

    public function uniao(Automato $a, Automato $b)
    {
        $novo = new Automato();
        $novo->alfabeto = $this->uneAlfabetos($a->alfabeto, $b->alfabeto);
        $novo->setEstado(0);
        $novo->setEstadoInicial(0);

        $startA = 1;
        $startB = count($a->estado) + 1;

        $novo->renomeiaEstados($a, $startA);
        $novo->renomeiaEstados($b, $startB);

        $novo->setEstado(count($novo->estado));
        $novo->finais[0] = count($novo->estado) - 1;

        $novo->setTransicao($novo->inicial, '&', $a->inicial + $startA);
        $novo->setTransicao($novo->inicial, '&', $b->inicial + $startB);

        $novo->setTransicao($a->finais[0] + $startA, '&', $novo->finais[0]);
        $novo->setTransicao($b->finais[0] + $startB, '&', $novo->finais[0]);

        return $novo;
    }

    public function renomeiaEstados(Automato $a, int $startA, $flag = false)
    {
        for ($i = 0; $i < count($a->estado); $i++) {
            $this->setEstado($i + $startA);
            for ($j = 0; $j < count($a->alfabeto); $j++) {
                $retorno = $a->transicao->getFuncaoTransicao($a->estado[$i], $a->alfabeto[$j]);
                if (count($retorno) == 0) continue;
                for ($k = 0; $k < count($retorno); $k++) {
                    $this->setTransicao($a->estado[$i] + $startA, $a->alfabeto[$j], $retorno[$k] + $startA);
                }
            }
        }
    }

    private function fechoE()
    {
        for ($i = 0; $i < count($this->estado); $i++) {

            if (!is_null($this->fecho[$i])) {
            } else {
                $this->fecho[$i] = [$this->estado[$i]];
            }

            if (in_array('&', $this->alfabeto)) {
                $j = array_search('&', $this->alfabeto);
                $retorno = $this->transicao->getFuncaoTransicao($this->estado[$i], $this->alfabeto[$j]);

                if (count($retorno) == 0) continue;
                for ($k = 0; $k < count($retorno); $k++) {
                    array_push($this->fecho[$i], $retorno[$k]);
                }
            };
        }
        return $this->fecho;
    }

    private function fechoERecursivo($fecho, $aux)
    {
        $retorno = [];
        for ($i = 0; $i < count($fecho); $i++) {
            if ($fecho[$i][0] == $aux) {
                for ($j = 0; $j < count($fecho[$i]); $j++) {
                    array_push($retorno, $fecho[$i][$j]);
                }
            }
        }
        return $retorno;
    }

    private function buscarFechoE()
    {
        $fecho = $this->fechoE();
        $fechoCompleto = [];

        for ($i = 0; $i < count($fecho); $i++) {
            $temp_fecho = [];

            for ($j = 0; $j < count($fecho[$i]); $j++) {
                array_push($temp_fecho, $fecho[$i][$j]);
            }

            for ($j = 1; $j < count($temp_fecho); $j++) {
                $retorno = $this->fechoERecursivo($fecho, $temp_fecho[$j]);
                if (count($retorno) > 1) {
                    array_shift($retorno);
                    for ($k = 0; $k < count($retorno); $k++) {
                        if (!in_array($retorno[$k], $temp_fecho))
                            array_push($temp_fecho, $retorno[$k]);
                    }
                }
            }

            sort($temp_fecho);
            array_push($fechoCompleto, $temp_fecho);
        }

        return $fechoCompleto;
    }

    public function imprimirFechoE()
    {
        echo ("</br>");
        $fecho = $this->buscarFechoE();

        for ($i = 0; $i < count($fecho); $i++) {
            echo ("Fecho ε(q" . $i . "): {");
            for ($j = 0; $j < count($fecho[$i]); $j++) {
                if ($j == count($fecho[$i]) - 1) {
                    echo ("q" . $fecho[$i][$j]);
                } else {
                    echo ("q" . $fecho[$i][$j] . ", ");
                }
            }

            echo ("}</br>");
        }
    }

    private function auxConverteParaAFD($atual, $alfabeto, $novos_estados, $aux, $antigo)
    {
        for ($i = 0; $i < count($alfabeto); $i++) {
            $estado = [];
            for ($j = 0; $j < count($atual); $j++) {
                $temp_estado = $antigo->transicao->getFuncaoTransicao($atual[$j], $alfabeto[$i])[0];
                if ($temp_estado)
                    array_push($estado, $temp_estado);
            }

            if ($estado) {
                for ($k = 0; $k < count($estado); $k++) {
                    if ($antigo->fecho[$estado[$k]]) {
                        $novo = implode("", $antigo->fecho[$estado[$k]]);
                        $novo = intval($novo);
                        if (!in_array($novo, $this->estado))
                            $this->setEstado($novo);

                        $finais_antigo = $antigo->finais;

                        for ($l = 0; $l < count($finais_antigo); $l++) {
                            for ($m = 0; $m < count($antigo->fecho[$estado[$k]]); $m++) {
                                if ($finais_antigo[$l] == $antigo->fecho[$estado[$k]][$m]) {
                                    $this->setEstadoFinal($novo);
                                }
                            }
                        }

                        $this->transicao->setFuncaoTransicao($aux, $alfabeto[$i], $novo);
                        array_push($novos_estados, $antigo->fecho[$estado[$k]]);
                    } else {
                        $this->transicao->setFuncaoTransicao($aux, $alfabeto[$i], 999);
                    }
                }
            } else {
                $this->transicao->setFuncaoTransicao($aux, $alfabeto[$i], 999);
            }
        }

        $atual = array_shift($novos_estados);


        if ($atual) {
            $novo = implode("", $atual);
            $novo = intval($novo);
            $this->auxConverteParaAFD($atual, $alfabeto, $novos_estados, $novo, $antigo);
        }
    }

    public function converteParaAFD()
    {
        $novo = new Automato();
        $alfabeto = $this->getAlfabeto();

        if (in_array('&', $alfabeto)) {
            $temp = implode('', $alfabeto);
            $temp = str_replace('&', '', $temp);
            $alfabeto = str_split($temp);
        }

        $novo->setEstado(999);

        for ($i = 0; $i < count($alfabeto); $i++) {
            $novo->setSimbolo($alfabeto[$i]);
            $novo->transicao->setFuncaoTransicao(999, $alfabeto[$i], 999);
        }

        $this->fecho = [];
        $fecho = $this->buscarFechoE();

        $atual = array_shift($fecho);
        $temp = [];

        for ($i = 0; $i < count($atual); $i++) {
            if (!in_array($atual[$i], $temp)) array_push($temp, $atual[$i]);
        }

        $atual = $temp;

        $aux = implode('', $atual);
        $aux = intval($aux);

        $novo->setEstado($aux);
        $novo->setEstadoInicial($aux);

        
        $novos_estados = [];
        $novo->auxConverteParaAFD($atual, $alfabeto, $novos_estados, $aux, $this);
        
        $temp_estados = $novo->estado;
        
        $temp_estados1 = array_slice($temp_estados, $novo->inicial + 1);
        $temp_estados2 = array_slice($temp_estados, 0, $novo->inicial);
        $temp_estados = array_merge($temp_estados1, $temp_estados2);
        
        array_unshift($temp_estados, $novo->estado[$novo->inicial]);
        
        $novo->estado = $temp_estados;
        $novo->inicial = 0;

        $tab_eq = [];

        for ($i = 0; $i < count($novo->estado); $i++) {
            $tab_eq[$i] = $novo->estado[$i];
        }

        $saida = new Automato();
        $saida->alfabeto = $novo->alfabeto;

        for ($i = 0; $i < count($novo->estado); $i++) {
            $saida->setEstado($i);
        }
        $i = 0;
        while ($i < count($novo->estado)) {
            for ($j = 0; $j < count($novo->alfabeto); $j++) {
                $retorno = $novo->transicao->getFuncaoTransicao($novo->estado[$i], $novo->alfabeto[$j]);
                $a = array_search($novo->estado[$i], $tab_eq);

                if (count($retorno) == 0) continue;
                for ($k = 0; $k < count($retorno); $k++) {
                    $b = array_search($retorno[$k], $tab_eq);
                    $saida->setTransicao($a, $novo->alfabeto[$j], $b);
                }
            }
            $i++;
        }

        $saidaInicial = array_search($novo->estado[$novo->inicial], $tab_eq);
        $saida->setEstadoInicial($saidaInicial);

        for ($i=0; $i < count($novo->finais); $i++) { 
            $saidaFinal = array_search($novo->estado[$novo->finais[$i]], $tab_eq);
            $saida->setEstadoFinal($saidaFinal);
        }
        $saida->imprimir();
    }
}
