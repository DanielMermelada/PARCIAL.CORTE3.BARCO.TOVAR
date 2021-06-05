package sample.logic.entities;

import sample.logic.PersonaException;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Persona extends Exportable implements Serializable {

    public Integer count;
    private String name;
    private String lastname;
    private int age;
    private String estado;
    private String trabajo;
    private String ced;

    //Variables - En persona se asignan las variables, que se encargan de asignar campos especificos a cada individuo

    public Persona(String age, String name, String lastname, String estado, String trabajo, String ced) throws PersonaException {
        this.name = name;
        this.lastname = lastname;
        this.setAge(age);
        this.setTrabajo(trabajo);
        this.estado = estado;
        this.trabajo = trabajo;
        this.ced = ced;
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

        return this.trabajo;
    }

    public void setTrabajo(String trabajoInput) throws PersonaException {

        try {

            trabajo = String.valueOf(trabajoInput);

            if(trabajo.equalsIgnoreCase("Presidente") != true && trabajo.equalsIgnoreCase("Alcaldesa") != true && trabajo.equalsIgnoreCase("Senador") != true
                    && trabajo.equalsIgnoreCase("Expresidente") != true && trabajo.equalsIgnoreCase("Profesor") != true && trabajo.equalsIgnoreCase("Estudiante") != true
                    && trabajo.equalsIgnoreCase("Contador") != true && trabajo.equalsIgnoreCase("Programador") != true && trabajo.equalsIgnoreCase("Limpieza") != true
                    && trabajo.equalsIgnoreCase("Desempleado") != true){
                JOptionPane.showMessageDialog(null, "ERROR, USTED SELECCIONÓ UN TRABAJO NO DISPONIBLE");
                throw new PersonaException(PersonaException.BAD_WORK);
            }

            this.trabajo = trabajo;

        }catch (NumberFormatException je){
        }
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

    @Override
    public String toString(){

        return String.format("age = %s, name=%s, lastname=%s, estado=%s, trabajo=%s, ced=%s", this.age, this.name, this.lastname,this.estado,this.trabajo,this.ced);
    }

    @Override
    public List<String> toListString() {
        List<String> result = new ArrayList<>();
        result.add(String.valueOf(this.age));
        result.add(this.name);
        result.add(this.lastname);
        result.add(this.estado);
        result.add(this.trabajo);
        result.add(this.ced);

        return result;
    }

    @Override
    public String getHeader() {

        return "age,name,lastname,estado,trabajo,ced,";
    }
}