package com.gomilkyway.profile.adari.pages;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Page entity
  */

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Page {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String content;

    private String slug;

    @Column(columnDefinition = "DATETIME default NOW()")
    private Date created;

    private boolean published = false;

}
