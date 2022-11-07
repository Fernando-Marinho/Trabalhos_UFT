#include <stdio.h>
int main () {
	float n1, n2, n3, med, ef, nf=5;
	scanf("%f %f %f", &n1, &n2, &n3);
	med=(n1+n2+n3)/3;
	if(med>=7){
		printf("Media: %0.2f\nAprovado\n",med);
	}
	if(med>=4){
		printf("Media: %0.2f\nExame Final\n",med);
		ef=10-med;
		printf("Nota necessaria no exame final para ser aprovado:%0.2f\n",ef);
	}
	if(med<4){
		printf("Media: %0.2f\nReprovado\n",med);
	}
	return 0;
}