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

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.syncope.common.lib.to.AttrTO;

@XmlRootElement(name = "membershipUR")
@XmlType
public class MembershipUR extends AbstractPatch {

    private static final long serialVersionUID = -6783121761221554433L;

    public static class Builder extends AbstractPatch.Builder<MembershipUR, Builder> {

        public Builder(final String group) {
            super();
            getInstance().setGroup(group);
        }

        @Override
        protected MembershipUR newInstance() {
            return new MembershipUR();
        }

        public Builder plainAttr(final AttrTO plainAttr) {
            getInstance().getPlainAttrs().add(plainAttr);
            return this;
        }

        public Builder plainAttrs(final AttrTO... plainAttrs) {
            getInstance().getPlainAttrs().addAll(Arrays.asList(plainAttrs));
            return this;
        }

        public Builder plainAttrs(final Collection<AttrTO> plainAttrs) {
            getInstance().getPlainAttrs().addAll(plainAttrs);
            return this;
        }

        public Builder virAttr(final AttrTO virAttr) {
            getInstance().getVirAttrs().add(virAttr);
            return this;
        }

        public Builder virAttrs(final AttrTO... virAttrs) {
            getInstance().getVirAttrs().addAll(Arrays.asList(virAttrs));
            return this;
        }

        public Builder virAttrs(final Collection<AttrTO> virAttrs) {
            getInstance().getVirAttrs().addAll(virAttrs);
            return this;
        }
    }

    private String group;

    private final Set<AttrTO> plainAttrs = new HashSet<>();

    private final Set<AttrTO> virAttrs = new HashSet<>();

    public String getGroup() {
        return group;
    }

    public void setGroup(final String group) {
        this.group = group;
    }

    @XmlElementWrapper(name = "plainAttrs")
    @XmlElement(name = "attribute")
    @JsonProperty("plainAttrs")
    public Set<AttrTO> getPlainAttrs() {
        return plainAttrs;
    }

    @XmlElementWrapper(name = "virAttrs")
    @XmlElement(name = "attribute")
    @JsonProperty("virAttrs")
    public Set<AttrTO> getVirAttrs() {
        return virAttrs;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                appendSuper(super.hashCode()).
                append(group).
                append(plainAttrs).
                append(virAttrs).
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
        final MembershipUR other = (MembershipUR) obj;
        return new EqualsBuilder().
                appendSuper(super.equals(obj)).
                append(group, other.group).
                append(plainAttrs, other.plainAttrs).
                append(virAttrs, other.virAttrs).
                build();
    }
}
