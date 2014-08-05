package org.daniels.examples.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import org.daniels.examples.dropwizard.core.Template;
import org.daniels.examples.dropwizard.core.User;
import org.daniels.examples.dropwizard.dao.UserDAO;
import org.daniels.examples.dropwizard.health.TemplateHealthCheck;
import org.daniels.examples.dropwizard.resources.HelloWorldResource;
import org.daniels.examples.dropwizard.resources.UserResource;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    private final HibernateBundle<HelloWorldConfiguration> hibernateBundle =
            new HibernateBundle<HelloWorldConfiguration>(User.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<HelloWorldConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle());
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        final Template template = configuration.buildTemplate();
        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        
        environment.healthChecks().register("template", new TemplateHealthCheck(template));


        environment.jersey().register(new HelloWorldResource(template));
        environment.jersey().register(new UserResource(userDAO));

    }
}
