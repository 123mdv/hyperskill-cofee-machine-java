package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static void main(String[] args) {
        var coffeeMaker = new CoffeeMaker(400, 540, 120, 9, 550);
        Scanner scanner = new Scanner(System.in);
        String input;

        coffeeMaker.goToStartMenu();

        while (true) {
            input = scanner.nextLine();
            if (input.equals("exit")) {
                    break;
            } else {
                coffeeMaker.processInput(input);
            }
        }

        //        while (true) {
//            var nextAction = coffeeMaker.getNextAction();
//            if (nextAction.equals("buy")) {
//                var coffeeType = coffeeMaker.getCoffeeType();
//                if (coffeeType == 0) {
//                    System.out.println("\n");
//                    continue;
//                }
//                coffeeMaker.getIngredients(coffeeType);
//                coffeeMaker.checkAvailabilityAndMakeCoffee();
//            } else if (nextAction.equals("fill")) {
//                coffeeMaker.fill();
//            } else if (nextAction.equals("take")) {
//                coffeeMaker.take();
//            } else if (nextAction.equals("remaining")) {
//                coffeeMaker.printRemaining();
//            } else {
//                break;
//            }
//        }
    }
}