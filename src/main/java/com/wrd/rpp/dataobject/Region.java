package com.wrd.rpp.dataobject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter@Setter //@Data在这里是个巨坑！麻痹哟
public class Region {
    @Id
    @GeneratedValue
    private Integer regionId;
    private String regionCode;
    private String regionName;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Region parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Region> children = new HashSet<Region>();
}
