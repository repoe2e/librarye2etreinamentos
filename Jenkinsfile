pipeline {
    agent any
    tools {
        maven 'MAVEN' // Nome configurado para o Maven
        jdk 'JDK17' // Nome configurado para o JDK
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
                    // Parar o serviço existente
                    bat 'nssm stop MyAppService || exit 0'
                    
                    // Remover o serviço existente
                    bat 'nssm remove MyAppService confirm || exit 0'
                    
                    // Instalar o novo serviço
                    bat 'nssm install MyAppService "C:\\Program Files\\Java\\jdk-17\\bin\\java.exe" -jar "C:\\Desenvolvimento\\library_e2e\\target\\library_e2e-0.0.1-SNAPSHOT.jar"'
                    
                    // Configurar o serviço
                    bat 'nssm set MyAppService AppDirectory "C:\\Desenvolvimento\\library_e2e"'
                    bat 'nssm set MyAppService AppStdout "C:\\Desenvolvimento\\library_e2e\\logs\\stdout.log"'
                    bat 'nssm set MyAppService AppStderr "C:\\Desenvolvimento\\library_e2e\\logs\\stderr.log"'
                    
                    // Iniciar o novo serviço
                    bat 'nssm start MyAppService'
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
