/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2014 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
package org.orcid.persistence.dao.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.orcid.persistence.dao.StatisticsGeneratorDao;

public class StatisticsGeneratorDaoImpl implements StatisticsGeneratorDao {

    @PersistenceContext(unitName = "orcid")
    protected EntityManager entityManager;

    public long getLiveIds() {
        Query query = entityManager.createNativeQuery("select count(*) from profile where profile_deactivation_date is null and record_locked = false");
        BigInteger numberOfLiveIds = (BigInteger) query.getSingleResult();
        return numberOfLiveIds.longValue();
    }

    public long getAccountsWithVerifiedEmails() {
        Query query = entityManager
                .createNativeQuery("select count(distinct profile.orcid) from email join profile on profile.profile_deactivation_date is null and email.is_verified=true and email.orcid=profile.orcid and profile.record_locked = false");
        BigInteger numberOfLiveIdsWithVerifiedEmail = (BigInteger) query.getSingleResult();
        return numberOfLiveIdsWithVerifiedEmail.longValue();
    }

    public long getAccountsWithWorks() {
        Query query = entityManager.createNativeQuery("select count (distinct orcid) from profile_work");
        BigInteger numberOfAccountsWithWorks = (BigInteger) query.getSingleResult();
        return numberOfAccountsWithWorks.longValue();
    }

    public long getNumberOfWorks() {
        Query query = entityManager.createNativeQuery("select count(*) from profile_work");
        BigInteger numberOfWorks = (BigInteger) query.getSingleResult();
        return numberOfWorks.longValue();
    }

    public long getNumberOfUniqueDOIs() {
        Query query = entityManager.createNativeQuery("SELECT COUNT(DISTINCT j->'workExternalIdentifierId'->>'content') FROM (SELECT json_array_elements(json_extract_path(external_ids_json, 'workExternalIdentifier')) AS j FROM work) AS a WHERE j->>'workExternalIdentifierType' = 'DOI'");
        BigInteger numberOfWorksWithDOIs = (BigInteger) query.getSingleResult();
        return numberOfWorksWithDOIs.longValue();
    }
    
    public long getNumberOfLockedRecords() {
        Query query = entityManager.createNativeQuery("select count(*) from profile where record_locked = true");
        BigInteger numberOfLockedRecords = (BigInteger) query.getSingleResult();
        return numberOfLockedRecords.longValue();
    }
}
