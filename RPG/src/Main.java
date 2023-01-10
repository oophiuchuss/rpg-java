import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // creating 3 items
        Item apple = new Item("Apple",3,1,0,3);
        Item potion = new Item("Potion of Stamina Boost",1,0,4,1);
        Item oldScroll = new Item("The old scroll",0,0,5,1);

// creating player
        System.out.println("Create your character!");
        Scanner s = new Scanner(System.in);
        System.out.print("Name: ");
        String playerName = s.nextLine();
        System.out.println("Now give 15 points between stats");
        int hp;
        int mana;
        int stam;
        while(true) {
            System.out.print("HP: ");
            hp = s.nextInt();
            System.out.print("Stamina: ");
            stam = s.nextInt();
            System.out.print("Mana: ");
            mana = s.nextInt();
            if(hp+stam+mana > 15) System.out.println("The sum of points should be 15!");
            else break;
        }
        int firstItem;
        int secondItem;
        Item[] inv = new Item[2];
        while(true) {   System.out.println("Chose 2 different of 3 items.");
            System.out.println("1 - Bunch of apples, 2 - Potion of Stamina Boost, 3 - The old scroll");
            firstItem = s.nextInt();
            System.out.println("1 - Bunch of apples, 2 - Potion of Stamina Boost, 3 - The old scroll");
            secondItem = s.nextInt();

            if(firstItem<=3 && secondItem<=3 && firstItem>0 && secondItem>0 && firstItem!=secondItem) break;
        }
        switch(firstItem){
            case 1:inv[0] = apple;
            break;
            case 2:inv[0] = potion;
            break;
            case 3:inv[0] = oldScroll;
            default:
                System.out.println("Invalid number");
        }
        switch(secondItem){
            case 1:inv[1] = apple;
            break;
            case 2:inv[1] = potion;
            break;
            case 3:inv[1] = oldScroll;
            default:
                System.out.println("Invalid number");
        }

        // creating encounters
        Item[] bossInv = new Item[1];
        bossInv[0] = new Item("The old scroll",0,0,5,1);
        Character user = new Character(playerName,hp,mana,stam,inv,false);
        Character allyTom = new Character("Tom",6,3,6,null,true);
        Character allySara = new Character("Sara",5,7,3,null,true);
        Monster murlok = new Monster("Murlok",7,10,10,null,false,3);
        Monster viking = new Monster("Viking",10,5,14,null,false,5);
        Monster boss = new Monster("Boss",15,12,12,bossInv,false,6);
        Character[] encounters = new Character[5];
        encounters[0] = murlok;
        encounters[1] = allyTom;
        encounters[2] = viking;
        encounters[3] = allySara;
        encounters [4] = boss;
        System.out.println("Game is started!");
        loop1:for (int i = 0; i < encounters.length;) {
            if(!encounters[i].isAlly()){
                System.out.println("Monster - "+ encounters[i].getName()+". You should defeat it.");
                encounters[i].showStats();
                System.out.println("Options: 1 - use item");
                System.out.println("2 - attack");
                System.out.println("3 - call for a help an ally");
                System.out.println("4 - show stats");
                switch (s.nextInt()){
                    case 1:
                        System.out.println("which do you want to use?");
                        while(true){for (int j = 0; j < user.getInv().length; j++)
                            System.out.println(j + 1 + " - " + user.getInv()[j].getName());
                        int usersChoice = s.nextInt()-1;
                            if(usersChoice < user.getInv().length){
                            boolean isUsed = user.getInv()[usersChoice].use(user);
                            if(isUsed)System.out.println("you used "+ user.getInv()[usersChoice].getName());
                            else System.out.println("You don`t have this item");
                            break ;}}
                        break;
                    case 2:
                        // if user don`t have enough stam or mana enemy anyway will attack, but if player defeat enemy first, the second won`t attack
                        loop:while(true){
                            System.out.println("1 - Phys attack; 2 - Mag attack");
                            switch (s.nextInt()){
                                case 1:user.attackPhys(encounters[i]);
                                if(!encounters[i].isDead()){int betweenTwo = (int)(Math.random()*10);
                                if(betweenTwo>=5) encounters[i].attackPhys(user);
                                else encounters[i].attackMag(user);}
                                else System.out.println("");
                                break loop;
                                case 2:user.attackMag(encounters[i]);
                                    if(!encounters[i].isDead()){int betweenTwo = (int)(Math.random()*10);
                                        if(betweenTwo>=5) encounters[i].attackPhys(user);
                                        else encounters[i].attackMag(user);}
                                    else System.out.println("");
                                    break loop;
                                default:
                                    System.out.println("Invalid number");
                            }
                        }
                        if(encounters[i].isDead())i++;
                        break;
                    case 3:if(user.getAllies().length>0){
                        for (int j = 0; j < user.getAllies().length; j++) {
                            System.out.println((j+1)+" - " + user.getAllies()[j].getName());
                        }
                    int whoHelp = s.nextInt();
                        System.out.println("1 - phys attack 2 - mag attack");
                        switch(s.nextInt()){
                            case 1: user.callAllyAttackPhys(encounters[i],user.getAllies()[whoHelp-1]);
                                if(!encounters[i].isDead()){int betweenTwo = (int)(Math.random()*10);
                                    if(betweenTwo>=5) encounters[i].attackPhys(user.getAllies()[whoHelp-1]);
                                    else encounters[i].attackMag(user.getAllies()[whoHelp-1]);}
                                else System.out.println();
                            break;
                            case 2: user.callAllyAttackMag(encounters[i], user.getAllies()[whoHelp-1]);
                                if(!encounters[i].isDead()){int betweenTwo = (int)(Math.random()*10);
                                    if(betweenTwo>=5) encounters[i].attackPhys(user.getAllies()[whoHelp-1]);
                                    else encounters[i].attackMag(user.getAllies()[whoHelp-1]);}
                                else System.out.println();
                                break;
                            default:
                                System.out.println("Invalid number");
                        }
                    if(encounters[i].isDead()){
                        i++;
                        break;
                    }
                    }
                        else System.out.println("You don`t have allies");
                        break;
                    case 4: user.showStats();
                    break;
                    default:
                        System.out.println("Invalid number");
                }
            }
            else {
                System.out.println("You meet ally - " + encounters[i].getName());
                encounters[i].showStats();
                System.out.println("Do you want to invite this person?");
                System.out.println("1 - yes 2 - no");
                switch (s.nextInt()){
                    case 1: user.addAlly(encounters[i]);
                }
                i++;

            }
            if(user.isDead()){
                System.out.println("You lose. Restart game to try again.");
                break loop1;
            }
        }
        if(!user.isDead())System.out.println("You win! Congratulations! Your stats are: ");
        user.showStats();
    }
}