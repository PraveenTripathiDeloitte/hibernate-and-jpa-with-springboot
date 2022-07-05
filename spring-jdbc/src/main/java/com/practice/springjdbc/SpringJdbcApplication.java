package com.practice.springjdbc;

import com.practice.springjdbc.dao.PersonJdbcDao;
import com.practice.springjdbc.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/*
CommandLineRunner is used to perform operation in console
 */
//@SpringBootApplication
public class SpringJdbcApplication implements CommandLineRunner {

    /*
    to print the output and comments in console
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonJdbcDao personJdbcDao;

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        logger will take the value and replace {} with the values
         */
        logger.info("All users -> {}",
                personJdbcDao.findAll());

        logger.info("User with id 10001 -> {}",
                personJdbcDao.findById(10001));

        logger.info("Deleting 10002 -> No of rows deleted - {}",
                personJdbcDao.deleteById(10002));


        logger.info("Inserting 10004 -> {}",
                personJdbcDao.insert(new Person(10004, "Tara", "Berlin", new Date())));

        logger.info("Update 10003 -> {}",
                personJdbcDao.update(new Person(10003, "Pieter", "Utrecht", new Date())));
    }
}
