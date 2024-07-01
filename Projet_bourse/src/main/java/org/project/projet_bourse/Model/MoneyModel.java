package org.project.projet_bourse.Model;

public class MoneyModel {
    private int money_id;
    private String level;
    private int amount;
    private int equipements;

    public MoneyModel(int money_id, String level, int amount, int equipements) {
        super();
        this.setMoney_id(money_id);
        this.setLevel(level);
        this.setAmount(amount);
        this.setEquipements(equipements);
    }
    public MoneyModel(String level, int amount, int equipements) {
        super();
        this.setLevel(level);
        this.setAmount(amount);
        this.setEquipements(equipements);
    }

    public int getMoney_id() {
        return money_id;
    }

    public void setMoney_id(int money_id) {
        this.money_id = money_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getEquipements() {
        return equipements;
    }

    public void setEquipements(int equipements) {
        this.equipements = equipements;
    }
}
