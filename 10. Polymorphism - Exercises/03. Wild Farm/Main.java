import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static Integer[] parseLineOfNumbers(String line) {
        String[] numberString = line.split(", ");
        Integer[] numbers = new Integer[numberString.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numberString[i]);
        }
        return numbers;
    }

    private static void removeIndex(int[] numbers, int[] newNumbers, int indexToRemove) {
        for (int i = 0; i < newNumbers.length; i++) {
            if (i >= indexToRemove) {
                newNumbers[i] = numbers[i + 1];
            } else {
                newNumbers[i] = numbers[i];
            }
        }
        numbers = newNumbers;
    }

    private static List<Integer> getIntegersInList(String line) {
        List<Integer> numbers = new ArrayList<>();
        String[] strings = line.split(" ");
        for (String numberString : strings) {
            numbers.add(Integer.parseInt(numberString));
        }
        return numbers;
    }
    //FIND DUPLICATED OBJECT IN A LIST
//    static Product findProduct(String name, List<Product> products) {
//        for (Product product : products) {
//            if (product.getName().equals(name)) {
//                return product;
//            }
//        }
//        return null;
//    }

    //REPEAT A WORD DEPENDING OT IT'S LETTERS COUNT
    private  static String repeat(String word, int length) {
        String[] repeated = new String[length];
        for (int i = 0; i < repeated.length; i++) {
            repeated[i] = word;
        }
        return String.join("", repeated);
    }

    private static void characterMultiplier(String firstString, String secondString) {
        int sum = 0;
        for (int i = 0; i < firstString.length() && i < secondString.length(); i++) {
            sum += firstString.charAt(i) * secondString.charAt(i);
        }

        if (firstString.length() > secondString.length()) {
            for (int i = secondString.length(); i < firstString.length(); i++) {
                sum += firstString.charAt(i);
            }
        } else {
            for (int i = firstString.length(); i < secondString.length(); i++) {
                sum += secondString.charAt(i);
            }
        }
        System.out.println(sum);
    }

    //FILLING THE MATRIX
    /*for (int row = 0; row < n; row++) {
        String[] input = scanner.nextLine().split("\\s+");
        for (int col = 0; col < input.length; col++) {
            matrix[row][col] = Integer.parseInt(input[col]);
        }
    }*/

    //BOTH DIAGONALS
    /*int row = 0;
    int col = 0;
    int primarySum = 0;
        while (row < n && col < n) {
        primarySum  += matrix[row][col];
        row++;
        col++;
    }

    row--;
    col = 0;
    int secondarySum = 0;
        while (row >= 0 && col < n) {
        secondarySum += matrix[row][col];
        row--;
        col++;
    }*/

    /*ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
    int value = 1;
        for (int row = 0; row < numbers[0]; row++) {
        ArrayList<Integer> rowList = new ArrayList<>();
        for (int col = 0; col < numbers[1]; col++) {
            rowList.add(value);
            value++;
        }
        matrix.add(rowList);
    }*/

    /*
    private static double calcFact(double number) {
        long fact = 1;
        for (int i = 1; i <= number; i++) {
            fact *= i;
        }
        return fact;
    }
     */

    //IS MATRIX IN RANGE
    private static boolean isInRange(int playerRow, int playerCol, char[][] matrix) {
        return playerRow >= 0 && playerRow < matrix.length && playerCol >= 0 && playerCol < matrix[playerRow].length;
    }

    //TRAP CHECKER
    private static boolean isOnTrap(int playerRow, int playerCol, char[][] matrix) {
        return matrix[playerRow][playerCol] == 'B';
    }

    //BONUS CHECKER
    private static boolean isOnBonus(int playerRow, int playerCol, char[][] matrix) {
        return matrix[playerRow][playerCol] == '*';
    }

    //FINAL CHECKER
    private static boolean isOnFinish(int playerRow, int playerCol, int finalRow, int finalCol) {
        return (playerRow == finalRow) && (playerCol == finalCol);
    }

    // PRINT MATRIX
    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        Mammal mammal = null;
        Felime felime = null;

        StringBuilder sb = new StringBuilder();

        String input = bfr.readLine();
        while (!"End".equals(input)) {
            String[] animalTokens = input.split("\\s+");

            String[] foodTokens = bfr.readLine().split("\\s+");
            Food food = null;
            switch (foodTokens[0]) {
                case "Vegetable":
                    food = new Vegetable(Integer.parseInt(foodTokens[1]));
                    break;
                case "Meat":
                    food = new Meat(Integer.parseInt(foodTokens[1]));
                    break;

            }
            switch (animalTokens[0]) {
                case "Mouse":
                    mammal = new Mouse(animalTokens[1], "Mouse", Double.parseDouble(animalTokens[2]), animalTokens[3]);
                    mammal.makeSound();
                    if (foodTokens[0].equals("Vegetable")) {
                        mammal.eat(food);
                    } else {
                        System.out.println("Mice are not eating that type of food!");
                    }

                    sb.append(mammal.toString()).append(System.lineSeparator());
                    break;

                case "Zebra":
                    mammal = new Zebra(animalTokens[1], "Zebra", Double.parseDouble(animalTokens[2]), animalTokens[3]);
                    mammal.makeSound();
                    if (foodTokens[0].equals("Vegetable")) {
                        mammal.eat(food);
                    } else {
                        System.out.println("Zebras are not eating that type of food!");
                    }

                    sb.append(mammal.toString()).append(System.lineSeparator());
                    break;

                case "Cat":
                    felime = new Cat(animalTokens[1], "Cat", Double.parseDouble(animalTokens[2]), animalTokens[3], animalTokens[4]);
                    felime.makeSound();
                    felime.eat(food);

                    sb.append(felime.toString()).append(System.lineSeparator());
                    break;

                case "Tiger":
                    felime = new Tiger(animalTokens[1], "Tiger", Double.parseDouble(animalTokens[2]), animalTokens[3]);
                    felime.makeSound();
                    if (foodTokens[0].equals("Meat")) {
                        felime.eat(food);
                    } else {
                        System.out.println("Tigers are not eating that type of food!");
                    }
                    sb.append(felime.toString()).append(System.lineSeparator());
                    break;
            }
            input = bfr.readLine();
        }
        System.out.println(sb.toString().trim());
    }
}

