pipeline {
    agent any
    tools {
        maven 'MAVEN' // Nome configurado para o Maven
        jdk 'JDK17' // Nome configurado para o JDK
        git 'GIT' // Nome configurado para o Git
    }
    environment {
        SERVICE_NAME = "LibraryE2EApp"
        LOG_PATH = "C:\\ProgramData\\Jenkins\\workspace\\librarye2etreinamentos\\target\\spring.log"
        WORKSPACE_DIR = "C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\librarye2etreinamentos"
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
                    // Comandos para parar e remover o serviço existente
                    bat "nssm stop ${env.SERVICE_NAME}"
                    bat "nssm remove ${env.SERVICE_NAME} confirm"

                    // Comando para instalar o serviço com nssm
                    bat """
                        nssm install ${env.SERVICE_NAME} "mvn.cmd" "spring-boot:run"
                        nssm set ${env.SERVICE_NAME} AppStdout ${env.LOG_PATH}
                        nssm set ${env.SERVICE_NAME} AppStderr ${env.LOG_PATH}
                        nssm set ${env.SERVICE_NAME} AppDirectory ${env.WORKSPACE_DIR}
                        nssm start ${env.SERVICE_NAME}
                    """

                    // Aguarde 20 segundos para garantir que o servidor inicie
                    bat 'ping -n 20 127.0.0.1 > nul'
                    // Verifique se a porta 8085 está em uso
                    bat 'netstat -an | findstr "8085"'
                    // Verifique se o processo Java está rodando
                    bat 'tasklist | findstr "java"'
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
