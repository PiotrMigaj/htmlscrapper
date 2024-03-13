package pl.migibud.htmlscrapper.internal.database.internal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ADVERTISEMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
class AdvertisementEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String price;
    private String title;
    private String address;
    private String description;
    private LocalDate createdAt;
}
