package csc1035.project2.util;

import java.util.List;

/**
 * Controller interface
 *
 * @author Titas Janusonis
 */
public interface IController<E> {
    void save(E e); // Create

    void update(E s); // Update

    E getById(Class<E> c, int id); // Read

    List<E> getAll(Class<E> c); // Supporting method for retrieving all entries in a database table

    void delete(Class<E> c,int id); // Delete

    void bulkListSave(List<E> e); // Bulk save a list collection of entries

}
