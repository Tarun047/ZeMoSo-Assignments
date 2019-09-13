package com.tarun.TalkBuddy.daos;

import java.util.List;

public interface GenericDao<T,K>
{
    List<T> list();

    T findById(K key);

    boolean removeById(K key);

    boolean remove(T entry);

    boolean createOrUpdate(T entry);

}
