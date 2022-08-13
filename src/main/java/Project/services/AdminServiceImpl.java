package Project.services;

import Project.beans.ClientType;
import Project.beans.Company;
import Project.beans.Coupon;
import Project.beans.Customer;
import Project.exceptions.CouponsException;
import Project.exceptions.EnumException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class AdminServiceImpl extends ClientService implements AdminService {

    @Override
    public void addCompany(Company company) throws CouponsException {
        if (this.companyRepository.existsByName(company.getName()))
            throw new CouponsException(EnumException.COMPANY_EXIST_NAME);
        if (this.companyRepository.existsByEmail(company.getEmail()))
            throw new CouponsException(EnumException.COMPANY_EXIST_EMAIL);
        this.companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponsException {
        if (companyId != company.getId())
            throw new CouponsException(EnumException.UPDATE_COMPANY_ID);
        if (!company.getName().equals(this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponsException(EnumException.COMPANY_NOT_FOUND))
                .getName()))
            throw new CouponsException(EnumException.UPDATE_COMPANY_NAME);
        this.companyRepository.save(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponsException {
        Company deletedCompany = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponsException(EnumException.COMPANY_NOT_FOUND));
        for (Coupon coupon : deletedCompany.getCoupons()) {
             this.couponRepository.deletePurchaseByCouponId(coupon.getId());
        }
        this.companyRepository.delete(deletedCompany);
    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getOneCompany(int companyId) throws CouponsException {
        return this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponsException(EnumException.COMPANY_NOT_FOUND));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponsException {
        if (this.customerRepository.existsByEmail(customer.getEmail()))
            throw new CouponsException(EnumException.EXIST_CUSTOMER_EMAIL);
        this.customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponsException {
        if (customerId != customer.getId())
            throw new CouponsException(EnumException.UPDATE_CUSTOMER_ID);
        this.customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CouponsException {
        Customer deletedCustomer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CouponsException(EnumException.CUSTOMER_NOT_FOUND));
        this.customerRepository.deleteById(deletedCustomer.getId());

    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerId) throws CouponsException {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CouponsException(EnumException.CUSTOMER_NOT_FOUND));
    }

    @Override
    public Boolean login(String email, String password) {
        return ((email.equals("admin@admin.com")) && (password.equals("admin")));
    }
}
