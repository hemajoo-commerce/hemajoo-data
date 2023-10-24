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
package com.hemajoo.data.geography.test.country;

import com.hemajoo.commons.test.unit.AbstractHemajooUnitTest;
import com.hemajoo.data.commons.i18n.model.I18n;
import com.hemajoo.data.commons.i18n.service.I18nService;
import com.hemajoo.data.geography.model.country.Country;
import com.hemajoo.data.geography.persistence.config.HemajooMongoDbConfiguration;
import com.hemajoo.data.geography.persistence.service.CountryService;
import com.hemajoo.i18n.core.localization.LocalizationException;
import com.hemajoo.i18n.data.communication.LanguageType;
import com.hemajoo.i18n.data.geography.country.CountryType;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HemajooMongoDbConfiguration.class)
class CountryUnitTest extends AbstractHemajooUnitTest
{
    @Autowired
    private CountryService countryService;

    @Autowired
    private I18nService i18nService;

//    @Autowired
//    private TimeSeriesService timeSeriesService;

//    @BeforeEach
//    public void setUp() throws CountryException
//    {
//        //countryService.deleteAllCountries();
//    }

    @Test
    @DisplayName("Find a country given its ISO 3166 Alpha2 code")
    void findCountryByIsoAlpha2Code() throws LocalizationException
    {
        Country country = countryService.findByIsoAlpha2Code(CountryType.ARGENTINA.getIsoAlpha2());

        I18n i18n = i18nService.findById(country.getMotto());

        String motto = i18nService.findById(country.getMotto(), LanguageType.GERMAN);

        Assertions.assertThat(country).isNotNull();
        Assertions.assertThat(country.getIsoAlpha2Code()).isEqualTo(CountryType.ARGENTINA.getIsoAlpha2());
        Assertions.assertThat(country.getName()).isEqualTo(CountryType.ARGENTINA.getName());
    }

    @Test
    @DisplayName("Find a country given its ISO 3166 Alpha3 code")
    void findCountryByIsoAlpha3Code() throws LocalizationException
    {
        Country country = countryService.findById(CountryType.FRANCE.getIsoAlpha3());

        assertThat(country).isNotNull();
        assertThat(country.getIsoAlpha2Code()).isEqualTo(CountryType.FRANCE.getIsoAlpha2());
        assertThat(country.getId()).isEqualTo(CountryType.FRANCE.getIsoAlpha3());
        assertThat(country.getName()).isEqualTo(CountryType.FRANCE.getName());
    }

//    @DisplayName("Fetch a country from database using its country type")
//    @Test
//    void findCountryByType() throws LocalizationException
//    {
//        CountryType countryType = CountryType.AUSTRIA;
//
//        Country country = countryService.findByType(countryType);
//
//        assertThat(country).isNotNull();
//        assertThat(country.getIsoAlpha2Code()).isEqualTo(countryType.getIsoAlpha2());
//        assertThat(country.getIsoAlpha3Code()).isEqualTo(countryType.getIsoAlpha3());
//        assertThat(country.getName()).isEqualTo(countryType.getName());
//    }
//
//    @DisplayName("Fetch a country from database using its name")
//    @Test
//    void findCountryByName() throws LocalizationException
//    {
//        CountryType countryType = CountryType.BOSNIA_AND_HERZEGOVINA;
//
//        Country country = countryService.findByName(countryType.getName());
//
//        assertThat(country).isNotNull();
//        assertThat(country.getIsoAlpha2Code()).isEqualTo(countryType.getIsoAlpha2());
//        assertThat(country.getIsoAlpha3Code()).isEqualTo(countryType.getIsoAlpha3());
//        assertThat(country.getName()).isEqualTo(countryType.getName());
//    }
//
//    @DisplayName("Fetch a country from database using its numeric code")
//    @Test
//    void findCountryByNumericCode() throws LocalizationException
//    {
//        CountryType countryType = CountryType.BOSNIA_AND_HERZEGOVINA;
//
//        Country country = countryService.findByNumericCode(70);
//
//        assertThat(country).isNotNull();
//        assertThat(country.getNumericCode()).isEqualTo(70);
//        assertThat(country.getIsoAlpha2Code()).isEqualTo(countryType.getIsoAlpha2());
//        assertThat(country.getIsoAlpha3Code()).isEqualTo(countryType.getIsoAlpha3());
//        assertThat(country.getName()).isEqualTo(countryType.getName());
//    }
//
//    @DisplayName("Create a country if it does not exist")
//    @Test
//    void createCountry() throws CountryException
//    {
//        Country country = countryService.findByType(CountryType.FRANCE);
//        if (country == null)
//        {
//            country = Country.builder()
//                    .withIsoAlpha2Code("FR")
//                    .withIsoAlpha3Code("FRA")
//                    .withOfficialName("France")
//                    .withNumericCode(250)
//                    .withTopLevelDomainCode(".fr")
//                    .withSovereignty("United nations")
//                    .withName("France")
//                    .build();
//
//            countryService.saveCountry(country);
//        }
//
//        assertThat(country).isNotNull();
//    }
//
//    @Test
//    void createCountryTimeSeries() throws TimeSeriesException
//    {
//        TimeSeries series = TimeSeries.builder().
//                withValueType(MetricValueType.INTEGER)
//                .withUnitType(MetricUnitType.PEOPLE)
//                .withTimeSeriesKey(TimeSeriesKey.builder()
//                        .withCollectionType(CollectionType.COUNTRIES)
//                        .withCollectionKey(CountryType.FRANCE.getIsoAlpha3())
//                        .withMetricType(MetricType.POPULATION_TOTAL)
//                        .build())
//                .build();
//
//        series.add(Metric.builder()
//                .withYear(2023)
//                .withValue(67588900)
//                .withSourceType(MetricSourceType.INSEE)
//                .build());
//
//        series = timeSeriesService.save(series);
//
//        assertThat(series).isNotNull();
//    }
//
//    @Test
//    void updateCountryTimeSeries() throws TimeSeriesException
//    {
//        TimeSeries series = timeSeriesService.findByKey("COUNTRIES:FRA:POPULATION_TOTAL");
//        assertThat(series).isNotNull();
//        assertThat(series.getTimeSeriesKey().getCollectionType()).isEqualTo(CollectionType.COUNTRIES);
//        assertThat(series.getTimeSeriesKey().getCollectionKey()).isEqualTo(CountryType.FRANCE.getIsoAlpha3());
//        assertThat(series.getTimeSeriesKey().getMetricType()).isEqualTo(MetricType.POPULATION_TOTAL);
//
//        series.add(Metric.builder()
//                .withYear(2022)
//                .withValue(65547600)
//                .build());
//        series.add(Metric.builder()
//                .withYear(2021)
//                .withValue(64213780)
//                .build());
//
//        series = timeSeriesService.save(series);
//        assertThat(series).isNotNull();
//    }
//
//    @Test
//    void getCountrySubdivision()
//    {
//        Country country = countryService.findByType(CountryType.FRANCE);
//        assertThat(country).isNotNull();
//
//        long count = country.countSubdivision();
//        assertThat(count).isPositive();
//
//        long countMetropolitanDepartment = country.countSubdivision(SubdivisionType.METROPOLITAN_DEPARTMENT);
//        assertThat(count).isPositive();
//
//        List<Subdivision> list = country.findAllSubdivisionByType(SubdivisionType.METROPOLITAN_DEPARTMENT);
//        assertThat(list).isNotEmpty();
//    }
//
//    @Test
//    void addCountrySubdivision()
//    {
//        Country country = countryService.findByType(CountryType.ANDORRA);
//        assertThat(country).isNotNull();
//
//        Subdivision subdivision = Subdivision.builder()
//                .withKey("FR-ARA")
//                .withType(SubdivisionType.METROPOLITAN_REGION)
//                .withParent(CountryType.FRANCE.getIsoAlpha2())
//                .withName("Auvergne-Rhône-Alpes")
//                .build();
//
//        subdivision.add(Subdivision.builder()
//                .withKey("FR-01")
//                .withType(SubdivisionType.METROPOLITAN_DEPARTMENT)
//                .withName("Ain")
//                .build());
//        subdivision.add(Subdivision.builder()
//                .withKey("FR-03")
//                .withType(SubdivisionType.METROPOLITAN_DEPARTMENT)
//                .withName("Allier")
//                .build());
//
//        country.addSubdivision(subdivision);
//
//        country = countryService.saveCountry(country);
//        assertThat(country).isNotNull();
//    }
//
//    @Test
//    void deleteAll()
//    {
//        countryService.deleteAll();
//        assertThat(countryService.findAll()).isEmpty();
//    }

//    @Disabled
//    @Test
//    void updateObjectIds() throws LocalizationException
//    {
//        int i = 1;
//
//        for (Country country : countryService.findAll())
//        {
//            country.setAnthem("ObjectId('i18n.country:" + country.getId() + ".anthem')");
//            country.setMotto("ObjectId('i18n.country:" + country.getId() + ".motto')");
//
//            countryService.saveCountry(country);
//            System.out.println("Updated country #" + i++ + " - " + country.getId());
//        }
//    }
}
