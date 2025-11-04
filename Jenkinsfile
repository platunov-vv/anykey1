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

                bat 'mvn clean verify'
               // bat 'mvn allure:report'
            }
        }

        /* stage('Publish Allure Report') {
                    steps {
                        // публикация отчета
                        publishHTML target: [
                            //reportDir: 'allure-report',       // папка с отчетом
                           // reportFiles: 'index.html',        // стартовая страница
                          //  reportName: 'Allure отчет Report'  // отображаемое имя
                                 allowMissing: false,
                                                alwaysLinkToLastBuild: true,
                                                keepAll: true,
                                                reportDir: 'target/allure-report', // Directory where reports are located
                                                reportFiles: 'surefire-report.html', // The main HTML file
                                                reportName: "Surefire Test Report"
                        ]
                    }
                } */
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
     post {
            always {
                // Используйте этот шаг для автоматической публикации отчета Allure
                      allure([
                               // Список путей к директориям с результатами Allure.
                               // Можно указать несколько путей, если результаты разбросаны.
                               results: [[path: 'target/allure-results']],
                               // Имя конфигурации Allure Commandline, указанной в Global Tool Configuration
                               commandline: 'Allure_2.x',
                               // Политика создания отчета (например, ALWAYS, UNSTABLE, UNSUCCESSFUL)
                               reportBuildPolicy: 'ALWAYS'
                           ])
            }
        }

}