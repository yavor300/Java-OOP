package barracksWars.core;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Runnable;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Engine implements Runnable {

	private static final String COMMANDS_PACKAGE_NAME =
			"barracksWars.core.commands.";

	private Repository repository;
	private UnitFactory unitFactory;

	public Engine(Repository repository, UnitFactory unitFactory) {
		this.repository = repository;
		this.unitFactory = unitFactory;
	}

	@Override
	public void run() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			try {
				String input = reader.readLine();
				String[] data = input.split("\\s+");
				String commandName = data[0];

				Class<?> commandClass = Class.forName(COMMANDS_PACKAGE_NAME + commandName.replaceFirst("[a-z]", String.valueOf(Character.toUpperCase(commandName.charAt(0)))));
				Object command = commandClass.getConstructor(String[].class, Repository.class, UnitFactory.class).newInstance(data, this.repository, this.unitFactory);

				Method method = commandClass.getMethod("execute");

				String result = (String) method.invoke(command);
				if (result.equals("fight")) {
					break;
				}
				System.out.println(result);
			} catch (ClassNotFoundException e) {
				System.out.println("Invalid command!");
			}catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
