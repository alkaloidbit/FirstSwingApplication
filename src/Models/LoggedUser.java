package Models;

public class LoggedUser{
    private static User logUser = null;

    public void logUser(User logUser) {
        this.logUser = logUser;
        System.out.println("logUserOP");
    }
    public void disconnectUser(){
        this.logUser = null;
    }
    public int adminStatus(){
        return logUser.getIs_admin();
    }
    public User getLogUser(){
        return this.logUser;
    }

}
