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
package com.hemajoo.data.geography.persistence.repository;

import com.hemajoo.data.geography.model.country.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends MongoRepository<Country, String>
{
    /**
     * Find a country given its ISO alpha 2 country code.
     * @param isoAlpha2Code ISO alpha 2 country code.
     * @return {@link Country} if found, <b>null</b> otherwise.
     */
    Country findByIsoAlpha2Code(final String isoAlpha2Code);

    /**
     * Find a country given its name.
     * @param name Country name.
     * @return {@link Country} if found, <b>null</b> otherwise.
     */
    Country findByName(final String name);

    /**
     * Find a country given its numeric code.
     * @param numericCode Country numeric code.
     * @return {@link Country} if found, <b>null</b> otherwise.
     */
    Country findByNumericCode(final int numericCode);
}
