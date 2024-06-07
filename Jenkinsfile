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
                script {
                    // Use `start /B` para iniciar a aplicação em segundo plano
                    bat '''
                        start "" /B powershell -Command "Start-Process 'cmd.exe' -ArgumentList '/c mvn spring-boot:run' -NoNewWindow"
                    '''
                    // Aguarde 20 segundos para garantir que o servidor inicie
                    bat 'ping -n 20 127.0.0.1 > nul'
                    // Verifique se a porta 8085 está em uso
                    bat 'netstat -an | findstr "8085"'
                    // Verifique se o processo Java está rodando
                    bat 'tasklist | findstr "java"'
                    // Mostre o conteúdo do log da aplicação
                    bat 'type target\\spring.log || more target\\spring.log'
                    // Teste se a aplicação está respondendo
                    bat 'curl http://127.0.0.1:8085'
                }
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
