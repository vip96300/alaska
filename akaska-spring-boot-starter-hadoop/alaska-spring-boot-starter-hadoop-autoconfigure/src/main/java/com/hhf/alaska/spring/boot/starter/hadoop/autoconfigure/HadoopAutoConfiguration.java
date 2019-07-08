package com.hhf.alaska.spring.boot.starter.hadoop.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseConfigurationFactoryBean;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * @author huang hong fei
 */
@Configuration
@EnableConfigurationProperties(HadoopProperties.class)
public class HadoopAutoConfiguration {

    @Autowired
    private HadoopProperties hadoopProperties;

    @Bean
    @ConditionalOnMissingBean(HbaseConfigurationFactoryBean.class)
    public HbaseConfigurationFactoryBean hbaseConfigurationFactoryBean(){
        HbaseConfigurationFactoryBean hbaseConfigurationFactoryBean = new HbaseConfigurationFactoryBean();
        hbaseConfigurationFactoryBean.setZkQuorum(hadoopProperties.getQuorum());
        hbaseConfigurationFactoryBean.setZkPort(hadoopProperties.getPort());
        hbaseConfigurationFactoryBean.afterPropertiesSet();
        return hbaseConfigurationFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean(HbaseTemplate.class)
    public HbaseTemplate hbaseTemplate(HbaseConfigurationFactoryBean hbaseConfigurationFactoryBean) {
        org.apache.hadoop.conf.Configuration configuration = hbaseConfigurationFactoryBean.getObject();
        return new HbaseTemplate(configuration);
    }
}
