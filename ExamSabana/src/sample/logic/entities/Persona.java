package sample.logic.entities;

import sample.logic.PersonaException;

import java.util.UUID;

public class Persona {

    private UUID id;
    private String name;
    private String lastname;
    private int age;
    private String estado;
    private String trabajo;
    private String ced;


    public Persona(String age, String name, String lastname, String estado, String trabajo, String ced) throws PersonaException {
        this.name = name;
        this.lastname = lastname;
        this.setAge(age);
        this.estado = estado;
        this.trabajo = trabajo;
        this.id = UUID.randomUUID();
        this.ced = ced;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEstado() {
        return estado;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public String getCed() {
        return ced;
    }

    public void setAge(String ageInput) throws PersonaException {
        try {
            age = Integer.parseInt(ageInput);

            if(age < 0) throw new PersonaException(PersonaException.BAD_AGE_LOWER);
            if(age > 120) throw new PersonaException(PersonaException.BAD_AGE_UPPER);

            this.age = age;

        }catch (NumberFormatException je){
            throw new PersonaException(PersonaException.BAD_AGE + je.getMessage());
        }
    }

    public String getAge() {
        return "La edad es: " + this.age;
    }
}