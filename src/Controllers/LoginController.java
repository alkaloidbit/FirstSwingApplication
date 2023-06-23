package Controllers;

import Models.User;

public class LoginController {
    private static User loggedUser;

    LoginController(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        LoginController.loggedUser = loggedUser;
    }
}
