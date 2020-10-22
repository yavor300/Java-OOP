package robotService.core;

import robotService.common.Command;
import robotService.core.interfaces.Controller;
import robotService.core.interfaces.Engine;
import robotService.io.ConsoleReader;
import robotService.io.ConsoleWriter;
import robotService.io.interfaces.InputReader;
import robotService.io.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private InputReader reader;
    private OutputWriter writer;
    private Controller controller;

    public EngineImpl() {
        // this.controller = new ControllerImpl();   //TODO Implement first
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if ("Exit".equals(result)) {
                    break;
                }

            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }

            this.writer.writeLine(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s");

        Command command = Command.valueOf(tokens[0]);
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        String result = null;

        switch (command) {
            case Manufacture:
                result = manufacture(data);
                break;
            case Repair:
                result = repair(data);
                break;
            case Work:
                result = work(data);
                break;
            case Charge:
                result = charge(data);
                break;
            case Sell:
                result = sell(data);
                break;
            case History:
                result = history(data);
                break;
            case Exit:
                result = "Exit";
                break;
        }

        return result;
    }

    private String manufacture(String[] data) {
        // TODO
        return null;
    }

    private String repair(String[] data) {
        // TODO
        return null;
    }

    private String work(String[] data) {
        // TODO
        return null;
    }

    private String charge(String[] data) {
        // TODO
        return null;
    }

    private String sell(String[] data) {
        // TODO
        return null;
    }

    private String history(String[] data) {
        // TODO
        return null;
    }
}
