package com.minlia.cloud.framework.common.persistence.filter;

import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * User: DOOM
 * Date: 14.02.13
 * Time: 19:51
 */
public class SpecificationContext {

    public Map<String, Path> relationPathCache = new HashMap<String, Path>();
    public Map<String, From> relationJoinCache = new HashMap<String, From>();

}
