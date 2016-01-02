package com.jdbc.demo.web.rest;

import com.jdbc.demo.DriverDAO;
import com.jdbc.demo.domain.Driver;
import com.jdbc.demo.services.DriverEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Mateusz on 21-Dec-15.
 */
@Path("drivers")
public class DriverRestServlet {
    private final static Logger LOGGER = LoggerFactory.getLogger(DriverRestServlet.class);

    DriverDAO driverManager = new DriverEntityManager();

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Driver> getDrivers() {
        return driverManager.getAll();
    }

    @POST
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")
    public Driver addDriver(Driver driver) {

        LOGGER.info(String.format("Adding driver %s.", driver));
        return driverManager.add(driver);
    }

    @Path("/{id}")
    @GET
    @Produces("application/json; charset=UTF-8")
    public Driver getDriver(@PathParam("id") int id) {
        return driverManager.get(id);
    }

    @Path("/{id}")
    @POST
    @Consumes("application/json; charset=UTF-8")
    public void updateDriver(Driver driver) {
        LOGGER.info(String.format("Updating driver %s.", driver));
        driverManager.update(driver);
    }

    @Path("/{id}")
    @DELETE
    public void deleteDriver(@PathParam("id") int id) {

        Driver driver = driverManager.get(id);
        LOGGER.info(String.format("Deleting driver %s.", driver));
        driverManager.delete(id);
    }

}
