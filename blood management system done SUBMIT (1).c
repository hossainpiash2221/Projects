#include<stdio.h>
#include<stdlib.h>
#include<string.h>

struct node
{

    int  rno;
    char pname[30];
    char paddress[30];
    char pmobile[20];
    char pblood[10];
    int page;

    struct node *next;
}*front=NULL, *rear=NULL;



struct doner
{
    int rno;
    char name[20];
    int age;
    char mobile[20];
    char address[50];
    char bloodgroup[10];
    char lastdonet[10];

    char distance[10];

    struct doner *next;
} *head=NULL,*top=NULL;



void printfline()
{
    printf("\n");
    int i;
    for(i=0; i<113; i++)
    {
        printf("-");
    }
    printf("\n");
}







//queue er enque deque print list

void  enqueue()                                                   //ENQUEUE..................
{

    int rno;
    char pname[30];
    char paddress[30];
    char pmobile[20];
    char pblood[10];
    int page;

    struct node *newnode;
    newnode = (struct node *)malloc(sizeof(struct node));

    printf("\n\n\tSerial no:");
    scanf("%d",&rno);
    newnode->rno=rno;

    fflush(stdin);
    printf("\n\n\tPatient name:");
    gets(pname);
    strcpy(newnode->pname,pname);

    printf("\n\n\tPatient Age:");
    scanf("%d",&page);
    newnode->page=page;

    fflush(stdin);
    printf("\n\n\tBlood group:");
    gets(pblood);
    strcpy(newnode->pblood,pblood);

    printf("\n\n\tMobile Number:");
    gets(pmobile);
    strcpy(newnode->pmobile,pmobile);

    printf("\n\n\tAddress:");
    gets(paddress);
    strcpy(newnode->paddress,paddress);

    newnode->next=NULL;

    if(front==NULL && rear==NULL)
    {
        front=rear=newnode;
    }
    else
    {
        rear->next=newnode;
        rear=newnode;
    }

}




void dequeue()                                                      // DEQUEUE.................
{
    if(front==NULL)
    {
        printf("\t\t\t      If you need Blood.Please,give your information first.!");
    }
    else
    {
        struct node *temp=front;
        front=front->next;
        if(front==NULL)
        {
            rear=NULL;
        }
    }

    printf("\n\n\t\t\t\t First one was completed!");
}





void printList()                                                    //PRINTLIST..............
{
    printfline();

    printf("|%-8s|%-20s|%-12s|%-10s|%-17s|%-25s|\n","SERIAL","PATIENT NAME","AGE","BLOOD","MOBILE NUMBER","ADDRESS");
    printfline();

    struct node *temp=front;
    if(front==NULL)
    {
        printf("\t\t\t\t It seems everyone is fine,Nobody needs blood.!");
    }
    else
    {
        while(temp->next!=NULL)
        {

            printf("|%-8d|%-20s|%d Years    |%-10s|%-17s|%-25s|\n",temp->rno,temp->pname,temp->page,temp->pblood,temp->pmobile,temp->paddress);
            temp=temp->next;
        }
        printf("|%-8d|%-20s|%d Years    |%-10s|%-17s|%-25s|\n",temp->rno,temp->pname,temp->page,temp->pblood,temp->pmobile,temp->paddress);


    }
    printfline();
}







//stack er push pop

void push(struct doner *ptr)                                                //PUSH........................
{
    if(top==NULL)
    {

        ptr->next=NULL;
        top=ptr;
    }
    else
    {
        ptr->next=top;
        top=ptr;
    }

}






void pop()                                                                 //POP=================
{
    struct doner *p=top;
    if(top==NULL)
    {
        printf("\t\t\t\t\tEmpty");
        printfline();
    }
    else
    {

        top=top->next;
        free(p);
    }
}






//delete er sob first last by pos

void deletefirst()                                                              //DELETE FIRST====================
{
    if(head==NULL)
    {
        printf(" empty list");
    }
    else
    {
        struct doner *ptr=head;
        head=head->next;
        push(ptr);
    }

}




void deletemid(int index)                                               //DELETE MID===========
{
    int i;
    struct doner *p=head;
    struct doner *q=head->next;

    for(i=1; i<index-1; i++)
    {
        p=p->next;
        q=q->next;
    }
    p->next=q->next;
    push(q);

}



void deletelast()                                                       //DELETE LAST==============
{
    if(head==NULL)
    {
        printf("Empty");

    }
    if(head->next==NULL)
    {
        struct node *ptr=head;
        head=NULL;
        free(ptr);
    }
    else
    {
        struct doner *p=head;
        struct doner *q=head->next;

        while(q->next!=NULL)
        {
            p=p->next;
            q=q->next;
        }
        p->next=NULL;
        push(q);
    }

}






void deletebypos(struct doner *h)                                                   // DELTE BY POS==============
{
    system("cls");

    printf("\n\n\t\t\t\t\t|Delete information|\n");
    printfline();
    int n=count(h);
    int pos=0;

    char donorname[30];
    int found=0,value;
    fflush(stdin);
    printf("\n\tEnter donor name for delete:");
    gets(donorname);
    printfline();
    printf("|%-8s|%-20s|%-12s|%-10s|%-17s|%-25s|%-10s|\n","SERIAL","DONOR NAME","AGE","Blood","Mobile NUMBER","ADDRESS","LAST DONATE");
    printfline();
    while(h!=NULL && found==0)
    {
        value=strcmp(donorname,h->name);
        if(value==0)
        {
            pos++;
            printf("|%-8d|%-20s|%d Years    |%-10s|%-17s|%-25s|%-10s|\n",h->rno,h->name,h->age,h->bloodgroup,h->mobile,h->address,h->lastdonet);
            printfline();

            char choise;
            printf("\n\nIs this the donor whose information need to be delete ?(y/n):");
            scanf(" %c",&choise);


            if(choise=='y')
            {
                found=1;
                if(pos==1)
                {
                    deletefirst();
                    break;
                }
                if(pos==n)
                {
                    deletelast();
                }
                if(pos>1 && pos<n)
                {
                    deletemid(pos);
                }



                //delete call korte hobe






            }
            else
            {
                system("cls");
                printf("\n\n\t\t\t\t\t|Delete information|\n");
                printfline();
                struct doner *ptr=h->next;
                pos++;

                char number[15];
                int value2;
                fflush(stdin);
                printf("\n\t\tEnter mobile number of donor:");
                gets(number);
                printfline();
                printf("|%-8s|%-20s|%-12s|%-10s|%-17s|%-25s|%-10s|\n","SERIAL","DONOR NAME","AGE","Blood","Mobile NUMBER","ADDRESS","LAST DONATE");
                printfline();

                while(ptr!=NULL && found !=2)
                {

                    value2=strcmp(number,ptr->mobile);

                    if(value2==0)
                    {


                        printf("|%-8d|%-20s|%d Years    |%-10s|%-17s|%-25s|%-10s|\n",ptr->rno,ptr->name,ptr->age,ptr->bloodgroup,ptr->mobile,ptr->address,ptr->lastdonet);
                        printfline();

                        if(pos==1)
                        {
                            deletefirst();
                        }
                        if(pos==n)
                        {
                            deletelast();
                        }
                        if(pos>1 && pos<n)
                        {
                            deletemid(pos);
                        }




                        //delete korte hobe

                        found=2;


                    }
                    ptr=ptr->next;
                    pos++;

                }
                break;

            }
        }
        h=h->next;
        pos++;
    }

    if(!found)
    {
        printf("\t\t\t\t\t Sorry!!...Record is not found!\n");
    }
    else
    {
        printfline();
        printf("\n\t\t\t\t\t Deleted Successfully");
    }
    printfline();
}










//update
void update(struct doner *h)                                                //UPADATE ======================
{
    system("cls");
    int n=count(h);

    char donorname[30];
    int found=0,value;
    fflush(stdin);
    printf("\n\n\t\t\t\t\t Update Information of Blood Donor.");
    printfline();
    printf("\n\n\t\tEnter donor name for update:");
    gets(donorname);
    printfline();
    printf("|%-8s|%-20s|%-12s|%-10s|%-17s|%-25s|%-10s|\n","SERIAL","DONOR NAME","AGE","Blood","Mobile NUMBER","ADDRESS","LAST DONET");
    printfline();
    while(h!=NULL && found==0)
    {
        value=strcmp(donorname,h->name);
        if(value==0)
        {

            printf("|%-8d|%-20s|%d Years    |%-10s|%-17s|%-25s|%-10s|\n",h->rno,h->name,h->age,h->bloodgroup,h->mobile,h->address,h->lastdonet);
            printfline();

            char choise;
            printf("\n\nIs this a donor whose need to update ?(y/n):");
            scanf(" %c",&choise);

            system("cls");

            if(choise=='y')
            {

                int rno;
                char name[20];
                int age;
                char mobile[20];
                char address[50];
                char bloodgroup[10];
                char lastdonet[10];
                char distance[10];

                printfline();

                printf("\n\n\t\t\t\t\t Update Donor Information\n\n ");
                printfline();
                printf("\tSerial no:");
                scanf("%d",&rno);

                fflush(stdin);
                printf("\n\tDonor name:");
                gets(name);

                printf("\n\tDonor Age:");
                scanf("%d",&age);

                fflush(stdin);
                printf("\n\tBlood group(A+ / B+ / O+ / O-/ AB+ / A- / B-) :");
                gets(bloodgroup);

                printf("\n\tMobile number:");
                gets(mobile);

                printf("\n\tAddress:");
                gets(address);

                printf("\n\tLast donate date(day/month/last 2 digit of year:");
                gets(lastdonet);
                printfline();

                h->rno=rno;
                strcpy(h->name,name);
                strcpy(h->mobile,mobile);


                strcpy(h->bloodgroup,bloodgroup);
                strcpy(h->mobile,mobile);
                strcpy(h->address,address);
                strcpy(h->lastdonet,lastdonet);
                found=1;

            }
            else
            {
                system("cls");
                struct doner *ptr=h->next;
                char number[15];
                int value2;
                fflush(stdin);
                printf("\n\n\t\t\t\t\t Update Donor Information\n\n ");
                printf("\n\n\t\tEnter mobile number of donor:");
                gets(number);
                printfline();
                printf("|%-8s|%-20s|%-12s|%-10s|%-17s|%-25s|%-10s|\n","SERIAL","DONOR NAME","AGE","Blood","MOBILE NUMBER","ADDRESS","LAST DONATE");
                printfline();

                while(ptr!=NULL && found !=2)
                {

                    value2=strcmp(number,ptr->mobile);

                    if(value2==0)
                    {


                        printf("|%-8d|%-20s|%d Years    |%-10s|%-17s|%-25s|%-10s|\n",ptr->rno,ptr->name,ptr->age,ptr->bloodgroup,ptr->mobile,ptr->address,ptr->lastdonet);
                        printfline();

                        int rno;
                        char name[20];
                        int age;
                        char mobile[20];
                        char address[50];
                        char bloodgroup[10];
                        char lastdonet[10];
                        char distance[10];

                        printf("\n\t\t Update patient Information\n\n ");
                        printfline();
                        printf("\tSerial no:");
                        scanf("%d",&rno);

                        fflush(stdin);
                        printf("\n\tDonor name:");
                        gets(name);

                        printf("\n\tDonor Age:");
                        scanf("%d",&age);

                        fflush(stdin);
                        printf("\n\tBlood group(A+ / B+ / O+ / O-/ AB+ / A- / B-) :");
                        gets(bloodgroup);

                        printf("\n\tMobile number:");
                        gets(mobile);

                        printf("\n\tAddress:");
                        gets(address);

                        printf("\n\tLast donate date(day/month/last two digit of years):");
                        gets(lastdonet);
                        printfline();

                        ptr->rno=rno;
                        strcpy(ptr->name,name);
                        ptr->age=age;
                        strcpy(ptr->mobile,mobile);


                        strcpy(ptr->bloodgroup,bloodgroup);
                        strcpy(ptr->mobile,mobile);
                        strcpy(ptr->address,address);
                        strcpy(ptr->lastdonet,lastdonet);

                        found=2;


                    }
                    ptr=ptr->next;

                }
                break;

            }
        }
        h=h->next;
    }

    if(!found)
    {
        printf("\t\t\t\t\t Sorry!!...Record is not found!\n");
    }
    else
    {
        printf("\t\t\t\t\t\t Update successfully");
    }
    printfline();
}







//search
void search(struct doner *h)                                                         //SEARCH==============
{
    system("cls");
    int numdonor=0;
    int n=count(h);
    char blood[10],place[30];
    int found=0,value,value2;
    int choose;


    fflush(stdin);
    printf("\n\n\n\t\t 1.Search By Place.\n");
    printf("\n\t\t 2.Search By blood group.\n");
    printf("\n\t\t\tEnter your choice:");
    scanf("%d",&choose);

    fflush(stdin);
    if(choose==1)
    {
        printf("\n\n\n\t\t\t Enter your place:");
        gets(place);
    }
    if(choose==2)
    {
        printf("\n\n\n\t\t\tWhich Blood Group you need? :");
        gets(blood);
    }



    printfline();
    printf("|%-8s|%-20s|%-12s|%-10s|%-17s|%-25s|%-10s|\n","SERIAL","DONOR NAME","AGE","BLOOD","MOBILE NUMBER","ADDRESS","LAST DONATE");
    printfline();
    while(h!=NULL)
    {
        value=strcmp(blood,h->bloodgroup);
        value2=strcmp(place,h->address);
        if(value==0 || value2==0)
        {
            numdonor++;
            found=1;
            printf("|%-8d|%-20s|%d Years    |%-10s|%-17s|%-25s|%-10s|\n",h->rno,h->name,h->age,h->bloodgroup,h->mobile,h->address,h->lastdonet);
        }
        h=h->next;
    }
    if(!found)
    {
        printf("\t\t\t\t\tSorry!!...Record is not found!\n");
    }
    printfline();
    printf("\t\t\t\t\t\t|Available %d|",numdonor);
}








//display
void display(struct doner *h)                                                       //DISPLAY==============
{
    system("cls");
    printf("\n\n\t\t\t\t\t |ALL Information of BLood donors|");
    int n=count(h);
    printfline();
    printf("|%-8s|%-20s|%-12s|%-10s|%-17s|%-25s|%-10s|\n","SERIAL","DONOR NAME","AGE","BLOOD","MOBILE NUMBER","ADDRESS","LAST DONATE");
    printfline();
    if(h==NULL)
    {
        printf("\t\t\t\t\t\t\tEmpty");

    }
    while(h!=NULL)
    {
        printf("|%-8d|%-20s|%d Years    |%-10s|%-17s|%-25s|%-10s|\n",h->rno,h->name,h->age,h->bloodgroup,h->mobile,h->address,h->lastdonet);

        h=h->next;
    }
    printfline();
}




//soetednode



struct doner  *sortednode(struct doner *head)                                      //SORTED NODE =======
{
    struct doner *end,*r,*p,*q,*temp;
    if(head==NULL)
    {
        display(head);
    }
    else
    {
        for(end=NULL; end!=head->next; end=p)
        {
            for(r=p=head; p->next!=end; r=p,p=p->next)
            {
                q=p->next;
                if(p->age > q->age)
                {
                    p->next=q->next;
                    q->next=p;
                    if(p!=head)
                    {
                        r->next=q;
                    }
                    else
                    {
                        head=q;
                    }
                    temp=p;
                    p=q;
                    q=temp;
                }
            }
        }
    }
    return head;
}



//create function insert first last mid serially

void create(struct doner *h)
{                                                                                   //CREATE INSERT LAST================
    system("cls");
    int rno;
    char name[20];
    char mobile[20];
    char address[50];
    char bloodgroup[10];
    char lastdonet[10];
    char distance[10];
    int age;


    printf("\n\n\n\t\t\t\t\tEnter Donor Information");
    printfline();
    printf("\n\tSerial no:");
    scanf("%d",&rno);

    fflush(stdin);
    printf("\n\tDonor name:");
    gets(name);


    printf("\n\tDonor Age:");
    scanf("%d",&age);

    fflush(stdin);
    printf("\n\tBlood group(A+ / B+ / O+ / O-/ AB+ / A- / B-) :");
    gets(bloodgroup);

    printf("\n\tMobile number:");
    gets(mobile);

    printf("\n\tAddress:");
    gets(address);

    printf("\n\tLast donate date(day/month/last 2 digits of year):");
    gets(lastdonet);
    printfline();

    if(h==NULL)
    {
        head=(struct doner*)malloc(sizeof(struct doner));
        head->rno=rno;
        strcpy(head->name,name);
        head->age=age;
        strcpy(head->bloodgroup,bloodgroup);
        strcpy(head->mobile,mobile);
        strcpy(head->address,address);
        strcpy(head->lastdonet,lastdonet);

        head->next=NULL;
    }
    else
    {
        while(h->next!=NULL)
        {
            h=h->next;
        }

        h->next=(struct doner*)malloc(sizeof(struct doner));
        h->next->rno=rno;
        strcpy(h->next->name,name);
        h->next->age=age;
        strcpy(h->next->bloodgroup,bloodgroup);
        strcpy(h->next->mobile,mobile);
        strcpy(h->next->address,address);
        strcpy(h->next->lastdonet,lastdonet);
        h->next->next=NULL;

    }
}






void insertfirst(struct doner *h)                                               //INSERT FIRST======================
{

    int rno;
    char name[20];
    int age;
    char mobile[20];
    char address[50];
    char bloodgroup[10];
    char lastdonet[10];
    char distance[10];


    printf("\n\tSerial no:");
    scanf("%d",&rno);

    fflush(stdin);
    printf("\n\tDonor name:");
    gets(name);

    printf("\n\tDonor Age:");
    scanf("%d",&age);

    fflush(stdin);
    printf("\n\tBlood group:");
    gets(bloodgroup);

    printf("\n\tMobile number:");
    gets(mobile);

    printf("\n\tAddress:");
    gets(address);

    printf("\n\tLast donate date(day/month/last 2 digit of year):");
    gets(lastdonet);
    printfline();

    struct doner *temp=(struct doner*)malloc(sizeof(struct doner));
    temp->rno=rno;
    strcpy(temp->name,name);
    temp->age=age;
    strcpy(temp->mobile,mobile);


    strcpy(temp->bloodgroup,bloodgroup);
    strcpy(temp->mobile,mobile);
    strcpy(temp->address,address);
    strcpy(temp->lastdonet,lastdonet);

    temp->next=h;
    head=temp;

    printfline();
}







int count(struct doner *h)                                          //COUNT ================
{
    int cnt=0;
    while(h!=NULL)
    {
        h=h->next;
        cnt++;
    }
    return cnt;
}






void insertmid(struct doner *h)                                         //INSERT MIDDLE=================
{
    system("cls");
    int pos,n=count(h),i;
    printf("\n\n\n\t\t\t\t\tAdd Information of BLOOD DONOR");
    printfline();
    printf("\n\n\tEnter position:");
    scanf("%d",&pos);
    if(pos==1)
    {
        insertfirst(h);
    }
    else if (pos==n+1)
    {
        create(h);
    }
    else if(pos>n)
    {
        printf("Index out of bounds");
    }
    else if(pos>0)
    {
        int rno;
        char name[20];
        char mobile[20];
        char address[50];
        char bloodgroup[10];
        char lastdonet[10];
        char distance[10];
        int age;

        printfline();
        printf("\n\t\t\t Add Donor Information\n\n ");
        printfline();
        printf("\n\tSerial no:");
        scanf("%d",&rno);

        fflush(stdin);
        printf("\n\tDonor name:");
        gets(name);


        printf("\n\t Donor Age:");
        scanf("%d",&age);

        fflush(stdin);
        printf("\n\tBlood group(A+ / B+ / O+ / O-/ AB+ / A- / B-) :");
        gets(bloodgroup);

        printf("\n\tMobile number:");
        gets(mobile);

        printf("\n\tAddress:");
        gets(address);

        printf("\n\tLast donate date(day/month/year):");
        gets(lastdonet);
        printfline();

        for(i=1; i<pos-1; i++)
        {
            h=h->next;
        }
        struct doner *temp=(struct doner*)malloc(sizeof(struct doner));
        temp->rno=rno;
        strcpy(temp->name,name);
        temp->age=age;
        strcpy(temp->mobile,mobile);

        strcpy(temp->bloodgroup,bloodgroup);
        strcpy(temp->mobile,mobile);
        strcpy(temp->address,address);
        strcpy(temp->lastdonet,lastdonet);

        temp->next=h->next;
        h->next=temp;

    }
    printfline();
}





int main()
{
    int ch,cnt,cnt2,i,put,req;
    printf("\n\n\n\n\n\n\t\t\t\t\tLoding |");
    for(i=0; i<2; i++)
    {
        printf("||||");
        sleep(1);
    }

    do
    {
        system("cls");
        cnt=count(head);
        cnt2=count(top);

        printf("\t\t\t\t\t\t\t\t\t\t\t\t\t<------------");
        printf("\t\t\t\t\t  BLOOD MANAGEMENT SYSTEM");

        printfline();
        printf("\t\t\t\t......Total number of Donors:%d.......\n",cnt);
        printf("---->");
        printf("\n\t1.Add donor .");
        printf("\t\t\t\t\t2.All donor information.\n");
        printf("\t3.Insert donor at any position.");
        printf("\t\t\t4.Search blood donor.\n");
        printf("\t5.Update Blood donor.");
        printf("\t\t\t\t6.Delete information.\n");
        printf("\t7.Recycle Bin.....Item(%d)",cnt2);
        printf("\t\t\t8.Blood request.\n");
        printf("\n\t\t\t\t\t0.exit\n");
        printf("\t\t\t\t\t\t\t\t\t\t\t\t\t<------------");
        printfline();
        printf("---->");
        printf("\n\tEnter your choice:");
        scanf("%d",&ch);

        switch(ch)
        {
        case 1:
            create(head);
            break;
        case 2:
            printf("\n\n\t\t 1. for Sorted Info by age. ");
            printf("\n\t\t 2. for Unsorted Info. ");
            printf("\n\n\tEnter your choice:");
            scanf("%d",&put);
            if(put==1)
            {
                head=sortednode(head);
                display(head);
            }
            else
            {
                display(head);
            }
            break;
        case 3:
            insertmid(head);
            break;
        case 4:
            search(head);
            break;
        case 5:
            update(head);
            break;
        case 6:
            deletebypos(head);
            break;
        case 7:
            display(top);

            int press;
            printf("\n\n\t\t 1.Clean Top info.");
            printf("\n\n\t\t 2.All Clean.");
            printf("\n\n\t\t\tEnter your choice:");
            scanf("%d",&press);
            if(press==1)
            {
                printf("");
                pop();
            }
            if(press==2)
            {
                while(head!=NULL)
                {
                    pop();
                    head=head->next;
                }
            }
            break;
        case 8:

first:
second:
            system("cls");

            printf("\n\n\t\t\t..........Blood Request..........");

            printf("\n\n\t 1.Blood Request");
            printf("\n\n\t 2.All requested.");
            printf("\n\n\t 3.Back;");
            printf("\n\n\t\tEnter your choice:");
            scanf("%d",&req);
            if(req==1)
            {

                enqueue();
                goto second;


            }

            if(req==2)
            {
                int one;
                printList();
                printf("\n\n\t 1.First one  accepted.");
                printf("\n\n\t 2.Back");

                printf("\n\n\t\tEnter your choice:");
                scanf("%d",&one);
                if(one==1)
                {
                    dequeue();
                }
                else
                {
                    goto first;
                }
            }
            break;

        case 0:
            printf("\t\t\t\t..........Thank You............\n\n\n");
            break;
        }
        printf("\n\n");
        system("pause");
    }
    while(ch!=0);
}
