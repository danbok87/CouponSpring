package Project.services;



import Project.beans.ClientType;
import Project.exceptions.CouponsException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class LoginManager {
    @Autowired
    private ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType) throws CouponsException {

        switch (clientType) {
            case Administrator: {
                AdminService administrator = ctx.getBean(AdminService.class);
                if (((AdminServiceImpl) administrator).login(email, password)) {
                    return (ClientService) administrator;
                }
                break;
            }
            case Company: {
                CompanyService company =  ctx.getBean(CompanyService.class);
                if (((CompanyServiceImpl) company).login(email, password)) {
                    return (ClientService) company;
                }
                break;
            }
            case Customer: {
                CustomerService customer = ctx.getBean(CustomerService.class);
                if (((CustomerServiceImpl) customer).login(email, password)) {
                    return (ClientService) customer;
                }
                break;
            }
        }
        return null;
    }
}
