pipeline {
    agent any
    tools {
        maven 'MAVEN' // Nome configurado para o Maven
        jdk 'JDK17' // Nome configurado para o JDK
        git 'GIT' // Nome configurado para o Git
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/repoe2e/librarye2etreinamentos.git']]])
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                bat 'start "" /B cmd /c "mvn spring-boot:run > target\\spring.log 2>&1"'
                bat 'ping -n 20 127.0.0.1 > nul' // Aguarde 20 segundos para garantir que o servidor inicie
                bat 'netstat -an | findstr "8085"' // Verifique se a porta 8085 está em uso
                bat 'tasklist | findstr "java"' // Verifique se o processo Java está rodando
                bat 'type target\\spring.log || more target\\spring.log' // Mostre o conteúdo do log da aplicação
                // Adicione um comando para testar a acessibilidade da aplicação
                bat 'curl http://127.0.0.1:8085' // Teste se a aplicação está respondendo
            }
        }
    }
    post {
        success {
            echo 'Build successful'
        }
        failure {
            echo 'Build failed'
        }
    }
}
