/*
 * Copyright 2015-2022 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project.geocoding;

import java.util.Objects;

public class Address {

	private String road;
	private String state;
	private String city;
	private String zip;
	private String houseNumber;

	public Address(String road, String state, String city, String zip, String houseNumber) {
		this.road = road;
		this.state = state;
		this.city = city;
		this.zip = zip;
		this.houseNumber = houseNumber;
	}

	@Override
    public String toString() {
        return "{" +
            " road='" + road() + "'" +
            ", state='" + state() + "'" +
            ", city='" + city() + "'" +
            ", zip='" + zip() + "'" +
            ", houseNumber='" + houseNumber() + "'" +
            "}";
    }

	@Override
    public int hashCode() {
        return Objects.hash(road, state, city, zip, houseNumber);
    }

	@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Address)) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(road, address.road) && Objects.equals(state, address.state) && Objects.equals(city, address.city) && Objects.equals(zip, address.zip) && Objects.equals(houseNumber, address.houseNumber);
    }

	public String road() {
		return road;
	}

	public String state() {
		return state;
	}

	public String city() {
		return city;
	}

	public String zip() {
		return zip;
	}

	public String houseNumber() {
		return houseNumber;
	}
	
}
