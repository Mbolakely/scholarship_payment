package org.project.projet_bourse.Dao;

import org.project.projet_bourse.Model.MoneyModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoneyDao {
    private static String url="jdbc:mysql://localhost:3306/bourse?useSSL=false";

    private static final String SELECT_SQL = "SELECT * from money;";
    private static final String INSERT_SQL = "INSERT INTO money" + "(level, amount, equipements) VALUES" + "(?, ?, ?);";
    private static final String GET_SQL = "SELECT money_id, level, amount, equipements FROM money WHERE money_id=?;";
    private static final String UPDATE_SQL = "update money set level=?, amount=?, equipements=? where money_id=?;";
    private static final String DELETE_SQL = "DELETE FROM money WHERE money_id=?;";
    private static final String PDF_PAY_SQL = "SELECT amount, equipements from money where level =?;";

    private static Connection connection = null;

    protected static Connection getConnection() {
        System.out.println("connexion in MONEY PART");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,"root","");
            System.out.println("connected to bd");
        }catch(Exception e) {
            System.out.println("NOT connected to bd");
        }
        return connection;
    }

    //LIST OF MONEY
    public List<MoneyModel> getAllMoney() {
        List<MoneyModel> moneyList = new ArrayList<>();
        try (Connection connexion = getConnection()) {
            PreparedStatement stm = connexion.prepareStatement(SELECT_SQL);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                int money_id = res.getInt("money_id");
                String level = res.getString("level");
                int amount = res.getInt("amount");
                int equipements = res.getInt("equipements");
                moneyList.add(new MoneyModel(money_id, level, amount, equipements));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moneyList;
    }

    //INSERT MONEY
    public static void insertMoney(MoneyModel money) throws SQLException {
        System.out.println(INSERT_SQL);
        try(Connection connexion = getConnection();
        PreparedStatement stm = connexion.prepareStatement(INSERT_SQL)) {
            stm.setString(1, money.getLevel());
            stm.setInt(2, money.getAmount());
            stm.setInt(3, money.getEquipements());
            System.out.println(stm);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //GET MONEY BY IDENTIFIANT
    public MoneyModel getMoney(int money_id) {
        System.out.println("getting money function");
        MoneyModel money = null;
        try(Connection connexion = getConnection();
        PreparedStatement stm = connexion.prepareStatement(GET_SQL);) {
            stm.setInt(1, money_id);
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                String level = res.getString("level");
                int amount = res.getInt("amount");
                int equipements = res.getInt("equipements");
                money = new MoneyModel(money_id, level, amount, equipements);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return money;
    }

    //UPDATE A MONEY RECORDING
    public boolean updateMoney(MoneyModel money) throws SQLException {
        boolean updatedMoney;
        try(Connection connexion = getConnection();
        PreparedStatement stm = connexion.prepareStatement(UPDATE_SQL);) {
            System.out.println("Updating money recording:"+ stm);
            stm.setString(1, money.getLevel());
            stm.setInt(2, money.getAmount());
            stm.setInt(3, money.getEquipements());
            stm.setInt(4, money.getMoney_id());
            updatedMoney = stm.executeUpdate() > 0;
            System.out.println(updatedMoney);
        }
        return updatedMoney;
    }

    //DELETE  A MONEY RECORD
    public boolean deleteMoney(int money_id) throws SQLException {
        boolean deletedMoney;
        try(Connection connexion = getConnection();
        PreparedStatement stm = connexion.prepareStatement(DELETE_SQL)) {
            stm.setInt(1, money_id);
            deletedMoney = stm.executeUpdate() > 0;
        }
        return deletedMoney;
    }

    public MoneyModel getValueForPDF(String level) {
        System.out.println("getting money function");
        MoneyModel money = null;
        try(Connection connexion = getConnection();
            PreparedStatement stm = connexion.prepareStatement(PDF_PAY_SQL);) {
            stm.setString(1, level);
            System.out.println(stm);
            ResultSet res = stm.executeQuery();

            while (res.next()) {
                int amount = res.getInt("amount");
                int equipements = res.getInt("equipements");
                money = new MoneyModel(level, amount, equipements);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return money;
    }


}
