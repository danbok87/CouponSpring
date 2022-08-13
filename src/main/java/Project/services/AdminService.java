package Project.services;

import Project.beans.Company;
import Project.beans.Customer;
import Project.exceptions.CouponsException;

import java.util.List;
import java.util.Optional;

public interface AdminService{
    void addCompany(Company company) throws CouponsException;
    void updateCompany(int companyId, Company company) throws CouponsException;
    void deleteCompany(int companyId) throws CouponsException;
    List<Company> getAllCompanies();
    Company getOneCompany(int companyId) throws CouponsException;

    void addCustomer(Customer customer) throws CouponsException;
    void updateCustomer(int customerId, Customer customer) throws CouponsException;
    void deleteCustomer(int customerId) throws CouponsException;
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerId) throws CouponsException;
}
