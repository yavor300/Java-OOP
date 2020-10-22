package blackBoxInteger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        Class<BlackBoxInt> blackBoxIntClass = BlackBoxInt.class;

        try {
            Constructor<BlackBoxInt> declaredConstructor = blackBoxIntClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            BlackBoxInt blackBoxInt = declaredConstructor.newInstance();

            Class<? extends BlackBoxInt> aClass = blackBoxInt.getClass();
            Method[] methods = blackBoxIntClass.getDeclaredMethods();

            String input = bfr.readLine();
            while (!"END".equals(input)) {
                String[] tokens = input.split("_");
                for (Method method : methods) {
                    method.setAccessible(true);
                    if (method.getName().equals(tokens[0])) {
                        method.invoke(blackBoxInt, Integer.parseInt(tokens[1]));
                        Field innerValue = blackBoxIntClass.getDeclaredField("innerValue");
                        innerValue.setAccessible(true);
                        System.out.println(innerValue.get(blackBoxInt));
                    }
                }
                input = bfr.readLine();
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | IOException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
