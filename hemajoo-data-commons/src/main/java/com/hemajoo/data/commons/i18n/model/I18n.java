/*
 * (C) Copyright Hemajoo Systems Inc. 2021-2023 - All Rights Reserved
 * -----------------------------------------------------------------------------------------------
 * All information contained herein is, and remains the property of
 * Hemajoo Inc. and its suppliers, if any. The intellectual and technical
 * concepts contained herein are proprietary to Hemajoo Systems Inc.
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents
 * in process, and are protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained from
 * Hemajoo Systems Inc.
 * -----------------------------------------------------------------------------------------------
 */
package com.hemajoo.data.commons.i18n.model;

import com.hemajoo.i18n.data.communication.LanguageType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "i18n")
public class I18n implements Serializable
{
    /**
     * Identifier (i18n primary key).
     */
    @Id
    @Getter
    private String id;

    /**
     * Translations.
     */
    @Getter
    @Setter
    private List<Translation> translations;

    @Builder(setterPrefix = "with")
    public I18n(final @NonNull String id)
    {
        this.id = id;
    }

    public static I18n of(final @NonNull String id)
    {
        return I18n.builder()
                .withId(id)
                .build();
    }

    public final void addTranslation(final @NonNull Translation translation)
    {
        translations.add(translation);
    }

    public Translation getTranslation(final @NonNull String language)
    {
        return translations.stream().filter(t -> t.getLanguage().equalsIgnoreCase(language)).findAny().orElse(null);
    }

    public String getForLanguage(LanguageType languageType)
    {
        Translation translation = translations.stream().filter(t -> t.getLanguage().equalsIgnoreCase(languageType.getLocale().getLanguage())).findAny().orElse(null);

        return translation != null ? translation.getValue() : null;
    }
}
