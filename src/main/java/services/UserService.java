package services;

import DAO.UserDAO;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    public boolean checkPassword(int userId, String oldPassword) {
        return userDAO.verifyPassword(userId, oldPassword);
    }

    public void changePassword(int userId, String newPassword) {
        userDAO.updatePassword(userId, newPassword);
    }
}
