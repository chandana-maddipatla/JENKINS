pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/chandana-maddipatla/JENKINS'
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

        stage('Report') {
            steps {
                bat 'mvn verify'
            }
        }
    }
}