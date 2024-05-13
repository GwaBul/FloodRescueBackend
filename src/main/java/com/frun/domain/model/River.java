package com.frun.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Polygon;

@Entity
@Table(name = "river")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class River {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String name;

    @Setter
    private MultiPoint exitList;

    @Setter
    private Polygon boundary;

    @Setter
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
