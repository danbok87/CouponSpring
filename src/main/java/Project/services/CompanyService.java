package Project.services;

import Project.beans.Category;
import Project.beans.Company;
import Project.beans.Coupon;
import Project.exceptions.CouponsException;

import java.util.List;

public interface CompanyService {
    void addCoupon(Coupon coupon) throws CouponsException;
    void updateCoupon(int couponId, Coupon coupon) throws CouponsException;
    void deleteCoupon(int couponId) throws CouponsException;
    List<Coupon> getCompanyCoupons() throws CouponsException;
    List<Coupon> getCompanyCoupons(Category category) throws CouponsException;
    List<Coupon> getCompanyCoupons(Double maxPrice) throws CouponsException;
    Company getCompanyDetails() throws CouponsException;
    void setCompanyId(int companyId);

}
