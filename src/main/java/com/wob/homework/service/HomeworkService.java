package com.wob.homework.service;

public interface HomeworkService {

    /**
     * After the startup this method downloads the data from the remote API, validates and saves them to the database,
     * and creates the necessary reports.
     * */
    void fetchDataAndCreateReport();
}
