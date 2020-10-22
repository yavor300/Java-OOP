import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable {
    private List<String>  numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }


    @Override
    public String call() {
        String numberRegex = "[0-9]+";
        Pattern numberPattern = Pattern.compile(numberRegex);

        StringBuilder sb = new StringBuilder();

        for (String number : this.numbers) {
            Matcher matcher = numberPattern.matcher(number);
            if (matcher.matches()) {
                sb.append("Calling... ").append(number).append(System.lineSeparator());
            } else {
                sb.append("Invalid number!").append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }


    @Override
    public String browse() {
        String urlRegex = "^(https?|ftp|file)://[-a-zA-Z+&@#/%?=~_|!:,.;]*[-a-zA-Z+&@#/%=~_|]";
        Pattern urlPattern = Pattern.compile(urlRegex);

        StringBuilder sb = new StringBuilder();

        for (String url : this.urls) {
            Matcher matcher = urlPattern.matcher(url);
            if (matcher.matches()) {
                sb.append("Browsing: ").append(url).append("!").append(System.lineSeparator());
            } else {
                sb.append("Invalid URL!").append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}
