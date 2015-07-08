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
package org.orcid.core.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.orcid.core.adapter.Jpa2JaxbAdapter;
import org.orcid.core.manager.WorkManager;
import org.orcid.jaxb.model.message.Visibility;
import org.orcid.jaxb.model.record.summary.FundingSummary;
import org.orcid.persistence.dao.ProfileWorkDao;
import org.orcid.persistence.dao.WorkDao;
import org.orcid.persistence.jpa.entities.ProfileWorkEntity;
import org.orcid.persistence.jpa.entities.WorkEntity;
import org.orcid.persistence.jpa.entities.custom.MinimizedWorkEntity;
import org.springframework.cache.annotation.Cacheable;

public class WorkManagerImpl implements WorkManager {

    @Resource
    private WorkDao workDao;

    @Resource
    private ProfileWorkDao profileWorkDao;

    @Resource
    private Jpa2JaxbAdapter jpa2JaxbAdapter;

    /**
     * Add a new work to the work table
     * 
     * @param work
     *            The work that will be persited
     * @return the work already persisted on database
     * */
    public WorkEntity addWork(WorkEntity work) {
        return workDao.addWork(work);
    }

    /**
     * Edits an existing work
     * 
     * @param work
     *            The work to be edited
     * @return The updated entity
     * */
    public WorkEntity editWork(WorkEntity work) {
        return workDao.editWork(work);
    }

    /**
     * Find the works for a specific user
     * 
     * @param orcid
     *            the Id of the user
     * @return the list of works associated to the specific user
     * */
    @Cacheable(value = "works", key = "#orcid.concat('-').concat(#lastModified)")
    public List<MinimizedWorkEntity> findWorks(String orcid, long lastModified) {
        return workDao.findWorks(orcid);
    }

    /**
     * Find the public works for a specific user
     * 
     * @param orcid
     *            the Id of the user
     * @return the list of works associated to the specific user
     * */
    public List<MinimizedWorkEntity> findPublicWorks(String orcid) {
        return workDao.findPublicWorks(orcid);
    }

    /**
     * Updates the visibility of an existing work
     * 
     * @param workId
     *            The id of the work that will be updated
     * @param visibility
     *            The new visibility value for the profile work relationship
     * @return true if the relationship was updated
     * */
    public boolean updateVisibilities(String orcid, ArrayList<Long> workIds, Visibility visibility) {
        return workDao.updateVisibilities(orcid, workIds, visibility);
    }
    
    /**
     * Removes a work.
     * 
     * @param workId
     *            The id of the work that will be removed from the client
     *            profile
     * @param clientOrcid
     *            The client orcid
     * @return true if the work was deleted
     * */
    public boolean removeWorks(String clientOrcid, ArrayList<Long> workIds) {
        return workDao.removeWorks(clientOrcid, workIds);
    }
    
    /**
     * Sets the display index of the new work
     * @param orcid     
     *          The work owner
     * @param workId
     *          The work id
     * @return true if the work index was correctly set                  
     * */
    public boolean updateToMaxDisplay(String orcid, String workId) {
        ProfileWorkEntity profileWork = profileWorkDao.getProfileWork(orcid, workId);
        return workDao.updateToMaxDisplay(workId, profileWork.getDisplayIndex());
    }
    
    /**
     * Get the list of works that belongs to a user
     * 
     * @param userOrcid
     * @param lastModified
     *          Last modified date used to check the cache
     * @return the list of works that belongs to this user
     * */
    public List<FundingSummary> getWorksSummaryList(String userOrcid, long lastModified) {
        //TODO
        return null;
    }
}









































