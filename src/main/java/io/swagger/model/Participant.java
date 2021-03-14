package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-01T12:29:37.288Z[GMT]")


@Entity
@Table(name = "SurvHey_DB.Survey_Participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Participant_ID")
    @JsonProperty("id")
    private Long id = null;

    @Column(name = "IP_Adress")
    @JsonProperty("IP")
    private String ipAddress = null;


    @Column(name = "Cookie_ID")
    @JsonProperty("Cookie")
    private String cookieID = null;


    public Participant(Long id, String ipAddress, String cookieID) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.cookieID = cookieID;
    }

    public Participant() {
        this.id = null;
        this.ipAddress = null;
        this.cookieID = null;
    }

    // ID Getter and Setter
    @Schema(required = true, description = "")
    @NotNull
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    //Getter and Setter IP
    @Schema(example = "192.1.1.1", required = true, description = "")
    @NotNull



    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }



    @Schema(example = "regsethdfghysdrhetjsrtgjhsgfhj456sgaru", required = true, description = "")
    @NotNull

    public String getCookieID() {
        return cookieID;
    }

    public void setCookieID(String cookieID) {
        this.cookieID = cookieID;
    }
}
