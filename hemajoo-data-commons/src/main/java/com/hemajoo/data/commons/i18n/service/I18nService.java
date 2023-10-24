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
package com.hemajoo.data.commons.i18n.service;

import com.hemajoo.data.commons.i18n.model.I18n;
import com.hemajoo.data.commons.i18n.repository.I18nRepository;
import com.hemajoo.i18n.data.communication.LanguageType;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service used to manage {@link I18n} entities.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@Service
public class I18nService
{
    /**
     * <b>JPA</b> repository for the {@link I18n} entities.
     */
    @Autowired
    private I18nRepository i18nRepository;

    /**
     * Find a {@link I18n} entity.
     * @param entity Entity to find.
     * @return {@link I18n} if found, <b>null</b> otherwise.
     */
    public I18n find(final @NonNull I18n entity)
    {
        return i18nRepository.findById(entity.getId()).orElse(null);
    }

    /**
     * Find a {@link I18n} entity given its identifier.
     * @param id Identifier.
     * @return {@link I18n} if found, <b>null</b> otherwise.
     */
    public I18n findById(final @NonNull String id)
    {
        return i18nRepository.findById(id).orElse(null);
    }

    /**
     * Find a {@link I18n} entity given its identifier.
     * @param id Identifier.
     * @return {@link I18n} if found, <b>null</b> otherwise.
     */
    public String findById(final @NonNull String id, LanguageType languageType)
    {
        I18n i18n = i18nRepository.findById(id).orElse(null);
        return i18n.getForLanguage(languageType);
    }

    /**
     * Save a {@link I18n} entity.
     * @param entity Entity to save.
     * @return Saved entity.
     */
    public I18n save(final @NonNull I18n entity)
    {
        return i18nRepository.save(entity);
    }

    /**
     * Return a list of all {@link I18n} entities.
     * @return List of entities.
     */
    public List<I18n> findAll()
    {
        return i18nRepository.findAll();
    }

    /**
     * Delete all {@link I18n} entities.
     */
    public void deleteAll()
    {
        i18nRepository.deleteAll();
    }
}
