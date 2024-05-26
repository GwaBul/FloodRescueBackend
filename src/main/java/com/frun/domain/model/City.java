package com.frun.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "city")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class City {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<River> riverList;

    public void insertRiver(River river) {
        river.setCity(this);
        riverList.add(river);
    }
}
