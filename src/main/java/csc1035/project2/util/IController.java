package csc1035.project2.util;

import java.util.List;

public  interface IController<E>  {
    void create(E e);

    void update(E s);

    E readById(Class<E> c, int id);

    List<E> readAll(String name);

    void delete(Class<E> c, int id);

    void bulkListRead(List<E> e);
}

