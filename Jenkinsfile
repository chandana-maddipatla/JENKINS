pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat '"C:\\Users\\saich\\Downloads\\apache-maven-3.9.16-bin\\apache-maven-3.9.16\\bin\\mvn.cmd" clean compile'
            }
        }

        stage('Test') {
            steps {
                bat '"C:\\Users\\saich\\Downloads\\apache-maven-3.9.16-bin\\apache-maven-3.9.16\\bin\\mvn.cmd" test'
            }
        }
    }
}