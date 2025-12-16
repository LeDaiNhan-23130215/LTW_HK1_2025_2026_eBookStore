package services;

import DAO.CheckoutDAO;
import DAO.CheckoutDetailDAO;
import models.Checkout;
import models.CheckoutDetail;
import models.CartDetail;
import utils.DBConnection;

import java.sql.Connection;
import java.util.List;

public class CheckoutService {

    private CheckoutDAO checkoutDAO = new CheckoutDAO();
    private CheckoutDetailDAO checkoutDetailDAO = new CheckoutDetailDAO();

    public boolean checkout(Checkout checkout, List<CartDetail> cart) {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            int checkoutID = checkoutDAO.createCheckout(con, checkout);

            for (CartDetail item : cart) {
                checkoutDetailDAO.addCheckoutDetail(
                        con,
                        new CheckoutDetail(checkoutID, item.getBookID(), item.getPrice())
                );
            }

            checkoutDAO.updateStatus(con, checkoutID, "success");
            con.commit();
            return true;

        } catch (Exception e) {
            try {
                if (con != null) con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

