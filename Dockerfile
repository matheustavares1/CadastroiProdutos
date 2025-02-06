
# Usar a imagem base do JDK
FROM openjdk:17-jdk-alpine

# Definir o diretório de trabalho no container
WORKDIR /app

# Copiar o arquivo JAR gerado para o container
COPY target/sua-aplicacao.jar app.jar

# Expor a porta em que a aplicação será executada
EXPOSE 3000

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]