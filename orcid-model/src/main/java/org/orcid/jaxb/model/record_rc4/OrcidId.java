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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.01.18 at 10:32:14 AM GMT 
//

package org.orcid.jaxb.model.record_rc4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.orcid.org/ns/orcid-id>orcid">
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "value" })
@XmlRootElement(name = "orcid-id", namespace = "http://www.orcid.org/ns/orcid-id")
public class OrcidId implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlValue
    protected String value;

    public OrcidId() {
    }
    
    public OrcidId(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        OrcidId orcid = (OrcidId) o;

        if (value != null ? !value.equals(orcid.value) : orcid.value != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
