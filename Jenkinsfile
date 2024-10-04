pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials'
        DOCKERHUB_REPO = 'amirdi/devopschain' // Update this as needed
        DOCKER_IMAGE_TAG = 'ver2' // Update this as needed
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull code from your Git repository
                git url: 'https://github.com/eetuam1/RollDice.git', branch: 'main'
            }
        }

        stage('Run Tests') {
            steps {
                // Run the tests first to generate data for Jacoco and JUnit
                script {
                    try {
                        // Run Maven tests (use bat for Windows)
                        bat 'mvn clean test' // For Windows agents
                        // sh 'mvn clean test' // Uncomment if on a Linux agent
                    } catch (Exception e) {
                        echo "Tests failed: ${e}"
                        throw e  // Stop the pipeline if tests fail
                    }
                }
            }
        }

        stage('Code Coverage') {
            steps {
                script {
                    try {
                        // Generate JaCoCo coverage report (use bat for Windows)
                        bat 'mvn jacoco:report'
                    } catch (Exception e) {
                        echo "Code coverage report generation failed: ${e}"
                        throw e  // Stop the pipeline if JaCoCo report generation fails
                    }
                }
            }
        }

        stage('Publish Test Results') {
            steps {
                // Publish JUnit test results
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                // Publish JaCoCo coverage report
                jacoco execPattern: 'target/jacoco.exec',
                       classPattern: 'target/classes',
                       sourcePattern: 'src/main/java',
                       inclusionPattern: '**/*.class'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${env.DOCKERHUB_REPO}:${env.DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', env.DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${env.DOCKERHUB_REPO}:${env.DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }

    post {
        always {
            // Cleanup workspace after the build is finished
            cleanWs()
        }
    }
}
