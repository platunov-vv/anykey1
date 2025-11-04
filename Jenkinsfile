pipeline {
     agent { label 'node1' }

    environment {

        IMAGE_NAME = 'platunov17/platunov'
        IMAGE_TAG = 'latest'
        CONTAINER_NAME = 'anykey'
       // ALLURE_RESULTS = 'allure-results'
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

                bat 'mvn clean test'
            }
        }
          stage('Generate Allure Report') {
              steps {
                  // Генерация HTML-отчета из результатов
                  bat 'mvn allure:report -Dallure.results.directory=../allure-results'
              }
          }
        stage('Publish Allure Report') {
                    steps {
                        // публикация отчета
                        publishHTML target: [
                            reportDir: 'allure-report',       // папка с отчетом
                            reportFiles: 'index.html',        // стартовая страница
                            reportName: 'Allure Test Report'  // отображаемое имя
                        ]
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