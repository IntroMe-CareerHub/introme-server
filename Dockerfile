# FROM <이미지명>:<태그>
FROM openjdk:17
# 변수 선언
ARG JAR_FILE=/build/libs/*.jar
# JAR_FILE을 컨테이너 내 app.jar로 복사
COPY ${JAR_FILE} app.jar
# 컨테이너 시작 시, 스크립트 실행
ENTRYPOINT ["java","-jar","/app.jar"]
