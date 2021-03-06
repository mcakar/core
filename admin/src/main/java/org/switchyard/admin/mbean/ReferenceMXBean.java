/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
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

package org.switchyard.admin.mbean;

import java.util.List;

/**
 * Management interface for a composite reference consumed by a SwitchYard application.
 */
public interface ReferenceMXBean extends MessageMetricsMXBean {

    /**
     * Reference name.
     * @return the name of this reference.
     */
    String getName();

    /**
     * The component reference that this composite reference promotes.
     * @return the component reference promoted by this reference.
     */
    String getPromotedReference();

    /**
     * List of managed bindings used by this reference.
     * @return management interfaces for gateway bindings on this reference.
     */
    List<BindingMXBean> getBindings();

    /**
     * The composite reference interface.
     * @return the interface used by this reference.
     */
    String getInterface();

    /**
     * The application which uses this reference.
     * @return the application which exports this reference.
     */
    ApplicationMXBean getApplication();
}
