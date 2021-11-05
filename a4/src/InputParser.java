import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputParser {

    // 1. Use regular expresssions (Pattern) for validating input data	//
    // 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd"
    //    SimpleDateFormat format "yyyy-MM-dd"

    private static final String FIRST_NAME_PATTERN = "(?:[A-Z][a-z]+)";
    private static final String LAST_NAME_PATTERN = "(?:[A-Z][a-z]+)";

    private static final String YEAR_PATTERN = "(?:[0-9]{4})";
    private static final String MONTH_PATTERN = "(?:(?:[0][1-9])" +
            "|(?:[1][0-2]))";
    private static final String DAY_PATTERN = "(?:(?:[0][1-9])" +
            "|(?:[1-2][0-9])" +
            "|(?:[3][0-1]))";
    private static final String SEPARATOR = "-";
    private static final String BIRTHDAY_PATTERN = "(?:" + YEAR_PATTERN + SEPARATOR +
            MONTH_PATTERN + SEPARATOR + DAY_PATTERN + ")";

    private static final String LINE = "(?<firstName>" + FIRST_NAME_PATTERN + ")" + "\\s" + "(?<lastName>" + LAST_NAME_PATTERN + ")" +
            "\\s" + "(?<birthday>" + BIRTHDAY_PATTERN + ")";

    private static final Pattern LINE_PATTERN = Pattern.compile(LINE);

    public static List<Person> parse(File file) {
        List<Person> people = new LinkedList<>();
        Matcher matcher;
        try (
                BufferedReader br =
                        new BufferedReader(
                                new FileReader(file));
        ){
            String line;
            while ( (line = br.readLine()) != null) {
                matcher = LINE_PATTERN.matcher(line);
                if (matcher.find()) {
                    String first_name = matcher.group(1);
                    String last_name = matcher.group(2);
                    Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(matcher.group(3));
                    Person p = new Person(first_name, last_name, birthdate);
                    people.add(p);
                }
            }
        } catch(IOException | ParseException e) { e.printStackTrace(); }
        return people;
    }
}