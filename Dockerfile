FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app

COPY target/*.jar target/*.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine
VOLUME /db
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
RUN ls
ENTRYPOINT ["java","-cp","app:app/lib/*","br.com.leoguilbor.surl.SurlApplication"]