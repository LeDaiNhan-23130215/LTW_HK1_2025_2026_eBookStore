package services;

import DAO.*;
import DTO.AdminEbookView;
import DTO.PaymentAdminView;
import jakarta.servlet.http.Part;
import mappers.*;
import models.*;
import DTO.FeedbackAdminView;
import utils.CSVUtil;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class AdminServices {
    private UserDAO userDAO = new UserDAO();
    private CheckoutDAO checkoutDAO = new CheckoutDAO();
    private CheckoutDetailDAO checkoutDetailDAO = new CheckoutDetailDAO();
    private PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private EbookDAO ebookDAO = new EbookDAO();
    private BannerDAO bannerDAO = new BannerDAO();
    private NewsDAO newsDAO = new NewsDAO();
    private FeedbackDAO feedbackDAO = new FeedbackDAO();
    private AuthorDAO authorDAO = new AuthorDAO();
    private EbookService ebookService = new EbookService();
    private ImageServices imageServices = new ImageServices();

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

    public void importUserFile(Part filePart) {

        try {
            InputStream is = filePart.getInputStream();

            List<String[]> rows = CSVUtil.read(is);

            CSVMapper<User> mapper = new UserCSVMapper();

            for (String[] row : rows) {
                User u = mapper.map(row);
                if (u != null && !userDAO.checkAvailableUserNameOrEmail(u.getUsername())) {
                    userDAO.addUser(u);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Import user thất bại");
        }
    }

    //Ebook
    public int getTotalEbooks(){
        return ebookDAO.countTotalEBook();
    }
    public boolean addEbook(Ebook ebook){ return ebookDAO.insert(ebook); }
    public boolean updateEbook(Ebook ebook){ return ebookDAO.update(ebook); }
    public boolean deleteEbook(int id){ return ebookDAO.delete(id); }
    public List<AdminEbookView> getAllEbooks(){ return ebookDAO.findAllForAdmin(); }
    public Ebook getEbookByID(int id){ return ebookDAO.getEbookByID(id); }
    public List<Ebook> findAll() { return ebookDAO.findAll(); }
    public String generateEbookCode(int categoryId) {
        return ebookService.generateEBookCode(categoryId);
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

    public Map<Integer, Double> monthlyRevenueData(int year) { return checkoutDAO.getMonthlyRevenue(year); }

    public Map<Integer, Integer> getCheckoutPerMonth() { return checkoutDAO.checkoutPerMonth(); }

    public Map<String, Double> getRevenuePerCategory() {return checkoutDAO.revenueByCategory(); }

    public Map<String, Double> getTop5Ebook() { return checkoutDAO.top5Ebook(); }

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

    public void importCategoryFile(Part filePart) {

        try {
            InputStream is = filePart.getInputStream();

            List<String[]> rows = CSVUtil.read(is);

            CSVMapper<Category> mapper = new CategoryCSVMapper();

            for (String[] row : rows) {
                Category c = mapper.map(row);
                if (c != null && !categoryDAO.hasCategoryName(c.getName())) {
                    categoryDAO.addCategory(c);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Import category thất bại");
        }
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

    public void importBannerFile(Part filePart) {

        try {
            InputStream is = filePart.getInputStream();

            List<String[]> rows = CSVUtil.read(is);

            CSVMapper<Banner> mapper = new BannerCSVMapper();

            for (String[] row : rows) {
                Banner b = mapper.map(row);
                if (b != null) {
                    bannerDAO.addBanner(b);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Import category thất bại");
        }
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

    public void importNewsFile(Part filePart) {

        try {
            InputStream is = filePart.getInputStream();

            List<String[]> rows = CSVUtil.read(is);

            CSVMapper<News> mapper = new NewsCSVMapper();

            for (String[] row : rows) {
                News n = mapper.map(row);
                if (n != null) {
                    newsDAO.addNews(n);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Import category thất bại");
        }
    }

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

    //Author
    public List<Author> getListAuthors(){
        return authorDAO.getAllAuthors();
    }

    public boolean addAuthor(Author author){
        return authorDAO.addAuthor(author);
    }

    public boolean updateAuthor(Author author){
        return authorDAO.updateAuthor(author);
    }

    public boolean deleteAuthor(int id){
        return authorDAO.deleteAuthor(id);
    }

    public Author getAuthorById(int id){
        return authorDAO.getAuthorByID(id);
    }

    public void importAuthorFile(Part filePart) {

        try {
            InputStream is = filePart.getInputStream();

            List<String[]> rows = CSVUtil.read(is);

            CSVMapper<Author> mapper = new AuthorCSVMapper();

            for (String[] row : rows) {
                Author a = mapper.map(row);
                if (a != null) {
                    authorDAO.addAuthor(a);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Import category thất bại");
        }
    }

    //Image
    public int getIDAfterInserted(Image image ) {
        return imageServices.insertAndGetImgID(image);
    }
}
