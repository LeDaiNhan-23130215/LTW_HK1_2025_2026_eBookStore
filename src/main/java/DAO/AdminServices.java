package DAO;

import models.Category;

import java.util.List;

public class AdminServices {
    UserDAO userDAO = new UserDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    CheckoutDAO checkoutDAO = new CheckoutDAO();
    EbookDAO ebookDAO = new EbookDAO();

    public AdminServices() {}

    //User
    public int getTotalUsers(){
        return userDAO.countTotalUser();
    }

    //Ebook
    public int getTotalEbooks(){
        return ebookDAO.countTotalEBook();
    }

    //Checkout
    public int getTotalSuccessOrders(){
        return checkoutDAO.countSuccessOrder();
    }

    public double getTotalMonthlyRevenue(){
        return checkoutDAO.getMonthlyRevenue();
    }

    //Category
    public List<Category> getListCategory(){
        return categoryDAO.getAllCategory();
    }

    public boolean addCategory(Category category){
        return categoryDAO.addCategory(category);
    }

    public boolean updateCategory(Category category){
        return categoryDAO.updateCategory(category);
    }

    public boolean deleteCategory(int id){
        return categoryDAO.deleteCategory(id);
    }

    public Category getCategoryById(int id){
        return categoryDAO.getCategoryById(id);
    }

    //Banner

    //News

    //Review

    //Feedback
}
