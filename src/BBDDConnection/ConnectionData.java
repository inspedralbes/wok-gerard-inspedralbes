package src.BBDDConnection;

public class ConnectionData {
    private static String url = "jdbc:mysql://daw.inspedralbes.cat:3306/gtorrents_wok_daw";
    private static String usr = "gtorrents_wokdaw";
    private static String pwd = "1Password!";

    public static String getUrl() {
        return url;
    }

    public static String getUsr() {
        return usr;
    }

    public static String getPwd() {
        return pwd;
    }
}
