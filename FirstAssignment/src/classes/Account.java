package classes;

import java.util.Random;

public class Account {
    private String accountNumber;
    private Integer balance;
    private Integer savingBalance;
    private Customer customer;
    private String password;

    public String getAccountNumber() {
        return accountNumber;
    }
    public Integer getSavingBalance() {
        return savingBalance;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public void setSavingBalance(Integer savingBalance) {
        this.savingBalance = savingBalance;
    }
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
    public void printAccountDetail(){
        System.out.println("개설자 : " + customer.getName() + " 계좌번호 : " + accountNumber
        + " 이체가능 잔액 : " + balance + " 적금 잔액 : " + savingBalance + " 비밀번호 : " + password);
    }
    public void printAccount(){
        System.out.println("개설자 : " + customer.getName() + " 계좌번호 : " + accountNumber);
    }
    public void deposit(Integer money){
        this.setBalance(this.balance + money);
    }
    public boolean withdraw(Integer money){
        if (this.balance < money)
            return false;
        this.setBalance(this.balance - money);
        return true;
    }
    public boolean transfer(Account receiveAccount, Integer money){
        if (!this.withdraw(money))
            return false;
        receiveAccount.deposit(money);
        return true;
    }
    public void saving(Double interestRate, Integer price, Integer year){
        this.setSavingBalance((int)(price * Math.pow(1 + interestRate * 0.01, year)));
    }

    public Account(String accountNumber, Integer balance, Integer savingBalance, Customer customer, String password) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.savingBalance = savingBalance;
        this.customer = customer;
        this.password = password;
    }

    public static Account create(Customer customer, String password) {
        Random random = new Random();
        String accountNumber = "";
        for (int i = 0 ; i < 10 ; i++){
            accountNumber += Integer.toString(random.nextInt(9));
        }
        Account account = new Account(accountNumber, 0, 0, customer, password);
        customer.addAccount(account);
        return account;
    }
}
