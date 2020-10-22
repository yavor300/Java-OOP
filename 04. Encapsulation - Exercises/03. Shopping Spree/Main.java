package softUni.development;

import java.io.*;
import java.util.*;
import java.util.List;

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

        List<Person> people = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        String inputPeople = bfr.readLine();
        if (inputPeople.contains(";")) {
            String[] peopleData = inputPeople.split(";");
            for (String peopleDatum : peopleData) {
                String[] nameAndMoney = peopleDatum.split("=");
                try {
                    Person person = new Person(nameAndMoney[0], Double.parseDouble(nameAndMoney[1]));
                    people.add(person);

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    return;
                }


            }
        } else {
            String[] peopleData = inputPeople.split("=");
            try {
                Person person = new Person(peopleData[0], Double.parseDouble(peopleData[1]));
                people.add(person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        String inputProducts = bfr.readLine();
        if (inputProducts.contains(";")) {
            String[] productsData = inputProducts.split(";");
            for (String productsDatum : productsData) {
                String[] nameAndCost = productsDatum.split("=");
                try {
                    Product product = new Product(nameAndCost[0], Double.parseDouble(nameAndCost[1]));
                    products.add(product);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
        } else {
            String[] productsData = inputProducts.split("=");
            try {
                Product product = new Product(productsData[0], Double.parseDouble(productsData[1]));
                products.add(product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        String command = bfr.readLine();
        while (!"END".equals(command)) {
            String[] tokens = command.split("\\s+");

            String name = tokens[0];
            String productName = tokens[1];

            Person person = null;
            Product product = null;

            for (Person value : people) {
                if (value.getName().equals(name)) {
                    person = value;
                    break;
                }
            }

            for (Product value : products) {
                if (value.getName().equals(productName)) {
                    product = value;
                    break;
                }
            }


            if (product != null && person != null && person.hasEnoughMoney(product)) {
                person.buyProduct(product);
                System.out.println(person.getSuccessfulMessage(product));
            } else {
                if (person != null) {
                    if (product != null) {
                        System.out.println(person.getNotEnoughMoneyMessage(product));
                    }
                }
            }
            command = bfr.readLine();
        }

        for (Person person : people) {
            if (person.getProducts().size() == 0) {
                System.out.printf("%s - Nothing bought%n", person.getName());
            } else {
                String[] boughtProducts = new String[person.getProducts().size()];
                for (int i = 0; i < person.getProducts().size(); i++) {
                    boughtProducts[i] = person.getProducts().get(i).getName();
                }
                System.out.printf("%s - %s%n", person.getName(), String.join(", ", boughtProducts));
            }
        }

    }
}

