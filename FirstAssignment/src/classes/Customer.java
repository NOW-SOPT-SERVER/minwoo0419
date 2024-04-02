package classes;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;

    public List<Account> getAccountList() {
        return accountList;
    }
    private List<Account> accountList = new ArrayList<>();
    public String getName() {
        return name;
    }
    public void addAccount(Account account){
        this.accountList.add(account);
    }
    public Customer(String name) {
        this.name = name;
    }
    public static Customer create(String name) {
        return new Customer(name);
    }
}
