
#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#define N1 3
#define M 2
#define M2 3
int main(){
  srand(time(NULL));
  int A[N1][M]={{2,3},{0,0},{6,4}}, B[M][M2]={{4,5,2},{0,9,8}}, R[N1][M2], i, j, k, s;
  //printf("Conteudo da Matriz A\n");
  // for(i=0; i<N1; i++, putchar('\n'))
  //   for(j=0; j<M; j++){
  //     A[i][j] = rand()%10;
  //     printf("%d ", A[i][j]);
  //   }
  // printf("Conteudo da Matriz B\n");
  // for(i=0; i<M; i++, putchar('\n'))
  //   for(j=0; j<M2; j++){
  //     B[i][j] = rand()%10;
  //     printf("%d ", B[i][j]);
  //  }
     printf("zerando matriz resultado\n");
   for(i=0; i<N1; i++, putchar('\n'))
     for(j=0; j<M2; j++){
       R[i][j] = 0;
       printf("%d ", R[i][j]);
     }

  printf("Conteudo da Matriz A*B\n");
  for(i=0; i<N1; i++, putchar('\n'))
    for(j=0; j<M2; j++){
      for(k=0; k<M; k++)
        R[i][j] += A[i][k]*B[k][j];
      printf("%d ", R[i][j]);
    }
    return 0;
}