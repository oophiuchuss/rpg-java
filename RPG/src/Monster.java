public class Monster extends Character {
    private final int EXPtoGive;
    public Monster (String name, int hp, int mana, int stam, Item[] inv, boolean isAlly, int EXPtoGive){
       super(name,hp,mana,stam,inv,isAlly);
       this.EXPtoGive=EXPtoGive;
    }
    public void dying(Character killer){
        if(this.getHp() <=0){
            System.out.println(super.getName() + " is dead");
            super.transferItems(killer);
            super.setDead(true);
            killer.setEXP(killer.getExp()+this.EXPtoGive);
            if(killer.getExp()>=5) killer.upgrade();
        }
    }
    public void showStats(){
        System.out.println("Name: " + this.getName());
        System.out.println("HP: " + this.getHp());
        System.out.println("Stamina: " + this.getStam());
        System.out.println("Mana: " + this.getMana());
        if(this.getInv() != null)for (Item c:
                this.getInv()) {
            System.out.println(c.getName() +": "+ c.getAmount());
        }
        else System.out.println("Don`t have any items");
        System.out.println("Gives exp: " + this.EXPtoGive);
    }
    // attacks
    //attackPhys costs 1 stam and damage is 2

    public void attackPhys(Character enemy){
        if(super.getStam()>0){
            if(!enemy.isDead()){
                    super.setStam(super.getStam()-1);
                    enemy.setHp(enemy.getHp()-2);
            }
            else System.out.println(super.getName()+" can not attack dead enemy");
        }
        else System.out.println(super.getName()+" don`t have enough stam");
        enemy.dying(this);
    }
    //attackMag costs 1 mana and damage is 2

    public void attackMag(Character enemy){
        if(super.getStam()>0){
            if(!enemy.isDead()){
                    super.setMana(super.getMana()-1);
                    enemy.setHp(enemy.getHp()-2);
                }
                else System.out.println(super.getName()+" can not attack your Ally");
        }
        else System.out.println(super.getName()+" don`t have enough mana");
        enemy.dying(this);
    }
}
