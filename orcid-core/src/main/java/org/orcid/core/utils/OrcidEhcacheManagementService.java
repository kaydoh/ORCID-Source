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
package org.orcid.core.utils;

import javax.annotation.Resource;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.hibernate.management.impl.EhcacheHibernateMbeanNames;
import net.sf.ehcache.management.ManagementService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Will Simpson
 * 
 */
public class OrcidEhcacheManagementService extends ManagementService {

    @Resource
    private MBeanServer mbeanServer;

    @Resource
    private CacheManager coreCacheManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrcidEhcacheManagementService.class);

    public OrcidEhcacheManagementService(CacheManager cacheManager, MBeanServer mBeanServer, boolean registerCacheManager, boolean registerCaches,
            boolean registerCacheConfigurations, boolean registerCacheStatistics) throws CacheException {
        super(cacheManager, mBeanServer, registerCacheManager, registerCaches, registerCacheConfigurations, registerCacheStatistics);
    }

    public OrcidEhcacheManagementService(CacheManager cacheManager, MBeanServer mBeanServer, boolean registerCacheManager, boolean registerCaches,
            boolean registerCacheConfigurations, boolean registerCacheStatistics, boolean registerCacheStores) throws CacheException {
        super(cacheManager, mBeanServer, registerCacheManager, registerCaches, registerCacheConfigurations, registerCacheStatistics, registerCacheStores);
    }

    @Override
    public void init() throws CacheException {
        try {
            mbeanServer.getObjectInstance(createObjectName(coreCacheManager));
        } catch (InstanceNotFoundException e) {
            super.init();
        }
    }

    static ObjectName createObjectName(net.sf.ehcache.CacheManager cacheManager) {
        ObjectName objectName;
        try {
            int hashCode = OrcidEhcacheManagementService.class.getClassLoader().hashCode();
            String suffix = "_" + hashCode;
            String safeCacheManagerName = EhcacheHibernateMbeanNames.mbeanSafe(cacheManager.getName() + suffix);
            LOGGER.info("Cache manager name = {}", safeCacheManagerName);
            cacheManager.setName(safeCacheManagerName);
            objectName = new ObjectName("net.sf.ehcache:type=CacheManager,name=" + safeCacheManagerName);
        } catch (MalformedObjectNameException e) {
            throw new CacheException(e);
        }
        return objectName;
    }

}
