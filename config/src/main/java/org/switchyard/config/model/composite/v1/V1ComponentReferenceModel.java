/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
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

package org.switchyard.config.model.composite.v1;

import java.util.Set;

import javax.xml.namespace.QName;

import org.switchyard.config.Configuration;
import org.switchyard.config.model.BaseNamedModel;
import org.switchyard.config.model.Descriptor;
import org.switchyard.config.model.composite.ComponentModel;
import org.switchyard.config.model.composite.ComponentReferenceModel;
import org.switchyard.config.model.composite.CompositeModel;
import org.switchyard.config.model.composite.InterfaceModel;

/**
 * A version 1 ComponentReferenceModel.
 *
 * @author David Ward &lt;<a href="mailto:dward@jboss.org">dward@jboss.org</a>&gt; (C) 2011 Red Hat Inc.
 */
public class V1ComponentReferenceModel extends BaseNamedModel implements ComponentReferenceModel {

    private InterfaceModel _interface;

    /**
     * Constructs a new V1ComponentReferenceModel.
     */
    public V1ComponentReferenceModel() {
        super(new QName(CompositeModel.DEFAULT_NAMESPACE, ComponentReferenceModel.REFERENCE));
    }

    /**
     * Constructs a new V1ComponentReferenceModel with the specified Configuration and Descriptor.
     * @param config the Configuration
     * @param desc the Descriptor
     */
    public V1ComponentReferenceModel(Configuration config, Descriptor desc) {
        super(config, desc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComponentModel getComponent() {
        return (ComponentModel)getModelParent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMultiplicity() {
        return getModelAttribute(ComponentReferenceModel.MULTIPLICITY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComponentReferenceModel setMultiplicity(String multiplicity) {
        setModelAttribute(ComponentReferenceModel.MULTIPLICITY, multiplicity);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfaceModel getInterface() {
        if (_interface == null) {
            _interface = (InterfaceModel)getFirstChildModelStartsWith(InterfaceModel.INTERFACE);
        }
        return _interface;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComponentReferenceModel setInterface(InterfaceModel interfaze) {
        setChildModel(interfaze);
        _interface = interfaze;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSecurity() {
        return getModelAttribute(ComponentReferenceModel.SECURITY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComponentReferenceModel setSecurity(String security) {
        setModelAttribute(ComponentReferenceModel.SECURITY, security);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPolicyRequirement(String policyName) {
        Set<String> requires = PolicyConfig.getRequires(this);
        requires.add(policyName);
        PolicyConfig.setRequires(this, requires);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getPolicyRequirements() {
        return PolicyConfig.getRequires(this);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPolicyRequirement(String policyName) {
        return PolicyConfig.getRequires(this).contains(policyName);
    }

}
