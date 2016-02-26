package ua.in.zloch.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Transactional
public abstract class HibernateDAO<O,K extends Serializable> implements GenericDAO<O,K> {
    @Autowired
    private SessionFactory sessionFactory;
    private Class<O> type;

    public HibernateDAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public K create(O t) {
        return (K) getSession().save(t);
    }

    @Override
    public O read(K id) {
        return getSession().get(type, id);
    }

    @Override
    public void update(O t) {
        getSession().update(t);
    }

    @Override
    public void delete(O t) {
        getSession().delete(t);
    }

    @Override
    public void delete(K id) {
        delete(read(id));
    }

    @Override
    public List getAll() {
        return getSession().createCriteria(type).list();
    }

    @Override
    public Long count() {
        return (Long) getSession().createCriteria(type).setProjection(Projections.rowCount()).uniqueResult();
    }
}
