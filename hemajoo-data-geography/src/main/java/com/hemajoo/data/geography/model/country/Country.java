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
package com.hemajoo.data.geography.model.country;

import com.hemajoo.data.geography.model.country.subdivision.Subdivision;
import com.hemajoo.data.geography.model.country.subdivision.SubdivisionType;
import com.hemajoo.i18n.data.geography.country.CountryType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Document(collection = "country")
public class Country implements Serializable
{
    /**
     * Country unique identifier (ISO 3166 Alpha3 code).
     */
    @Id
    @Getter
    private String id;

    /**
     * Country name.
     */
    @Getter
    @Setter
    @Indexed
    private String name;

    /**
     * Country ISO Alpha-2 code.
     */
    @Getter
    @Setter
    @Indexed
    private String isoAlpha2Code;

    /**
     * Country numeric code.
     */
    @Getter
    @Setter
    @Indexed
    private int numericCode;

    /**
     * Country official name.
     */
    @Getter
    @Setter
    private String officialName;

    /**
     * Country subdivision code.
     */
    @Getter
    @Setter
    private String subdivisionCode;

    /**
     * Country top-level domain code (ccTld).
     */
    @Getter
    @Setter
    private String topLevelDomainCode;

    /**
     * Country sovereignty.
     */
    @Getter
    @Setter
    private String sovereignty;

    /**
     * Demonym(s).
     */
    @Getter
    @Setter
    private String demonym;

    /**
     * Calling code.
     */
    @Getter
    @Setter
    private String callingCode;

    /**
     * Coordinates.
     */
    @Getter
    @Setter
    private String coordinates;

    /**
     * Driving mode.
     */
    @Getter
    @Setter
    private String drivingMode;

    /**
     * Motto identifier.
     */
    @Getter
    @Setter
    private String motto;

    /**
     * Anthem identifier.
     */
    @Getter
    @Setter
    private String anthem;

    /**
     * Subdivisions.
     */
    @Setter
    private HashMap<String, Subdivision> subdivisions = new HashMap<>();

    /**
     * Country type.
     */
    @Getter
    @Transient
    private CountryType type;

    @Builder(setterPrefix = "with")
    public Country(final String name, final String isoAlpha2Code, final String officialName, final Integer numericCode, final String sovereignty, final String subdivisionCode, final String topLevelDomainCode, final String demonym, final String callingCode, final String drivingMode, final String coordinates, final String motto, final String anthem)
    {
        this.name = name;
        this.isoAlpha2Code = isoAlpha2Code;
        this.numericCode = numericCode != null ? numericCode : 0;
        this.officialName = officialName;
        this.sovereignty = sovereignty;
        this.subdivisionCode = subdivisionCode;
        this.topLevelDomainCode = topLevelDomainCode;
        this.demonym = demonym;
        this.callingCode = callingCode;
        this.drivingMode = drivingMode;
        this.coordinates = coordinates;
        this.motto = motto;
        this.anthem = anthem;
    }

    /**
     * Return the subdivision given its key.
     * @param key Subdivision key.
     * @return {@link Subdivision} if found, <b>null</b> otherwise.
     */
    public Subdivision getSubdivision(final @NonNull String key)
    {
        return subdivisions.get(key);
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

    /**
     * Return if the subdivision exist given its key.
     * @param key Subdivision key.
     * @return <b>True</b> if the subdivision exist, <b>false</b> otherwise.
     */
    public final boolean existSubdivision(final @NonNull String key)
    {
        return subdivisions.values().stream().anyMatch(e -> e.getKey().equalsIgnoreCase(key));
    }

    /**
     * Return the number of subdivisions (first level only).
     * @return Number of subdivisions.
     */
    public final long countSubdivision()
    {
        return subdivisions.values().size();
    }

    /**
     * Return the number of subdivisions of the given type.
     * @param type Subdivision type.
     * @return Number of subdivisions.
     */
    public final long countSubdivision(final @NonNull SubdivisionType type)
    {
        long count = 0;

        for (Subdivision subdivision : subdivisions.values())
        {
            if (subdivision.getType() == type)
            {
                count += 1;
            }

            for (Subdivision sub :subdivision.getSubdivisions().values())
            {
                count += sub.countSubdivision(type);
            }
        }

        return count;
    }

    /**
     * Add a subdivision.
     * @param subdivision Subdivision to add.
     */
    public void addSubdivision(final @NonNull Subdivision subdivision)
    {
        subdivisions.put(subdivision.getKey(), subdivision);
    }

    /**
     * Delete a subdivision given its key.
     * @param key Subdivision key to delete.
     */
    public void deleteSubdivision(final @NonNull String key)
    {
        subdivisions.remove(key);
    }
}
