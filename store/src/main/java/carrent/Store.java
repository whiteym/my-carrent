package carrent;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Store_table")
public class Store {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long bookingId;
    private Long productId;
    private Integer qty;
    private String status;

    @PostPersist
    public void onPostPersist(){
        CarRented carRented = new CarRented();
        BeanUtils.copyProperties(this, carRented);
        carRented.publishAfterCommit();


    }

    @PreUpdate
    public void onPreUpdate(){
        CarReturned carReturned = new CarReturned();
        BeanUtils.copyProperties(this, carReturned);
        carReturned.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
