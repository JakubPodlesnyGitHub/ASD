import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class s20540 {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(/*new File(args[0]*/new File("Liczby.txt"));
        int maxCounter = 0, maxSum = 0, counter = 0, sum = 0, continuousSum = 0, continuousCounter = 0;
        int checkValue = 0, value;
        boolean ifIncreasing = false, ifDecreasing = false;

        while (scanner.hasNextInt()) {
            value = scanner.nextInt();
            System.out.println("CHECK VALUE NA POCZATKU: " + checkValue);
            System.out.println("VALUE NA POCZATKU: " + value);
            if (value > checkValue) {

                ifIncreasing = true;
                System.out.println("ISINCREASING USTAWIA NA: " + ifIncreasing);
                if (ifDecreasing == true) {
                    System.out.println("przesok na zwiekszajacy");
                    System.out.println("////////////////////////////");
                    System.out.println("If deacrasing przed przskokiem zwiekszajacym: " + ifDecreasing);
                    System.out.println("checkvalue przed przeskokiem zwiekszajcym: " + checkValue );
                    System.out.println("value przed przeskokiem zwiekszajcym: " + value);
                    System.out.println("sum przed przeskokiem zwiekszajcym: " + sum);
                    System.out.println("counter przed przeskokiem zwiekszajcym: " + counter);
                    System.out.println("////////////////////////////");
                    System.out.println();
                    ifDecreasing = false;
                    counter = continuousCounter;
                    sum = 0;
                    sum += continuousSum;
                    System.out.println("////////////////////////////");
                    System.out.println("If deacrasing po przskokiem zwiekszajacym: " + ifDecreasing);
                    System.out.println("checkvalue po przeskokiem zwiekszajcym: " + checkValue );
                    System.out.println("value po przeskokiem zwiekszajcym: " + value);
                    System.out.println("sum po przeskokiem zwiekszajcym: " + sum);
                    System.out.println("counter po przeskokiem zwiekszajcym: " + counter);
                    System.out.println("////////////////////////////");
                    System.out.println();
                }

                continuousSum = value;
                continuousCounter = 1;

                System.out.println("COUNTIUS COUNTER: " + continuousCounter);
                System.out.println("COUNTIUS SUM: " + continuousSum);
                checkValue = value;
                counter++;
                sum += checkValue;

                System.out.println("////////////////ZMIANA////////////////");
                System.out.println("CheckValue po zmianie: " + checkValue);
                System.out.println("value po zmianie: " + value);
                System.out.println("counter po zmianie: " + counter);
                System.out.println("sum po zmianie: " + sum);
                System.out.println("////////////////ZMIANA////////////////");
                System.out.println();

            } else if (value < checkValue) {

                ifDecreasing = true;
                System.out.println("ISDECRASING USTAWIA NA: " + ifDecreasing);
                if (ifIncreasing == true) {
                    System.out.println("przesok na zzmniejszajacy");
                    System.out.println("////////////////////////////");
                    System.out.println("If increasing przed przskokiem zmniejszajcym: " + ifIncreasing);
                    System.out.println("checkvalue przed przeskokiem zmniejszajcym: " + checkValue );
                    System.out.println("value przed przeskokiem zmniejszajacym: " + value);
                    System.out.println("sum przed przeskokiem zmniejszajcym: " + sum);
                    System.out.println("counter przed przeskokiem zmniejszajcym: " + counter);
                    System.out.println("////////////////////////////");
                    System.out.println();
                    ifIncreasing = false;
                    counter = continuousCounter;
                    sum = 0;
                    sum += continuousSum;
                    System.out.println("////////////////////////////");
                    System.out.println("If increasinng po przskokiem zmniejszajcym: " + ifIncreasing);
                    System.out.println("checkvalue po przeskokiem zmniejszajcym: " + checkValue );
                    System.out.println("value po przeskokiem zmniejszajacym: " + value);
                    System.out.println("sum po przeskokiem zmniejszajcym: " + sum);
                    System.out.println("counter po przeskokiem zmniejszajcym: " + counter);
                    System.out.println("////////////////////////////");
                    System.out.println();
                }

                continuousSum = value;
                continuousCounter = 1;
                System.out.println("COUNTIUS COUNTER: " + continuousCounter);
                System.out.println("COUNTIUS SUM: " + continuousSum);

                checkValue = value;

                counter++;
                sum += checkValue;
                System.out.println("////////////////ZMIANA////////////////");
                System.out.println("CheckValue po zmianie: " + checkValue);
                System.out.println("value po zmianie: " + value);
                System.out.println("sum po zmianie: " + sum);
                System.out.println("counter po zmianie: " + counter);
                System.out.println("////////////////ZMIANA////////////////");
                System.out.println();
            } else if (checkValue == value) {

                continuousCounter++;
                continuousSum += value;
                System.out.println("COUNTIUS COUNTER PRZY =: " + continuousCounter);
                System.out.println("COUNTIUS SUM PRZY =: " + continuousSum);
                counter++;
                sum += value;

                checkValue = value;
                System.out.println("////////////////ZMIANA =========////////////////");
                System.out.println("CheckValue po zmianie: " + checkValue);
                System.out.println("value po zmianie: " + value);
                System.out.println("counter po zmianie: " + counter);
                System.out.println("sum po zmianie: " + sum);
                System.out.println("////////////////ZMIANA =============////////////////");
            }

            if (counter > maxCounter) {
                maxCounter = counter;
                maxSum = sum;
            }

        }

        System.out.println(maxCounter + " " + maxSum);

    }
}

