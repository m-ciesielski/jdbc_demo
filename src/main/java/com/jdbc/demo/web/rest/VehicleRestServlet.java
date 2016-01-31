package com.jdbc.demo.web.rest;

import com.jdbc.demo.VehicleDAO;
import com.jdbc.demo.domain.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Mateusz on 21-Dec-15.
 */

@Stateless
@Path("/vehicles")
public class VehicleRestServlet {
    private final static Logger LOGGER = LoggerFactory.getLogger(VehicleRestServlet.class);

    @EJB
    VehicleDAO vehicleManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Vehicle> getVehicles() {
        return vehicleManager.getAll();
    }

    @POST
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")
    public Vehicle addVehicle(Vehicle vehicle) {

        LOGGER.info(String.format("Adding vehicle %s.", vehicle));
        return vehicleManager.add(vehicle);
    }

    @Path("/{id}")
    @GET
    @Produces("application/json; charset=UTF-8")
    public Vehicle getVehicle(@PathParam("id") int id) {
        return vehicleManager.get(id);
    }

    @Path("/{id}")
    @POST
    @Consumes("application/json; charset=UTF-8")
    public void updateVehicle(Vehicle vehicle) {
        LOGGER.info(String.format("Updating vehicle %s.", vehicle));
        vehicleManager.update(vehicle);
    }

    @Path("/{id}")
    @DELETE
    public void deleteVehicle(@PathParam("id") int id) {

        Vehicle vehicle = vehicleManager.get(id);
        LOGGER.info(String.format("Deleting vehicle %s.", vehicle));
        vehicleManager.delete(vehicle);
    }
}
