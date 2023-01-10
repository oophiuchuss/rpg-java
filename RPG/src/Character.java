import java.util.Scanner;

public class Character {
    final private String name;
    private int hp;
    private int mana;
    private int stam;
    private boolean isDead = false;
    final private boolean isAlly;
    private Item[] inv; /* inventory*/
private Character[] allies = new Character[0];
    private int exp = 0;
    public Character(String name, int hp, int mana, int stam, Item[] inv, boolean isAlly){
        this.name = name;
        this.hp=hp;
        this.mana=mana;
        this.stam=stam;
        this.inv=inv;
        this.isAlly=isAlly;
    }
    // setters
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setStam(int stam) {
        this.stam = stam;
    }

    public void setInv(Item[] inv) {
        this.inv = inv;
    }

    public void setDead(boolean isDead){
        this.isDead=isDead;
    }
    public void setEXP(int exp){
        this.exp = exp;
    }

    public void setAllies(Character[] allies) {
        this.allies = allies;
    }
    // getters

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public int getMana() {
        return this.mana;
    }

    public int getStam() {
        return this.stam;
    }

    public boolean isAlly() {
        return this.isAlly;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public Item[] getInv() {
        return inv;
    }
    public int getExp() {
        return exp;
    }

    public Character[] getAllies(){return this.allies;}

    // attacks
    //attackPhys costs 1 stam and damage is 2

    public void attackPhys(Character enemy){
        if(this.stam>0){
            if(!enemy.isDead){
        if(!enemy.isAlly()){
           this.stam--;
           enemy.setHp(enemy.getHp()-2);
        }
        else System.out.println(this.name+" can not attack your Ally");
        }
        else System.out.println(this.name+" can not attack dead enemy");
        }
        else System.out.println(this.name+" don`t have enough stam");
        enemy.dying(this);
    }
    //attackMag costs 1 mana and damage is 2

    public void attackMag(Character enemy){
        if(this.mana>0){
            if(!enemy.isDead){
                if(!enemy.isAlly()){
                    this.mana--;
                    enemy.setHp(enemy.getHp()-2);
                }
                else System.out.println(this.name+" can not attack your Ally");
            }
            else System.out.println(this.name+" can not attack dead enemy");
        }
        else System.out.println(this.name+" don`t have enough mana");
        enemy.dying(this);
    }

    //call ally to help (attack)
    public void callAllyAttackPhys(Character enemy, Character ally){
        if(ally.isAlly() && !ally.isDead) ally.attackPhys(enemy);
        else System.out.println(this.name+" can not call dead character or an enemy");
    }

    public void callAllyAttackMag(Character enemy, Character ally){
        if(ally.isAlly() && !ally.isDead) ally.attackMag(enemy);
        else System.out.println(this.name+" can not call an enemy to help");
    }

    // using Item from array
    public void useItem(String nameItem){
        boolean isUsed = false;
        if(this.inv.length !=0){
            for (Item item : this.inv) {
                if (item.getName().equalsIgnoreCase(nameItem)) isUsed = item.use(this);
            }
        }
        else System.out.println("Your inventory is empty");
        if(!isUsed) System.out.println(this.name+" don`t have this item");
    }

    public void transferItems(Character to){
        if(this.getInv()!=null){
        boolean isTransferred = false;
        if(this.inv.length!=0){
            if(to.getInv().length==0) to.setInv(this.getInv());
            else {
                for (Item item : this.inv) {
                    for (int j = 0; j < to.getInv().length; j++) {
                        if (item.getName().equalsIgnoreCase(to.getInv()[j].getName())) {
                            to.getInv()[j].setAmount(to.getInv()[j].getAmount() + item.getAmount());
                            isTransferred = true;
                        }
                    }
                    if (!isTransferred && item.getAmount() > 0) {
                        Item[] temp = new Item[to.getInv().length + 1];
                        System.arraycopy(to.getInv(), 0, temp, 0, to.getInv().length);
                        temp[to.getInv().length] = item;
                        to.setInv(temp);
                    }
                    isTransferred = false;
                }
            }
        }
        }
    }

    public void addAlly(Character ally){
        if(ally.isAlly() && !ally.isDead()){
            Character[] temp = new Character[this.allies.length+1];
            System.arraycopy(this.allies, 0,temp , 0, this.allies.length);
            temp[this.allies.length] = ally;
            this.setAllies(temp);
            for (Character s: this.getAllies()
                 ) {if(s!=null)
                System.out.println(s.getName());

            }
        }
    }
    public void dying(Character killer){
     if(this.getHp() <=0){
         System.out.println(this.name + " is dead");
         this.transferItems(killer);
         this.isDead=true;
     }
    }

    public void upgrade(){
        System.out.println(this.getName() + " have an upgrade!");
        System.out.println("Chose what to upgrade by 5: h - hp s - stamina, m - mana");
        Scanner s = new Scanner(System.in);
        char user;
    loop:    while(true) {
            user = s.nextLine().charAt(0);
            switch(user){
            case'h':this.setHp(this.getHp()+5);
                break loop;
            case's':this.setStam(this.getStam()+5);
                break loop;
            case'm':this.setMana(this.getMana()+5);
                break loop;
            default:
                System.out.println("Invalid char");
          }
        }
    }
    public void showStats(){
        System.out.println("Name: " + this.getName());
        if(this.getHp()>0)System.out.println("HP: " + this.getHp());
        else System.out.println("HP: 0 (dead)");
        System.out.println("Stamina: " + this.getStam());
        System.out.println("Mana: " + this.getMana());
        if(this.inv != null)for (Item c:
             this.inv) {
            System.out.println(c.getName() +": "+ c.getAmount());
        }
        else System.out.println("Don`t have any items");
        System.out.println("EXP: " + this.exp);
        System.out.println();

        if(this.getAllies().length>0 && this.getAllies()!=null) {System.out.println("Teammates stats: ");
        for (Character ally: this.getAllies()
                                           ) {ally.showStats();

        }}
    }
}
