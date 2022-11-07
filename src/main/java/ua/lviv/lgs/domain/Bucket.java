package ua.lviv.lgs.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="Bucket")
public class Bucket {

    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    protected User user;
    @ManyToOne
    @JoinColumn(name="productId", referencedColumnName = "id")
    protected Product product;
    @Column(name="purchaseDate")
    private Date purchaseDate;

    public Bucket(Integer id,Integer userId, Integer productId, Date purchaseDate) {
    }

    public Bucket(Integer productId,Integer userId, Date purchaseDate) {
    }

    public Bucket() {
    }

    public Integer getId() {
        return id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", product=" + product +
                ", purchaseDate=" + purchaseDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return Objects.equals(id, bucket.id) && Objects.equals(user, bucket.user) && Objects.equals(product, bucket.product) && Objects.equals(purchaseDate, bucket.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product, purchaseDate);
    }
}
