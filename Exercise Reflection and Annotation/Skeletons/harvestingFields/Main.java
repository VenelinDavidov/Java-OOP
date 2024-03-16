package Skeletons.harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Class<RichSoilLand> clazz = RichSoilLand.class;
        //всички полета, без значение от техния access modifier
        Field[] fields = clazz.getDeclaredFields();

        String command = scanner.nextLine();

        //Consumer<тип> -> void -> accept
        //"{access modifier} {field type} {field name}"
        Consumer<Field> fieldPrinter = field -> System.out.printf("%s %s %s%n",
                Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName());

        while (!command.equals("HARVEST")) {
            switch (command) {
                case "private":
                    //print all private fields
                    Arrays.stream(fields)
                            .filter(field -> Modifier.isPrivate(field.getModifiers()))
                            .forEach(fieldPrinter);
                    break;
                case "protected":
                    //print all protected fields
                    Arrays.stream(fields)
                            .filter(field -> Modifier.isProtected(field.getModifiers()))
                            .forEach(fieldPrinter);
                    break;
                case "public":
                    //print all public fields
                    Arrays.stream(fields)
                            .filter(field -> Modifier.isPublic(field.getModifiers()))
                            .forEach(fieldPrinter);
                    break;
                case "all":
                    //print ALL declared fields
                    Arrays.stream(fields).forEach(fieldPrinter);
                    break;
            }

            command = scanner.nextLine();
        }

    }
}

//        Map <String, List<Field>> fieldMap = getFieldsMap();
//
//        Consumer <Field> printerField = field -> {
//            System.out.println(Modifier.toString(field.getModifiers())
//                    + " " + field.getType().getSimpleName()
//                    + " " + field.getName());
//        };
//
//        String commnad = scanner.nextLine();  // command
//        while (!commnad.equals("HARVEST")) {
//            // private,public,protected,all
//            switch (commnad) {
//                case "private":
//                    // "{access modifier} {field type} {field name}"
//                    // spisuka s private fields
//                    fieldMap.get("private").forEach(printerField::accept);
//                    break;
//                case "public":
//                    fieldMap.get("public").forEach(printerField::accept);
//                    break;
//                case "protected":
//                    fieldMap.get("protected").forEach(printerField::accept);
//                    break;
//                case "all":
//                    fieldMap.get("all").forEach(printerField::accept);
//                    break;
//            }
//
//
//            commnad = scanner.nextLine();
//        }
//
//    }
//
//    private static Map<String, List<java.lang.reflect.Field>> getFieldsMap() {
//
//        Map<String, List<java.lang.reflect.Field>> map = new HashMap<>();
//        map.put("private", new ArrayList<>());
//        map.put("public", new ArrayList<>());
//        map.put("protected", new ArrayList<>());
//
//        List<java.lang.reflect.Field> allFields = Arrays.asList(RichSoilLand.class.getDeclaredFields());
//        map.put("all", allFields);
//
//        allFields.forEach(field -> {
//            String modifier = Modifier.toString(field.getModifiers());
//            switch (modifier) {
//                case "public":
//                    map.get("public").add(field);
//                    break;
//                case "private":
//                    map.get("private").add(field);
//                    break;
//                case "protected":
//                    map.get("protected").add(field);
//                    break;
//            }
//
//        });
//        return map;
//    }
//}
