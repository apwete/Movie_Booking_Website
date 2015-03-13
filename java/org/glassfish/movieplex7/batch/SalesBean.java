/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.glassfish.movieplex7.batch;

import java.util.List;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.movieplex7.entities.Sales;

/**
 *
 * @author Development
 */
@Named
@RequestScoped
public class SalesBean {

    public void runJob() {
        try {
            JobOperator job = BatchRuntime.getJobOperator();
            long jobId = job.start("eod-sales", new Properties());
            System.out.println("Started job with id: " + jobId);
        } catch (JobStartException ex) {
            ex.printStackTrace();
        }
    }
    @PersistenceContext
    EntityManager entityManager;

    public List<Sales> getSalesData() {
        return entityManager.createNamedQuery("Sales.findAll", Sales.class).getResultList();
    }

}