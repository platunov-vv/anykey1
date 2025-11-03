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
   stage('Test') {
            steps {

                bat 'mvn test -Pallure'
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



        stage('Push Docker Image') {
            steps {

                script {
                     def img = docker.image("${IMAGE_NAME}:${IMAGE_TAG}")
                                                         img.push()
                }
            }
        }

         stage('Run Docker Image!') {
                    steps {

                        script {
                             def img = docker.image("${IMAGE_NAME}:${IMAGE_TAG}")
                             bat "docker rm -f  ${CONTAINER_NAME} || true"
                             bat "docker run -d -p 8081:8081 --name ${CONTAINER_NAME} ${IMAGE_NAME}:${IMAGE_TAG}"
                        }
                    }
                }
    }
}