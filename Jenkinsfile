pipeline {
	agent {
		 docker {
            image 'emanagement'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
	}
	environment {
		mavenHome = tool 'jenkins-maven'
	}
	tools {
		jdk 'java-17'
	}
	stages {
		stage('Build'){
			steps {
				sh "mvn clean install"
			}
		}
		stage('Image') {
			steps {
				sh "docker build -t emanagement ."
				sh "docker ps"
			}
		}
		stage('Test'){
			steps{
				sh "mvn test"
			}
		}
		stage('Deploy') {
			steps {
			    echo "Run Passed"
			}
		}
	}
}
