package machine;

import java.util.Scanner;

public class CoffeeMaker {
    int waterLevel;
    int milkLevel;
    int beansLevel;
    int cupLevel;
    int moneyLevel;
    int waterNeeded;
    int milkNeeded;
    int beansNeeded;
    int price;
    CoffeeMakerState state;
    private static Scanner scanner = new Scanner(System.in);

    public CoffeeMaker(int waterLevel, int milkLevel, int beansLevel, int cupLevel, int moneyLevel) {
        this.waterLevel = waterLevel;
        this.milkLevel = milkLevel;
        this.beansLevel = beansLevel;
        this.cupLevel = cupLevel;
        this.moneyLevel = moneyLevel;
        this.state = CoffeeMakerState.WAITING_FOR_NEXT_ACTION;
    }

    public void goToStartMenu() {
        System.out.println("Next action: buy, fill, take, remaining or exit?");
        state = CoffeeMakerState.WAITING_FOR_NEXT_ACTION;
    }

    public void processInput(String input) {
        if (state == CoffeeMakerState.WAITING_FOR_NEXT_ACTION) {
            if (input.equals("buy")) {
                System.out.println("What type of coffee? 1,2, 3 or back? ");
                state = CoffeeMakerState.WAITING_FOR_COFFEE_TYPE;
            } else if (input.equals("fill")) {
                fill();
                goToStartMenu();
            } else if (input.equals("take")) {
                take();
                goToStartMenu();
            } else if (input.equals("remaining")) {
                printRemaining();
                goToStartMenu();
            }
        } else if (state == CoffeeMakerState.WAITING_FOR_COFFEE_TYPE) {
            if (input.equals("back")) {
                goToStartMenu();
            } else {
                getIngredients(Integer.parseInt(input));
                checkAvailabilityAndMakeCoffee();
                goToStartMenu();
            }
        }
    }

    private void getIngredients(int coffeeType) {
        switch (coffeeType) {
            case 1: // espresso
                waterNeeded = 250;
                milkNeeded = 0;
                beansNeeded = 16;
                price = 4;
                break;
            case 2: // latte
                waterNeeded = 350;
                milkNeeded = 75;
                beansNeeded = 20;
                price = 7;
                break;
            case 3: // cappuccino
                waterNeeded = 200;
                milkNeeded = 100;
                beansNeeded = 12;
                price = 6;
                break;
        }
    }

    private void checkAvailabilityAndMakeCoffee() {
        boolean allIngredientsAvailable = true;
        if (waterLevel < waterNeeded) {
            System.out.println("Sorry, not enough water!\n");
            allIngredientsAvailable = false;
        }
        if (milkLevel < milkNeeded) {
            System.out.println("Sorry, not enough milk!\n");
            allIngredientsAvailable = false;
        }
        if (beansLevel < beansNeeded) {
            System.out.println("Sorry, not enough beans!\n");
            allIngredientsAvailable = false;
        }
        if (cupLevel == 0) {
            System.out.println("Sorry, no cups left!\n");
            allIngredientsAvailable = false;
        }
        if (allIngredientsAvailable) {
            System.out.println("I have enough ingredients, making you a coffee!\n");
            waterLevel -= waterNeeded;
            milkLevel -= milkNeeded;
            beansLevel -= beansNeeded;
            cupLevel --;
            moneyLevel += price;
        }
    }

    private void fill() {
        System.out.println("How many ml of water do you want to add:");
        waterLevel += scanner.nextInt();
        System.out.println("How many ml of milk do you want to add:");
        milkLevel += scanner.nextInt();
        System.out.println("How many g of coffee beans do you want to add:");
        beansLevel += scanner.nextInt();
        System.out.println("How many cups do you want to add:");
        cupLevel += scanner.nextInt();
        System.out.println("Added all ingredients successfully!\n");
    }

    private void take() {
        System.out.println("I gave you $" + moneyLevel + "\n");
        moneyLevel = 0;
    }

    private void printRemaining() {
        System.out.println("The coffee machine has:");
        System.out.println(waterLevel + " of water");
        System.out.println(milkLevel + " of milk");
        System.out.println(beansLevel + " of coffee beans");
        System.out.println(cupLevel + " of disposable cups");
        System.out.println("$" + moneyLevel + " of money\n");
    }
}
