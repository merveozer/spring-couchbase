package merveozer.toDoApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {
    @Override
    public String getConnectionString() {
        return "localhost";
    }

    @Override
    public String getUserName() {
        return "user";
    }

    @Override
    public String getPassword() {
        return "123456";
    }

    @Override
    public String getBucketName() {
        return "ToDo";
    }

}