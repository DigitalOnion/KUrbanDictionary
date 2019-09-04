
package com.outerspace.kurbandictionary.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TermDefinitionList {

    @SerializedName("list")
    @Expose
    public java.util.List<TermDefinition> list = new ArrayList<TermDefinition>();

}
