package Project.job;

import Project.beans.Coupon;
import Project.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DailyRemoval {

    private final CouponRepository couponRepository;

    //@Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    @Scheduled(fixedRate = 100 * 60 * 1 * 1)
    public void removeExpiredCoupons() {
        List<Coupon> expCoupons = couponRepository.findByEndDateBefore(Date.valueOf(LocalDate.now()));
        expCoupons.forEach(coupon -> couponRepository.delete(coupon));
    }

}