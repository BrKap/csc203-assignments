import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;

import static java.lang.Character.isDigit;

public class FileProcessor {

    /**
     * Processes arithmetic expressions line-by-line in the given file.
     *
     * @param filePath Path to a file containing arithmetic expressions.
     */
    public static void processFile(String filePath) {
        File infile = new File(filePath);

        List<LinkedList> linkedListFirst = new ArrayList<>();
        List<String> operatorList = new ArrayList<>();
        List<LinkedList> linkedListLast = new ArrayList<>();

        try (Scanner scan = new Scanner(infile)) {
            while (scan.hasNext()) {
                // TODO: Process each line of the input file here.
                String line = scan.nextLine();

                List<String> formattedLine = new ArrayList<>();

                boolean lineValid = true;
                String[] nums = line.split("[+*^]");
                String operator = "";

                if (nums.length == 2) {
                    nums[0] = nums[0].replaceAll(" ", "");
                    nums[1] = nums[1].replaceAll(" ", "");
                    char[] num1 = nums[0].toCharArray();
                    char[] num2 = nums[1].toCharArray();
                    for (char character : num1) {
                        if (!isDigit(character)) {
                            lineValid = false;
                        }
                    }
                    for (char character : num2) {
                        if (!isDigit(character)) {
                            lineValid = false;
                        }
                    }

                    if (lineValid) {
                        operator = line.replaceAll("[0-9\\s]", "");
                        formattedLine.add(nums[0]);
                        formattedLine.add(operator);
                        formattedLine.add(nums[1]);
                    }

                }


                if (lineValid) {
                    for (int i = 0; i < formattedLine.size(); i = i + 3) {
                        char[] firstNum = formattedLine.get(i).toCharArray();
                        char[] secondNum = formattedLine.get(i+2).toCharArray();

                        LinkedList temp = new LinkedList();
                        for (int ii = 0; ii < firstNum.length; ii++) {
                            temp.insert(Integer.parseInt(String.valueOf(firstNum[ii])));
                        }
                        linkedListFirst.add(temp);
                        operatorList.add(formattedLine.get(i+1));

                        temp = new LinkedList();
                        for (int ii = 0; ii < secondNum.length; ii++) {
                            temp.insert(Integer.parseInt(String.valueOf(secondNum[ii])));
                        }
                        linkedListLast.add(temp);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + infile.getPath());
        }

        try {
            FileWriter output = new FileWriter("output.txt");

            for (int i = 0; i < linkedListFirst.size(); i++) {
                String operator = operatorList.get(i);
                String line = "";
                LinkedList digit1 = linkedListFirst.get(i);
                LinkedList digit2 = linkedListLast.get(i);


                switch (operator) {
                    case "+": {
                        String result = digit1.add(digit2).getString();
                        line = digit1.getString() + " + " + digit2.getString() + " = " + result;
                        break;
                    }
                    case "*": {
                        String result = digit1.multiply(digit2).getString();
                        line = digit1.getString() + " * " + digit2.getString() + " = " + result;
                        break;
                    }
                    case "^": {
                        String result = digit1.exponent(digit2).getString();
                        line = digit1.getString() + " ^ " + digit2.getString() + " = " + result;
                        break;
                    }
                }
                if (i != 0) {
                    output.write("\n" + line);
                    System.out.print("\n" + line);

                } else {
                    output.write(line);
                    System.out.print(line);

                }
            }
//            System.out.println(filePath + " has been successfully solved in output.txt");
            output.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
