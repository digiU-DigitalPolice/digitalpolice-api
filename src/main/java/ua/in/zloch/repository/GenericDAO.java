package ua.in.zloch.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <O,K extends Serializable>{
    K create(O t);
    O read(K id);
    void update(O t);
    void delete(O t);
    void delete(K id);
    List getAll();
    Long count();
}