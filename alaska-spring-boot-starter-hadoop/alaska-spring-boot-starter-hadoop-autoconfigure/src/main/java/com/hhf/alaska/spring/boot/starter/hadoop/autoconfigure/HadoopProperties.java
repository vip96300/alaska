package com.hhf.alaska.spring.boot.starter.hadoop.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huang hong fei
 */
@ConfigurationProperties(prefix="alaska.spring.boot.starter.hadoop")
public class HadoopProperties {

    private String quorum;

    private Integer port;

    public String getQuorum() {
        return quorum;
    }

    public void setQuorum(String quorum) {
        this.quorum = quorum;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
