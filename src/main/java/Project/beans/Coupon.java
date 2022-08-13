package Project.beans;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    @ManyToOne
    @ToString.Exclude
    private Company company;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

}
