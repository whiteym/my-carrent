
package carrent;

import java.util.Date;

public class BookingCancelled extends AbstractEvent {

    private Long id;
    private Long productId;
    private Integer qty;
    private String status;
    private Date startDate;
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public Date getBookingFrom() {
        return startDate;
    }

    public void setBookingFrom(Date startDate) {
        this.startDate = startDate;
    }
    public Date getBookingTo() {
        return endDate;
    }

    public void setBookingTo(Date endDate) {
        this.endDate = endDate;
    }
}

