/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package org.switchyard.common.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Resolves properties from multiple sources.
 *
 * @author David Ward &lt;<a href="mailto:dward@jboss.org">dward@jboss.org</a>&gt; &copy; 2012 Red Hat Inc.
 */
public class CompoundPropertyResolver implements PropertyResolver {

    private List<PropertyResolver> _resolvers = new ArrayList<PropertyResolver>();

    /**
     * Construction with an array of property resolvers.
     * @param resolvers the property resolvers
     */
    protected CompoundPropertyResolver(PropertyResolver... resolvers) {
        if (resolvers != null) {
            for (PropertyResolver resolver : resolvers) {
                addResolver(resolver);
            }
        }
    }

    /**
     * Construction with a collection of property resolvers.
     * @param resolvers the property resolvers
     */
    protected CompoundPropertyResolver(Collection<PropertyResolver> resolvers) {
        if (resolvers != null) {
            for (PropertyResolver resolver : resolvers) {
                addResolver(resolver);
            }
        }
    }

    private void addResolver(PropertyResolver resolver) {
        if (resolver != null && !_resolvers.contains(resolver) && resolver != this) {
            if (resolver instanceof CompoundPropertyResolver) {
                for (PropertyResolver pr : ((CompoundPropertyResolver)resolver)._resolvers) {
                    addResolver(pr);
                }
            } else {
                _resolvers.add(resolver);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object resolveProperty(String key) {
        Object value = null;
        if (key != null) {
            for (PropertyResolver resolver : _resolvers) {
                value = resolver.resolveProperty(key);
                if (value != null) {
                    break;
                }
            }
        }
        return value;
    }

    /**
     * Creates a compact property resolver given an array of property resolvers, weeding out nulls and only compounding when necessary.
     * @param resolvers the property resolvers
     * @return the always not-null compact property resolver
     */
    public static PropertyResolver compact(PropertyResolver... resolvers) {
        CompoundPropertyResolver compound = new CompoundPropertyResolver(resolvers);
        return compound._resolvers.size() == 1 ? compound._resolvers.get(0) : compound;
    }

    /**
     * Creates a compact property resolver given a collection of property resolvers, weeding out nulls and only compounding when necessary.
     * @param resolvers the property resolvers
     * @return the always not-null compact property resolver
     */
    public static PropertyResolver compact(Collection<PropertyResolver> resolvers) {
        CompoundPropertyResolver compound = new CompoundPropertyResolver(resolvers);
        return compound._resolvers.size() == 1 ? compound._resolvers.get(0) : compound;
    }

}
