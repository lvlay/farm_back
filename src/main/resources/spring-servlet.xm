<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:util="http://www.springframework.org/schema/util" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc   http://www.springframework.org/schema/mvc/spring-mvc.xsd
        http://www.springframework.org/schema/util    http://www.springframework.org/schema/util/spring-util.xsd">

    <!-- 启用spring mvc 注解 -->
    <context:annotation-config/>
    <mvc:annotation-driven/>
    <!-- 设置使用注解的类所在的jar包 -->

    <!-- Swagger资源重定向(仅作为后台使用不提供静态资源) -->
    <mvc:resources location="classpath:/META-INF/resources/" mapping="swagger-ui.html"/>
    <mvc:resources location="classpath:/META-INF/resources/webjars/" mapping="/webjars/**"/>
    <!--swagger2-->
    <bean class="springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration"
          id="swagger2Config"/>

    <context:component-scan base-package="com.zh.monitor.controller"/>
    <bean class="com.zh.monitor.interceptor.LogInfoAspect" id="LogInfoAspect"/>
    <!--controller 异常同意处理-->
    <bean id="exceptionResolver" class="com.zh.monitor.interceptor.MyExceptionHandler"/>
    <!--启用注解的支持 -->
    <aop:aspectj-autoproxy/>
    <mvc:default-servlet-handler/>

    <mvc:interceptors>

        <mvc:interceptor>
            <mvc:mapping path="/*/*.do"/>
            <mvc:mapping path="/*/*/*.do"/>
            <mvc:mapping path="/*/*/*/*.do"/>
            <mvc:mapping path="/*/*/*/*/*.do"/>
            <!--<mvc:exclude-mapping path="/v2/api-do cs"/>-->
            <mvc:exclude-mapping path="/sys/logout.do"/>
            <mvc:exclude-mapping path="/sys/login.do"/>
            <mvc:exclude-mapping path="/app/sys/login.do"/>
            <mvc:exclude-mapping path="/app/sys/logout.do"/>
            <mvc:exclude-mapping path="/app/sys/getDecrypt.do"/>
            <mvc:exclude-mapping path="/app/sys/getString.do"/>
            <mvc:exclude-mapping path="/*/nolimit*.do"/>
            <mvc:exclude-mapping path="/*/*/nolimit*.do"/>
            <mvc:exclude-mapping path="/*/*/*/nolimit*.do"/>
            <mvc:exclude-mapping path="/*/*/*/*/nolimit*.do"/>
            <mvc:exclude-mapping path="/*/noLimit*.do"/>
            <mvc:exclude-mapping path="/*/*/noLimit*.do"/>
            <mvc:exclude-mapping path="/*/*/*/noLimit*.do"/>
            <mvc:exclude-mapping path="/*/*/*/*/noLimit*.do"/>
            <bean class="com.zh.monitor.interceptor.SpringMVCHandlerInterceptor"/>
        </mvc:interceptor>
        <mvc:interceptor>
            <mvc:mapping path="/*/*.do"/>
            <mvc:mapping path="/*/*/*.do"/>
            <mvc:mapping path="/*/*/*/*.do"/>
            <mvc:mapping path="/*/*/*/*/*.do"/>
            <!--<mvc:exclude-mapping path="/v2/api-docs"/>-->
            <mvc:exclude-mapping path="/sys/logout.do"/>
            <mvc:exclude-mapping path="/sys/login.do"/>
            <mvc:exclude-mapping path="/app/sys/login.do"/>
            <mvc:exclude-mapping path="/app/sys/logout.do"/>
            <mvc:exclude-mapping path="/app/sys/getDecrypt.do"/>
            <mvc:exclude-mapping path="/app/sys/getString.do"/>
            <mvc:exclude-mapping path="/*/nopermission*.do"/>
            <mvc:exclude-mapping path="/*/*/nopermission*.do"/>
            <mvc:exclude-mapping path="/*/*/*/nopermission*.do"/>
            <mvc:exclude-mapping path="/*/*/*/*/nopermission*.do"/>
            <mvc:exclude-mapping path="/*/nolimit*.do"/>
            <mvc:exclude-mapping path="/*/*/nolimit*.do"/>
            <mvc:exclude-mapping path="/*/*/*/nolimit*.do"/>
            <mvc:exclude-mapping path="/*/*/*/*/nolimit*.do"/>
            <mvc:exclude-mapping path="/*/noPermission*.do"/>
            <mvc:exclude-mapping path="/*/*/noPermission*.do"/>
            <mvc:exclude-mapping path="/*/*/*/noPermission*.do"/>
            <mvc:exclude-mapping path="/*/*/*/*/noPermission*.do"/>
            <mvc:exclude-mapping path="/*/noLimit*.do"/>
            <mvc:exclude-mapping path="/*/*/noLimit*.do"/>
            <mvc:exclude-mapping path="/*/*/*/noLimit*.do"/>
            <mvc:exclude-mapping path="/*/*/*/*/noLimit*.do"/>
            <bean class="com.zh.monitor.interceptor.PermissionsHandlerInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>

    <!-- 完成请求和注解POJO的映射 -->
    <!--<bean
        class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter" />-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <ref bean="stringHttpMessageConverter"/>
             <ref bean="fastJsonHttpMessageConverter" />
          <!--  <ref bean="marshallingHttpMessageConverter"
                /> -->
            <!--时间字符串转换 -->
            <!--<bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="com.fasterxml.jackson.databind.ObjectMapper">
                        <property name="dateFormat">
                            <bean class="java.text.SimpleDateFormat">
                                <constructor-arg type="java.lang.String" value="yyyy-MM-dd HH:mm:ss"/>
                            </bean>
                        </property>
                    </bean>
                </property>
            </bean>-->
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!-- 对转向页面的路径解析。prefix：前缀， suffix：后缀 -->
    <!-- <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
        p:prefix="/WEB-INF/jsp/" p:suffix=".jsp" /> -->
    <bean id="stringHttpMessageConverter"
          class="org.springframework.http.converter.StringHttpMessageConverter">
        <constructor-arg value="UTF-8" index="0"></constructor-arg>
        <!--避免出现乱码 -->
        <property name="supportedMediaTypes">
            <list>
                <value>text/html;charset=UTF-8</value>
                <value>application/json;charset=UTF-8</value>
            </list>
        </property>
    </bean>
    <bean id="fastJsonHttpMessageConverter" class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
        <property name="supportedMediaTypes">
            <list>
                <value>text/html;charset=UTF-8</value>
                <value>application/json;charset=UTF-8</value>
            </list>
        </property>
        <property name="features">
            <list>
                <!-- 输出key时是否使用双引号 -->
                <value>QuoteFieldNames</value>
                <!-- 是否输出值为null的字段 -->
                <!-- <value>WriteMapNullValue</value> -->
                <!-- 数值字段如果为null,输出为0,而非null -->
                <value>WriteNullNumberAsZero</value>
                <!-- List字段如果为null,输出为[],而非null -->
                <value>WriteNullListAsEmpty</value>
                <!-- 字符类型字段如果为null,输出为"",而非null -->
                <value>WriteNullStringAsEmpty</value>
                <!-- Boolean字段如果为null,输出为false,而非null -->
                <value>WriteNullBooleanAsFalse</value>
                <!-- null String不输出  -->
                <value>WriteNullStringAsEmpty</value>
                <!-- null String也要输出  -->
                <!-- <value>WriteMapNullValue</value> -->

                <!-- Date的日期转换器 -->
                <value>WriteDateUseDateFormat</value>
            </list>
        </property>
    </bean>




    <bean id="jspViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/"/>
        <property name="suffix" value="*.jsp,*.html"/>
    </bean>
    <bean id="castorMarshaller" class="org.springframework.oxm.castor.CastorMarshaller"/>

    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="utf-8"></property>
        <property name="maxUploadSize" value="10485760000"></property>
        <property name="maxInMemorySize" value="40960"></property>
    </bean>
</beans>