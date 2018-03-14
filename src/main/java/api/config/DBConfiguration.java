package api.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import lombok.Data;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Properties;

@Data
@Configuration
@EnableConfigurationProperties(DBProperties.class)
public class DBConfiguration {

    @Autowired
    DBProperties dbProperties;

    @Bean
    public DruidDataSource DBDataSource() throws Exception {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dbProperties.getUrl());
        druidDataSource.setUsername(dbProperties.getUsername());
        druidDataSource.setPassword(dbProperties.getPassword());
        druidDataSource.setMaxWait(dbProperties.getMaxWait());
        druidDataSource.setMaxActive(dbProperties.getMaxActive());
        druidDataSource.setInitialSize(dbProperties.getInitialSize());
        druidDataSource.setTimeBetweenEvictionRunsMillis(dbProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(dbProperties.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setValidationQuery("select 1");
        // druidDataSource.setConnectionInitSqls(Arrays.asList("set names utf8mb4;"));

        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(100);
        statFilter.setMergeSql(true);
        statFilter.setLogSlowSql(true);
        druidDataSource.getProxyFilters().add(statFilter);

        return druidDataSource;
    }

    @Bean
    public DataSourceTransactionManager DBTransactionManager() throws Exception {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(DBDataSource());
        return manager;
    }

    @Bean
    public SqlSessionFactory DBSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(DBDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/**/*Mapper*.xml"));

        // 配置分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        pageInterceptor.setProperties(properties);
        factoryBean.setPlugins(new Interceptor[]{pageInterceptor});

        return factoryBean.getObject();
    }

    // 因为MapperScannerConfigurer是BeanDefinitionRegistryPostProcessor，所以必须是static的，
    // 要不然会让DBConfiguration被提前实例化，导致没有正常依赖注入（spring中叫ConfigurationClassEnhancer）
    // 如果@Bean方法是static就是普通的bean定义；否者，@Configuration作为FactoryBean必须先被实例化
    @Bean
    public static MapperScannerConfigurer DBMapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("DBSessionFactory");
        configurer.setBasePackage("com.dianwoba.platform.mapper");
        return configurer;
    }

}
