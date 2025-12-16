package services;

import DAO.*;
import DTO.PaymentAdminView;
import models.*;
import DTO.FeedbackAdminView;

import java.util.List;

public class AdminServices {
    private UserDAO userDAO = new UserDAO();
    private CheckoutDAO checkoutDAO = new CheckoutDAO();
    private CheckoutDetailDAO checkoutDetailDAO = new CheckoutDetailDAO();
    private PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private EbookDAO ebookDAO = new EbookDAO();
    private BannerDao bannerDAO = new BannerDao();
    private NewsDAO newsDAO = new NewsDAO();
    private FeedbackDAO feedbackDAO = new FeedbackDAO();

    public AdminServices() {}

    //User
    public int getTotalUsers(){
        return userDAO.countTotalUser();
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public boolean addUser(User user){
        return userDAO.addUser(user);
    }

    public boolean deleteUser(int id){
        return userDAO.deleteUser(id);
    }

    public boolean updateUser(User user){
        return userDAO.updateUser(user);
    }

    public User getUserById(int id){
        return userDAO.getUserByID(id);
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

    public List<Checkout> getAllCheckouts() {
        return checkoutDAO.getCheckouts();
    }

    public List<Checkout> getCheckoutsByUser(int id){
        return checkoutDAO.getCheckoutsByUser(id);
    }

    public int countSuccessOrdersInThisMonth(){
        return checkoutDAO.countSuccessOrderThisMonth();
    }

    public Checkout getCheckoutById(int id) {
        return checkoutDAO.getCheckoutById(id);
    }

    public List<PaymentAdminView> getAllPayments() { return checkoutDAO.getAllPaymentWithUser(); }

    public PaymentAdminView getPaymentByUserID(int id) { return checkoutDAO.getPaymentWithUserById(id); }

    //CheckoutDetail
    public List<CheckoutDetail> getCheckoutDetails(int checkoutID) {
        return checkoutDetailDAO.getDetailsByCheckoutID(checkoutID);
    }

    //PaymentMethod
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodDAO.getAllPMs();
    }

    public PaymentMethod getPMbyId(int id) {
        return paymentMethodDAO.getPMById(id);
    }

    public boolean addPaymentMethod(PaymentMethod pm){
        return  paymentMethodDAO.addPM(pm);
    }

    public boolean deletePaymentMethod(int id){
        return paymentMethodDAO.deletePM(id);
    }

    public boolean updatePaymentMethod(PaymentMethod pm){
        return paymentMethodDAO.updatePM(pm);
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
    public List<Banner> getListBanner(){
        return bannerDAO.getAllBanner();
    }

    public boolean addBanner(Banner banner){
        return bannerDAO.addBanner(banner);
    }

    public boolean updateBanner(Banner banner){
        return bannerDAO.updateBanner(banner);
    }

    public boolean deleteBanner(int id){
        return bannerDAO.deleteBanner(id);
    }

    public Banner getBannerById(int id){
        return bannerDAO.getBannerById(id);
    }

    //News
    public List<News> getListNews(){
        return newsDAO.getAllNews();
    }

    public boolean addNews(News news){
        return newsDAO.addNews(news);
    }

    public boolean updateNews(News news){
        return newsDAO.updateNews(news);
    }

    public boolean deleteNews(int id){
        return newsDAO.deleteNews(id);
    }

    public News getNewsById(int id){ return newsDAO.getNewsById(id); }

    //Review

    //Feedback
    public List<FeedbackAdminView> getFeedbackWithUser() {
        return feedbackDAO.getAllFeedbackWithUser();
    }

    public boolean deleteFeedback(int id){
        return feedbackDAO.deleteFeedback(id);
    }

    public boolean markFeedbackAsRead(int id){
        return feedbackDAO.markAsRead(id);
    }

    public FeedbackAdminView getFeedbackWithUserById(int id) {
        return feedbackDAO.getFeedbackWithUserById(id);
    }

}
