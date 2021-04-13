import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {

    public static String MostFreeTime(String[] strArr) {
        ArrayList<LocalTime> eventTimesList = new ArrayList<LocalTime>();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma", Locale.ENGLISH);
        //parses our string array to date list
        for (String times : strArr) {
            String[] timesSplitted = times.split("-");
            for (String time : timesSplitted) {
                eventTimesList.add(LocalTime.parse(time, timeFormatter));
            }
        }
        Collections.sort(eventTimesList);
        Duration mostFreeTime = Duration.ZERO;
        /*a loop that counts and searches most time difference between end of the passed event
          and start of a new one*/
        try {
            for (int i = 1; i < eventTimesList.size(); i += 2) {
                if (i + 2 > eventTimesList.size()) {
                    break;
                }
                if (Duration.between(eventTimesList.get(i), eventTimesList.get(i + 1)).compareTo(mostFreeTime) == 1) {
                    mostFreeTime = Duration.between(eventTimesList.get(i), eventTimesList.get(i + 1));
                }
            }
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            indexOutOfBoundsException.printStackTrace();
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
        } finally {
            return LocalTime.MIDNIGHT.plus(mostFreeTime).format(DateTimeFormatter.ofPattern("HH:mm"));
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print(MostFreeTime(new String[]{"06:00AM-08:00PM", "05:00AM-05:45AM", "08:02PM-08:04PM", "08:10PM-09:00PM", "09:09PM-09:11PM"}));
    }

}
