package com.example.cadastropet.Model;
import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Pets")
@JsonPropertyOrder({"id", "name","age","address","weight", "gender", "type", "race"})
public class CadastroModel implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String VALUE_NOT_INFORMED = "NOT INFORMED";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Petid;
    private String race;
    private String weight;
    private String age;

    @Transient
    @JsonIgnore
    private String firstname;

    @JsonIgnore
    @Transient
    private String lastname;

    @Transient
    @JsonIgnore
    private String number;

    @JsonIgnore
    @Transient
    private String city;

    @Transient
    @JsonIgnore
    private String street;

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private CatOrDog type;

    @Enumerated(EnumType.STRING)
    private MascOrFem gender;

    public String GenerateFullAddress(){
        return this.street + ", " + this.number + " - " + this.city;
    }
    public String GenerateName(){
        return this.firstname + " " + this.lastname;
    }

    public void HandleEmptyFields(CadastroRecordDTO cadastroRecordDTO){
        if (cadastroRecordDTO.race().trim().isEmpty()){setRace(VALUE_NOT_INFORMED);}
        if(cadastroRecordDTO.number().trim().isEmpty()){setNumber(VALUE_NOT_INFORMED);}
        if(cadastroRecordDTO.weight().trim().isEmpty()){setWeight(VALUE_NOT_INFORMED);}
        if (cadastroRecordDTO.age().trim().isEmpty()){setAge(VALUE_NOT_INFORMED);}
    }

    public CadastroModel(Long petid, String race, String weight, String age, String firstname, String lastname, String number, String city, String street, CatOrDog type, MascOrFem gender) {
        Petid = petid;
        this.race = race;
        this.weight = weight;
        this.age = age;
        this.firstname = firstname;
        this.lastname = lastname;
        this.number = number;
        this.city = city;
        this.street = street;
        this.name = firstname + " " + lastname;
        this.address = street + ", " + number + " - " + city;
        this.type = type;
        this.gender = gender;
    }


    public CadastroModel() {
    }

    public CatOrDog getType() {
        return type;
    }

    public void setType(CatOrDog type) {
        this.type = type;
    }

    public MascOrFem getGender() {
        return gender;
    }

    public void setGender(MascOrFem gender) {
        this.gender = gender;
    }

    public Long getPetid() {
        return Petid;
    }

    public void setPetid(Long petid) {
        Petid = petid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
