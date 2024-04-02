import classes.Account;
import classes.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static Customer me = Customer.create("조민우");
    static final double RATE = 4.5;
    public static Account accountOpening(Scanner scanner){
        String password;
        System.out.print("비밀번호 6자리를 입력해 주세요. : ");
        password = scanner.next();
        System.out.println("개설이 완료되었습니다.");
        return Account.create(me, password);
    }
    public static void checkPassword(Account account, Scanner scanner){
        String password = "";
        System.out.print("비밀번호를 입력해 주세요 : ");
        password = scanner.next();
        while(!account.checkPassword(password)){
            System.out.print("다시 입력해 주세요 : ");
            password = scanner.next();
        }
    }
    public static Integer chooseAccount(List<Account> accountList, Scanner scanner){
        int accountNum;
        System.out.println("통장을 선택해 주세요.");
        AtomicInteger idx = new AtomicInteger();
        accountList.forEach(account -> {System.out.print(idx.getAndIncrement() + "번 : "); account.printAccount();});
        accountNum = scanner.nextInt();
        while(accountNum >= accountList.size() || accountNum < 0){
            System.out.println("다시 선택해 주세요.");
            accountNum = scanner.nextInt();
        }
        return accountNum;
    }
    public static Account chooseSomeOneAccount(List<Account> accountList, Scanner scanner){
        String accountNum;
        System.out.println("이체하실 통장의 계좌번호를 적어주세요");
        accountList.forEach(account -> {account.printAccount();});
        accountNum = scanner.next();
        Account account = null;
        while(account == null){
            for (int i = 0 ; i < accountList.size() ; i++){
                if (accountList.get(i).getAccountNumber().equals(accountNum)){
                    account = accountList.get(i);
                }
            }
            if (account == null){
                System.out.println("다시 입력해 주세요.");
                accountNum = scanner.next();
            }
        }
        return account;
    }
    public static void deposit(Account account, Scanner scanner){
        int price;
        System.out.print("입금하실 금액을 입력해 주세요 : ");
        price = scanner.nextInt();
        while(price <= 0){
            System.out.print("다시 입력해 주세요 : ");
            price = scanner.nextInt();
        }
        checkPassword(account, scanner);
        account.deposit(price);
        System.out.println("입금이 완료되었습니다");
        account.printAccountDetail();
    }
    public static void withdraw(Account account, Scanner scanner){
        int price;
        System.out.print("출금하실 금액을 입력해 주세요 : ");
        price = scanner.nextInt();
        while(price <= 0){
            System.out.print("다시 입력해 주세요 : ");
            price = scanner.nextInt();
        }
        checkPassword(account, scanner);
        if(account.withdraw(price)){
            System.out.println("출금이 완료되었습니다");
        } else{
            System.out.println("잔액이 부족합니다");
        }
        account.printAccountDetail();
    }
    public static void saving(Account account, Scanner scanner){
        int price, year;
        System.out.print("적금하실 금액을 입력해 주세요 : ");
        price = scanner.nextInt();
        while(price <= 0){
            System.out.print("다시 입력해 주세요 : ");
            price = scanner.nextInt();
        }
        System.out.print("적금하실 기간을 입력해 주세요 : ");
        year = scanner.nextInt();
        checkPassword(account, scanner);
        account.saving(RATE, price, year);
        System.out.println("적금이 완료되었습니다. " + account.getSavingBalance() + "원이 적금될 예정입니다.");
        account.printAccountDetail();
    }
    public static void transfer(Account account, Account tempAccount, Scanner scanner){
        int price;
        System.out.print("이체하실 금액을 입력해 주세요 : ");
        price = scanner.nextInt();
        while(price <= 0){
            System.out.print("다시 입력해 주세요 : ");
            price = scanner.nextInt();
        }
        checkPassword(account, scanner);
        if(account.transfer(tempAccount, price)){
            System.out.println("이체가 완료되었습니다");
            account.printAccountDetail();
        } else{
            System.out.println("잔액이 부족합니다");
        }
    }
    public static void main(String[] args) {
        Customer customer1 = Customer.create("김철수");
        Customer customer2 = Customer.create("김영희");
        List<Account> myAccountList = me.getAccountList();
        List<Account> accountList = new ArrayList<>();
        accountList.add(Account.create(customer1, "111111"));
        accountList.add(Account.create(customer2, "123456"));
        int number = 1;
        Scanner scanner = new Scanner(System.in);
        do{
            Account account;
            if (myAccountList.isEmpty()) {
                System.out.println("계좌 목록이 비었습니다. 계좌를 개설해 주세요.");
                accountOpening(scanner);
            } else {
                System.out.println("=============================\n" +
                        "무엇을 도와드릴까요?\n" +
                        "1번 : 계좌개설\n" +
                        "2번 : 입금\n" +
                        "3번 : 출금\n" +
                        "4번 : 계좌이체\n" +
                        "5번 : 적금하기\n" +
                        "6번 : 계좌조회\n" +
                        "0번 : 종료하기\n" +
                        "=============================");
                number = scanner.nextInt();
                switch (number){
                    case 0:
                        break;
                    case 1:
                        accountOpening(scanner);
                        break;
                    case 2:
                        account = myAccountList.get(chooseAccount(myAccountList, scanner));
                        deposit(account, scanner);
                        break;
                    case 3:
                        account = myAccountList.get(chooseAccount(myAccountList, scanner));
                        withdraw(account, scanner);
                        break;
                    case 4:
                        account = myAccountList.get(chooseAccount(myAccountList, scanner));
                        Account tempAccount = chooseSomeOneAccount(accountList, scanner);
                        transfer(account, tempAccount, scanner);
                        break;
                    case 5:
                        account = myAccountList.get(chooseAccount(myAccountList, scanner));
                        saving(account, scanner);
                        break;
                    case 6:
                        account = myAccountList.get(chooseAccount(myAccountList, scanner));
                        account.printAccountDetail();
                        break;
                    default:
                        System.out.println("다시 입력해 주세요.");
                        break;
                }
            }
        }while(number != 0);
    }
}