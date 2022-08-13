package Project.repos;

import Project.beans.Category;
import Project.beans.Company;
import Project.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsByIdAndTitle(int companyId, String title);

    @Query(
            value = "DELETE FROM `spring-coupons`.customers_coupons " +
                    "where coupons_id = :couponId"
            ,nativeQuery = true)
    @Transactional
    @Modifying
    void deletePurchaseByCouponId(@Param("couponId")int couponId);

    @Query(value = "SELECT * FROM coupons\n" +
            "            where company_id = ?1 and price <= ?2 ; ", nativeQuery = true)
    List<Coupon> findCompanyCouponsByMaxPrice(int companyId, double maxPrice);

    List<Coupon> findByCompanyAndCategory(Company company, Category category);

    @Query(value = "SELECT * FROM coupons \n" +
            "where category = ?2 and id in( \n" +
                "select coupons_id from customers_coupons \n" +
                "where customer_id = ?1) ;",nativeQuery = true)
    List<Coupon> findCustomerCouponsByCategory(int customerId, String category);

    @Query(value = "SELECT * FROM coupons\n" +
            "            where price <= ?2 and id in(\n" +
            "            select coupons_id from customers_coupons\n" +
            "                where `customer_id` = ?1); ", nativeQuery = true)
    List<Coupon> findCustomerCouponsByMaxPrice(int customerId, double maxPrice);

    List<Coupon> findByEndDateBefore(Date valueOf);
}
