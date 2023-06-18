FROM openjdk:8-jre-alpine
ARG JAR_FILE="target/ecom-gateway-1.0-SNAPSHOT.jar"
ENV server.port="7095"
ENV secret_key="abcd@ef#gh*I&jkl" 
ENV server.servlet.context-path="/ecom-gateway-1.0-SNAPSHOT"
ENV endpoint_svc_core=http://localhost:7091/ecom-svc-core-1.0-SNAPSHOT
ENV endpoint_svc_catalog=http://localhost:7092/ecom-svc-catalog-1.0-SNAPSHOT 
ENV endpoint_svc_sales=http://localhost:7093/ecom-svc-sales-1.0-SNAPSHOT 
ENV endpoint_svc_admin=http://localhost:7094/ecom-svc-admin-1.0-SNAPSHOT 
ENV endpoint_svc_pos=http://localhost:7097/ecom-svc-pos-1.0-SNAPSHOT 
ENV endpoint_svc_deploy=http://localhost:7098/ecom-svc-deploy-1.0-SNAPSHOT 
ENV logging.file="/opt/microservice/logs/Gateway.log"
COPY ${JAR_FILE} /opt/ecom-gateway-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/opt/ecom-gateway-1.0-SNAPSHOT.jar"]
WORKDIR /opt/microservice
EXPOSE 7095
