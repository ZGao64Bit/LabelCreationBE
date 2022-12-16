package com.zack.labelcreation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="label")
@JsonDeserialize(builder = Label.Builder.class)
public class Label implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User host;

    private Label(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.host = builder.host;
    }

    public Label() {

    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getHost() {
        return host;
    }


    // Builder patter - Class
    public static class Builder {
        @JsonProperty("id")
        private Long id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("host")
        private User host;

        // ==================== Setters

        // Setters
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setHost(User host) {
            this.host = host;
            return this;
        }

        public Label build() {
            return new Label(this);
        }
    }
}
