package harvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {
	public static void main(String[] args) throws IOException, IllegalAccessException {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

		Class<RichSoilLand> richSoilLandClass = RichSoilLand.class;
		Field[] declaredFields = richSoilLandClass.getDeclaredFields();

		String modifier = bfr.readLine();
		while (!"HARVEST".equals(modifier)) {
			switch (modifier) {
				case "private":
					for (Field field : declaredFields) {
						field.setAccessible(true);
						if (Modifier.isPrivate(field.getModifiers())) {
							System.out.printf("private %s %s%n",  field.getType().getSimpleName(), field.getName());
						}
					}
					break;
				case "public":
					for (Field field : declaredFields) {
						if (Modifier.isPublic(field.getModifiers())) {
							System.out.printf("public %s %s%n",  field.getType().getSimpleName(), field.getName());
						}
					}
					break;
				case "protected":
					for (Field field : declaredFields) {
						field.setAccessible(true);
						if (Modifier.isProtected(field.getModifiers())) {
							System.out.printf("protected %s %s%n", field.getType().getSimpleName(), field.getName());
						}
					}
					break;
				case "all":
					for (Field field : declaredFields) {
						field.setAccessible(true);
						if (Modifier.isPrivate(field.getModifiers())) {
							System.out.printf("private %s %s%n",  field.getType().getSimpleName(), field.getName());
						}
						if (Modifier.isPublic(field.getModifiers())) {
							System.out.printf("public %s %s%n",  field.getType().getSimpleName(), field.getName());
						}
						if (Modifier.isProtected(field.getModifiers())) {
							System.out.printf("protected %s %s%n",  field.getType().getSimpleName(), field.getName());
						}
					}
					break;
			}
			modifier = bfr.readLine();
		}

	}
}
