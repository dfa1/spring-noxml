package com.humaorie.noxml.person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewPersonDto {

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
