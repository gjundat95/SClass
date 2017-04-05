package com.tinhngo.sclassandroid.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ittin on 02/04/2017.
 */

public class Register2Model {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private Object message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public class Data {

        @SerializedName("users")
        @Expose
        private Users users;

        public Users getUsers() {
            return users;
        }

        public void setUsers(Users users) {
            this.users = users;
        }

        public class Users {

            @SerializedName("per_page")
            @Expose
            private int perPage;
            @SerializedName("current_page")
            @Expose
            private int currentPage;
            @SerializedName("next_page_url")
            @Expose
            private String nextPageUrl;
            @SerializedName("prev_page_url")
            @Expose
            private Object prevPageUrl;
            @SerializedName("from")
            @Expose
            private int from;
            @SerializedName("to")
            @Expose
            private int to;
            @SerializedName("data")
            @Expose
            private List<Datum> data = null;

            public int getPerPage() {
                return perPage;
            }

            public void setPerPage(int perPage) {
                this.perPage = perPage;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public String getNextPageUrl() {
                return nextPageUrl;
            }

            public void setNextPageUrl(String nextPageUrl) {
                this.nextPageUrl = nextPageUrl;
            }

            public Object getPrevPageUrl() {
                return prevPageUrl;
            }

            public void setPrevPageUrl(Object prevPageUrl) {
                this.prevPageUrl = prevPageUrl;
            }

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
                this.from = from;
            }

            public int getTo() {
                return to;
            }

            public void setTo(int to) {
                this.to = to;
            }

            public List<Datum> getData() {
                return data;
            }

            public void setData(List<Datum> data) {
                this.data = data;
            }

            public class Datum {

                @SerializedName("id")
                @Expose
                private int id;
                @SerializedName("first_name")
                @Expose
                private String firstName;
                @SerializedName("last_name")
                @Expose
                private String lastName;
                @SerializedName("email")
                @Expose
                private String email;
                @SerializedName("sex")
                @Expose
                private int sex;
                @SerializedName("phone")
                @Expose
                private String phone;
                @SerializedName("birthday")
                @Expose
                private String birthday;
                @SerializedName("description")
                @Expose
                private String description;
                @SerializedName("address")
                @Expose
                private String address;
                @SerializedName("company")
                @Expose
                private String company;
                @SerializedName("relationships")
                @Expose
                private int relationships;
                @SerializedName("phone_parent")
                @Expose
                private String phoneParent;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getFirstName() {
                    return firstName;
                }

                public void setFirstName(String firstName) {
                    this.firstName = firstName;
                }

                public String getLastName() {
                    return lastName;
                }

                public void setLastName(String lastName) {
                    this.lastName = lastName;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public int getSex() {
                    return sex;
                }

                public void setSex(int sex) {
                    this.sex = sex;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getBirthday() {
                    return birthday;
                }

                public void setBirthday(String birthday) {
                    this.birthday = birthday;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public int getRelationships() {
                    return relationships;
                }

                public void setRelationships(int relationships) {
                    this.relationships = relationships;
                }

                public String getPhoneParent() {
                    return phoneParent;
                }

                public void setPhoneParent(String phoneParent) {
                    this.phoneParent = phoneParent;
                }

            }

        }

    }
}
