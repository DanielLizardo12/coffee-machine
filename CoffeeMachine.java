package coffe;

import java.util.Scanner;

public class CoffeeMachine {

    public static String ESPRESSO = "espresso";
    public static String LATTE = "latte";
    public static String CAPPUCCINO = "cappuccino";



    public static void main(String[] args) {

        int water = 400;
        int milk = 540;
        int coffeeBeans = 120;
        int disposableCups = 9;
        int money = 550;

        Scanner scan = new Scanner(System.in);
        Coffee coffee = new Coffee();

        label:
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String option = scan.next();

            switch (option) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String coffeeType = scan.next();

                    Coffee resultCoffee = createCoffe(water, milk, coffeeBeans, disposableCups, coffeeType);

                    if (!coffeeType.equals("back")) {
                        if (resultCoffee.isResources()) {
                            water -= resultCoffee.getWater();
                            milk -= resultCoffee.getMilk();
                            coffeeBeans -= resultCoffee.getCoffeeBeans();
                            disposableCups -= 1;
                            money += resultCoffee.getCost();
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            if ((water - resultCoffee.getWater()) < 0) {
                                System.out.println("Sorry, not enough water!");
                            }
                            if ((milk - resultCoffee.getMilk()) < 0) {
                                System.out.println("Sorry, not enough milk!");
                            }
                            if ((coffeeBeans - resultCoffee.getCoffeeBeans()) < 0) {
                                System.out.println("Sorry, not enough coffee beans!");
                            }
                            if ((disposableCups - 1) < 0) {
                                System.out.println("Sorry, not enough disposable cups!");
                            }
                        }
                    }

                    break;
                case "fill":
                    System.out.println("Write how many ml of water you want to add: ");
                    water += scan.nextInt();
                    System.out.println("Write how many ml of milk you want to add: ");
                    milk += scan.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add: ");
                    coffeeBeans += scan.nextInt();
                    System.out.println("Write how many disposable cups of coffee you want to add: ");
                    disposableCups += scan.nextInt();

                    break;
                case "take":
                    System.out.println("I gave you $" + money);
                    money = 0;

                    break;
                case "remaining":
                    System.out.println("The coffee machine has:");
                    System.out.println(water + " ml of water");
                    System.out.println(milk + " ml of milk");
                    System.out.println(coffeeBeans + " g of coffee beans");
                    System.out.println(disposableCups + " disposable cups");
                    System.out.println("$" + money + " of money\n");

                    break;
                case "exit":
                    break label;
            }
        }

    }

    public static Coffee createCoffe(int water, int milk, int coffeeBeans,
                                  int disposableCups, String coffeeType) {
        Coffee coffee = new Coffee();

        switch (coffeeType) {
            case "1" -> {
                coffee.setWater(250);
                coffee.setCoffeeBeans(16);
                coffee.setCost(4);
                coffee.setName(ESPRESSO);
            }
            case "2" -> {
                coffee.setWater(350);
                coffee.setMilk(75);
                coffee.setCoffeeBeans(20);
                coffee.setCost(7);
                coffee.setName(LATTE);
            }
            case "3" -> {
                coffee.setWater(200);
                coffee.setMilk(100);
                coffee.setCoffeeBeans(12);
                coffee.setCost(6);
                coffee.setName(CAPPUCCINO);
            }
            default -> System.out.println("Coffee not found");
        }
        coffee.setResources(water >= coffee.getWater() && milk >= coffee.getMilk()
                && coffeeBeans >= coffee.getCoffeeBeans() && disposableCups > 0);
        return coffee;
    }

}

class Coffee {
    private String name;
    private int water = 0;
    private int milk = 0;
    private int coffeeBeans = 0;
    private int cost = 0;

    private boolean resources;

    public boolean isResources() {
        return resources;
    }

    public void setResources(boolean resources) {
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

