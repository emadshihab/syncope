/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.common.lib.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.syncope.common.lib.to.AttrTO;

@XmlRootElement(name = "attrPatch")
@XmlType
public class AttrPatch extends AbstractPatch {

    private static final long serialVersionUID = 6881634224246176673L;

    public static class Builder extends AbstractPatch.Builder<AttrPatch, Builder> {

        public Builder(final AttrTO attrTO) {
            super();
            getInstance().setAttrTO(attrTO);
        }

        @Override
        protected AttrPatch newInstance() {
            return new AttrPatch();
        }
    }

    private AttrTO attrTO;

    public AttrTO getAttrTO() {
        return attrTO;
    }

    public void setAttrTO(final AttrTO attrTO) {
        this.attrTO = attrTO;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return attrTO == null || attrTO.getValues().isEmpty();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                appendSuper(super.hashCode()).
                append(attrTO).
                build();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AttrPatch other = (AttrPatch) obj;
        return new EqualsBuilder().
                appendSuper(super.equals(obj)).
                append(attrTO, other.attrTO).
                build();
    }
}
