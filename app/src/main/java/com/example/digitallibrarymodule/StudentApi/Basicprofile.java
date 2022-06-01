package com.example.digitallibrarymodule.StudentApi;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
public class Basicprofile {
    public int id;
    public String secondaryPhone;
    public String currentAddress;
    public String permanentAddress;
    public String emergencyContactName;
    public String emergencyPhone;
    public Object relationship;
    public String gender;
    public Object dob;
    public String bloodGroup;
    public Object documents;
}
