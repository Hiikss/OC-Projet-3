package com.openclassrooms.ocprojet3.domains.rental;

import com.openclassrooms.ocprojet3.domains.message.Message;
import com.openclassrooms.ocprojet3.domains.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rentals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int surface;

    @Column
    private int price;

    @Column
    private String picture;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "rental")
    private List<Message> messages;
}
