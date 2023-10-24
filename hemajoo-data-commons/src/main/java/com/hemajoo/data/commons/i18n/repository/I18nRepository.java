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
package com.hemajoo.data.commons.i18n.repository;

import com.hemajoo.data.commons.i18n.model.I18n;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I18nRepository extends MongoRepository<I18n, String>
{
//    /**
//     * Find a {@link I18n} given its id.
//     * @param id Identifier.
//     * @return {@link I18n} if found, <b>null</b> otherwise.
//     */
//    I18n findById(final @NonNull String id);
}
