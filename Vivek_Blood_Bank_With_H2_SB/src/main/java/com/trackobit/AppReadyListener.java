package com.trackobit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Aadil Khan
 * @since : 06/11/23
 */

@Service
@Slf4j
public class AppReadyListener {

    @Autowired
    DataSource dataSource;

    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
        log.info("AppReadyListener sddf");
        try {
            Connection connection = this.dataSource.getConnection();
            String schema = connection.getSchema();
            log.info("schema = {}", schema);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
