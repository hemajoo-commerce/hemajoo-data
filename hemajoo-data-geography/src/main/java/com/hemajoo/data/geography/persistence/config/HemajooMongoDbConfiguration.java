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
package com.hemajoo.data.geography.persistence.config;

import com.hemajoo.data.commons.i18n.repository.I18nRepository;
import com.hemajoo.data.commons.i18n.service.I18nService;
import com.hemajoo.data.geography.persistence.repository.CountryRepository;
import com.hemajoo.data.geography.persistence.service.CountryService;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

/**
 * <b>Hemajoo - MongoDB</b> unit test configuration for <b>Data Country</b>.
 * @author <a href="mailto:christophe.resse@kyndryl.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = { I18nRepository.class, CountryRepository.class })
@ComponentScan(basePackageClasses = { I18nService.class, CountryService.class } )
public class HemajooMongoDbConfiguration extends AbstractMongoClientConfiguration
{
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Value("${spring.data.mongodb.authentication-database}")
    private String mongoAuthenticationDatabase;

    @Value("${spring.data.mongodb.username}")
    private String mongoUsername;

    @Value("${spring.data.mongodb.password}")
    private String mongoPassword;

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Bean
    public MongoTemplate mongoTemplate()
    {
        return new MongoTemplate(mongo(), mongoDatabase);
    }

    @Bean
    public MongoClient mongo()
    {
        MongoCredential credential = MongoCredential.createCredential(mongoUsername, mongoAuthenticationDatabase, mongoPassword.toCharArray());

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(List.of(new ServerAddress(mongoHost, Integer.parseInt(mongoPort)))))
                .credential(credential)
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public MappingMongoConverter mongoConverter() throws Exception
    {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mongoMappingContext(customConversions(), mongoManagedTypes()));

        mongoConverter.setMapKeyDotReplacement("_");
        mongoConverter.afterPropertiesSet();

        return mongoConverter;
    }

    /**
     * Create a new {@link HemajooMongoDbConfiguration} instance.
     */
    public HemajooMongoDbConfiguration()
    {
        // Empty!
    }

    @Override
    protected String getDatabaseName()
    {
        return mongoDatabase;
    }

    @Override
    public MongoCustomConversions customConversions()
    {
        return new MongoCustomConversions(
                List.of(
//                        DateToZonedDateTimeConverter.INSTANCE,
//                        ZonedDateTimeToDateConverter.INSTANCE
                ));
    }
}
