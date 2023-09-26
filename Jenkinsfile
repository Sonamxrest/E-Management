pipeline {
	agent any
	environment {
		mavenHome = tool 'jenkins-maven'
	}
	tools {
		jdk 'java-17'
	}
	stages {
		stage('Build'){
			steps {
				sh "mvn install -Dmaven.test.skip=true"
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
