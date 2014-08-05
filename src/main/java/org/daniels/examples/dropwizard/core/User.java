package org.daniels.examples.dropwizard.core;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(
        name = "org.daniels.examples.dropwizard.core.User.findAll",
        query = "SELECT u FROM User u"
    ),
    @NamedQuery(
        name = "org.daniels.examples.dropwizard.core.User.findById",
        query = "SELECT u FROM User u WHERE u.id = :id"
    )
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created", nullable = false)
    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}


    



}
