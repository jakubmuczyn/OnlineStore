import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static List<User> users;
    private static int lastUserID = 0;

    public UserManager() {
        users = new ArrayList<>();
        User user = new User(lastUserID, "anonek21", "admin123", "filip.anonek@vp.pl", "Filip",
                "Chajzer", "Bończa", "888 777 666",
                true);
        addUser(user);
    }

    public static void addUser(User user) {

        users.add(user);
        lastUserID++;
    }

    public static void removeUser(int userID) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == userID) {
                users.remove(i);
            }
        }
    }

    public static User getUser(int userID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == userID) {
                return users.get(i);
            }
        }
        return null;
    }
}
