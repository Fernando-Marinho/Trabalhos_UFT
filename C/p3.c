#include <stdio.h>
#include <stdlib.h>

int pvetor(int *v, int tam)
{ //preenche vetor

    int i;
    for (i = 0; i < tam; i++)
    {
        scanf("%i", &v[i]);
        //printf("%i", v[i]);
    }
    
}

int buscabinaria(int *v, int inicio, int fim, int nbusca)
{
    int qtd = 1;
    int meio = (inicio + fim) / 2;

    //printf("%i", qtd);

    if (inicio > fim)
    {
        return 1;
    }

    if (nbusca == v[meio])
    {
        return 1;
    }
    else
    {
        if (nbusca > v[meio]){
            qtd += buscabinaria(v, meio + 1, fim, nbusca);
        }
        else{
            qtd += buscabinaria(v, inicio, meio - 1, nbusca);
        }
        if (qtd > 0){
            return qtd;
        }
    }
}

int main(int argc, char const *argv[])
{
    int tam, nbusca;
    puts("Tamanho do vetor numero a ser buscado");
    scanf("%i %i", &tam, &nbusca);

    int *v = (int *)malloc(sizeof(int) * tam);

    pvetor(v, tam);

    printf("\n%i\n", buscabinaria(v, 0, tam, nbusca));

    return 0;
}