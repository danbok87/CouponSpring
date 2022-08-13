package Project.repos;

import Project.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);
}
