package Project.services;

import Project.beans.Category;
import Project.beans.ClientType;
import Project.beans.Company;
import Project.beans.Coupon;
import Project.exceptions.CouponsException;
import Project.exceptions.EnumException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor

public class CompanyServiceImpl extends ClientService implements CompanyService {
    private int companyId;

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }


    @Override
    public Boolean login(String email, String password) {
        return (this.companyRepository.existsByEmailAndPassword(email, password));
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponsException {
        if (this.couponRepository.existsByIdAndTitle(companyId, coupon.getTitle()))
            throw new CouponsException(EnumException.EXIST_TITLE_FOR_COMPANY);
        this.couponRepository.save(coupon);

    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) throws CouponsException {
        Coupon updatedCoupon = this.couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponsException(EnumException.COUPON_NOT_EXIST));
        if (couponId != coupon.getId())
            throw new CouponsException(EnumException.UPDATE_COUPON_ID);
        if (updatedCoupon.getCompany().getId() != coupon.getCompany().getId())
            throw new CouponsException(EnumException.UPDATE_COUPON_COMPANY_ID);
        this.couponRepository.save(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) throws CouponsException {
        Coupon deletedCoupons = this.couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponsException(EnumException.COUPON_NOT_EXIST));
        this.couponRepository.deletePurchaseByCouponId(couponId);
        this.couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons() throws CouponsException {
        return this.companyRepository.findById(this.companyId)
                .orElseThrow(() -> new CouponsException(EnumException.COMPANY_NOT_EXIST))
                .getCoupons();
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category) throws CouponsException {
        return this.couponRepository
                .findByCompanyAndCategory(
                        this.companyRepository.findById(this.companyId)
                                .orElseThrow(() -> new CouponsException(EnumException.COMPANY_NOT_EXIST)), category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Double maxPrice) throws CouponsException {
        return this.couponRepository
                .findCompanyCouponsByMaxPrice(this.companyId, maxPrice);

    }

    @Override
    public Company getCompanyDetails() throws CouponsException {
        return this.companyRepository.findById(this.companyId)
                .orElseThrow(() -> new CouponsException(EnumException.COMPANY_NOT_EXIST));
    }
}
