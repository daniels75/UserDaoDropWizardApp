package org.daniels.examples.dropwizard.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.daniels.examples.dropwizard.core.User;
import org.hibernate.SessionFactory;

import com.google.common.base.Optional;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<User> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public User create(User user) {
        return persist(user);
    }

    public List<User> findAll() {
        return list(namedQuery("org.daniels.examples.dropwizard.core.User.findAll"));
    }
}
