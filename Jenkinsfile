pipeline {
     agent { label 'node1' }

    environment {

        IMAGE_NAME = 'your-dockerhub-username/your-app'
        IMAGE_TAG = 'latest'

        DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials-id'
    }

    stages {
        stage('Checkout') {
            steps {

                checkout scm

            }
        }

        stage('Build') {
            steps {

                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {

                script {

                    docker.build("${IMAGE_NAME}:${IMAGE_TAG}")
                }
            }
        }

        stage('Test') {
            steps {

                bat 'mvn test'
            }
        }

        stage('Pubat Docker Image') {
            steps {

                script {
                    docker.withRegistry('https://registry.hub.docker.com', "${DOCKERHUB_CREDENTIALS_ID}") {
                        docker.image("${IMAGE_NAME}:${IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}