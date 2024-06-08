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
        withMaven(
          maven: 'MAVEN',
          mavenLocalRepo: '.repository',
          mavenSettingsConfig: 'my-maven-settings'
        ) {
          bat 'mvn clean install'
        }
      }
    }
    stage('Test') {
      steps {
        withMaven(
          maven: 'MAVEN',
          mavenLocalRepo: '.repository',
          mavenSettingsConfig: 'my-maven-settings'
        ) {
          bat 'mvn test'
        }
      }
    }
    stage('Deploy') {
      steps {
        script {
        
          // Inicie a aplicação Spring Boot usando o script PowerShell
          bat 'powershell -File C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\librarye2etreinamentos\\start-app.ps1'

          // Aguarde alguns segundos para garantir que a aplicação inicie
          bat 'ping -n 20 127.0.0.1 > nul'

          // Verifique se a aplicação está rodando
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
