pipeline {
     agent { label 'node1' }

    environment {

        IMAGE_NAME = 'platunov17/platunov'
        IMAGE_TAG = 'latest'
        CONTAINER_NAME = 'anykey'

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

         stage('Run Docker Image') {
                    steps {

                        script {
                             def img = docker.image("${IMAGE_NAME}:${IMAGE_TAG}")
                             bat "docker run -p 81:80-d --name ${CONTAINER_NAME} ${IMAGE_NAME}:${IMAGE_TAG}"
                        }
                    }
                }
    }
}