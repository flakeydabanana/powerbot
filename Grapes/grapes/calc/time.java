package calc;

public class time {

public static String gettime(final long time) {
final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":"
+ (s < 10 ? "0" + s : s);

}

public static int perHour(int in, long time) {
return (int) ((in) * 3600000D / time);
}

}
