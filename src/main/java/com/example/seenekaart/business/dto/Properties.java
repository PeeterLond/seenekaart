package com.example.seenekaart.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Properties implements Serializable {

    private Integer id;
    private String title;
    private String description;

}
