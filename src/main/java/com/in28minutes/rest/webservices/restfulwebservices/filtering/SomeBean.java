package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//@JsonIgnoreProperties("field1")
@JsonFilter("SomeBeanFilter")

public class SomeBean {
    @JsonProperty
    private String field1;
    @JsonProperty
//    @JsonIgnore

    private String field2;
    @JsonProperty

    private String field3;

    public SomeBean(final String field1, final String field2, final String field3) {
        super();
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;

    }

    @Override
    public String toString() {
        return "SomeBean{" +
            "value1='" + field1 + '\'' +
            ", value2='" + field2 + '\'' +
            ", value3='" + field3 + '\'' +
            '}';
    }

    public void setField1(final String field1) {
        this.field1 = field1;
    }

    public void setField2(final String field2) {
        this.field2 = field2;
    }

    public void setField3(final String field3) {
        this.field3 = field3;
    }


}
