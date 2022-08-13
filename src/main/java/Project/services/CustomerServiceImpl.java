package Project.services;

import Project.beans.Category;
import Project.beans.ClientType;
import Project.beans.Coupon;
import Project.beans.Customer;
import Project.exceptions.CouponsException;
import Project.exceptions.EnumException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor

public class CustomerServiceImpl extends ClientService implements CustomerService {
    private int customerId;
    private LoginManager loginManager = new LoginManager();

    @Override
    public Boolean login(String email, String password) {
        return (this.customerRepository.existsByEmailAndPassword(email, password));
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponsException {
        Customer currentCustomer = this.customerRepository.findById(this.customerId)
                .orElseThrow(() -> new CouponsException(EnumException.CUSTOMER_NOT_EXIST));
        Coupon purchasedCoupon = this.couponRepository.findById(coupon.getId())
                .orElseThrow(() -> new CouponsException(EnumException.CUSTOMER_NOT_EXIST));
        if (currentCustomer.getCoupons().contains(purchasedCoupon.getClass()))
            throw new CouponsException(EnumException.COUPON_ALREADY_PURCHASE);
        if (purchasedCoupon.getAmount() < 1) {
            throw new CouponsException(EnumException.COUPON_RUN_OFF);
        }
        if (purchasedCoupon.getEndDate().before(new Date(System.currentTimeMillis())))
            throw new CouponsException(EnumException.COUPON_EXPIRED);
        purchasedCoupon.setAmount(purchasedCoupon.getAmount() - 1);
        this.couponRepository.save(purchasedCoupon);
        currentCustomer.getCoupons().add(purchasedCoupon);
        this.customerRepository.save(currentCustomer);
    }

    @Override
    public List<Coupon> getCustomerCoupons() throws CouponsException {
        return this.customerRepository.findById(this.customerId)
                .orElseThrow(() -> new CouponsException(EnumException.CUSTOMER_NOT_EXIST))
                .getCoupons();
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category) {
        return this.couponRepository.findCustomerCouponsByCategory(this.customerId, category.name());
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return this.couponRepository.findCustomerCouponsByMaxPrice(this.customerId,maxPrice);
    }

    @Override
    public Customer getCustomerDetails() throws CouponsException {
        return this.customerRepository.findById(this.customerId)
                .orElseThrow(() -> new CouponsException(EnumException.CUSTOMER_NOT_EXIST));
    }

    @Override
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
