package Project.services;

import Project.repos.CompanyRepository;
import Project.repos.CouponRepository;
import Project.repos.CustomerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;

    public abstract Boolean login(String email, String password);

}
