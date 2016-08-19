package Jexpress;

/** Version file
 * Created by Pengfei on 8/19/2016.
 */
public class Version {
    private static int major = 0;
    private static int minor = 0;
    private static int minor2 = 1;
    private static int scrum = 1;

    public static String versionNumber(){
        return String.valueOf(major) + "." + String.valueOf(minor) + "." + String.valueOf(minor2) + "." + String.valueOf(scrum);
    }

    public static String getVersion(){
        return "(" + versionNumber() +")";
    }
}
