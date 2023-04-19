/*
 * Copyright 2023 Ari Adari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gomilkyway.profile.adari.pages;


import java.sql.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.gomilkyway.profile.adari.audit.AuditAbstract;
import com.gomilkyway.profile.adari.utils.SanitizingUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Page extends AuditAbstract<String> {
    
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
        if (content.length() > 50) {
            cont = content.substring(0, 50) + "...";
        } else {
            cont = content;
        }

        return SanitizingUtil.stripAndOneLine(cont);
    }

}
