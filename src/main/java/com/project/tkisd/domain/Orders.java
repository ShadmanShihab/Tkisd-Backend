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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "trx_type", nullable = false)
    private TrxType trxType;

    @Column(name = "user_id")
    private Long userId;

    @JsonIgnoreProperties(value = { "categoryId", "orders" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Courses courseId;

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

    public Long getUserId() {
        return this.userId;
    }

    public Orders userId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Courses getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Courses courses) {
        this.courseId = courses;
    }

    public Orders courseId(Courses courses) {
        this.setCourseId(courses);
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
            ", userId=" + getUserId() +
            "}";
    }
}
