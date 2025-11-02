pipeline {
    agent any

    environment {
        // Название образа и тег
        IMAGE_NAME = 'your-dockerhub-username/your-app'
        IMAGE_TAG = 'latest'
        // Название репозитория на Docker Hub
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials-id'
    }

    stages {
        stage('Checkout') {
            steps {
                // Клонируем репозиторий
                checkout scm
                // Или, если настроите вручную:
                // sh 'git clone https://github.com/ваш-репозиторий.git'
            }
        }

        stage('Build') {
            steps {
                // Собираем проект с помощью Maven
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Строим Docker образ
                script {
                    // Собираем образ с актуальным Dockerfile в корне
                    docker.build("${IMAGE_NAME}:${IMAGE_TAG}")
                }
            }
        }

        stage('Test') {
            steps {
                // Запускаем тесты (если есть)
                sh 'mvn test'
            }
        }

        stage('Push Docker Image') {
            steps {
                // Отправляем образ на Docker Hub
                script {
                    docker.withRegistry('https://registry.hub.docker.com', "${DOCKERHUB_CREDENTIALS_ID}") {
                        docker.image("${IMAGE_NAME}:${IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
