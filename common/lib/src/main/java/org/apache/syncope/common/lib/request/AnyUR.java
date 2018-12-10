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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.PathParam;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.syncope.common.lib.to.AttrTO;

@XmlType
@XmlSeeAlso({ UserUR.class, GroupUR.class, AnyObjectUR.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "@class")
@JsonPropertyOrder(value = { "@class", "key" })
@Schema(subTypes = { UserUR.class, GroupUR.class, AnyObjectUR.class }, discriminatorProperty = "@class")
public abstract class AnyUR implements Serializable, AttributableUR {

    private static final long serialVersionUID = -7445489774552440544L;

    protected abstract static class Builder<R extends AnyUR, B extends Builder<R, B>> {

        protected R instance;

        protected abstract R newInstance();

        protected R getInstance() {
            if (instance == null) {
                instance = newInstance();
            }
            return instance;
        }

        @SuppressWarnings("unchecked")
        public B key(final String key) {
            getInstance().setKey(key);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B realm(final StringReplacePatchItem realm) {
            getInstance().setRealm(realm);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B auxClass(final StringPatchItem auxClass) {
            getInstance().getAuxClasses().add(auxClass);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B auxClasses(final StringPatchItem... auxClasses) {
            getInstance().getAuxClasses().addAll(Arrays.asList(auxClasses));
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B auxClasses(final Collection<StringPatchItem> auxClasses) {
            getInstance().getAuxClasses().addAll(auxClasses);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B plainAttr(final AttrPatch plainAttr) {
            getInstance().getPlainAttrs().add(plainAttr);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B plainAttrs(final AttrPatch... plainAttrs) {
            getInstance().getPlainAttrs().addAll(Arrays.asList(plainAttrs));
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B plainAttrs(final Collection<AttrPatch> plainAttrs) {
            getInstance().getPlainAttrs().addAll(plainAttrs);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B virAttr(final AttrTO virAttr) {
            getInstance().getVirAttrs().add(virAttr);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B virAttrs(final Collection<AttrTO> virAttrs) {
            getInstance().getVirAttrs().addAll(virAttrs);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B virAttrs(final AttrTO... virAttrs) {
            getInstance().getVirAttrs().addAll(Arrays.asList(virAttrs));
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B resource(final StringPatchItem resource) {
            getInstance().getResources().add(resource);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B resources(final StringPatchItem... resources) {
            getInstance().getResources().addAll(Arrays.asList(resources));
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B resources(final Collection<StringPatchItem> resources) {
            getInstance().getResources().addAll(resources);
            return (B) this;
        }

        public R build() {
            return getInstance();
        }
    }

    @XmlTransient
    @JsonProperty("@class")
    private String discriminator;

    private String key;

    private StringReplacePatchItem realm;

    private final Set<StringPatchItem> auxClasses = new HashSet<>();

    private final Set<AttrPatch> plainAttrs = new HashSet<>();

    private final Set<AttrTO> virAttrs = new HashSet<>();

    private final Set<StringPatchItem> resources = new HashSet<>();

    @Schema(name = "@class", required = true)
    public abstract String getDiscriminator();

    public void setDiscriminator(final String discriminator) {
        // do nothing
    }

    @JsonProperty(required = true)
    @XmlElement(required = true)
    public String getKey() {
        return key;
    }

    @PathParam("key")
    public void setKey(final String key) {
        this.key = key;
    }

    public StringReplacePatchItem getRealm() {
        return realm;
    }

    public void setRealm(final StringReplacePatchItem realm) {
        this.realm = realm;
    }

    @XmlElementWrapper(name = "auxClasses")
    @XmlElement(name = "auxClass")
    @JsonProperty("auxClasses")
    public Set<StringPatchItem> getAuxClasses() {
        return auxClasses;
    }

    @XmlElementWrapper(name = "plainAttrs")
    @XmlElement(name = "attribute")
    @JsonProperty("plainAttrs")
    @Override
    public Set<AttrPatch> getPlainAttrs() {
        return plainAttrs;
    }

    @XmlElementWrapper(name = "virAttrs")
    @XmlElement(name = "attribute")
    @JsonProperty("virAttrs")
    @Override
    public Set<AttrTO> getVirAttrs() {
        return virAttrs;
    }

    @XmlElementWrapper(name = "resources")
    @XmlElement(name = "resource")
    @JsonProperty("resources")
    public Set<StringPatchItem> getResources() {
        return resources;
    }

    /**
     * @return true if no actual changes are defined
     */
    @JsonIgnore
    public boolean isEmpty() {
        return realm == null
                && auxClasses.isEmpty()
                && plainAttrs.isEmpty() && virAttrs.isEmpty()
                && resources.isEmpty();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(discriminator).
                append(key).
                append(realm).
                append(auxClasses).
                append(plainAttrs).
                append(virAttrs).
                append(resources).
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
        final AnyUR other = (AnyUR) obj;
        return new EqualsBuilder().
                append(discriminator, other.discriminator).
                append(key, other.key).
                append(realm, other.realm).
                append(auxClasses, other.auxClasses).
                append(plainAttrs, other.plainAttrs).
                append(virAttrs, other.virAttrs).
                append(resources, other.resources).
                build();
    }
}