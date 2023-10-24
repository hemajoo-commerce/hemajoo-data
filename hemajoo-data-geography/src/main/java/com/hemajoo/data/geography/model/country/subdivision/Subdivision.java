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
package com.hemajoo.data.geography.model.country.subdivision;

import com.hemajoo.i18n.core.annotation.I18n;
import com.hemajoo.i18n.core.localization.I18nManager;
import com.hemajoo.i18n.core.localization.LocalizationException;
import com.hemajoo.i18n.data.calendar.MonthType;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class Subdivision implements Serializable
{
    /**
     * Represents the resource bundle pathname.
     */
    private static final String BUNDLE_NAME = "i18n/geography/subdivision";

    /**
     * Represents the generic path to access the 'description' property.
     */
    private static final String I18N_SUBDIVISION_PROPERTY_DESCRIPTION = "subdivision.${key}.${name}.description";

    /**
     * Subdivision key (according to ISO 3166-2 subdivision codes).
     */
    @Getter
    private String key;

    @Getter
    private String parent;

    /**
     * Subdivision type.
     */
    @Getter
    private SubdivisionType type;

    /**
     * Subdivision name.
     */
    @Getter
    private String name;

    /**
     * Subdivision description.
     */
    @Transient
    private String description;

    @Getter
    private LocalDateTime createdAt;

    @Getter
    private boolean isConnected;

    @Getter
    private String uuid;

    /**
     * Subdivisions.
     */
    @Getter
    @Setter
    private Map<String, Subdivision> subdivisions = new HashMap<>();
//    private final List<Subdivision> subdivisions = new ArrayList<>();

    @Builder(setterPrefix = "with")
    public Subdivision(final String key, final String parent, final String name, final SubdivisionType type)
    {
        this.name = name;
        this.key = key;
        this.parent = parent;
        this.type = type;
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
        this.isConnected = false;
        this.uuid = UUID.randomUUID().toString();
    }

    /**
     * Returns the localized subdivision description in the current locale.
     * @return Localized subdivision description.
     * @throws LocalizationException Thrown to indicate an error occurred while trying to localize a resource.
     */
    @I18n(bundle = BUNDLE_NAME, key = I18N_SUBDIVISION_PROPERTY_DESCRIPTION)
    public static String getDescription() throws LocalizationException
    {
        return I18nManager.getInstance().localize(MonthType.class, I18nManager.getInstance().getLocale());
    }

    /**
     * Returns the localized subdivision description in the given locale.
     * @param locale Locale to use.
     * @return Subdivision description.
     * @throws LocalizationException Thrown to indicate an error occurred while trying to localize a resource.
     */
    @I18n(bundle = BUNDLE_NAME, key = I18N_SUBDIVISION_PROPERTY_DESCRIPTION)
    public static String getDescription(final @NonNull Locale locale) throws LocalizationException
    {
        return I18nManager.getInstance().localize(MonthType.class, locale);
    }

    /**
     * Return a list of subdivisions matching the given type.
     * @param type Subdivision type.
     * @return List of subdivisions matching the given type, otherwise an empty list is returned.
     */
    public List<Subdivision> findAllSubdivisionByType(final @NonNull SubdivisionType type)
    {
        List<Subdivision> result = new ArrayList<>();

        for (Subdivision subdivision : subdivisions.values())
        {
            if (subdivision.getType() == type)
            {
                result.add(subdivision);
            }

            result.addAll(subdivision.findAllSubdivisionByType(type));
        }

        return result;
    }

    public void add(final @NonNull Subdivision subdivision)
    {
        subdivisions.put(subdivision.getKey(), subdivision);
    }

    public Subdivision get(final @NonNull String key)
    {
        return subdivisions.get(key);
    }

    /**
     * Return the number of subdivisions of the given type.
     * @param type Subdivision type.
     * @return Number of subdivisions.
     */
    public final long countSubdivision(final @NonNull SubdivisionType type)
    {
        long count = 0;

        if (getType() == type)
        {
            count += 1;
        }

        for (Subdivision subdivision : getSubdivisions().values())
        {
            count += subdivision.countSubdivision(type);
        }

        return count;
    }
}


