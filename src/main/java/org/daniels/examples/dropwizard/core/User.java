package org.daniels.examples.dropwizard.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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
@NamedNativeQueries({
	@NamedNativeQuery(
			name = "org.daniels.examples.dropwizard.core.User.findtop10",
			query = "SELECT  u.* FROM User u order by u.id desc limit 10", resultClass = User.class)
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
