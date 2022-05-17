package com.paf.exercise.exercise.model.abstracts;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * This abstract class serves common data for all models.
 *
 * - Auto generated ID
 * - Timestamps
 */
@MappedSuperclass
public abstract class Audit {

    /* Variables */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /* super-constructor */

    public Audit() {
        this.id = getId();
        this.createdAt = getCreatedAt();
        this.updatedAt = getUpdatedAt();
    }

    /* Getters */

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
