package AuctionClass;

public class User {
    private static int id=0;
    private String name;
    private double money;
    public User(String name, double money) {
        this.name = name;
        this.money = money;
        id+=1;
    }
    public String getName() {
        return name;
    }
    public double getMoney() {
        return money;
    }
    public int getId() {
        return id;
    }
    public void addMoney(double money) {
        this.money+=money;
    }
    public void removeMoney(double money) {
        this.money-=money;
    }


}
