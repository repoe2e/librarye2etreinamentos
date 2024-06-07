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
                    // Script PowerShell para iniciar a aplicação em segundo plano
                    def psScript = '''
                    $startInfo = New-Object System.Diagnostics.ProcessStartInfo
                    $startInfo.FileName = "mvn.cmd"
                    $startInfo.Arguments = "spring-boot:run"
                    $startInfo.RedirectStandardOutput = $true
                    $startInfo.RedirectStandardError = $true
                    $startInfo.UseShellExecute = $false
                    $startInfo.CreateNoWindow = $true
                    $process = New-Object System.Diagnostics.Process
                    $process.StartInfo = $startInfo
                    $process.Start() | Out-Null
                    $process.WaitForInputIdle()
                    Start-Sleep -s 20
                    $process.Id
                    '''
                    def processId = bat(script: "powershell -Command \"${psScript}\"", returnStdout: true).trim()
                    
                    // Verifique se o servidor está rodando
                    bat 'netstat -an | findstr "8085"' // Verifique se a porta 8085 está em uso
                    bat 'tasklist | findstr "java"' // Verifique se o processo Java está rodando
                    bat 'type target\\spring.log || more target\\spring.log' // Mostre o conteúdo do log da aplicação
                    bat 'curl http://127.0.0.1:8085' // Teste se a aplicação está respondendo

                    // Salve o PID do processo para futuras operações (opcional)
                    writeFile file: 'process.pid', text: processId
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
