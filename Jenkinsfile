pipeline {
    agent any
    tools {
        maven 'MAVEN' // Nome configurado para o Maven
        jdk 'JDK17' // Nome configurado para o JDK
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/repoe2e/librarye2etreinamentos.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn spring-boot:run'
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
