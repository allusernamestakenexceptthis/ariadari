/*
 * Copyright 2023 Ari Adari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gomilkyway.profile.adari.files;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.gomilkyway.profile.adari.audit.AuditAbstract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * File database entity to store file information.
 * The purpose is to associate file hashes with file names.
 * without storing the file itself.
 * 
 * @author Ari Adari
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Filedb extends AuditAbstract<String> {
    
    /**
     * The table id of the file record.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    /**
     * (Required) The file name that will be visible to users.
     * Must be url encoded and unique
     */
    @NotBlank
    @Column(unique=true)
    private String name;

    /**
     * (Required) Content type of the file. such as image/png
     */
    @NotBlank
    private String type;

    /**
     * (Required) The hash of the file to be stored on server.
     */
    @NotBlank
    private String serverFileName;

}
