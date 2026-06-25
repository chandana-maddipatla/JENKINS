pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.16.8-hotspot'
    }

    stages {

        stage('Debug') {
            steps {
                bat '''
                set PATH=%JAVA_HOME%\\bin;%PATH%
                echo %JAVA_HOME%
                where java
                java -version
                where mvn
                mvn -version
                '''
            }
        }

        stage('Build') {
            steps {
                bat '''
                set PATH=%JAVA_HOME%\\bin;%PATH%
                mvn clean compile
                '''
            }
        }

        stage('Test') {
            steps {
                bat '''
                set PATH=%JAVA_HOME%\\bin;%PATH%
                mvn test
                '''
            }
        }
    }
}