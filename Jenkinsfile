pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.16.8-hotspot'
        PATH = "C:\\Program Files\\Eclipse Adoptium\\jdk-17.0.16.8-hotspot\\bin;C:\\Users\\saich\\Downloads\\apache-maven-3.9.16-bin\\apache-maven-3.9.16\\bin"
    }

    stages {

        stage('Debug') {
            steps {
                bat 'echo %JAVA_HOME%'
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}