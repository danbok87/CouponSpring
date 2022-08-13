package Project;

import Project.beans.Company;
import Project.exceptions.CouponsException;
import Project.services.AdminService;
import Project.services.AdminServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponSpringApplication {

	public static void main(String[] args) throws CouponsException {
		SpringApplication.run(CouponSpringApplication.class, args);
	}
}
