package com.labcorp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class User {
        public int createdAt;
        public String employee_firstname;
        public String employee_lastname;
        public String employee_phonenumbe;
        public String ademployee_emaildress;
        public String citemployee_addressy;
        public String stateemployee_dev_level;
        public String employee_gender;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        public Date employee_hire_date;
        public boolean employee_onleave;
        public List<String> tech_stack;
        public List<String> project;
        public String id;

        public User(int createdAt, String employee_firstname, String employee_lastname, String employee_phonenumbe, String ademployee_emaildress, String citemployee_addressy, String stateemployee_dev_level, String employee_gender, Date employee_hire_date, boolean employee_onleave, List<String> tech_stack, List<String> project) {
                this.createdAt = createdAt;
                this.employee_firstname = employee_firstname;
                this.employee_lastname = employee_lastname;
                this.employee_phonenumbe = employee_phonenumbe;
                this.ademployee_emaildress = ademployee_emaildress;
                this.citemployee_addressy = citemployee_addressy;
                this.stateemployee_dev_level = stateemployee_dev_level;
                this.employee_gender = employee_gender;
                this.employee_hire_date = employee_hire_date;
                this.employee_onleave = employee_onleave;
                this.tech_stack = tech_stack;
                this.project = project;
        }

        public User() {
        }

        public int getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(int createdAt) {
                this.createdAt = createdAt;
        }

        public String getEmployee_firstname() {
                return employee_firstname;
        }

        public void setEmployee_firstname(String employee_firstname) {
                this.employee_firstname = employee_firstname;
        }

        public String getEmployee_lastname() {
                return employee_lastname;
        }

        public void setEmployee_lastname(String employee_lastname) {
                this.employee_lastname = employee_lastname;
        }

        public String getEmployee_phonenumbe() {
                return employee_phonenumbe;
        }

        public void setEmployee_phonenumbe(String employee_phonenumbe) {
                this.employee_phonenumbe = employee_phonenumbe;
        }

        public String getAdemployee_emaildress() {
                return ademployee_emaildress;
        }

        public void setAdemployee_emaildress(String ademployee_emaildress) {
                this.ademployee_emaildress = ademployee_emaildress;
        }

        public String getCitemployee_addressy() {
                return citemployee_addressy;
        }

        public void setCitemployee_addressy(String citemployee_addressy) {
                this.citemployee_addressy = citemployee_addressy;
        }

        public String getStateemployee_dev_level() {
                return stateemployee_dev_level;
        }

        public void setStateemployee_dev_level(String stateemployee_dev_level) {
                this.stateemployee_dev_level = stateemployee_dev_level;
        }

        public String getEmployee_gender() {
                return employee_gender;
        }

        public void setEmployee_gender(String employee_gender) {
                this.employee_gender = employee_gender;
        }

        public Date getEmployee_hire_date() {
                return employee_hire_date;
        }

        public void setEmployee_hire_date(Date employee_hire_date) {
                this.employee_hire_date = employee_hire_date;
        }

        public boolean isEmployee_onleave() {
                return employee_onleave;
        }

        public void setEmployee_onleave(boolean employee_onleave) {
                this.employee_onleave = employee_onleave;
        }

        public List<String> getTech_stack() {
                return tech_stack;
        }

        public void setTech_stack(List<String> tech_stack) {
                this.tech_stack = tech_stack;
        }

        public List<String> getProject() {
                return project;
        }

        public void setProject(List<String> project) {
                this.project = project;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }
}
