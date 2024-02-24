package com.project.tkisd.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.tkisd.domain.enumeration.TrxType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Orders.
 */
@Entity
@Table(name = "orders")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "trx_type", nullable = false)
    private TrxType trxType;

    @JsonIgnoreProperties(value = { "courses", "orders" }, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Courses courses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Orders id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return this.amount;
    }

    public Orders amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUuid() {
        return this.uuid;
    }

    public Orders uuid(String uuid) {
        this.setUuid(uuid);
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public TrxType getTrxType() {
        return this.trxType;
    }

    public Orders trxType(TrxType trxType) {
        this.setTrxType(trxType);
        return this;
    }

    public void setTrxType(TrxType trxType) {
        this.trxType = trxType;
    }

    public User getUser() {
        return this.user;
    }

    public Orders user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Courses getCourses() {
        return this.courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public Orders courseId(Courses courses) {
        this.setCourses(courses);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Orders)) {
            return false;
        }
        return getId() != null && getId().equals(((Orders) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Orders{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", trxType='" + getTrxType() + "'" +
            ", userId=" + getUser().getId() +
            "}";
    }
}
