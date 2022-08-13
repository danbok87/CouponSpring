package Project.clr;

import Project.beans.*;
import Project.exceptions.CouponsException;
import Project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(1)
public class projectPresentation implements CommandLineRunner {
    @Autowired
    private LoginManager loginManager;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("************************");
        System.out.println("** INITIALIZE PROJECT **");
        System.out.println("************************");
        AdminService adminService = (AdminService) loginManager.login("admin@admin.com","admin", ClientType.Administrator);
        Company tnuva = Company.builder()
                .name("Tnuva")
                .email("tnuva@tnuva.com")
                .password("tnuva")
                .build();
        Company global = Company.builder()
                .name("GlobalFlights")
                .email("global@Flights.com")
                .password("globalFlights")
                .build();
        Company electraMitzi = Company.builder()
                .name("ElectraMitzi")
                .email("electra@Mitzi.com")
                .password("electraMitzi")
                .build();
        Company zara = Company.builder()
                .name("ZaraFashion")
                .email("zara@Fashion.com")
                .password("zaraFashion")
                .build();
        adminService.addCompany(tnuva);
        adminService.addCompany(global);
        adminService.addCompany(electraMitzi);
        adminService.addCompany(zara);
        System.out.println("\n"+ "Printed all companies: ");
        adminService.getAllCompanies().forEach(System.out::println);
        Customer moshe = Customer.builder()
                .firstName("Moshe")
                .lastName("Cohen")
                .email("mosheCohen@gmail.com")
                .password("moshe")
                .build();
        Customer david = Customer.builder()
                .firstName("David")
                .lastName("Levi")
                .email("davidLevi@gmail.com")
                .password("david")
                .build();
        Customer yossi = Customer.builder()
                .firstName("Yossi")
                .lastName("israli")
                .email("yossiIsraeli@gmail.com")
                .password("yossi")
                .build();
        adminService.addCustomer(moshe);
        adminService.addCustomer(david);
        adminService.addCustomer(yossi);
        System.out.println("\n"+ "Printed all customers: ");
        adminService.getAllCustomers().forEach(System.out::println);
        Coupon iceCream = Coupon.builder()
                .category(Category.FOOD)
                .company(tnuva)
                .title("Ice Cream Coupon")
                .description("Two Ice cream in 35 shekel")
                .startDate(Date.valueOf(LocalDate.of(2022, 8, 3)))
                .endDate(Date.valueOf(LocalDate.of(2022, 9, 1)))
                .amount(2)
                .price(35.0)
                .image("url://tnuva/IceCreams")
                .build();
        Coupon bamba = Coupon.builder()
                .category(Category.FOOD)
                .company(tnuva)
                .title("Bamba Coupon")
                .description("3 bamba in 10 shekel")
                .startDate(Date.valueOf(LocalDate.of(2022, 8, 3)))
                .endDate(Date.valueOf(LocalDate.of(2022, 9, 1)))
                .amount(2)
                .price(10.0)
                .image("url://tnuva/Bamba")
                .build();
        Coupon sharmAlShech = Coupon.builder()
                .category(Category.FLIGHT)
                .company(global)
                .title("sharmAlShech flight coupon")
                .description("flight to sharm only 297$")
                .startDate(Date.valueOf(LocalDate.of(2022, 8, 3)))
                .endDate(Date.valueOf(LocalDate.of(2022, 9, 1)))
                .amount(2)
                .price(297.0)
                .image("url://global/sharmAlShech")
                .build();
        Coupon santorini = Coupon.builder()
                .category(Category.FLIGHT)
                .company(global)
                .title("santorini flight coupon")
                .description("flight to santorini only 70$")
                .startDate(Date.valueOf(LocalDate.of(2022, 8, 3)))
                .endDate(Date.valueOf(LocalDate.of(2022, 9, 1)))
                .amount(2)
                .price(70)
                .image("url://global/santorini")
                .build();
        Coupon iphone = Coupon.builder()
                .category(Category.ELECTRICITY)
                .company(electraMitzi)
                .title("Iphone Coupon")
                .description("Iphone + Ipad in 998$")
                .startDate(Date.valueOf(LocalDate.of(2022, 7, 3)))
                .endDate(Date.valueOf(LocalDate.of(2022, 8, 2)))
                .amount(2)
                .price(998.0)
                .image("url://electraMitzi/IphoneIpad")
                .build();
        Coupon iRobot = Coupon.builder()
                .category(Category.ELECTRICITY)
                .company(electraMitzi)
                .title("iRobot Coupon")
                .description("IRobot in 300$")
                .startDate(Date.valueOf(LocalDate.of(2022, 8, 3)))
                .endDate(Date.valueOf(LocalDate.of(2022, 9, 1)))
                .amount(1)
                .price(300.0)
                .image("url://electraMitzi/iRobot")
                .build();
        Coupon tShirt = Coupon.builder()
                .category(Category.FASHION)
                .company(zara)
                .title("tShirt Coupon")
                .description("3 shirts in 100$")
                .startDate(Date.valueOf(LocalDate.of(2022, 7, 3)))
                .endDate(Date.valueOf(LocalDate.of(2022, 8, 2)))
                .amount(2)
                .price(100.0)
                .image("url://zara/tShirts")
                .build();
        Coupon jeans = Coupon.builder()
                .category(Category.FASHION)
                .company(zara)
                .title("jeans Coupon")
                .description("2 jeans in 50$")
                .startDate(Date.valueOf(LocalDate.of(2022, 8, 3)))
                .endDate(Date.valueOf(LocalDate.of(2022, 9, 1)))
                .amount(2)
                .price(50.0)
                .image("url://zara/jeans")
                .build();
        CompanyService tnuvaCompany = (CompanyService) loginManager.login(tnuva.getEmail(), tnuva.getPassword(), ClientType.Company);
        tnuvaCompany.setCompanyId(tnuva.getId());
        tnuvaCompany.addCoupon(iceCream);
        tnuvaCompany.addCoupon(bamba);
        System.out.println("\n"+ "Printing Tnuva's details: ");
        System.out.println(tnuvaCompany.getCompanyDetails());
        CompanyService globalCompany = (CompanyService) loginManager.login(global.getEmail(), global.getPassword(), ClientType.Company);
        globalCompany.setCompanyId(global.getId());
        globalCompany.addCoupon(santorini);
        globalCompany.addCoupon(sharmAlShech);
        System.out.println("\n"+ "Printing Global's details: ");
        System.out.println(globalCompany.getCompanyDetails());
        CompanyService electroMitziCompany = (CompanyService) loginManager.login(electraMitzi.getEmail(), electraMitzi.getPassword(), ClientType.Company);
        electroMitziCompany.setCompanyId(electraMitzi.getId());
        electroMitziCompany.addCoupon(iphone);
        electroMitziCompany.addCoupon(iRobot);
        System.out.println("\n"+ "Printing ElctraMitzi's details: ");
        System.out.println(electroMitziCompany.getCompanyDetails());
        CompanyService zaraCompany = (CompanyService) loginManager.login(zara.getEmail(), zara.getPassword(), ClientType.Company);
        zaraCompany.setCompanyId(zara.getId());
        zaraCompany.addCoupon(tShirt);
        zaraCompany.addCoupon(jeans);
        System.out.println("\n"+ "Printing Zara's details: ");
        System.out.println(zaraCompany.getCompanyDetails());
        CustomerService davidCustomer = (CustomerService) loginManager.login(david.getEmail(), david.getPassword(), ClientType.Customer);
        davidCustomer.setCustomerId(david.getId());
        davidCustomer.purchaseCoupon(iceCream);
        davidCustomer.purchaseCoupon(iRobot);
        System.out.println("\n"+ "printing all David's coupons: ");
        davidCustomer.getCustomerCoupons().forEach(System.out::println);
        System.out.println("\n"+ "printing all David's coupons in category: FOOD: ");
        davidCustomer.getCustomerCoupons(Category.FOOD).forEach(System.out::println);
        System.out.println("\n"+ "printing all David's coupons who cheapest then 50: ");
        davidCustomer.getCustomerCoupons(50.0).forEach(System.out::println);
        System.out.println("\n"+ "printing David's details: ");
        System.out.println(davidCustomer.getCustomerDetails());

        System.out.println("****************************");
        System.out.println("*** LET'S MAKE SOME TEST ***");
        System.out.println("****************************");
        System.out.println();

        System.out.println("\n"+ "Trying to add Company with existing name or email");

        try {
            Company gnuva = Company.builder()
                    .name("Tnuva")
                    .email("gnuva@tnuva.com")
                    .password("tnuva")
                    .build();
            adminService.addCompany(gnuva);
        }catch (CouponsException e){
            System.out.println(e);
        }

        System.out.println();
        System.out.println("\n"+ "Trying to update Company name or id");
        try {
            tnuva.setName("Snuva");
            adminService.updateCompany(tnuva.getId(),tnuva);
        }catch (CouponsException e){
            System.out.println(e);
        }

        System.out.println();
        System.out.println("Deleting a company and checking that the coupons and purchase history have been deleted");
        adminService.deleteCompany(tnuva.getId());
        System.out.println(davidCustomer.getCustomerDetails());

        System.out.println();
        System.out.println("Adding customer with same email: ");
        Customer dossi = Customer.builder()
                .firstName("Dossy")
                .lastName("israli")
                .email("yossiIsraeli@gmail.com")
                .password("yossi")
                .build();
        try{
            adminService.addCustomer(dossi);
        }catch (CouponsException e){
            System.out.println(e);
        }
        System.out.println();
        System.out.println("delete customer and his purchased coupons: ");
        adminService.deleteCustomer(david.getId());

        System.out.println();
        System.out.println("Return all company's coupons from specific category: ");
        zaraCompany.getCompanyCoupons(Category.FASHION).forEach(System.out::println);

        System.out.println();
        System.out.println("Try to purchased expired coupon");
        CustomerService mosheCustomer = (CustomerService) loginManager.login(moshe.getEmail(), moshe.getPassword(), ClientType.Customer);
        mosheCustomer.setCustomerId(moshe.getId());
        try{
            mosheCustomer.purchaseCoupon(iphone);
        }catch (CouponsException e){
            System.out.println(e);
        }

        System.out.println();
        System.out.println("Try to purchased an out of stock coupon: ");
        try {
            mosheCustomer.purchaseCoupon(iRobot);
        }catch (CouponsException e){
            System.out.println(e);
        }



    }

}
