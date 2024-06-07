pipeline {
    agent any
    tools {
        maven 'MAVEN' // Nome configurado para o Maven
        jdk 'JDK17' // Nome configurado para o JDK
        git 'GIT' // Nome configurado para o Git
    }
    environment {
        NSSM_PATH = 'C:\\nssm-2.24\\win64\\nssm.exe'
        SERVICE_NAME = 'LibraryE2EApp'
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
                    def logPath = "${WORKSPACE}\\target\\spring.log"
                    def workspaceDir = "${WORKSPACE}"

                    // Stop the existing service
                    bat "${env.NSSM_PATH} stop ${env.SERVICE_NAME} || exit 0"

                    // Remove the existing service
                    bat "${env.NSSM_PATH} remove ${env.SERVICE_NAME} confirm || exit 0"

                    // Install the service
                    bat "${env.NSSM_PATH} install ${env.SERVICE_NAME} mvn.cmd spring-boot:run"

                    // Configure the service
                    bat "${env.NSSM_PATH} set ${env.SERVICE_NAME} AppStdout ${logPath}"
                    bat "${env.NSSM_PATH} set ${env.SERVICE_NAME} AppStderr ${logPath}"
                    bat "${env.NSSM_PATH} set ${env.SERVICE_NAME} AppDirectory ${workspaceDir}"

                    // Start the service
                    bat "${env.NSSM_PATH} start ${env.SERVICE_NAME}"

                    // Wait for the service to start
                    bat 'ping -n 20 127.0.0.1 > nul'

                    // Check if the service is running
                    bat 'netstat -an | findstr "8085"'
                    bat 'tasklist | findstr "java"'
                    
                    // Show the log
                    bat "type ${logPath} || more ${logPath}"

                    // Test if the application is responding
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
