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

/**
 * Enumeration exposing the possible values of the country <b>subdivision</b> types according to ISO 3166 2.
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum SubdivisionType
{
    /**
     * parish.
     */
    PARISH,

    /**
     * Division.
     */
    DIVISION,

    /**
     * <b>Metropolitan region</b>.
     */
    METROPOLITAN_REGION,

    /**
     * <b>Metropolitan department</b>.
     */
    METROPOLITAN_DEPARTMENT,

    /**
     * <b>Metropolitan collectivity with special status</b>.
     */
    METROPOLITAN_COLLECTIVITY_WITH_SPECIAL_STATUS,
}
