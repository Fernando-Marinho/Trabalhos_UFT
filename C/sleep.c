#include<stdio.h>
#include<unistd.h>

int main(int argc, char const *argv[])
{
    int control=1;
    while(control!=0){
        printf("Hello ");
        sleep(2);
        printf("World\n");
        printf("Pressione uma tecla diferente de 0 para repetir o processo.\n");
        scanf("%d", &control);
    }
    //fflush(stdin);
    //getchar();
    return 0;
}
