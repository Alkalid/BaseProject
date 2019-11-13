#include <stdio.h>
#include <stdlib.h>
/*
writen by Jibanyan_FCU
*/
typedef struct node{
    int n;
    struct node *leftptr;
    struct node *rightptr;
}NODE, *LIST;

LIST minptr = NULL, maxptr = NULL;

void insert(){
    LIST newptr = (LIST)malloc(sizeof(NODE)), tempptr = minptr;
    if(newptr==NULL){
        printf("Memory is full!!\n");
        return;
    }
    printf("Input a number: ");
    scanf("%d",&newptr->n);
    if(minptr == NULL){
        minptr = maxptr = newptr->leftptr = newptr->rightptr = newptr;
    }
    else{
        do{
            if(tempptr->n >= newptr->n) break;
            tempptr = tempptr->rightptr;
        }while(tempptr!=minptr);
        newptr->leftptr = tempptr->leftptr;
        tempptr->leftptr->rightptr = newptr;
        newptr->rightptr = tempptr;
        tempptr->leftptr = newptr;
        if(newptr->n > maxptr->n) maxptr = newptr;
        else if(newptr->n < minptr->n) minptr = newptr;
    }
}

void delete(){
    int n;
    int flag = 0;
    LIST tempptr = minptr;
    if(tempptr == NULL){
        printf("No element!!\n");
        return;
    }
    printf("Input a number you want to delete: ");
    scanf("%d",&n);
    do{
        if(tempptr->n == n){
            flag = 1;
            break;
        }
    }while(tempptr != minptr);
    if(flag == 0) printf("Not found!!\n");
    else{
        tempptr->leftptr->rightptr = tempptr->rightptr;
        tempptr->rightptr->leftptr = tempptr->leftptr;
        if(minptr == maxptr) minptr = maxptr = NULL;
        else if(tempptr == maxptr) maxptr = maxptr->leftptr;
        else if (tempptr == minptr) minptr = minptr->rightptr;
        free(tempptr);
    }
}

void print(){
    LIST tempptr = minptr;
    if(tempptr == NULL){
        printf("No element!!\n");
        return;
    }
    do{
        printf("%d ",tempptr->n);
        tempptr = tempptr->rightptr;
    }while(tempptr!=minptr);
    puts("");
}

int main()
{
    void (*f[3])() = {insert,delete,print};
    int sel;
    while(1){
        printf("[1] insert [2] delete [3] print [others] exit\n");
        scanf("%d",&sel);
        if(sel >= 1 && sel <= 3) f[sel-1]();
        else break;
    }
    return 0;
}
