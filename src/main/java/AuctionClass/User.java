package AuctionClass;

public class User {
    private int id=0;
    private String name;
    private double money;
    public User(String name, double money) {
        this.name = name;
        this.money = money;
        id+=1;
    }
    public User(int Id, String name, double money) {
        this.id=Id;
        this.name = name;
        this.money = money;
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
        DatabaseUpdater.updateUserSalary(this.id, this.money);
    }
    public void removeMoney(double money) {
        this.money-=money;
    }


}
