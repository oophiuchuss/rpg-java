public class Item {
    final private String name;
    final private int plusHP;
    final private int plusMana;
    final private int plusStam;
    private int amount;
    public Item(String name, int plusHP, int plusMana, int plusStam, int amount){
        this.name=name;
        this.plusHP=plusHP;
        this.plusMana=plusMana;
        this.plusStam=plusStam;
        this.amount=amount;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }
    public String getName(){
        return this.name;
    }
    public int getPlusHP(){
        return this.plusHP;
    }
    public int getPlusMana(){
        return this.plusMana;
    }
    public int getPlusStam(){
        return this.plusStam;
    }
    public int getAmount(){
        return this.amount;
    }

    public boolean use(Character user){
        if(this.amount > 0){
        user.setHp(user.getHp()+this.plusHP);
        user.setMana(user.getMana()+this.plusMana);
        user.setStam(user.getStam()+this.plusStam);
        this.amount--;
        return true;}
        else return false;
    }
}
