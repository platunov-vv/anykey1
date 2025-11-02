pipeline {
     agent { label 'node1' }

    environment {

        IMAGE_NAME = 'platunov17/platunov'
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

                    def image =   docker.build("${IMAGE_NAME}:${IMAGE_TAG}")
                }
            }
        }

        stage('Test') {
            steps {

                bat 'mvn test'
            }
        }

        stage('Push Docker Image') {
            steps {

                script {
                     def img = docker.image("${IMAGE_NAME}:${IMAGE_TAG}")
                                                         img.push()
                }
            }
        }
    }
}