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
package com.hemajoo.data.geography.persistence.service;

import com.hemajoo.data.geography.model.country.Country;
import com.hemajoo.data.geography.persistence.repository.CountryRepository;
import com.hemajoo.i18n.data.geography.country.CountryType;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service used to manage {@link Country} entity.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Service
public class CountryService
{
    /**
     * <b>JPA</b> repository for the country.
     */
    @Autowired
    private CountryRepository countryRepository;

    /**
     * <b>JPA</b> repository for the time-series.
     */
//    @Autowired
//    private TimeSeriesRepository timeSeriesRepository;

    /**
     * Return a country given its type.
     * @param type Country type.
     * @return {@link Country} if found, <b>null</b> otherwise.
     */
    public Country findByType(final @NonNull CountryType type)
    {
        return countryRepository.findByIsoAlpha2Code(type.getIsoAlpha2());
    }

    /**
     * Return a country given its ISO alpha 2 code.
     * @param isoAlpha2Code ISO alpha 2 country code.
     * @return {@link Country} if found, <b>null</b> otherwise.
     */
    public Country findByIsoAlpha2Code(final String isoAlpha2Code)
    {
        return countryRepository.findByIsoAlpha2Code(isoAlpha2Code);
    }

    /**
     * Return a country given its ISO alpha 3 code.
     * @param isoAlpha3Code ISO alpha 3 country code.
     * @return {@link Country} if found, <b>null</b> otherwise.
     */
    public Country findById(final String isoAlpha3Code)
    {
        return countryRepository.findById(isoAlpha3Code).orElse(null);
    }

    /**
     * Return a country given its name.
     * @param name Country name.
     * @return {@link Country} if found, <b>null</b> otherwise.
     */
    public Country findByName(final String name)
    {
        return countryRepository.findByName(name);
    }

    /**
     * Return a country given its numeric code.
     * @param numericCode Country numeric code.
     * @return {@link Country} if found, <b>null</b> otherwise.
     */
    public Country findByNumericCode(final int numericCode)
    {
        return countryRepository.findByNumericCode(numericCode);
    }

    /**
     * Save a country.
     * @param country Country to save.
     * @return Saved country.
     */
    public Country saveCountry(final @NonNull Country country)
    {
        return countryRepository.save(country);
    }

    /**
     * Return a list of all countries.
     * @return List of countries.
     */
    public List<Country> findAll()
    {
        return countryRepository.findAll();
    }

    /**
     * Delete all countries.
     */
    public void deleteAll()
    {
        countryRepository.deleteAll();
    }
}
