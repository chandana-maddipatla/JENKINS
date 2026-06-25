pipeline {
    agent any

    stages {

        stage('Debug Environment') {
            steps {
                bat 'echo JAVA_HOME=%JAVA_HOME%'
                bat 'where java'
                bat 'java -version'
                bat 'where mvn'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
    }
}