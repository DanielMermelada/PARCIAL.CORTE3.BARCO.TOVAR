package sample.logic.services;

import javafx.collections.ObservableList;
import sample.logic.entities.Persona;

import java.util.List;
import java.util.UUID;

public interface IPersonaServices {

    ObservableList<Persona> getAll();
    Persona getById(UUID id);
    Persona insert(Persona persona);
    void delete(List<Persona> personas);
}
