package com.gomilkyway.profile.adari.pages;


import java.sql.Date;

import com.gomilkyway.profile.adari.utils.HtmlUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

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
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Column(unique = true)
    private String slug;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date created;

    private boolean published = false;

    @Transient
    @Getter(AccessLevel.NONE)
    private String excerpt;

    public String getExcerpt() {
        String cont;
        if (content.length() > 100) {
            cont = content.substring(0, 100) + "...";
        } else {
            cont = content;
        }

        return HtmlUtil.stripAndOneLine(cont);
    }

}
