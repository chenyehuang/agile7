package cn.edu.jnu.agile7.ui.notifications;
public class Account {
    private String name;
    private float amount;

    public Account(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }
}
