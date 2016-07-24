
public class TimeFormat
{
    public static double duration_seconds;

    public static void stringtoseconds(String time)
    {
        String[] units = time.split(":");
        double hours = Integer.parseInt(units[0]);
        double minutes = Integer.parseInt(units[1]);
        double seconds = Integer.parseInt(units[2]);
        duration_seconds = (3600 * hours) + (60 * minutes) + (seconds);
    }

    public static String secondstostring(int secs)
    {
        int hours = secs / 3600,
                remainder = secs % 3600,
                minutes = remainder / 60,
                seconds = remainder % 60;

        String hh = (hours < 10 ? "0" : "") + hours;
        String mm = (minutes < 10 ? "0" : "") + minutes;
        String ss = (seconds < 10 ? "0" : "") + seconds;

        String newformat = hh +":"+ mm+":"+ss;

        return newformat;

    }
}
