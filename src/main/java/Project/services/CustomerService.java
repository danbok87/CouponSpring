package Project.services;

import Project.beans.Category;
import Project.beans.Coupon;
import Project.beans.Customer;
import Project.exceptions.CouponsException;

import java.util.List;

public interface CustomerService {
    void purchaseCoupon(Coupon coupon) throws CouponsException;
    List<Coupon> getCustomerCoupons() throws CouponsException;
    List<Coupon> getCustomerCoupons(Category category);
    List<Coupon> getCustomerCoupons(double maxPrice);
    Customer getCustomerDetails() throws CouponsException;
    void setCustomerId(int customerId);

}
