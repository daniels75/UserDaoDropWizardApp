package org.daniels.examples.dropwizard.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.daniels.examples.dropwizard.core.User;
import org.daniels.examples.dropwizard.dao.UserDAO;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO userDAO;
    private final AtomicLong counter;

    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.counter = new AtomicLong();
    }

    @POST
    @UnitOfWork
    public User createUser(User user) {
    	user.setCreated(new Date());
        return userDAO.create(user);
    }
    
    @POST
    @Path("/random")
    @UnitOfWork
    public User createRandomUser() {
    	User user = new User();
    	user.setName("anyName" + counter.incrementAndGet());
    	user.setCreated(new Date());
        return userDAO.create(user);
    }

    @GET
    @UnitOfWork
    public List<User> listPeople() {
        return userDAO.findAll();
    }

}
