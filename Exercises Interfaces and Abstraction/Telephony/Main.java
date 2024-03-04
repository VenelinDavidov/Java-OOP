package Exercise_InterfacesAndAbstraction.Telephony;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      //0882134215 0882134333 08992134215 0558123 3333 1
        // http://softuni.bg http://youtube.com http://www.g00gle.com
        List<String> phoneNumbers = Arrays.stream(scanner
                        .nextLine()
                        .split(" "))
                .collect(Collectors.toList());
        List<String> urls = Arrays.stream(scanner
                        .nextLine()
                        .split(" "))
                .collect(Collectors.toList());

        Smartphone smartphone = new Smartphone(phoneNumbers, urls);


        System.out.println(smartphone.call());
        System.out.println(smartphone.browse());
    }
}
